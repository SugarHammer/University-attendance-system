package com.lxq.model.dao;

import com.lxq.model.object.LeaveApplication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface LeaveApplicationDAO {

    //查询请假全部信息
    List<LeaveApplication> getAllLeaveApplication(LeaveApplication LeaveApplication);
    //查询请假信息
    LeaveApplication getLeaveApplication(LeaveApplication LeaveApplication);
    //增加请假信息
    int addLeaveApplication(LeaveApplication LeaveApplication);
    //删除请假信息
    int delLeaveApplication(LeaveApplication LeaveApplication);
    //修改请假信息
    int updateLeaveApplication(LeaveApplication LeaveApplication);

    LeaveApplication getLeaveApplicationbyPrimary(LeaveApplication LeaveApplication);
}
