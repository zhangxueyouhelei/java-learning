package com.gunkeed.sparkdemo.HttpWeb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class PrimitiveServlet implements Servlet {

    private static  final Logger logger = LoggerFactory.getLogger(PrimitiveServlet.class);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        logger.info("I have inited");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("from service");
        PrintWriter printWriter = servletResponse.getWriter();
        printWriter.println("hello, Roses are red");
        printWriter.print("Violets are bule .");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
