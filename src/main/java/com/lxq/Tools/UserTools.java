package com.lxq.Tools;

import com.lxq.model.object.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/*
    从session中获取当前用户信息
*/
@Component
public class UserTools {

    public User getUserMessageForSeesion(HttpServletRequest request){
        User user = null;
        Object users = request.getSession().getAttribute("users");
        try {
            //获取当前用户
            if (null != users) {
                user = (User) request.getSession().getAttribute("users");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  user;
    }
}
