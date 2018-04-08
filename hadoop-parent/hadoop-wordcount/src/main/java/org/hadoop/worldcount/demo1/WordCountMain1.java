package org.hadoop.worldcount.demo1;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountMain1 {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration configuration = new Configuration();
		Job job = Job.getInstance(configuration);
		//Job job = new Job();
		job.setJobName("wordcount");
		
		//重要：指定本job所在的jar包 hadoop利用这个类来查找包含他的jar文件，进而找到相关的jar文件
		job.setJarByClass(WordCountMain1.class);
		//设置wordCountJob所用的mapper逻辑类为哪个类 
		job.setMapperClass(WordCountMapper1.class);
		//设置wordCountJob所用的reducer逻辑类为哪个类  
		job.setReducerClass(WordCountReducer1.class);
		
		//设置map阶段输出的kv数据类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		//设置最终输出的kv数据类型  
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//设置输入的类型是文本输入格式-默认值
		job.setInputFormatClass(TextInputFormat.class);
		
		//设置要处理的文本数据所存放的路径
		FileInputFormat.setInputPaths(job, "hdfs://localhost:9000/wordcount/input/");
		//路径可以是单个文件，一个目录，可以调用addInputPath来实现多路径的输入
		//FileInputFormat.addInputPath(job,new Path("hdfs://localhost:9000/wordcount/input/"));
		//只能有一个输出的路径，运行前目录不存在，防止数据丢失
		FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/wordcount/output/"));
		
		//FileInputFormat.setInputPaths(job, new Path("c:/wordcount/input"));
		//FileOutputFormat.setOutputPath(job, new Path("c:/wordcount/output"));
		
		//提交job给hadoop集群 
		boolean result = job.waitForCompletion(true); 
		System.out.println(result);
		System.exit(result?1:0);
	}
}
