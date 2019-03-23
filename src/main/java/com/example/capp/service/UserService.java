package com.example.capp.service;

import com.example.capp.dto.User;

public interface UserService {
    User checkUserLogin(User user);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);
}
