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


    /**
     * 平台主页
     * @param request
     * @param page
     * @param limit
     * @return
     */
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

    /**
     * 搜索实现
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/home/search")
    public String search(
            HttpServletRequest request,
            @ApiParam(name = "page", value = "页数", required = false)
            @RequestParam(name = "page", required = false, defaultValue = "1")
                    int page,
            @ApiParam(name = "limit", value = "每页数量", required = false)
            @RequestParam(name = "limit", required = false, defaultValue = "5")
                    int limit,
            @RequestParam(name = "searchInput", required = true) String searchInput
    ) {
        ContentCond contentCond = new ContentCond();
        contentCond.setContent(searchInput);
        PageInfo<ContentDomain> articles = contentService.getArticles(contentCond, page, limit);

        request.setAttribute("articles",articles);
        return "home/index";
    }
}
