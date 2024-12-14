package com.lxq.controller;

import com.lxq.Tools.TimeTools;
import com.lxq.Tools.UserTools;
import com.lxq.model.object.LeaveApplication;
import com.lxq.model.object.User;
import com.lxq.model.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/leaveApplication")
public class LeaveApplicationController {

    @Autowired
    private LeaveApplicationService leaveApplicationService;
    @Autowired
    private UserTools userTools;
    @Autowired
    private TimeTools timeTools;

    //查询
    @RequestMapping("/s")
    @ResponseBody
    public  List<LeaveApplication> get(LeaveApplication leaveApplication){
        return leaveApplicationService.getAllLeaveApplication(leaveApplication);
    }

    //新增
    @RequestMapping("/add")
    @ResponseBody
    public  String add(@RequestBody LeaveApplication leaveApplication, HttpServletRequest request){
        //封装申请的学生信息
        User userMessageForSeesion = userTools.getUserMessageForSeesion(request);
        leaveApplication.setUserId(userMessageForSeesion.getUserId());
        //封装申请时间
        String timeYMD = timeTools.getTimeYMD();
        leaveApplication.setTime(timeYMD);

        if (leaveApplicationService.getLeaveApplicationbyPrimary(leaveApplication) != null){
            return "false-has-double";
        }

        return leaveApplicationService.addLeaveApplication(leaveApplication);

    }

    //删除
    @RequestMapping("/del")
    @ResponseBody
    public  String del(@RequestBody LeaveApplication leaveApplication){
        return leaveApplicationService.delLeaveApplication(leaveApplication);

    }

    //更新
    @RequestMapping("/up")
    @ResponseBody
    public  String up(@RequestBody LeaveApplication leaveApplication, HttpServletRequest request){

        User userMessageForSeesion = userTools.getUserMessageForSeesion(request);

        //学生只允许进行取消，和重新申请（权限操作）
        if("2".equals(userMessageForSeesion.getUserLv())){
            if("同意".equals(leaveApplication.getState()) || "拒绝".equals(leaveApplication.getState())){
                return   "false";
            }
        }
        return leaveApplicationService.updateLeaveApplication(leaveApplication);
    }


}
