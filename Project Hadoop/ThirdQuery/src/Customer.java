
public class Customer implements Comparable<Customer>{
	String id;
	Double income;
	Integer date;
	Integer money;
	
	public Customer(String s) {
		 String[] arr = s.split(",");
		 id=arr[0];
		 income = Double.parseDouble(arr[1]);
		 date=Integer.parseInt(arr[2]);
		 money = Integer.parseInt(arr[3]);

	}
	
	@Override
	public int compareTo(Customer o) {
		return 0;
	}
	
}
