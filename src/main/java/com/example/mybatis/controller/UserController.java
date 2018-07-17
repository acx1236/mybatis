package com.example.mybatis.controller;

import com.example.mybatis.model.mysql.Role;
import com.example.mybatis.model.mysql.User;
import com.example.mybatis.model.postgresql.WorkOrderManage;
import com.example.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("queryAllUser")
    public List<User> queryAllUser() {
        return userService.queryAllUser();
    }

    @RequestMapping("queryAllWorkOrderManage")
    public List<WorkOrderManage> queryAllWorkOrderManage() {
        return userService.queryAllWorkOrderManage();
    }

    @RequestMapping("insertUser")
    public List<User> insertUser(@ModelAttribute("user1") User user) {
        userService.insertUser(user);
        return userService.queryAllUser();
    }

    @RequestMapping("queryRolesByUserId")
    public List<Role> queryRolesByUserId(int userId) {
        return userService.queryRolesByUserId(userId);
    }

    @RequestMapping("insertRole")
    public String insertRole() {
        return userService.insertRole();
    }

    @RequestMapping("batchInsertRole")
    public String batchInsertRole() {
        return userService.batchInsertRole();
    }

    @RequestMapping("mergeRole")
    public String mergeRole() {
        return userService.mergeRole();
    }

}
