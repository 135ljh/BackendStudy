package com.ljh.request;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet("/demo3")
public class servletDemo3 implements Servlet {
    private ServletConfig config;

    /*init: servlet被第一次访问时调用，仅会被调用一次*/
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        this.config=config;
        System.out.println("init...");

    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("servlet hello world");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
