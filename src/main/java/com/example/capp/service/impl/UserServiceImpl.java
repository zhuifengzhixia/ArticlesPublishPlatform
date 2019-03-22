package com.example.capp.service.impl;

import com.example.capp.dto.User;
import com.example.capp.exception.CommonException;
import com.example.capp.mapper.UserMapper;
import com.example.capp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUserLogin(User user) {
        User returnUser = userMapper.selectByUsername(user.getUsername());
        if (returnUser != null) {
            if(!user.getPassword().equals(returnUser.getPassword())){
                throw new CommonException("密码错误");
            }
        } else {
            throw new CommonException("用户名不存在");
        }

        return returnUser;
    }
}
