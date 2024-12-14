package com.lxq.model.dao;

import com.lxq.model.object.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface UserDAO {

    //查询用户全部信息
    List<User> getAllUser(User User);
    //查询用户信息
    User getUser(User User);
    //增加用户信息
    int addUser(User User);
    //删除用户信息
    int delUser(User User);
    //修改用户信息
    int updateUser(User User);

    User getUserByAccount(User user);
}
