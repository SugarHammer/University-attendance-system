package com.lxq.model.service;

import com.lxq.model.dao.UserDAO;
import com.lxq.model.object.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    //查询全部用户信息
    public List<User> getAllUser(User user){
        List<User> allUser = new ArrayList<>();
        try{
            allUser = userDAO.getAllUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return allUser;
    }
    //查询用户信息
    public User getUser(User user){
        User userSearch = null;
        try{
            userSearch = userDAO.getUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userSearch;
    }

    //增加用户信息
    public String addUser(User user){
        String isSuccess = "false";
        try{
            int resultNum = userDAO.addUser(user);
            if(resultNum>0){
                isSuccess = "true";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return isSuccess;
    }
    //删除用户信息
    public String delUser(User user){
        String isSuccess = "false";
        try{
            int resultNum  = userDAO.delUser(user);
            if(resultNum>0){
                isSuccess = "true";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return isSuccess;
    }
    //修改用户信息
    public String updateUser(User user){
        String isSuccess = "false";
        try{
            int resultNum = userDAO.updateUser(user);
            if(resultNum>0){
                isSuccess = "true";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return isSuccess;
    }

    //通过Account查询用户信息
    public User getUserByAccount(User user) {
        User userSearch = null;
        try {
            userSearch = userDAO.getUserByAccount(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userSearch;
    }
}
