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
			Integer  MntWines = Integer.parseInt(arr[9]);
			Integer  MntFruits=Integer.parseInt(arr[10]);
			Integer  MntMeatProducts=Integer.parseInt(arr[11]);
			Integer  MntFishProducts=Integer.parseInt(arr[12]);
			Integer  MntSweetProducts=Integer.parseInt(arr[13]);
			Integer  MntGoldPRods=Integer.parseInt(arr[14]);
			Integer  sum = MntWines+MntFruits+MntMeatProducts+MntFishProducts+MntSweetProducts+MntGoldPRods;
			Double  income = Double.parseDouble(arr[4]);
			Integer date = Integer.parseInt(arr[7].substring(arr[7].length()-2));
			Double mean_mnt = Double.parseDouble(context.getConfiguration().get("Mean"));
			Double mean_income = Double.parseDouble(context.getConfiguration().get("Mean1"));
			if(sum>mean_mnt*1.5 && date ==21 && income>69500) {
				context.write(new Text("Gold"), new Text(arr[0]));
			}
			else if (sum>mean_mnt*1.5 && date <21 && income>69500)
			{
				context.write(new Text("Silver"), new Text(arr[0]));
			}
			else if (sum<=mean_mnt*0.25 && date ==21 && income<mean_income)
			{
				context.write(new Text("Bronze"), new Text(arr[0]));
			}
			else if (sum<=mean_mnt*0.25 && date <21 && income<mean_income)
			{
				context.write(new Text("Paper"), new Text(arr[0]));
			}
			
		}
	}
}
