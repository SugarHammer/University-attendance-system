package com.lxq.model.dao;

import com.lxq.model.object.Curriculum;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface CurriculumDAO {

    //查询课程全部信息
    List<Curriculum> getAllCurriculum(Curriculum Curriculum);
    //查询课程信息
    Curriculum getCurriculum(Curriculum Curriculum);
    //通过主键查找课程
    Curriculum getCurriculumbyPrimary(Curriculum Curriculum);
    //增加课程信息
    int addCurriculum(Curriculum Curriculum);
    //删除课程信息
    int delCurriculum(Curriculum Curriculum);
    //修改课程信息
    int updateCurriculum(Curriculum Curriculum);

}
