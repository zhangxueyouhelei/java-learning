package com.gunkeed.sparkdemo.HttpWeb;


import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

public class ServletProcessor1 {

    public void process(MyRequest request, MyResponse response)  {
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader urlClassLoader = null;
        try{
            URL[] urls = new URL[1];
            URLStreamHandler urlStreamHandler = null;
            File classFath = new File(Constants.WEB_ROOT);
            String repository = new URL("file", null, classFath.getCanonicalPath() + File.separator).toString();
            urls[0] = new URL(null, repository,urlStreamHandler);
            urlClassLoader = new URLClassLoader(urls);
        }catch (Exception e){
            e.printStackTrace();
        }

        Class myClass = null;
        try {
            myClass = urlClassLoader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Servlet servlet = null;

        try {
            servlet = (Servlet)myClass.newInstance();
            servlet.service(request, response);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
