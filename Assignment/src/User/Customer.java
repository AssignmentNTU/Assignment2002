package User;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Customer implements Serializable{	

	private static final long serialVersionUID = 1L;
	
	private String name;
	private int phoneNumber;
	private String emailAdd = "";
	private String transactionId = "";
	private int age;
	private String seat;
	
	public Customer(String name, int phoneNumber, String emailAdd){
		
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.emailAdd = emailAdd;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public int getPhoneNumber() {
		
		return phoneNumber;
		
	}
	
	public String getEmailAdd() {
		
		return emailAdd;
		
	}
	
	public boolean confirmCustomerInfo(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Your name: " + this.name);
		System.out.println("Your phone number: " + Integer.toString(this.phoneNumber));
		System.out.println("Your email address: " +  this.emailAdd);
		System.out.println("To confirm your info and proceed, enter 'y'.");
		String confirm = sc.next();
		return confirm.equals("y") || confirm.equals("Y");
		
	}

	public String getTransactionId(){
		
		transactionId += name.substring(0,3).toUpperCase();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		Calendar calendar = Calendar.getInstance();
		
		transactionId += dateFormat.format(calendar.getTime());
		
		return transactionId;
		
	}
	
	public void setAge(int age){
		
		this.age = age;
		
	}

	public int getAge(){
		
		return age;
		
	}
	
	public void assignSeat(String seat){
		
		this.seat = seat;
		
	}
	
	public String getSeat(){
		
		return seat;
		
	}
	
}
