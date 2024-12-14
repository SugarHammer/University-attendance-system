package com.lxq.model.dao;

import com.lxq.model.object.CourseApplication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface CourseApplicationDAO {

    //查询申请全部信息
    List<CourseApplication> getAllCourseApplication(CourseApplication CourseApplication);
    //查询申请信息
    CourseApplication getCourseApplication(CourseApplication CourseApplication);
    //增加申请信息
    int addCourseApplication(CourseApplication CourseApplication);
    //删除申请信息
    int delCourseApplication(CourseApplication CourseApplication);
    //修改申请信息
    int updateCourseApplication(CourseApplication CourseApplication);

    CourseApplication getCourseApplicationbyPrimary(CourseApplication CourseApplication);
}
