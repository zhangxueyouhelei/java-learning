package com.gunkeed.sparkdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class ApiJob {

    private static final Logger logger = LoggerFactory.getLogger(ApiJob.class);

    private static  List<String> list = new ArrayList<>();


//    static {
//        try {
//            readFile("D:/API URL.txt");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) throws IOException{
        //doJob();
        System.out.println(String.format("%" , "1"));
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public static void doJob() throws IOException{
        System.out.println("我执行了");
        File file =new File("D:/data.txt");

        FileOutputStream fos = new FileOutputStream(file,true);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        for(int i =0; i< 1000000 ; i++){
            int index = (int)(Math.random() * list.size());
            String apiUrl = list.get(index);
            String useTime = String.valueOf((int)(Math.random() * 10000000));
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = sdf.format(date);
            bw.write(apiUrl + "," + useTime + "," + time + "\r\n");
            bw.flush();
        }
        fos.close();
        bw.close();
        System.out.println("执行完毕");
    }

    private static void readFile(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line = br.readLine()) != null) {
            if (line.lastIndexOf("---") < 0) {
                list.add(line);
            }
        }
    }
}
