import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//Κυριακόπουλος Βασίλης,2022201800103,Κυριακόπουλος Γιώργος 2022201400112
public class NewWordCount {
	public static String readFileFromHDFS(String pathh) throws IOException {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://localhost:9000");
        FileSystem fileSystem = FileSystem.get(configuration);
        //Create a path
        String fileName = "/part-r-00000";
        Path hdfsReadPath = new Path(pathh + fileName);
        
        FSDataInputStream inputStream = fileSystem.open(hdfsReadPath);
        //Classical input stream usage
        
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line = null;
        line=bufferedReader.readLine();
        
        inputStream.close();
        fileSystem.close();
        return line;
    }
	
	public static void main(String[] args) throws Exception {
		
		if(args.length > 3) {
			System.err.println("Give the correct arguements.");
			System.exit(3);
		}
		
		
		
		
		Configuration conf2 = new Configuration();
		

		Job job2 = Job.getInstance(conf2, "Mean");

		job2.setJarByClass(NewWordCount.class);

		job2.setOutputKeyClass(Text.class);

		job2.setOutputValueClass(Text.class);

		job2.setMapperClass(MeanMapper.class);

		//job.setCombinerClass(NewWordReducer.class);
		job2.setReducerClass(MeanReducer.class);

		FileInputFormat.addInputPath(job2, new Path(args[0]));

		FileOutputFormat.setOutputPath(job2,new Path(args[1]));
		job2.waitForCompletion(true);
		
		
		
		Configuration conf1 = new Configuration();
		

		Job job1 = Job.getInstance(conf1, "mean2");

		job1.setJarByClass(NewWordCount.class);

		job1.setOutputKeyClass(Text.class);

		job1.setOutputValueClass(Text.class);

		job1.setMapperClass(MeanMapper2.class);

		//job.setCombinerClass(NewWordReducer.class);
		job1.setReducerClass(MeanReducer2.class);

		FileInputFormat.addInputPath(job1, new Path(args[1]+ "/part-r-00000"));

		FileOutputFormat.setOutputPath(job1,new Path("/r_output3.1"));
		
		
		job1.waitForCompletion(true) ;
		
		
		
		
		
		
		
		
Configuration conf3 = new Configuration();
		

		Job job3 = Job.getInstance(conf3, "Mean3");

		job3.setJarByClass(NewWordCount.class);

		job3.setOutputKeyClass(Text.class);

		job3.setOutputValueClass(Text.class);

		job3.setMapperClass(MeanMapper3.class);

		//job.setCombinerClass(NewWordReducer.class);
		job3.setReducerClass(MeanReducer3.class);

		FileInputFormat.addInputPath(job3, new Path(args[0]));

		FileOutputFormat.setOutputPath(job3,new Path("/r_output3.2"));
		job3.waitForCompletion(true);
		
		
		
		Configuration conf4 = new Configuration();
		

		Job job4 = Job.getInstance(conf4, "mean4");

		job4.setJarByClass(NewWordCount.class);

		job4.setOutputKeyClass(Text.class);

		job4.setOutputValueClass(Text.class);

		job4.setMapperClass(MeanMapper4.class);

		//job.setCombinerClass(NewWordReducer.class);
		job4.setReducerClass(MeanReducer4.class);

		FileInputFormat.addInputPath(job4, new Path("/r_output3.2"+ "/part-r-00000"));

		FileOutputFormat.setOutputPath(job4,new Path("/r_output3.3"));
		
		
		job4.waitForCompletion(true) ;
		
		
		
		
		
		
		
		Configuration conf = new Configuration();
		String s1 = readFileFromHDFS("/r_output3.1");
		String s2 = readFileFromHDFS("/r_output3.3");
		
		conf.set("Mean", s1);
		conf.set("Mean1", s2);
		Job job = Job.getInstance(conf, "count");
		job.setJarByClass(NewWordCount.class);

		job.setOutputKeyClass(Text.class);

		job.setOutputValueClass(Text.class);

		job.setMapperClass(NewWordMapper.class);

		//job.setCombinerClass(NewWordReducer.class);
		job.setReducerClass(NewWordReducer.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));

		FileOutputFormat.setOutputPath(job,new Path("/r_output3.4"));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
	}
}