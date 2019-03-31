package com.example.capp.controller.admin;


import com.example.capp.constant.Types;
import com.example.capp.constant.WebConst;
import com.example.capp.controller.BaseController;
import com.example.capp.dto.MetaDto;
import com.example.capp.exception.BusinessException;
import com.example.capp.model.UserDomain;
import com.example.capp.service.meta.MetaService;
import com.example.capp.utils.APIResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api("分类管理")
@Controller
@RequestMapping("/admin/category")
public class CategoryController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private MetaService metaService;


    @ApiOperation("进入分类和标签页")
    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        List<MetaDto> categories = metaService.getMetaList(Types.CATEGORY.getType(),null,WebConst.MAX_POSTS);
        List<MetaDto> tags = metaService.getMetaList(Types.TAG.getType(), null, WebConst.MAX_POSTS);
        request.setAttribute("categories",categories);
        request.setAttribute("tags",tags);
        return "admin/category";
    }

    @ApiOperation("进入分类和标签页")
    @GetMapping(value = "/personal")
    public String index2(HttpServletRequest request) {
        //获取当前登录用户信息
        UserDomain userinfo = (UserDomain)request.getSession().getAttribute(WebConst.LOGIN_SESSION_KEY);
        Integer authorId = userinfo.getUid();
        List<MetaDto> categories = metaService.getMetaListByAuthorId(Types.CATEGORY.getType(),null,WebConst.MAX_POSTS, authorId);
        List<MetaDto> tags = metaService.getMetaListByAuthorId(Types.TAG.getType(), null, WebConst.MAX_POSTS, authorId);
        request.setAttribute("categories",categories);
        request.setAttribute("tags",tags);
        return "admin/category";
    }


    @ApiOperation("保存分类")
    @PostMapping(value = "/save")
    @ResponseBody
    public APIResponse addCategory(
            @ApiParam(name = "cname", value = "分类名", required = true)
            @RequestParam(name = "cname", required = true)
            String cname,
            @ApiParam(name = "mid", value = "meta编号", required = false)
            @RequestParam(name = "mid", required = false)
            Integer mid
    ) {
        try {
            metaService.saveMeta(Types.CATEGORY.getType(),cname,mid);

        } catch (Exception e) {
            e.printStackTrace();
            String msg = "分类保存失败";
            if (e instanceof BusinessException) {
                BusinessException ex = (BusinessException) e;
                msg = ex.getErrorCode();
            }
            LOGGER.error(msg, e);
            return APIResponse.fail(msg);
        }
        return APIResponse.success();
    }

    @ApiOperation("删除分类")
    @PostMapping(value = "/delete")
    @ResponseBody
    public APIResponse deleteCategory(
            @ApiParam(name = "mid", value = "meta编号", required = true)
            @RequestParam(name = "mid", required = true)
            Integer mid
    ) {
        try {
            metaService.deleteMetaById(mid);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return APIResponse.fail(e.getMessage());
        }
        return APIResponse.success();

    }

}
