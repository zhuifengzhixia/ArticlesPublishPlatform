package com.example.capp.controller;


import com.example.capp.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 文章控制器
 * @author WM
 * @date 2019/3/21
 */
@RestController
@RequestMapping(value = "/content")
public class ContentController {

    @Autowired
    private ContentService contentService;
}
