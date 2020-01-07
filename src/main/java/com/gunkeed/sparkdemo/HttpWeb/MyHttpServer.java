package com.gunkeed.sparkdemo.HttpWeb;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MyHttpServer {

    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webRoot";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public void await() {
        ServerSocket serverSocket  = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!isShutdown()){
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                socket = serverSocket.accept();
                inputStream =socket.getInputStream();
                outputStream = socket.getOutputStream();
                MyRequest myRequest = new MyRequest(inputStream);
                myRequest.parse();

                MyResponse myResponse = new MyResponse(outputStream);
                myResponse.setMyRequest(myRequest);
                myResponse.sendStaticResource();

                socket.close();
                shutdown = SHUTDOWN_COMMAND.equals(myRequest.getUri());
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }

    }

    public static void main(String[] args) {
        MyHttpServer myHttpServer = new MyHttpServer();
        myHttpServer.await();
    }

    public boolean isShutdown() {
        return shutdown;
    }

    public void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }
}
