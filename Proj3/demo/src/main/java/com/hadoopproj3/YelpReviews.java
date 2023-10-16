package com.hadoopproj3;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class YelpReviews {
    public static void main(String[] args) throws Exception {
        if(args.length != 2){
            System.err.println("Usage: YelpReviews <inputPath> <outputPath>");
            System.exit(-1);
        }

        Job job = Job.getInstance();
        job.setJarByClass(YelpReviews.class);
        job.setJobName("Yelp Reviews");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setMapperClass(YelpReviewMapper.class);
        job.setReducerClass(YelpReviewReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}