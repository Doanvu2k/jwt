package com.example.sample_BE.jwt;

import com.example.sample_BE.table.Users;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private static final long  EXPIRE_DURATION = 24 * 60 * 60 * 1000;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);
    @Value("${app.jwt.secret}")
    private String secretKey;

    public String generateAccessToken(Users user){
        return Jwts.builder()
                .setSubject(user.getId() + ","+ user.getEmail())
                .setIssuer("learn jwt")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512,secretKey)
                .compact();

    }
    public boolean validateToken (String token){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException ex){
            LOGGER.error("Token expired", ex);
        }catch (IllegalArgumentException ex){
            LOGGER.error("Token is null or has only white space", ex);
        }catch(MalformedJwtException ex){
            LOGGER.error("Token invalid ");
        }catch (UnsupportedJwtException ex){
            LOGGER.error("Not support JWT",ex);
        }catch (SignatureException ex){
            LOGGER.error("Signature invalid", ex);
        }
        return false;
    }

    public String getSubject(String token){
        return parseClaims(token).getSubject();
    }
    private Claims parseClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
