package com.gunkeed.sparkdemo.HttpWeb;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

public class MyResponse implements ServletResponse {

    private static final int BUFF_SIZE = 1024;

    private MyRequest myRequest;

    private OutputStream outputStream;

    private PrintWriter printWriter;


    public MyResponse( OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void sendStaticResource(){
        byte[] buffer = new byte[BUFF_SIZE];
        FileInputStream fileInputStream = null;
        try {
            File file = new File(MyHttpServer.WEB_ROOT,myRequest.getUri());
            if(file.exists()){
                fileInputStream = new FileInputStream(file);
                int ch = fileInputStream.read(buffer, 0, BUFF_SIZE);
                while (ch!=-1){
                    outputStream.write(buffer, 0 ,ch);
                    ch = fileInputStream.read(buffer, 0, BUFF_SIZE);
                }
            }else {
                String errorMessage = "HTTP/1.1 404 File Not Found \r\n" +
                        "Content-Type : text/html\r\n" +
                        "Content-Length : 23\r\n" +
                        "\r\n" +
                        "<h1> File Not Found </h1>";
                outputStream.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public MyRequest getMyRequest() {
        return myRequest;
    }

    public void setMyRequest(MyRequest myRequest) {
        this.myRequest = myRequest;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }


    @Override
    public PrintWriter getWriter() throws IOException {
        printWriter = new PrintWriter(outputStream, true);
        return printWriter;
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentLengthLong(long l) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }

}
