import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NewWordMapper extends Mapper <LongWritable, Text, Text, Text> {
	
	
	
	public void map(LongWritable key, Text value, Context context) throws IOException,InterruptedException {
		
		if(((LongWritable)key).get() == 0) {
			return;
		}
		String line = value.toString();
		if(line.length()!=0) {
			String[] arr= line.split(",");
			Double d = Double.parseDouble(context.getConfiguration().get("Mean"));
			if(Double.parseDouble(arr[9]) > (d*1.5)) {
				String text = arr[0] +","+ (2021-Integer.parseInt(arr[1])) +","+ arr[2] +","+ arr[3] +","+ arr[4] +","+ arr[9];
				context.write(new Text(arr[9]),new Text(text));
			}
		}
	}
}
