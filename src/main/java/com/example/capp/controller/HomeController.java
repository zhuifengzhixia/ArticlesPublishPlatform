package com.example.capp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * 前台主页控制器
 * @author WM
 * @date 2019/3/21
 */
@Controller
public class HomeController {

    @GetMapping(value = "/login")
    public String login(){
        return "/front/login";
    }
}
