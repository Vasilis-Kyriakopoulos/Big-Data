import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//Κυριακόπουλος Βασίλης,2022201800103,Κυριακόπουλος Γιώργος 2022201400112
public class NewWordCount {
	
	public static void main(String[] args) throws Exception {
		
		if(args.length > 3) {
			System.err.println("Give the correct arguements.");
			System.exit(3);
		}
		
		Configuration conf = new Configuration();
		

		Job job = Job.getInstance(conf, "count");

		job.setJarByClass(NewWordCount.class);

		job.setOutputKeyClass(Text.class);

		job.setOutputValueClass(IntWritable.class);

		job.setMapperClass(NewWordMapper.class);

		
		job.setReducerClass(NewWordReducer.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));

		FileOutputFormat.setOutputPath(job,new Path(args[1]));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
	}
}