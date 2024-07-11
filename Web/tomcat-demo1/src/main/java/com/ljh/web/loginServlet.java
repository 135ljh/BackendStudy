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

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        //1.接收用户名和密码】
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        //2.调用mybatis来完成查询
        //2.1获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.2获取SqlSession对象
        SqlSession sqlSession=sqlSessionFactory.openSession();

        //2.3获取Mapper
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        //2.4调用Mapper方法
        User user=userMapper.select(username,password);

        //2.5释放资源
        sqlSession.close();

        //获取字符输出流，并设置content type
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer=response.getWriter();
        //3.判断user是否为null
        if(user!=null){
            //登录成功
            writer.write("登录成功");
        }else{
            //登录失败
            writer.write("登录失败");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{

    }
}
