package com.ljh.web;

import com.ljh.mapper.UserMapper;
import com.ljh.pojo.User;

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

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        //接受用户数据
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        //封装用户对象
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);

        //2.调用mapper 根据用户名查询用户对象
        //2.1获取SqlSessionFactory对象

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.2获取SqlSession对象
        SqlSession sqlSession=sqlSessionFactory.openSession();

        //2.3获取Mapper
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        //2.4调用方法
        User u=userMapper.selectByUsername(username);

        //3.判断用户对象是否为null
        if(u==null){
            //用户名不存在，可以添加用户
            userMapper.add(user);
            //提交事务
            sqlSession.commit();
            //释放资源
            sqlSession.close();
        }else{
            //用户名存在，给出提示信息
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("用户名已存在");


        }


    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{

    }
}
