package com.example.sample_BE;

import com.example.sample_BE.repository.UserRepository;
import com.example.sample_BE.table.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserTest {
    @Autowired
    UserRepository userRepository;
//    @Test
//    public void testCreateUser(){
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String rawPassword = "12345678";
//        String encoderPassword = passwordEncoder.encode(rawPassword);
//        Users newUser = new Users("doanvd@testdata.com", encoderPassword);
//        Users saveUser = userRepository.save(newUser);
//
//    }
}
