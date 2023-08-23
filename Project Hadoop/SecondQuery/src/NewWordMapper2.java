import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NewWordMapper2 extends Mapper <LongWritable, Text, Text, Text> {
	
	
	
	public void map(LongWritable key, Text value, Context context) throws IOException,InterruptedException {
		
	
		String line = value.toString();
		if(line.length()!=0) {
				context.write(new Text(""),new Text(line));

		}
	}
}
