package com.lxq.controller;

import com.lxq.Tools.PageTools;
import com.lxq.Tools.UserTools;
import com.lxq.model.object.*;
import com.lxq.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private UserTools userTools;
    @Autowired
    private PageTools pageTools;
    @Autowired
    private CurriculumService curriculumService;
    @Autowired
    private CourseApplicationService courseApplicationService;
    @Autowired
    private UserService userService;
    @Autowired
    private LeaveApplicationService leaveApplicationService;
    @Autowired
    private RecordService recordService;



    //课程
    @RequestMapping("/systemIndex")
    public ModelAndView systemIndex(HttpServletRequest request, Curriculum curriculum , String userAccount , String pageNum) {

        User userMessageForSeesion = userTools.getUserMessageForSeesion(request);
        ModelAndView mv = new ModelAndView("systemIndex");
        List<Curriculum> curriculumFilter = new ArrayList<>();


        //教师访问主页时，展示的是自己的课程信息
        if ("1".equals(userMessageForSeesion.getUserLv())) {
            curriculum.setTeacherId(userMessageForSeesion.getUserId());
            List<Curriculum> allCurriculum = curriculumService.getAllCurriculum(curriculum);

            curriculumFilter = allCurriculum;
        }

        //学生主页时，展示的是所有课程信息
        if ("2".equals(userMessageForSeesion.getUserLv())) {
            List<Curriculum> allCurriculum = curriculumService.getAllCurriculum(curriculum);
            if (userAccount != null){
                for (Curriculum curriculm:allCurriculum) {
                    if (curriculm.getTeacherMessage().getUserAccount().toLowerCase().contains(userAccount.toLowerCase())){
                        curriculumFilter.add(curriculm);
                    }
                }
            }else{
                curriculumFilter = allCurriculum;
            }
        }


        //分页
        if (pageNum == null){
            pageNum = "1";
        }
        //有数据才分页
        if (curriculumFilter.size() > 0){
            curriculumFilter = pageTools.PageHelper(curriculumFilter,pageNum,mv);
        }else{
            Page page = new Page(10,-1,0,-1,-1);
            mv.addObject("pageInfo",page);
        }

        mv.addObject("curriculumList", curriculumFilter);
        mv.addObject("active","systemIndex");

        return mv;
    }

    //课程申请
    @RequestMapping("/courseApplicationIndex")
    public ModelAndView courseApplicationIndex(HttpServletRequest request, CourseApplication courseApplication, String curriculumName , String place , String userAccount , String pageNum) {
        User userMessageForSeesion = userTools.getUserMessageForSeesion(request);
        ModelAndView mv = new ModelAndView("/courseApplication");
        List<CourseApplication> allCourseApplication = new ArrayList<>();
        //教师访问主页时，展示的是他人申请的信息
        if ("1".equals(userMessageForSeesion.getUserLv())) {
            courseApplication.setTeacherId(userMessageForSeesion.getUserId());

        }

        //学生主页时，展示的是自己的课程申请
        if ("2".equals(userMessageForSeesion.getUserLv())) {
            courseApplication.setUserId(userMessageForSeesion.getUserId());
        }

        allCourseApplication = courseApplicationService.getAllCourseApplication(courseApplication);
        List<CourseApplication> allCourseApplicationReturn = new ArrayList<>();
        if (null != userAccount) {
            //按用户账号筛选
            if("1".equals(userMessageForSeesion.getUserLv())){
                //老师按学生查找
                for (CourseApplication courseApplicationSearch: allCourseApplication) {
                    if(courseApplicationSearch.getStudentMessage().getUserAccount().toLowerCase().contains(userAccount.toLowerCase())){

                        allCourseApplicationReturn.add(courseApplicationSearch);
                    }
                }
            }
            if ("2".equals(userMessageForSeesion.getUserLv())){
                //学生按老师查找
                for (CourseApplication courseApplicationSearch: allCourseApplication) {
                    if(courseApplicationSearch.getTeacherMessage().getUserAccount().toLowerCase().contains(userAccount.toLowerCase())){

                        allCourseApplicationReturn.add(courseApplicationSearch);
                    }
                }
            }
        }else if (null != curriculumName){
            //按课程名筛选
            for (CourseApplication courseApplicationSearch:allCourseApplication) {

                if(courseApplicationSearch.getCurriculumMessage() != null && courseApplicationSearch.getCurriculumMessage().getCurriculumName().toLowerCase().contains(curriculumName.toLowerCase())){
                    allCourseApplicationReturn.add(courseApplicationSearch);
                }
            }
        }else if (null != place){
            //按授课地点筛选
            for (CourseApplication courseApplicationSearch:allCourseApplication) {

                if(courseApplicationSearch.getCurriculumMessage() != null && courseApplicationSearch.getCurriculumMessage().getPlace().toLowerCase().contains(place.toLowerCase())){
                    allCourseApplicationReturn.add(courseApplicationSearch);
                }
            }
        }else{
            allCourseApplicationReturn = allCourseApplication;
        }

        //分页
        if (pageNum == null){
            pageNum = "1";
        }
        //有东西才分页
        if (allCourseApplicationReturn.size() > 0){
            allCourseApplicationReturn = pageTools.PageHelper(allCourseApplicationReturn,pageNum,mv);
        }else{
            Page page = new Page(10,-1,0,-1,-1);
            mv.addObject("pageInfo",page);
        }

        mv.addObject("active","courseApplicationIndex");
        mv.addObject("courseApplicationList", allCourseApplicationReturn);
        return mv;
    }


    //请假申请
    @RequestMapping("/leaveApplicationIndex")
    public ModelAndView leaveApplicationIndex(HttpServletRequest request, LeaveApplication leaveApplication, String curriculumName , String userAccount , String pageNum) {
        User userMessageForSeesion = userTools.getUserMessageForSeesion(request);
        ModelAndView mv = new ModelAndView("/leaveApplication");

        //教师访问主页时，展示的是他人申请的信息
        if ("1".equals(userMessageForSeesion.getUserLv())) {
            leaveApplication.setTeacherId(userMessageForSeesion.getUserId());
        }

        //学生展示的是自己的请假申请
        if ("2".equals(userMessageForSeesion.getUserLv())) {
            leaveApplication.setUserId(userMessageForSeesion.getUserId());
        }

        List<LeaveApplication> allLeaveApplication = leaveApplicationService.getAllLeaveApplication(leaveApplication);
        List<LeaveApplication> allLeaveApplicationReturn = new ArrayList<>();


        if (null != userAccount) {
            //按用户账号筛选
            if("1".equals(userMessageForSeesion.getUserLv())){
                //老师按学生查找
                for (LeaveApplication leaveApplicationSearch: allLeaveApplication) {
                    if(leaveApplicationSearch.getStudentMessage().getUserAccount().toLowerCase().contains(userAccount.toLowerCase())){

                        allLeaveApplicationReturn.add(leaveApplicationSearch);
                    }
                }
            }
            if ("2".equals(userMessageForSeesion.getUserLv())){
                //学生按老师查找
                for (LeaveApplication leaveApplicationSearch: allLeaveApplication) {
                    if(leaveApplicationSearch.getTeacherMessage().getUserAccount().toLowerCase().contains(userAccount.toLowerCase())){

                        allLeaveApplicationReturn.add(leaveApplicationSearch);
                    }
                }
            }
        }else if (null != curriculumName){
            //按课程名筛选
            for (LeaveApplication leaveApplicationSearch:allLeaveApplication) {

                if(leaveApplicationSearch.getCurriculumMessage() != null && leaveApplicationSearch.getCurriculumMessage().getCurriculumName().toLowerCase().contains(curriculumName.toLowerCase())){
                    allLeaveApplicationReturn.add(leaveApplicationSearch);
                }
            }
        }else{
            allLeaveApplicationReturn = allLeaveApplication;
        }


        //分页
        if (pageNum == null){
            pageNum = "1";
        }
        //有东西才分页
        if (allLeaveApplicationReturn.size() > 0){
            allLeaveApplicationReturn = pageTools.PageHelper(allLeaveApplicationReturn,pageNum,mv);
        }else{
            Page page = new Page(10,-1,0,-1,-1);
            mv.addObject("pageInfo",page);
        }


        mv.addObject("active","leaveApplicationIndex");
        mv.addObject("leaveApplicationList", allLeaveApplicationReturn);
        return mv;
    }


    //用户主页
    @RequestMapping("/userIndex")
    public ModelAndView userIndex(HttpServletRequest request , User Search , String pageNum) {
        User user = new User();
        User userMessageForSeesion = userTools.getUserMessageForSeesion(request);
        ModelAndView mv = new ModelAndView("/userUp");
        List<User> allUser = new ArrayList<>();
        if("0".equals(userMessageForSeesion.getUserLv())){
            //通过用户信息筛选用户
            allUser = userService.getAllUser(Search);

            //分页
            if (pageNum == null){
                pageNum = "1";
            }
            //有数据才分页
            if (allUser.size() > 0){
                allUser = pageTools.PageHelper(allUser,pageNum,mv);
            }else{
                Page page = new Page(10,-1,0,-1,-1);
                mv.addObject("pageInfo",page);
            }

        }else{
            user.setUserId(userMessageForSeesion.getUserId());
            User userSearch = userService.getUser(user);
            allUser.add(userSearch);
        }
        mv.addObject("active","userIndex");
        mv.addObject("userList", allUser);
        return mv;
    }


    //考勤主页
    @RequestMapping("/recordIndex")
    public ModelAndView recordIndex(HttpServletRequest request, Record record , String userAccount , String curriculumName , String pageNum) {
        User userMessageForSeesion = userTools.getUserMessageForSeesion(request);
        ModelAndView mv = new ModelAndView("/record");

        if (record.getTime() != null){
            if (!record.getTime().equals("")){
                String[] split = record.getTime().split(" to ");
                Integer min = Integer.parseInt(split[0].replaceAll("-",""));
                Integer max = Integer.parseInt(split[1].replaceAll("-",""));
                record.setTimeNumMax(max);
                record.setTimeNumMin(min);

            }
            record.setTime(null);
        }

        //教师访问主页时，展示的是自己学生的考勤信息
        if ("1".equals(userMessageForSeesion.getUserLv())) {
            record.setTeacherId(userMessageForSeesion.getUserId());
        }

        //学生主页时，展示的是自己的考勤
        if ("2".equals(userMessageForSeesion.getUserLv())) {
            record.setUserId(userMessageForSeesion.getUserId());
        }

        List<Record> allRecord = recordService.getAllRecord(record);
        List<Record> allRecordReturn = new ArrayList<>();


        if (null != userAccount) {
            //按用户账号筛选
            if("1".equals(userMessageForSeesion.getUserLv())){
                //老师按学生查找
                for (Record recordSearch: allRecord) {
                    if(recordSearch.getStudentMessage().getUserAccount().toLowerCase().contains(userAccount.toLowerCase())){

                        allRecordReturn.add(recordSearch);
                    }
                }
            }
            if ("2".equals(userMessageForSeesion.getUserLv())){
                //学生按老师查找
                for (Record recordSearch: allRecord) {
                    if(recordSearch.getTeacherMessage().getUserAccount().toLowerCase().contains(userAccount.toLowerCase())){

                        allRecordReturn.add(recordSearch);
                    }
                }
            }
        }else if (null != curriculumName){
            //按课程名筛选
            for (Record recordSearch:allRecord) {
                if(recordSearch.getCurriculumMessage() != null && recordSearch.getCurriculumMessage().getCurriculumName().toLowerCase().contains(curriculumName.toLowerCase())){
                    allRecordReturn.add(recordSearch);
                }
            }
        }else{
            allRecordReturn = allRecord;
        }


        //分页
        if (pageNum == null){
            pageNum = "1";
        }
        //有东西才分页
        if (allRecordReturn.size() > 0){
            allRecordReturn = pageTools.PageHelper(allRecordReturn,pageNum,mv);
        }else{
            Page page = new Page(10,-1,0,-1,-1);
            mv.addObject("pageInfo",page);
        }



        mv.addObject("recordList", allRecordReturn);
        mv.addObject("active","recordIndex");
        return mv;
    }


}
