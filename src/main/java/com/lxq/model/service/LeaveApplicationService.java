package com.lxq.model.service;

import com.lxq.Tools.TimeTools;
import com.lxq.model.dao.LeaveApplicationDAO;
import com.lxq.model.object.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveApplicationService {
    @Autowired
    private LeaveApplicationDAO LeaveApplicationDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private CurriculumService curriculumService;
    @Autowired
    private TimeTools timeTools;
    @Autowired
    private RecordService recordService;


    //查询考勤申请全部信息
    public List<LeaveApplication> getAllLeaveApplication(LeaveApplication leaveApplication){
        List<LeaveApplication> allLeaveApplication = new ArrayList<>();
        try{


            List<LeaveApplication>  allLeaveApplicationSearch = LeaveApplicationDAO.getAllLeaveApplication(leaveApplication);
            for (LeaveApplication LeaveApplication: allLeaveApplicationSearch) {

                User student = new User();
                student.setUserId(LeaveApplication.getUserId());
                User userStudent = userService.getUser(student);

                User teacher = new User();
                teacher.setUserId(LeaveApplication.getTeacherId());
                User userTeacher = userService.getUser(teacher);

                Curriculum curriculum = new Curriculum();
                curriculum.setId(Integer.parseInt(LeaveApplication.getCurriculumId()));
                Curriculum curriculumSearch = curriculumService.getCurriculum(curriculum);

                //学生信息
                LeaveApplication.setStudentMessage(userStudent);
                //老师信息
                LeaveApplication.setTeacherMessage(userTeacher);
                //课程信息
                LeaveApplication.setCurriculumMessage(curriculumSearch);

                allLeaveApplication.add(LeaveApplication);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return allLeaveApplication;
    }
    //查询考勤申请信息
    public LeaveApplication getLeaveApplication(LeaveApplication leaveApplication){
        LeaveApplication LeaveApplicationSearch = null;
        try{
            LeaveApplicationSearch = LeaveApplicationDAO.getLeaveApplication(leaveApplication);
        }catch (Exception e){
            e.printStackTrace();
        }
        return LeaveApplicationSearch;
    }

    //通过主键查找请假申请，主要用于验证是否存在相同请假申请
    public LeaveApplication getLeaveApplicationbyPrimary(LeaveApplication leaveApplication) {
        LeaveApplication LeaveApplicationSearch = null;
        try {
            LeaveApplicationSearch = LeaveApplicationDAO.getLeaveApplicationbyPrimary(leaveApplication);
        }catch (Exception e){
            e.printStackTrace();
        }
        return LeaveApplicationSearch;
    }

    //增加考勤申请信息
    public String addLeaveApplication(LeaveApplication leaveApplication){
        String isSuccess = "false";
        try{
            leaveApplication.setState("申请");
            int resultNum = LeaveApplicationDAO.addLeaveApplication(leaveApplication);
            if(resultNum>0){
                isSuccess = "true";
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return isSuccess;
    }
    //删除考勤申请信息
    public String delLeaveApplication(LeaveApplication leaveApplication){
        String isSuccess = "false";
        try{
            int resultNum  = LeaveApplicationDAO.delLeaveApplication(leaveApplication);
            if(resultNum>0){
                isSuccess = "true";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return isSuccess;
    }

    //修改考勤申请信息
    public String updateLeaveApplication(LeaveApplication leaveApplication){
        String isSuccess = "false";
        try{
            //请假同意后，调整考勤数据
            if("同意".equals(leaveApplication.getState())){
                //通过请假id查找请假信息
                LeaveApplication leaveApplicationParam = new LeaveApplication();
                leaveApplicationParam.setId(leaveApplication.getId());
                LeaveApplication leaveApplicationSearch = LeaveApplicationDAO.getLeaveApplication(leaveApplicationParam);

                //设置请假时间段内的考勤状态为请假
                List<String> timeArr = timeTools.getTimeArr(leaveApplicationSearch.getTimeStart(), Integer.parseInt(leaveApplicationSearch.getTimeLength()));
                for (String time: timeArr) {
                    Record record = new Record();
                    record.setUserId(leaveApplicationSearch.getUserId());
                    record.setTeacherId(leaveApplicationSearch.getTeacherId());
                    record.setCurriculumId(leaveApplicationSearch.getCurriculumId());
                    record.setTime(time);
                    Record recordSearch = recordService.getRecord(record);
                    recordSearch.setState("请假");
                    String s = recordService.updateRecord(recordSearch);

                }
            }

            int resultNum = LeaveApplicationDAO.updateLeaveApplication(leaveApplication);
            if (resultNum>0){
                isSuccess = "true";
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return isSuccess;
    }
}
