package com.ljh.web;

import com.ljh.mapper.StudentMapper;
import com.ljh.pojo.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/add")
public class addGrade extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //接受用户学号
        String number=request.getParameter("number");
        String grade=request.getParameter("grade");

        //封装用户对象
        Student student=new Student();
        student.setNumber(number);
        student.setGrade(grade);


        //2.调用mapper 根据学号查询用户对象
        //2.1获取SqlSessionFactory对象

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.2获取SqlSession对象
        SqlSession sqlSession=sqlSessionFactory.openSession();

        //2.3获取Mapper
        StudentMapper studentMapper=sqlSession.getMapper(StudentMapper.class);

        //2.4调用方法
        Student s=studentMapper.selectByNumber(number);


        //3.判断用户对象是否为null
        if(s==null){
            //学号不存在，添加学号以及添加成绩
            studentMapper.add(student);
            //提交事务
            sqlSession.commit();
            //释放资源
            sqlSession.close();

        }else{
            //学号已存在
            //用户名存在，给出提示信息
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("学号已存在");


        }


    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{

    }
}
