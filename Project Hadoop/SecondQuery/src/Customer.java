
public class Customer implements Comparable<Customer>{
	String id;
	String age;
	String education;
	String marital_status;
	Double income;
	Integer mntwines;
	
	public Customer(String s) {
		 String[] arr = s.split(",");
		 id=arr[0];
		 age = arr[1];
		 education=arr[2];
		 marital_status=arr[3];
		 income = Double.parseDouble(arr[4]);
		 mntwines=Integer.parseInt(arr[5]);
	}
	public String toString() {
		return id+","+age+","+education+","+marital_status+","+String.valueOf(income)+","+String.valueOf(mntwines);
	}
	@Override
	public int compareTo(Customer o) {
		
		if(this.mntwines-o.mntwines>0) {
			return -1;
		}
		else if(this.mntwines-o.mntwines<0) {
			return 1;
		}
		else{
			if(this.income-o.income>0) {
				return -1;
			}
			else if(this.income-o.income<0) {
				return 1;
			}
			return 0;
		}
	}
	
}
