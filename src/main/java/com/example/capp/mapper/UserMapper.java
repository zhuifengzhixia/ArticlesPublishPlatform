package com.example.capp.mapper;

import com.example.capp.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    User selectByUsername(@Param("username") String username);

    /**
     * 根据用户名密码获取用户信息
     * @param username
     * @param pwd
     * @return
     */
    User getUserInfoByCond(@Param("username") String username, @Param("password") String pwd);
}
