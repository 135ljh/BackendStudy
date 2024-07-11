package com.ljh.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

//Request 通用方式获取请求数据
@WebServlet("/req5")
public class requestDemo5 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //GET 请求逻辑
        System.out.println("get...");

        //1.获取所有参数的Map集合
        Map<String, String[]> map = request.getParameterMap();
        for (String key : map.keySet()) {
            //username:zhangsan
            System.out.print(key + ":");

            //获取值
            String[] values = map.get(key);
            for (String value : values) {
                System.out.print(value + " ");
            }
            System.out.println();
        }

        System.out.println("----------------");

        //2.根据key获取参数值，数组
        String[] hobbies = request.getParameterValues("hobby");
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }

        //3.根据key 获取单个参数值
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(username);
        System.out.println(password);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doGet(request,response);
        /* System.out.println("post...");

        //1.获取所有参数的Map集合
        Map<String,String[]>map=request.getParameterMap();
        for(String key:map.keySet()){
            //username:zhangsan
            System.out.print(key+":");

            //获取值
            String[] values=map.get(key);
            for (String value:values){
                System.out.print(value+" ");
            }
            System.out.println();
        }

        System.out.println("----------------");

        //2.根据key获取参数值，数组
        String[] hobbies= request.getParameterValues("hobby");
        for (String hobby:hobbies){
            System.out.println(hobby);
        }

        //3.根据key 获取单个参数值
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        System.out.println(username);
        System.out.println(password);
    }*/
    }
}


