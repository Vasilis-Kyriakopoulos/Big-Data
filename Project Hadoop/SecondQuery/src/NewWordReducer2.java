import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class NewWordReducer2 extends Reducer <Text, Text, IntWritable, Text> {
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		
		ArrayList<Customer> arrlist = new ArrayList<Customer>();
		for(Text val:values) {
			 String s = val.toString();
			 arrlist.add(new Customer(s));
		}
		Collections.sort(arrlist);
		for(int i = 0;i<(arrlist.size());i++) {
				String text =arrlist.get(i).toString();
				context.write(new IntWritable(i+1),new Text(text));
		}
	
	}
}
