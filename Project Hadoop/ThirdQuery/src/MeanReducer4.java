import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class MeanReducer4 extends Reducer <Text, Text, Text, Text> {
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		
		ArrayList<Double> arrlist1 = new ArrayList<Double >();
		ArrayList<Integer> arrlist2 = new ArrayList<Integer>();
		for(Text val:values) {
			 String[] arr= val.toString().split(",");
			 arrlist1.add(Double .parseDouble(arr[0]));
			 arrlist2.add(Integer.parseInt(arr[1]));
			  
		}
		Double sum =0.0;
		Integer count=0;
		for(int i = 0;i<(arrlist1.size());i++) {
			Double  num  =arrlist1.get(i);
			sum+=num;
			count+=arrlist2.get(i);
		}
		Double mean =  sum/count;
		String s = String.valueOf(mean);
		
		context.write(new Text(""),new Text(s));
	}
}
