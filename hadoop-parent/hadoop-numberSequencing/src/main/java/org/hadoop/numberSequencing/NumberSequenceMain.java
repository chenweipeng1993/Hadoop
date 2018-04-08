package org.hadoop.numberSequencing;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//import org.apache.hadoop.util.GenericOptionsParser;

public class NumberSequenceMain {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		/*String[] ioArgs = new String[]{"NumberSequence/input","NumberSequence/output"};
		String[] otherArgs = new GenericOptionsParser(conf, ioArgs).getRemainingArgs();
		if(otherArgs.length!=2) {
			System.out.println("Usage:Data Sort <in> <out>");
			System.exit(2);
		}*/
		
		Job job = Job.getInstance(conf);
		job.setJobName("Number Sequence");
		job.setJarByClass(NumberSequenceMain.class);
		
		job.setMapperClass(NumberSequenceMapper.class);
		job.setReducerClass(NumberSequenceReducer.class);
		
		job.setPartitionerClass(NumberSequencePartitioner.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		
		/*FileInputFormat.addInputPath(job, new Path(otherArgs[0]));			
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));*/
		FileInputFormat.addInputPath(job, new Path("hdfs://localhost:9000/NumberSequence/input/"));			
		FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/NumberSequence/output/"));
		
		System.exit(job.waitForCompletion(true)?0:1);
	}
}
