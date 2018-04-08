package org.hadoop.numberSequencing;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//新的实现方式在mapreduce包中，且是虚类
public class NumberSequenceMapper extends Mapper<Object, Text, IntWritable, IntWritable>{

	//map将输入的value作为Intwritable类型，作为输出的key
	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, IntWritable, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String line = value.toString().trim();
		IntWritable data = new IntWritable();
		data.set(Integer.parseInt(line));
		//mapreduce默认按key排序！
		context.write(data, new IntWritable(1));
	}
	
}
