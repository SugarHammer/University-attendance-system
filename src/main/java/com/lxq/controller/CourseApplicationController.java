package com.lxq.controller;

import com.lxq.Tools.TimeTools;
import com.lxq.Tools.UserTools;
import com.lxq.model.object.CourseApplication;
import com.lxq.model.object.User;
import com.lxq.model.service.CourseApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/courseApplication")
public class CourseApplicationController {

    @Autowired
    private CourseApplicationService courseApplicationService;
    @Autowired
    private UserTools userTools;
    @Autowired
    private TimeTools timeTools;

    //查询
    @RequestMapping("/s")
    @ResponseBody
    public List<CourseApplication> get(CourseApplication courseApplication, HttpServletRequest request){
        //封装申请的学生信息
        User userMessageForSeesion = userTools.getUserMessageForSeesion(request);

        //学生只允许 查询自己的
        if("2".equals(userMessageForSeesion.getUserLv())){
            courseApplication.setUserId(userMessageForSeesion.getUserId());
            courseApplication.setState("同意");
        }
        return courseApplicationService.getAllCourseApplicationWithoutDelete(courseApplication);
    }

    //新增
    @RequestMapping("/add")
    @ResponseBody
    public String add(@RequestBody CourseApplication courseApplication, HttpServletRequest request){
        //封装申请的学生信息
        User userMessageForSeesion = userTools.getUserMessageForSeesion(request);
        courseApplication.setUserId(userMessageForSeesion.getUserId());
        if (courseApplicationService.getCourseApplicationbyPrimary(courseApplication) != null){
            return "false-has-double";
        }
        //添加时间
        String timeYMD = timeTools.getTimeYMD();
        courseApplication.setTime(timeYMD);
        //设置状态
        courseApplication.setState("申请");
        return courseApplicationService.addCourseApplication(courseApplication);

    }

    //删除
    @RequestMapping("/del")
    @ResponseBody
    public String del(@RequestBody CourseApplication courseApplication){
        return courseApplicationService.delCourseApplication(courseApplication);

    }

    //更新
    @RequestMapping("/up")
    @ResponseBody
    public String up(@RequestBody CourseApplication courseApplication, HttpServletRequest request){
        //封装申请的学生信息
        User userMessageForSeesion = userTools.getUserMessageForSeesion(request);

        //学生只允许进行取消，和重新申请（权限操作）
        if("2".equals(userMessageForSeesion.getUserLv())){
            if("同意".equals(courseApplication.getState()) || "拒绝".equals(courseApplication.getState())){
                return   "false";
            }
        }
        return courseApplicationService.updateCourseApplication(courseApplication);
    }


}
