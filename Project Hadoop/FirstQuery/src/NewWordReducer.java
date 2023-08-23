import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
//Κυριακόπουλος Βασίλης,2022201800103,Κυριακόπουλος Γιώργος 2022201400112
public class NewWordReducer extends Reducer <Text, IntWritable, Text, IntWritable> {
	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		
		int count = 0;
		for(IntWritable val:values) {
			count += val.get();
		}
		context.write(key, new IntWritable(count));
	}
}
