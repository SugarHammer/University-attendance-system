package com.lxq.controller;

import com.lxq.Tools.TimeTools;
import com.lxq.Tools.UserTools;
import com.lxq.model.object.Curriculum;
import com.lxq.model.object.User;
import com.lxq.model.service.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/curriculum")
public class CurriculumController {

    @Autowired
    private CurriculumService CurriculumService;
    @Autowired
    private UserTools userTools;
    @Autowired
    private TimeTools timeTools;

    //查询
    @RequestMapping("/s")
    @ResponseBody
    public  List<Curriculum> get(Curriculum Curriculum){
        return CurriculumService.getAllCurriculum(Curriculum);
    }

    //添加课程
    @RequestMapping("/add")
    @ResponseBody
    public  String add(@RequestBody Curriculum curriculum, HttpServletRequest request){
        //封装老师信息
        User userMessageForSeesion = userTools.getUserMessageForSeesion(request);
        //权限限制
        if(!"1".equals(userMessageForSeesion.getUserLv())){
            return  "false";
        }
        curriculum.setTeacherId(userMessageForSeesion.getUserId());
        curriculum.setJoinUserNum(0);

        if (CurriculumService.getCurriculumbyPrimary(curriculum) != null){
            return "false-has-double";
        }
        return CurriculumService.addCurriculum(curriculum);

    }

    //删除
    @RequestMapping("/del")
    @ResponseBody
    public  String del(@RequestBody Curriculum curriculum){
        return CurriculumService.delCurriculum(curriculum);

    }
    //更新
    @RequestMapping("/up")
    @ResponseBody
    public  String up(@RequestBody Curriculum curriculum, HttpServletRequest request){
        //封装老师信息
        User userMessageForSeesion = userTools.getUserMessageForSeesion(request);
        //权限限制
        if(!"1".equals(userMessageForSeesion.getUserLv())){
            return  "false";
        }
        curriculum.setTeacherId(userMessageForSeesion.getUserId());

        return CurriculumService.updateCurriculum(curriculum);
    }


}
