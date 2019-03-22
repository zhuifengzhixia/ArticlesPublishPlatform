package com.example.capp.controller;

import com.example.capp.dto.User;
import com.example.capp.service.UserService;
import com.example.capp.utils.APIResponse;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/check_user_login")
    public APIResponse<User> checkUserLogin(User user, HttpServletRequest request) {

        User resUser = userService.checkUserLogin(user);
        if(resUser != null){
            //校验通过时，在session里放入一个标识
            //后续通过判断session里是否存在该标识来判断用户是否登录
            request.getSession().setAttribute("username", user.getUsername());
            return APIResponse.success(resUser);
        }
        return APIResponse.withCode("用户名或者密码不存在");
    }
}
