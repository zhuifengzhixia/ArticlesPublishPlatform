package com.example.capp.controller;

import com.example.capp.constant.LogActions;
import com.example.capp.constant.WebConst;
import com.example.capp.dto.User;
import com.example.capp.exception.CommonException;
import com.example.capp.service.LogService;
import com.example.capp.service.UserService;
import com.example.capp.utils.APIResponse;
import com.example.capp.utils.TaleUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 登录相关接口
 */
@RestController
@RequestMapping(value = "/auth")
public class LoginController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;


    @PostMapping(value = "/login")
    public APIResponse<User> login(javax.servlet.http.HttpServletRequest request,
                                            HttpServletResponse response,
                                            @RequestParam(name = "username", required = true) String username,
                                            @RequestParam(name = "password", required = true) String password,
                                            @RequestParam(name = "remember_me", required = false) String remember_me) {
        Integer error_count = cache.get("login_error_count");
        try {
            // 调用Service登录方法
            User userInfo = userService.login(username, password);
            // 设置用户信息session
            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, userInfo);
            // 判断是否勾选记住我
            if (StringUtils.isNotBlank(remember_me)) {
                TaleUtils.setCookie(response, userInfo.getId().intValue());
            }
            // 写入日志
            logService.addLog(LogActions.LOGIN_FRONT.getAction(), userInfo.getUsername()+"用户", request.getRemoteAddr(), userInfo.getId());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            error_count = null == error_count ? 1 : error_count + 1;
            if (error_count > 3) {
                return APIResponse.fail("您输入密码已经错误超过3次，请10分钟后尝试");
            }
            System.out.println(error_count);
            // 设置缓存为10分钟
            cache.set("login_error_count", error_count, 10 * 60);
            String msg = "登录失败";
            if (e instanceof CommonException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg,e);
            }
            return APIResponse.fail(msg);
        }
        // 返回登录成功信息
        return APIResponse.success();
    }
}
