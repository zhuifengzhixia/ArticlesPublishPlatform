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
}
