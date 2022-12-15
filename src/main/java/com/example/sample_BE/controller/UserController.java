package com.example.sample_BE.controller;


import com.example.sample_BE.dto.AuthRequest;
import com.example.sample_BE.dto.AuthResponse;
import com.example.sample_BE.dto.SignUpUserDTO;
import com.example.sample_BE.jwt.JwtUtil;
import com.example.sample_BE.service.UserService;
import com.example.sample_BE.table.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    public AuthenticationManager authenticationManager;
    @Autowired
    public JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
            Users user = (Users) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(),accessToken );

            return ResponseEntity.ok(response);
        }catch (BadCredentialsException ex){
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("email or password not correct!");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpUserDTO newUser){
        Boolean isAvailaleUser = userService.checkEmail(newUser.getEmail());
        if (!isAvailaleUser){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("email is already in use!");
        }
        Users user = new Users();
        user.setEmail(newUser.getEmail());
        user.setName(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userService.save(user);

        return ResponseEntity.ok("success!");
    }
}
