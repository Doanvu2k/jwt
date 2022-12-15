package com.example.sample_BE.service.impl;

import com.example.sample_BE.repository.UserRepository;
import com.example.sample_BE.service.UserService;
import com.example.sample_BE.table.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean checkEmail(String email){
        Optional<Users> IsExitUser = userRepository.findByEmail(email);
        if(IsExitUser.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public void saveUser(Users user) {

    }

    @Override
    public void save(Users user){
        userRepository.save(user);
    }
}
