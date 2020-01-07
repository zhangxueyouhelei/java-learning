package com.gunkeed.sparkdemo;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class SparkDemo2 {

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("this is a demo");
        sparkConf.setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        List<Integer> data = Arrays.asList(1,1,2,2,1);
        JavaRDD<Integer> javaRDD = sc.parallelize(data);
        JavaPairRDD<Integer, Integer> pairRDD = javaRDD.mapToPair(new PairFunction<Integer, Integer, Integer>() {
            @Override
            public Tuple2<Integer, Integer> call(Integer integer) throws Exception {
                return new Tuple2(integer, integer*integer);
            }
        });

        List<Tuple2<Integer, Integer>> list = pairRDD.collect();
        for (Tuple2<Integer, Integer> integerIntegerTuple2 : list) {
            System.out.println(integerIntegerTuple2._1() +  ": " +integerIntegerTuple2._2());
        }
    }
}
