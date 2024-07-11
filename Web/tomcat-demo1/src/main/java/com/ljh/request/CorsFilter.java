package com.ljh.request;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class CorsFilter implements Filter {




    public void doOptions(HttpServletRequest request, HttpServletResponse response) {

        // 设置允许携带凭证（cookies）
        response.setHeader("Access-Control-Allow-Credentials", "true");




        // 获取请求的来源地址
        String origin="static-mp-ccaf0577-1b3a-4038-8268-a8a494a80abe.next.bspapp.com";
        //http://static-mp-ccaf0577-1b3a-4038-8268-a8a494a80abe.next.bspapp.com/


        System.out.println(origin);

        System.out.println("asSAsA");



        // 根据需求放宽或限制允许的源，这里假设只允许特定源

        if (origin != null && origin.equals("http://static-mp-ccaf0577-1b3a-4038-8268-a8a494a80abe.next.bspapp.com")) {
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
        response.setHeader("Access-Control-Expose-Headers", "static-mp-ccaf0577-1b3a-4038-8268-a8a494a80abe.next.bspapp.com/");




        // 注意：设置"Access-Control-Allow-Methods"和"Access-Control-Allow-Headers"为"*"
        // 虽然方便，但在安全性要求较高的场景下应尽量精确指定允许的方法和请求头
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        HttpServletRequest reqs = (HttpServletRequest) req;
        HttpServletResponse resp = (HttpServletResponse) res;
        doOptions(reqs,resp);

        try {
            chain.doFilter(req,resp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }


    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}
}