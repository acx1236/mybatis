package com.example.mybatis.service;

import com.example.mybatis.model.mysql.Role;
import com.example.mybatis.model.mysql.User;
import com.example.mybatis.model.postgresql.WorkOrderManage;

import java.util.List;

public interface IUserService {

    List<User> queryAllUser();

    void insertUser(User user);

    List<Role> queryRolesByUserId(int userId);

    List<WorkOrderManage> queryAllWorkOrderManage();

    String insertRole();

    String batchInsertRole();

    String mergeRole();

}
