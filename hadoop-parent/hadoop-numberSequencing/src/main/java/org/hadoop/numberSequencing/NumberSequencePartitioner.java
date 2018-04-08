package org.hadoop.numberSequencing;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

//Partitioner分区
public class NumberSequencePartitioner extends Partitioner<IntWritable, IntWritable>{

	/**
	 * numPartitions 分区个数
	 * 分区的逻辑，默认的partitioner通过哈希函数来分区
	 */
	@Override
	public int getPartition(IntWritable key, IntWritable value, int numPartitions) {
		int MaxNumber = 65223;
		//区间大小
		int bound = MaxNumber/numPartitions +1;
		int keyNumber = key.get();
		for(int i = 0;i<numPartitions;i++) {
			if(keyNumber < bound*i && keyNumber >= bound * (i-1)) {
				return i-1;
			}
		}
		return 0;
	}

}
