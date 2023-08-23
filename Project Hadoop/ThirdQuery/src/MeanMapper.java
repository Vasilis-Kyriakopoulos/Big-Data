import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MeanMapper extends Mapper <LongWritable, Text, Text, Text> {
	
	
	
	public void map(LongWritable key, Text value, Context context) throws IOException,InterruptedException {
		
		if(((LongWritable)key).get() == 0) {
			return;
		}
		String line = value.toString();
		if(line.length()!=0) {
			String[] arr= line.split(",");
			Integer MntWines = Integer .parseInt(arr[9]);
			Integer  MntFruits=Integer .parseInt(arr[10]);
			Integer  MntMeatProducts=Integer .parseInt(arr[11]);
			Integer  MntFishProducts=Integer .parseInt(arr[12]);
			Integer  MntSweetProducts=Integer .parseInt(arr[13]);
			Integer  MntGoldPRods=Integer .parseInt(arr[14]);
			Integer  sum = MntWines+MntFruits+MntMeatProducts+MntFishProducts+MntSweetProducts+MntGoldPRods;
			context.write(new Text(arr[2]),new Text(String.valueOf(sum)));
			
		}
	}
}
