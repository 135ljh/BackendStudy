package com.ljh.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add_grade")
public class responseDemo1 extends HttpServlet {

    public void doOptions(HttpServletRequest request, HttpServletResponse response) {

            // 设置允许携带凭证（cookies）
            response.setHeader("Access-Control-Allow-Credentials", "true");

            // 获取请求的来源地址
            String origin = request.getHeader("Origin");

            System.out.println(origin);



            // 根据需求放宽或限制允许的源，这里假设只允许特定源

            if (origin!=null) {
                response.setHeader("Access-Control-Allow-Origin", "*");
            }

            // 设置允许的方法
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");

            // 设置允许的请求头
            response.setHeader("Access-Control-Allow-Headers",
                    "Authorization, transfer, session, Content-Type, Accept, Origin, X-Requested-With, token, id, X-Custom-Header, X-Cookie, Connection, User-Agent, Cookie");

            // 设置预检请求的有效期
            response.setHeader("Access-Control-Max-Age", "3600");

            // 允许浏览器读取的所有响应头
            response.setHeader("Access-Control-Expose-Headers", "*");


            if(request.getMethod().equals("Options")){
                response.setStatus(200);
                return;
            }


        // 注意：设置"Access-Control-Allow-Methods"和"Access-Control-Allow-Headers"为"*"
        // 虽然方便，但在安全性要求较高的场景下应尽量精确指定允许的方法和请求头
    }





    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{



        System.out.println("resp1...");

     /*   //重定向
        //1.设置相应状态码 302
        response.setStatus(302);
        //2.设置相应头 Location
        response.setHeader("Location","/Web/resp2");*/

        //动态获取虚拟目录
        //简化方式
        String contextPath=request.getContextPath();
        response.sendRedirect(contextPath+"/select");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request,response);

    }

}
