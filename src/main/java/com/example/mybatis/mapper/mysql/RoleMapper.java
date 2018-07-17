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

    int batchInsert(List<Role> roleList);

    @InsertProvider(type = RoleSqlProvider.class, method = "annotateBatchInsert")
    @Options(useGeneratedKeys = true)
    int annotateBatchInsert(List<Role> roleList);

    @Select("select * from role where id = #{id}")
    Role selectByPrimaryKey(Integer id);

    @SelectProvider(type = RoleSqlProvider.class, method = "selectRolesByCondition")
    List<Role> selectRolesByCondition(Map<String, Object> params);

    @InsertProvider(type = RoleSqlProvider.class, method = "merge")
    @Options(useGeneratedKeys = true)
    int merge(Role role);

}