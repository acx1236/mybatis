package com.example.mybatis.mapper.mysql;

import com.example.mybatis.model.mysql.Role;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class RoleSqlProvider {

    public String selectRolesByCondition(Map<String, Object> map) {
        return new SQL() {
            {
                SELECT("*");
                FROM("role r");
                INNER_JOIN("user_role u_r on u_r.user_id = #{userId} and r.id = u_r.role_id");
                if (map.get("roleLevel") != null) {
                    WHERE("role_level = #{roleLevel}");
                }
            }
        }.toString();
    }

    public String insert(Role role) {
        return new SQL() {
            {
                INSERT_INTO("role");
                INTO_COLUMNS("description", "role_level");
                INTO_VALUES("#{description}", "#{roleLevel}");
                if (role.getName() == null) {
                    VALUES("name", "'默认值'");
                }
            }
        }.toString();
    }

}
