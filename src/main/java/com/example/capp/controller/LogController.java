package com.example.capp.controller;

import com.example.capp.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志控制器
 * @author WM
 * @date 2019/3/21
 */
@RestController
@RequestMapping(value = "/log")
public class LogController {

    @Autowired
    private LogService logService;
}
