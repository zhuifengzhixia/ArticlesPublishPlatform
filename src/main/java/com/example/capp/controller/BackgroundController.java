package com.example.capp.controller;

import com.example.capp.constant.LogActions;
import com.example.capp.constant.WebConst;
import com.example.capp.controller.admin.AuthController;
import com.example.capp.dto.StatisticsDto;
import com.example.capp.dto.cond.ContentCond;
import com.example.capp.exception.BusinessException;
import com.example.capp.model.CommentDomain;
import com.example.capp.model.ContentDomain;
import com.example.capp.model.LogDomain;
import com.example.capp.model.UserDomain;
import com.example.capp.service.article.ContentService;
import com.example.capp.service.log.LogService;
import com.example.capp.service.site.SiteService;
import com.example.capp.service.user.UserService;
import com.example.capp.utils.APIResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Controller
public class BackgroundController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BackgroundController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private ContentService contentService;

    @GetMapping(value = "/back/login")
    public String index(){
        return "background/login";
    }

    @PostMapping(value = "/back/admin/login")
    @ResponseBody
    public APIResponse toAdminLogin(HttpServletRequest request,
                               @RequestParam(name = "username", required = true) String username,
                               @RequestParam(name = "password", required = true) String password) {

        try {
            // 调用Service登录方法
            UserDomain userInfo = userService.login(username, password);
            if(userInfo.getGroupName().equals("admin")){
                //写入session
                HttpSession session = request.getSession();
                session.setAttribute("backAdmin", userInfo);
                // 写入日志
                logService.addLog(LogActions.LOGIN.getAction(), userInfo.getUsername()+"管理员", request.getRemoteAddr(), userInfo.getUid());
                // 返回登录成功信息
                return APIResponse.success();
            } else {
                return APIResponse.fail("账户身份不符");
            }
        } catch (Exception e){
                return APIResponse.fail(e.getMessage());
        }
    }

    /**
     * 进入首页
     * @param request
     * @return
     */
    @GetMapping(value = {"/back","/back/index"})
    public String index(HttpServletRequest request) {
        LOGGER.info("Enter admin index method");
        // 获取5条评论
        List<CommentDomain> comments = siteService.getComments(5);
        // 获取5篇文章
        List<ContentDomain> contents = siteService.getNewArticles(5);
        // 获取后台统计数
        StatisticsDto statistics = siteService.getStatistics();

        // 获取5篇日志
        PageInfo<LogDomain> logs = logService.getLogs(1,5);
        List<LogDomain> list = logs.getList();

        request.setAttribute("comments",comments);
        request.setAttribute("articles",contents);
        request.setAttribute("statistics",statistics);
        request.setAttribute("logs",list);
        LOGGER.info("Exit admin index method");
        return "background/index";
    }


    /**
     * 文章页
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/back/article_list")
    public String index(HttpServletRequest request,
                        @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                        @RequestParam(name = "limit", required = false, defaultValue = "15") int limit) {

        PageInfo<ContentDomain> articles = contentService.getArticlesByCond(new ContentCond(), page, limit);
        request.setAttribute("articles",articles);
        return "background/article_list";
    }


    /**
     * 删除文章
     * @param cid
     * @return
     */
    @PostMapping("/back/article/delete")
    @ResponseBody
    public APIResponse deleteArticle(@RequestParam(name = "cid", required = true) Integer cid) {
        // 删除文章
        contentService.deleteArticleById(cid);
        return APIResponse.success();
    }

    /**
     * 删除用户
     * @param uid
     * @return
     */
    @PostMapping("/back/user/delete")
    @ResponseBody
    public APIResponse deleteUser(@RequestParam(name = "uid", required = true) Integer uid) {
        // 删除用户
        userService.deleteUserById(uid);
        return APIResponse.success();
    }

    /**
     * 用户页
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/back/user_list")
    public String getUsers(HttpServletRequest request,
                           @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(name = "limit", required = false, defaultValue = "15") int limit) {

        PageInfo<UserDomain> users = userService.getUsers(page, limit);
        request.setAttribute("users",users);
        return "background/user_list";
    }


    @RequestMapping(value = "/back/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        // 移除session
        session.removeAttribute("backAdmin");
        try {
            // 跳转到登录页面
            response.sendRedirect("/back/login");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("注销失败",e);
        }
    }

}
