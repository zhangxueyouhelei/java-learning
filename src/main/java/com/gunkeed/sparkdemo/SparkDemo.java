package com.gunkeed.sparkdemo;

import com.clearspring.analytics.util.Lists;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class SparkDemo {

    private static  final Logger logger  = LoggerFactory.getLogger(SparkDemo.class);

    private static final  SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        String logFile = "D:/data.txt"; // Should be some file on your system
        System.out.println(System.getenv("HADOOP_HOME"));
        SparkSession spark = SparkSession.builder().appName("Simple Application").getOrCreate();
        Dataset<String> logData = spark.read().textFile(logFile).cache().filter(new FilterFunction<String>() {
            @Override
            public boolean call(String s) throws Exception {
                String arr[] = s.split(",");
                Date date = sDateFormat.parse(arr[2]);
                Date beginDate = sDateFormat.parse("2019-06-13 00:00:00");
                Date endDate = sDateFormat.parse("2019-06-14 00:00:00");
                return date.getTime()>beginDate.getTime() && date.getTime()<endDate.getTime();
            }
        });
        JavaPairRDD<String, Integer> pairRdd = logData.javaRDD().mapToPair(line -> {
            String arr[] = line.split(",");
            return new Tuple2<String, Integer>(arr[0], Integer.valueOf(arr[1]));
        });

        JavaPairRDD<String, Iterable<Integer>> group  = pairRdd.groupByKey();
        group.foreach(line-> {
            HashMap<String, Integer> map = new HashMap<>();
            List<Integer> list = Lists.newArrayList(line._2());
            int max = Collections.max(list);
            int min =  Collections.min(list);
            Double average = list.stream().mapToInt(Integer::intValue).average().getAsDouble();
            String str = line._1 + "    " + average + "    " + max + "    " + min + "\r\n";
            System.out.println(str);
            writeFile("D:/outPut.txt",str);
        });
        long numAs = logData.filter((FilterFunction<String>) s -> s.contains("a")).count();
        long numBs = logData.filter((FilterFunction<String>) s -> s.contains("b")).count();
        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
        spark.stop();
    }


    private static void writeFile(String path ,String line) throws IOException {
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file,true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        bw.write(line);
        bw.flush();
        fos.close();
        bw.close();
    }
}
