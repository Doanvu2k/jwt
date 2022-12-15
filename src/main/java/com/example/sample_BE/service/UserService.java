package com.example.sample_BE.service;

import com.example.sample_BE.table.Users;

public interface UserService {
    Boolean checkEmail (String email);

    void saveUser (Users user);

    void save(Users user);
}
