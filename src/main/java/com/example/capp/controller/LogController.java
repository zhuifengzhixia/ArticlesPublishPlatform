package com.example.capp.controller;

import com.example.capp.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/log")
public class LogController {

    @Autowired
    private LogService logService;
}
