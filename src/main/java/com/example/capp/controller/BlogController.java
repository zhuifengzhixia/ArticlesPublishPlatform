package com.example.capp.controller;


import com.example.capp.dto.cond.ContentCond;
import com.example.capp.model.ContentDomain;
import com.example.capp.service.article.ContentService;
import com.example.capp.service.comment.CommentService;
import com.example.capp.service.meta.MetaService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 平台首页
 */
@Controller
public class BlogController {


    @Autowired
    private ContentService contentService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private MetaService metaService;


    @GetMapping(value = "/")
    public String index(
            HttpServletRequest request,
            @ApiParam(name = "page", value = "页数", required = false)
            @RequestParam(name = "page", required = false, defaultValue = "1")
                    int page,
            @ApiParam(name = "limit", value = "每页数量", required = false)
            @RequestParam(name = "limit", required = false, defaultValue = "5")
                    int limit
    ) {
        PageInfo<ContentDomain> articles = contentService.getArticles(new ContentCond(), page, limit);

        request.setAttribute("articles",articles);
        return "home/index";
    }
}
