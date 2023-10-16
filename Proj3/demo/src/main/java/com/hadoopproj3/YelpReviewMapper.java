package com.hadoopproj3;


import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class YelpReviewMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);

    // make all words lowercase and remove any special characters
    public String formatWord(String str) throws IOException {;   
        return str.toLowerCase().replaceAll("[^A-Za-z]","");
    }

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
        String review = value.toString();
        
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObj = (JSONObject) parser.parse(review);
            
            if (jsonObj == null) {
                System.err.println("JSON parsing failed. JSON object is null.");
                return;
            }

            // Extract the "text" value
            String textValue = (String) jsonObj.get("text");
            
            if (textValue == null) {
                System.err.println("The 'text' value is null or missing in the JSON.");
                return;
            }

            StringTokenizer t = new StringTokenizer(textValue);
            String word ="";

            // iterate through entire list of tokens
                //if its a new word, add word to list, set value to 1
                //if word already is in list, increase value + 1



            while(t.hasMoreTokens()) {
                word = formatWord(t.nextToken());
                Text tempText = new Text(word);
                context.write(tempText, one);                
            }

        } catch (ParseException e) {
            // Handle parsing errors
            e.printStackTrace();
        }    
    }
}