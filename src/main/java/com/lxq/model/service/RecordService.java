package com.lxq.model.service;

import com.lxq.Tools.TimeTools;
import com.lxq.model.dao.RecordDAO;
import com.lxq.model.object.*;
import com.lxq.model.object.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {
    @Autowired
    private RecordDAO RecordDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private CurriculumService curriculumService;
    @Autowired
    private TimeTools timeTools;
    @Autowired
    private RecordService recordService;


    //查询全部考勤信息
    public List<Record> getAllRecord(Record Record){
        List<Record> allRecord = new ArrayList<>();
        try{
            List<Record>  allRecordSearch = RecordDAO.getAllRecord(Record);
            for (Record recordSearch: allRecordSearch) {
                //找学生
                User student = new User();
                student.setUserId(recordSearch.getUserId());
                User userStudent = userService.getUser(student);
                //找老师
                User teacher = new User();
                teacher.setUserId(recordSearch.getTeacherId());
                User userTeacher = userService.getUser(teacher);
                //找课程
                Curriculum curriculum = new Curriculum();
                curriculum.setId(Integer.parseInt(recordSearch.getCurriculumId()));
                Curriculum curriculumSearch = curriculumService.getCurriculum(curriculum);

                //学生信息
                recordSearch.setStudentMessage(userStudent);
                //老师信息
                recordSearch.setTeacherMessage(userTeacher);
                //课程信息
                recordSearch.setCurriculumMessage(curriculumSearch);
                allRecord.add(recordSearch);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return allRecord;
    }
    //查询考勤信息
    public Record getRecord(Record Record){
        Record RecordSearch = null;
        try{
            RecordSearch = RecordDAO.getRecord(Record);

            User student = new User();
            student.setUserId(RecordSearch.getUserId());
            User userStudent = userService.getUser(student);

            User teacher = new User();
            teacher.setUserId(RecordSearch.getTeacherId());
            User userTeacher = userService.getUser(teacher);

            Curriculum curriculum = new Curriculum();
            curriculum.setId(Integer.parseInt(RecordSearch.getCurriculumId()));
            Curriculum curriculumSearch = curriculumService.getCurriculum(curriculum);

            //学生信息
            RecordSearch.setStudentMessage(userStudent);
            //老师信息
            RecordSearch.setTeacherMessage(userTeacher);
            //课程信息
            RecordSearch.setCurriculumMessage(curriculumSearch);
        }catch (Exception e){
            e.printStackTrace();
        }
        return RecordSearch;
    }

    //通过主键查找考勤，主要用于验证是否存在相同考勤
    public Record getRecordbyPrimary(Record Record) {
        Record RecordSearch = null;
        try {
            RecordSearch = RecordDAO.getRecordbyPrimary(Record);
        }catch (Exception e){
            e.printStackTrace();
        }
        return RecordSearch;
    }

    //增加考勤信息
    public String addRecord(Record Record){
        String isSuccess = "false";
        try{
            int resultNum = RecordDAO.addRecord(Record);
            if(resultNum>0){
                isSuccess = "true";
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return isSuccess;
    }
    //删除考勤信息
    public String delRecord(Record Record){
        String isSuccess = "false";
        try{
            int resultNum  = RecordDAO.delRecord(Record);
            if(resultNum>0){
                isSuccess = "true";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return isSuccess;
    }

    //修改考勤信息
    public String updateRecord(Record Record){
        String isSuccess = "false";
        try{
            int resultNum = RecordDAO.updateRecord(Record);
            if(resultNum>0){
                isSuccess = "true";
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return isSuccess;
    }
}
