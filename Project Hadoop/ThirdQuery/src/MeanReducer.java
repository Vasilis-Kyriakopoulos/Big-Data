import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class MeanReducer extends Reducer <Text, Text, Text, Text> {
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		
		ArrayList<Integer> arrlist = new ArrayList<Integer >();
		for(Text val:values) {
			 String s = val.toString();
			 arrlist.add(Integer.parseInt(s));
		}
		Integer sum =0;
		Integer count = arrlist.size();
		for(int i = 0;i<(arrlist.size());i++) {
			Integer num  =arrlist.get(i);
			sum+=num;
		}
		String s = String.valueOf(sum)+","+ count;
		context.write(new Text(""),new Text(s));
	}
}
