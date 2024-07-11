package com.ljh.mapper;

import com.ljh.pojo.Student;
import com.ljh.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper {



    //根据用户名查询用户对象
    @Select("select * from tb_grade where number=#{number}")
    Student selectByNumber(String number);

    //查询成绩
    @Select("select grade from tb_grade where number=#{number}")
    String selectGrade(String number);


    //添加学号及成绩
    @Insert("insert into tb_grade values(null,#{number},#{grade}) ")
    void add(Student student);








}
