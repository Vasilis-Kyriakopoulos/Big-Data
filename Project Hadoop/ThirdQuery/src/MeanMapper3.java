import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MeanMapper3 extends Mapper <LongWritable, Text, Text, Text> {
	
	
	
	public void map(LongWritable key, Text value, Context context) throws IOException,InterruptedException {
		
		if(((LongWritable)key).get() == 0) {
			return;
		}
		String line = value.toString();
		if(line.length()!=0) {
			String[] arr= line.split(",");
			Double income = Double.parseDouble(arr[4]);
			Double  sum = income;
			if(sum>Math.pow(10,-6) && sum < 118703.125 ) {
				context.write(new Text(arr[2]),new Text(String.valueOf(sum)));
			}
			
		}
	}
}
