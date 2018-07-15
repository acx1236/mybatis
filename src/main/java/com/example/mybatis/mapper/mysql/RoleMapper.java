package com.example.mybatis.mapper.mysql;

import com.example.mybatis.model.mysql.Role;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface RoleMapper {

    @InsertProvider(type = RoleSqlProvider.class, method = "insert")
    // 返回主键     keyProperty = "roleId", keyColumn = "role_id"
    @Options(useGeneratedKeys = true)
    int insert(Role role);

    @Select("select * from role where id = #{id}")
    Role selectByPrimaryKey(Integer id);

    @SelectProvider(type = RoleSqlProvider.class, method = "selectRolesByCondition")
    List<Role> selectRolesByCondition(Map<String, Object> params);
}