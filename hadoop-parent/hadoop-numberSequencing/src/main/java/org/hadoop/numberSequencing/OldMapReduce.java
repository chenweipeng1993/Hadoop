package org.hadoop.numberSequencing;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
//旧的实现在包mapred中
public class OldMapReduce {

	static class OldMapper extends MapReduceBase implements Mapper<Object, OldMapReduce, IntWritable, IntWritable>{

		@Override
		public void map(Object key, OldMapReduce value, OutputCollector<IntWritable, IntWritable> output, Reporter reporter)
				throws IOException {
			// TODO Auto-generated method stub
			
		}
	}
	
	static class OldReducer extends MapReduceBase implements Reducer<IntWritable, IntWritable, IntWritable, IntWritable>{

		//注意values的类型是Iterator
		@Override
		public void reduce(IntWritable key, Iterator<IntWritable> values, OutputCollector<IntWritable, IntWritable> output,
				Reporter reporter) throws IOException {
			// TODO Auto-generated method stub
			
		}
		
	}

}
