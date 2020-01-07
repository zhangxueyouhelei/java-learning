package com.gunkeed.sparkdemo.HttpWeb;

public class StaticResourceProcesser {

    public void process(MyRequest request , MyResponse response){

        response.sendStaticResource();
    }
}
