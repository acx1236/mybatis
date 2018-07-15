package com.example.mybatis.mapper.postgresql;

import com.example.mybatis.model.postgresql.WorkOrderManage;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOrderManageMapper {

    @Select("select * from workordermanage")
    List<WorkOrderManage> queryAllWorkOrders();

}
