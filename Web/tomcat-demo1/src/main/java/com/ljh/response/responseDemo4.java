package com.ljh.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

/*
* 响应字节数据： 设置字节数据的响应体*/
@WebServlet("/resp4")
public class responseDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        ///1.读取文件
        FileInputStream fis=new FileInputStream("c://a.webp");
        //2.获取response字节输出流
        ServletOutputStream os = response.getOutputStream();

        //3.完成流的copy
        /*byte[] buff=new byte[10240000];
        int len=0;
        while((len= fis.read(buff))!=-1) {

            os.write(buff, 0, len);
        }
        fis.close();
        */
        IOUtils.copy(fis,os);
        fis.close();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);

    }

}
