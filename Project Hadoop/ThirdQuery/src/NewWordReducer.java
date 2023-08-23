import java.io.IOException;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class NewWordReducer extends Reducer <Text, Text, Text, Text> {
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		
		context.write(new Text(""),new Text(key));
		StringBuilder text = new StringBuilder();
		for(Text val:values) {
			 String s = val.toString();
			 text.append(s+", ");
			 
		}
		context.write(new Text(""),new Text(text.toString()));
		
	}
}
