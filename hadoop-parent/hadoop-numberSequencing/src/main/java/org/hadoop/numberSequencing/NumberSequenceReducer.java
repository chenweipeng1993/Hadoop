package org.hadoop.numberSequencing;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class NumberSequenceReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable>{
	//mapreduce默认按key排序！
	//reducer将输入的key的值复制到输出的key中
	//然后根据value-list中元素的个数决定key的输出次数
	//用全局的linenum来代表key的位次
	//values的类型是Iterable
	@Override
	protected void reduce(IntWritable key, Iterable<IntWritable> values,
			Reducer<IntWritable, IntWritable, IntWritable, IntWritable>.Context context)
			throws IOException, InterruptedException {
		IntWritable linenum = new IntWritable(1);
		for(IntWritable value : values) {
			//context.write(linenum, value);
			//注意这个值是key
			//mapreduce默认按key排序！
			System.out.println("这里的value的值根本不重要，重要的是key");
			context.write(linenum, key);
			linenum = new IntWritable(linenum.get()+1);
		}
	}

	
}
