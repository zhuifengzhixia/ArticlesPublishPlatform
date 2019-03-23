package com.example.capp.service.impl;

import com.example.capp.constant.ErrorConstant;
import com.example.capp.dto.User;
import com.example.capp.exception.CommonException;
import com.example.capp.mapper.UserMapper;
import com.example.capp.service.UserService;
import com.example.capp.utils.TaleUtils;
import org.apache.commons.lang3.StringUtils;
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


    @Override
    public User login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            throw CommonException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_IS_EMPTY);
        }

        String pwd = TaleUtils.MD5encode(username + password);
        User user = userMapper.getUserInfoByCond(username,pwd);
        if (null == user) {
            throw CommonException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_ERROR);
        }
        return user;
    }
}
