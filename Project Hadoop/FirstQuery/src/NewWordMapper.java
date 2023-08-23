import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//Κυριακόπουλος Βασίλης,2022201800103,Κυριακόπουλος Γιώργος 2022201400112
public class NewWordMapper extends Mapper <LongWritable, Text, Text, IntWritable> {
	
	
	
	public void map(LongWritable key, Text value, Context context) throws IOException,InterruptedException {
		
		if(((LongWritable)key).get() == 0) {
			return;
		}
		String line = value.toString();
		if(line.length()!=0) {
		String[] arr= line.split(",");
		String education = arr[2];
		context.write(new Text(education),new IntWritable(1));
		}
	}
}
