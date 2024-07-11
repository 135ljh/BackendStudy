package com.ljh.mapper;

import com.ljh.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    //提供一个接口方法，用来查询用户的，根据用户名和密码
    @Select("select * from tb_user where username=#{username} and password=#{password}")
    User select(@Param("username") String username, @Param("password") String password);

    //根据用户名查询用户对象
    @Select("select * from tb_user where username=#{username}")
    User selectByUsername(String username);

    //添加用户
    @Insert("insert into tb_user values(null,#{username},#{password}) ")
    void add(User user);





}
