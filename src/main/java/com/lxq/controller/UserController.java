package com.lxq.controller;

import com.lxq.Tools.UserTools;
import com.lxq.model.object.User;
import com.lxq.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserTools userTools;


    //用户登录
    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, @RequestBody User user) {
        User userSearch = userService.getUserByAccount(user);
        if (userSearch == null){
            return "false-account";
        }else if (userSearch.getUserPw().equals(user.getUserPw())) {
            //用于登录检查
            request.getSession().setAttribute("users", userSearch);
            //用于前端判断身份
            request.getSession().setAttribute("userInfo",userSearch);

            //更新访问量
            try {
                File file = new File("D:\\工作\\大学计算机\\code-workspace\\idea-workspace\\Student-Attendance-Management-System\\src\\main\\resources\\static\\file\\visitorCount.txt");
                if (!file.exists()){
                    if(file.createNewFile()){
                        System.out.println("成功创建文件！");
                    }
                }

                FileReader read = new FileReader(file);
                char[] ch = new char[1024];
                int d = read.read(ch);
                String str = new String(ch, 0, d);

                int count = Integer.parseInt(str);
                count = count + 1;
                BufferedWriter output = new BufferedWriter(new FileWriter(file));
                output.write(String.valueOf(count));
                output.close();

            }catch (Exception e){
                e.printStackTrace();
            }

            if(userSearch.getUserLv().equals("0")){
                return "true-admin";
            }else{
                return "true";
            }
        }else {
            return "false-pw";
        }
//        return "false";
    }

    //用户退出登录
    @RequestMapping("/outSignIn")
    @ResponseBody
    public boolean outSignIn(HttpServletRequest request) {
//        request.getSession().invalidate();
        request.getSession().setAttribute("users", null);
        return true;
    }

    //用户注册
    @RequestMapping("/userRegister")
    @ResponseBody
    public String userRegister(@RequestBody User user) {
        if (userService.getUserByAccount(user) != null){
            return "false-has-double";
        }
        //账号初始化昵称
        user.setUserName(user.getUserAccount());
        return userService.addUser(user);
    }

    //用户修改
    @RequestMapping("/up")
    @ResponseBody
    public String up(@RequestBody User user) {
        return userService.updateUser(user);
    }

    //用户删除
    @RequestMapping("/del")
    @ResponseBody
    public String del(User user) {
        return userService.delUser(user);
    }


}
