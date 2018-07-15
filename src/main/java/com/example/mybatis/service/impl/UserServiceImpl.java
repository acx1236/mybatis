package com.example.mybatis.service.impl;

import com.example.mybatis.mapper.mysql.RoleMapper;
import com.example.mybatis.mapper.mysql.UserMapper;
import com.example.mybatis.mapper.postgresql.WorkOrderManageMapper;
import com.example.mybatis.model.mysql.Role;
import com.example.mybatis.model.mysql.User;
import com.example.mybatis.model.postgresql.WorkOrderManage;
import com.example.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private WorkOrderManageMapper workOrderManageMapper;


    @Override
    public List<User> queryAllUser() {
        List<User> users = new ArrayList<>(1);
        users.add(userMapper.selectByPrimaryKey(1));
        return userMapper.selectAllUser();
    }

    @Override
    public void insertUser(User user) {
//        userMapper.insertUser();
    }

    @Override
    public List<Role> queryRolesByUserId(int userId) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("roleLevel", 2);
        params.put("userId", 1);
        return roleMapper.selectRolesByCondition(params);
    }

    @Override
    public List<WorkOrderManage> queryAllWorkOrderManage() {
        return workOrderManageMapper.queryAllWorkOrders();
    }

    @Override
    public String insertRole() {
        Role role = new Role();
        role.setRoleLevel(1);
        roleMapper.insert(role);
        return "success. id = " + role.getId();
    }
}
