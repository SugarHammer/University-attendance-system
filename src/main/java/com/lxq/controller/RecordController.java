package com.lxq.controller;

import com.lxq.Tools.TimeTools;
import com.lxq.Tools.UserTools;
import com.lxq.model.object.Record;
import com.lxq.model.object.User;
import com.lxq.model.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;
    @Autowired
    private UserTools userTools;
    @Autowired
    private TimeTools timeTools;

    //查询
    @RequestMapping("/s")
    @ResponseBody
    public  List<Record> get(Record Record, HttpServletRequest request){
        //封装申请的学生信息
        User userMessageForSeesion = userTools.getUserMessageForSeesion(request);

        //学生只允许 查询自己的
        if("2".equals(userMessageForSeesion.getUserLv())){
            Record.setUserId(userMessageForSeesion.getUserId());
        }
        return recordService.getAllRecord(Record);
    }

    //删除
    @RequestMapping("/del")
    @ResponseBody
    public String del(Record Record){
        return recordService.delRecord(Record);

    }

    //更新
    @RequestMapping("/up")
    @ResponseBody
    public String up(@RequestBody Record Record, HttpServletRequest request){
        //封装申请的学生信息
        User userMessageForSeesion = userTools.getUserMessageForSeesion(request);

        //学生不允许操作
        if("2".equals(userMessageForSeesion.getUserLv())){
            return  "false";
        }
        return recordService.updateRecord(Record);
    }


}
