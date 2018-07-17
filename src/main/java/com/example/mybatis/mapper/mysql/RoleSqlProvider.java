package com.example.mybatis.mapper.mysql;

import com.example.mybatis.model.mysql.Role;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
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

    public String merge(Role role) {
        return new SQL() {
            {
                INSERT_INTO("role");
                INTO_COLUMNS("id", "name", "description", "role_level");
                INTO_VALUES("#{id}", "#{name}", "#{description}", "#{roleLevel}");
            }
        }.toString() + "ON DUPLICATE KEY UPDATE description=#{description}";
    }

    public String annotateBatchInsert(Map map) {
        StringBuilder sql = new StringBuilder(new SQL() {
            {
                INSERT_INTO("role");
                INTO_COLUMNS("name", "description", "role_level");
            }
        }.toString()).append(" VALUES ");
        MessageFormat messageFormat = new MessageFormat("(" +
                "#'{'list[{0}].name }, " +
                "#'{'list[{0}].description }, " +
                "#'{'list[{0}].roleLevel }" +
                ")");
        List<Role> list = (List<Role>) map.get("list");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sql.append(messageFormat.format(new Object[]{i}));
            if (i < size - 1) {
                sql.append(",");
            }
        }
        return sql.toString();
    }

}
