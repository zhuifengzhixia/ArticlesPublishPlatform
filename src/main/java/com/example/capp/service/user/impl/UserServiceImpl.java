
package com.example.capp.service.user.impl;

import com.example.capp.constant.ErrorConstant;
import com.example.capp.dao.CommentDao;
import com.example.capp.dao.ContentDao;
import com.example.capp.dao.RelationShipDao;
import com.example.capp.dao.UserDao;
import com.example.capp.dto.cond.ContentCond;
import com.example.capp.exception.BusinessException;
import com.example.capp.model.CommentDomain;
import com.example.capp.model.ContentDomain;
import com.example.capp.model.RelationShipDomain;
import com.example.capp.model.UserDomain;
import com.example.capp.service.user.UserService;
import com.example.capp.utils.TaleUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户相关Service接口实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ContentDao contentDao;

    @Autowired
    private RelationShipDao relationShipDao;

    @Autowired
    private CommentDao commentDao;


    @Override
    public UserDomain login(String username, String password) {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password))
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_IS_EMPTY);

        String pwd = TaleUtils.MD5encode(username + password);
        UserDomain user = userDao.getUserInfoByCond(username,pwd);
        if (null == user)
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_ERROR);
        return user;
    }

    @Override
    public UserDomain getUserInfoById(Integer uid) {
        return userDao.getUserInfoById(uid);
    }

    // 开启事务
    @Transactional
    @Override
    public int updateUserInfo(UserDomain user) {
        if (null == user.getUid())
            throw BusinessException.withErrorCode("用户编号不能为空");
        return userDao.updateUserInfo(user);
    }

    @Transactional
    @Override
    public int insert(UserDomain userDomain) {
        return userDao.addUser(userDomain);
    }

    @Transactional
    @Override
    public void deleteUserById(Integer uid) {
        //获取改用户的文章列表
        ContentCond contentCond = new ContentCond();
        contentCond.setAuthorId(uid);
        List<ContentDomain> contents = contentDao.getArticleByCond(contentCond);

        if(contents != null){
            for (int i=0; i<contents.size(); i++){
                Integer cid = contents.get(i).getCid();
                // 删除文章
                contentDao.deleteArticleById(cid);

                // 同时要删除该 文章下的所有评论
                List<CommentDomain> comments = commentDao.getCommentByCId(cid);
                if (null != comments && comments.size() > 0) {
                    comments.forEach(comment -> {
                        commentDao.deleteComment(comment.getCoid());
                    });
                }

                // 删除标签和分类关联
                List<RelationShipDomain> relationShips = relationShipDao.getRelationShipByCid(cid);
                if (null != relationShips && relationShips.size() > 0) {
                    relationShipDao.deleteRelationShipByCid(cid);
                }
            }
        }

        //删除用户
        userDao.deleteUserById(uid);

    }

    @Override
    public PageInfo<UserDomain> getUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserDomain> users = userDao.getUsers();
        PageInfo<UserDomain> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }
}
