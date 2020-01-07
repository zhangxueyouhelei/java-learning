package com.gunkeed.sparkdemo.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Demo1 {

    public static void main(String[] args) throws  Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("d:/a.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        int bytesRead = fileChannel.read(byteBuffer);
        while (bytesRead != -1){
            System.out.println("read" + bytesRead);
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                System.out.println((char) byteBuffer.get());
            }
            byteBuffer.clear();
            bytesRead = fileChannel.read(byteBuffer);
        }
        randomAccessFile.close();
        fileChannel.close();
    }
}
