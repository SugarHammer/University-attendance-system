package com.lxq.model.service;

import com.lxq.Tools.TimeTools;
import com.lxq.model.dao.CourseApplicationDAO;
import com.lxq.model.object.CourseApplication;
import com.lxq.model.object.Curriculum;
import com.lxq.model.object.Record;
import com.lxq.model.object.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseApplicationService {
    @Autowired
    private CourseApplicationDAO CourseApplicationDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private CurriculumService curriculumService;
    @Autowired
    private TimeTools timeTools;
    @Autowired
    private RecordService recordService;


    //查询课程申请全部信息
    public List<CourseApplication> getAllCourseApplication(CourseApplication CourseApplication){
        List<CourseApplication> allCourseApplication = new ArrayList<>();
        try{
            List<CourseApplication>  allCourseApplicationSearch = CourseApplicationDAO.getAllCourseApplication(CourseApplication);
            for (CourseApplication courseApplication: allCourseApplicationSearch) {
                //找学生信息
                User student = new User();
                student.setUserId(courseApplication.getUserId());
                User userStudent = userService.getUser(student);
                //找老师信息
                User teacher = new User();
                teacher.setUserId(courseApplication.getTeacherId());
                User userTeacher = userService.getUser(teacher);
                //找课程信息
                Curriculum curriculum = new Curriculum();
                curriculum.setId(Integer.parseInt(courseApplication.getCurriculumId()));
                Curriculum curriculumSearch = curriculumService.getCurriculum(curriculum);

                //学生信息
                courseApplication.setStudentMessage(userStudent);
                //老师信息
                courseApplication.setTeacherMessage(userTeacher);
                //课程信息
                courseApplication.setCurriculumMessage(curriculumSearch);

                allCourseApplication.add(courseApplication);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return allCourseApplication;
    }

    //查询课程申请全部信息
    public List<CourseApplication> getAllCourseApplicationWithoutDelete(CourseApplication CourseApplication){
        List<CourseApplication> allCourseApplication = new ArrayList<>();
        try{
            List<CourseApplication>  allCourseApplicationSearch = CourseApplicationDAO.getAllCourseApplication(CourseApplication);
            for (CourseApplication courseApplication: allCourseApplicationSearch) {
                //找学生信息
                User student = new User();
                student.setUserId(courseApplication.getUserId());
                User userStudent = userService.getUser(student);
                //找老师信息
                User teacher = new User();
                teacher.setUserId(courseApplication.getTeacherId());
                User userTeacher = userService.getUser(teacher);
                //找课程信息
                Curriculum curriculum = new Curriculum();
                curriculum.setId(Integer.parseInt(courseApplication.getCurriculumId()));
                Curriculum curriculumSearch = curriculumService.getCurriculum(curriculum);

                //学生信息
                courseApplication.setStudentMessage(userStudent);
                //老师信息
                courseApplication.setTeacherMessage(userTeacher);
                //课程信息
                courseApplication.setCurriculumMessage(curriculumSearch);
                //如果课程没有被删除
                if (curriculumSearch != null){
                    allCourseApplication.add(courseApplication);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return allCourseApplication;
    }

    //查询课程申请信息
    public CourseApplication getCourseApplication(CourseApplication CourseApplication){
        CourseApplication CourseApplicationSearch = null;
        try{
            CourseApplicationSearch = CourseApplicationDAO.getCourseApplication(CourseApplication);

            User student = new User();
            student.setUserId(CourseApplicationSearch.getUserId());
            User userStudent = userService.getUser(student);

            User teacher = new User();
            teacher.setUserId(CourseApplicationSearch.getTeacherId());
            User userTeacher = userService.getUser(teacher);

            Curriculum curriculum = new Curriculum();
            curriculum.setId(Integer.parseInt(CourseApplicationSearch.getCurriculumId()));
            Curriculum curriculumSearch = curriculumService.getCurriculum(curriculum);

            //学生信息
            CourseApplicationSearch.setStudentMessage(userStudent);
            //老师信息
            CourseApplicationSearch.setTeacherMessage(userTeacher);
            //课程信息
            CourseApplicationSearch.setCurriculumMessage(curriculumSearch);
        }catch (Exception e){
            e.printStackTrace();
        }
        return CourseApplicationSearch;
    }

    //通过主键查找课程申请，主要用于验证是否存在相同课程申请
    public CourseApplication getCourseApplicationbyPrimary(CourseApplication CourseApplication) {
        CourseApplication CourseApplicationSearch = null;
        try {
            CourseApplicationSearch = CourseApplicationDAO.getCourseApplicationbyPrimary(CourseApplication);
        }catch (Exception e){
            e.printStackTrace();
        }
        return CourseApplicationSearch;
    }

    //增加课程申请信息
    public String addCourseApplication(CourseApplication CourseApplication){
        String isSuccess = "false";
        try{
            int resultNum = CourseApplicationDAO.addCourseApplication(CourseApplication);
            if(resultNum>0){
                isSuccess = "true";
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return isSuccess;
    }
    //删除课程申请信息
    public String delCourseApplication(CourseApplication CourseApplication){
        String isSuccess = "false";
        try{
            int resultNum  = CourseApplicationDAO.delCourseApplication(CourseApplication);
            if(resultNum>0){
                isSuccess = "true";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return isSuccess;
    }

    //修改课程申请信息
    public String updateCourseApplication(CourseApplication CourseApplication){
        String isSuccess = "false";
        try{

            if("同意".equals(CourseApplication.getState())){
                //通过课程申请id查找课程申请信息
                CourseApplication courseApplicationparam = new CourseApplication();
                courseApplicationparam.setId(CourseApplication.getId());
                CourseApplication courseApplicationSearch = CourseApplicationDAO.getCourseApplication(courseApplicationparam);

                //通过课程id查找课程信息
                Curriculum curriculum = new Curriculum();
                curriculum.setId(Integer.parseInt(courseApplicationSearch.getCurriculumId()));
                Curriculum curriculumSearch = curriculumService.getCurriculum(curriculum);

                //添加考勤数据
                List<String> timeArr = timeTools.getTimeArr(curriculumSearch.getTimeStart(), curriculumSearch.getTimeLength());
                for (String time: timeArr) {
                    Record record = new Record();
                    record.setUserId(courseApplicationSearch.getUserId());
                    record.setTeacherId(courseApplicationSearch.getTeacherId());
                    record.setCurriculumId(courseApplicationSearch.getCurriculumId());
                    record.setState("待打卡");
                    record.setTime(time);
                    record.setTimeNum(Integer.parseInt(time.replaceAll("-","")));
                    recordService.addRecord(record);
                }

                //更新课程人数
                curriculumSearch.setJoinUserNum( curriculumSearch.getJoinUserNum()+1);
                curriculumService.updateCurriculum(curriculumSearch);
            }

            int resultNum = CourseApplicationDAO.updateCourseApplication(CourseApplication);
            if (resultNum>0){
                isSuccess = "true";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return isSuccess;
    }
}
