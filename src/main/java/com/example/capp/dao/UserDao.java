package com.example.capp.dao;

import com.example.capp.model.UserDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户dao层接口
 */
@Mapper
public interface UserDao {

    /**
     * 根据用户名密码获取用户信息
     * @param username  用户名
     * @param password  密码
     * @return
     */
    UserDomain getUserInfoByCond(@Param("username") String username, @Param("password") String password);

    /**
     * 通过用户ID获取用户信息
     * @param uid
     * @return
     */
    UserDomain getUserInfoById(Integer uid);

    /**
     * 更改用户信息
     * @param user
     * @return
     */
    int updateUserInfo(UserDomain user);

    /**
     * 添加账户信息
     * @param user
     * @return
     */
    int addUser(UserDomain user);

    /**
     * 获取所有的用户信息
     * @return
     */
    List<UserDomain> getUsers();

    /**
     * 根据id删除用户
     * @param uid
     */
    void deleteUserById(Integer uid);
}
