package com.lxq.model.service;

import com.lxq.model.dao.CurriculumDAO;
import com.lxq.model.object.Curriculum;
import com.lxq.model.object.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurriculumService {
    @Autowired
    private CurriculumDAO CurriculumDAO;
    @Autowired
    private UserService userService;

    //查询课程全部信息
    public List<Curriculum> getAllCurriculum(Curriculum Curriculum){
        List<Curriculum> allCurriculum = new ArrayList<>();
        try{
            List<Curriculum> allCurriculumSearch = CurriculumDAO.getAllCurriculum(Curriculum);
            //映射教师信息
            if(null!= allCurriculumSearch){
                Map<String,User> teacherMap = new HashMap<>();
                //取出所有"教师"用户
                User user = new User();
                user.setUserLv("1");
                List<User> teacherList = userService.getAllUser(user);

                for (User teacher:teacherList) {
                    teacherMap.put(teacher.getUserId(),teacher);
                }
                for (Curriculum curriculum:allCurriculumSearch) {
                    curriculum.setTeacherMessage(teacherMap.get(curriculum.getTeacherId()));
                    allCurriculum.add(curriculum);
                }

                //数据库操作过于频繁
//                for (Curriculum curriculum: allCurriculumSearch) {
//                    User user = new User();
//                    user.setUserId(curriculum.getTeacherId());
//                    User userSearch = userService.getUser(user);
//                    curriculum.setTeacherMessage(userSearch);
//                    allCurriculum.add(curriculum);
//                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return allCurriculum;
    }
    //查询课程信息
    public Curriculum getCurriculum(Curriculum Curriculum){
        Curriculum CurriculumSearch = null;
        try{
            CurriculumSearch = CurriculumDAO.getCurriculum(Curriculum);
        }catch (Exception e){
            e.printStackTrace();
        }
        return CurriculumSearch;
    }
    //通过主键查找课程，主要用于验证是否存在相同课程
    public Curriculum getCurriculumbyPrimary(Curriculum Curriculum) {
        Curriculum curriculumSearch = null;
        try {
            curriculumSearch = CurriculumDAO.getCurriculumbyPrimary(Curriculum);
        }catch (Exception e){
            e.printStackTrace();
        }
        return curriculumSearch;
    }
    //增加课程信息
    public String addCurriculum(Curriculum Curriculum){
        String isSuccess = "false";
        try{
            //将时间转换成Int
            String timeEnd = Curriculum.getTimeEnd();
            String timeStart = Curriculum.getTimeStart();
            Curriculum.setTimeEndNum(Integer.parseInt(timeEnd.replace("-","")));
            Curriculum.setTimeStartNum(Integer.parseInt(timeStart.replace("-","")));
            int resultNum = CurriculumDAO.addCurriculum(Curriculum);
            if(resultNum>0){
                isSuccess = "true";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return isSuccess;
    }
    //删除课程信息
    public String delCurriculum(Curriculum Curriculum){
        String isSuccess = "false";
        try{
            int resultNum  = CurriculumDAO.delCurriculum(Curriculum);
            if(resultNum>0){
                isSuccess = "true";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return isSuccess;
    }
    //修改课程信息
    public String updateCurriculum(Curriculum Curriculum){
        String isSuccess = "false";
        try{
            //将时间转换成Int
            String timeEnd = Curriculum.getTimeEnd();
            String timeStart = Curriculum.getTimeStart();
            Curriculum.setTimeEndNum(Integer.parseInt(timeEnd.replace("-","")));
            Curriculum.setTimeStartNum(Integer.parseInt(timeStart.replace("-","")));

            int resultNum = CurriculumDAO.updateCurriculum(Curriculum);
            if(resultNum>0){
                isSuccess = "true";
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return isSuccess;
    }

}
