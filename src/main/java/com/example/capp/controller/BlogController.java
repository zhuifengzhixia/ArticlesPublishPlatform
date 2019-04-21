package com.example.capp.controller;


import com.example.capp.constant.LogActions;
import com.example.capp.constant.WebConst;
import com.example.capp.dto.cond.ContentCond;
import com.example.capp.exception.BusinessException;
import com.example.capp.model.ContentDomain;
import com.example.capp.model.UserDomain;
import com.example.capp.service.article.ContentService;
import com.example.capp.service.comment.CommentService;
import com.example.capp.service.meta.MetaService;
import com.example.capp.service.user.UserService;
import com.example.capp.utils.APIResponse;
import com.example.capp.utils.TaleUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @Autowired
    private UserService userService;

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

    @GetMapping(value = "/home/register")
    public String toRegister(){
        return "home/register";
    }

    @PostMapping(value = "/home/register")
    @ResponseBody
    public APIResponse toLogin(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam(name = "username", required = true) String username,
                               @RequestParam(name = "password", required = true) String password,
                               @RequestParam(name = "email", required = true) String email,
                               @RequestParam(name = "screenName", required = true) String screenName) {

        UserDomain userDomain = new UserDomain();
        if(username == null){
            return APIResponse.fail("账户名不能为空");
        }
        if(password == null){
            return APIResponse.fail("密码不能为空");
        }
        if(email == null){
            return APIResponse.fail("邮箱不能为空");
        }
        if(screenName == null){
            return APIResponse.fail("昵称不能为空");
        }
        //密码MD5加密
        String pwd = TaleUtils.MD5encode(username + password);

        userDomain.setUsername(username);
        userDomain.setPassword(pwd);
        userDomain.setEmail(email);
        userDomain.setScreenName(screenName);

        // 插入账户信息
        if(userService.insert(userDomain) != 1){
            return APIResponse.fail("插入失败");
        }
        // 返回登录成功信息
        return APIResponse.success();
    }
}
