package com.gunkeed.sparkdemo.concurrent;

import org.junit.jupiter.api.Test;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class TestThread implements  Runnable{

    private static int ticket = 100;

    @Override
    public void run() {

    }

    public static void main(String[] args) throws Exception{
        String encode = URLEncoder.encode("还好", "utf-8");
        System.out.println(encode);
        String decode = URLDecoder.decode("%BF%A8%BA%C5%B3%E4%D6%B5%B4%CE%CA%FD%CF%DE", "gbk");
        System.out.println(decode);
    }

    @Test
    public void testA(){

    }
}
