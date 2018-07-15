package com.example.mybatis.mapper.mysql;

import com.example.mybatis.model.mysql.User;

import java.util.List;

public interface UserMapper {

    List<User> selectAllUser();

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}