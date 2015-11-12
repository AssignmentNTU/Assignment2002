package User;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Interface.Database;

public class CustomerDatabase implements Database{

	public static void main(String[] args){
		System.out.println("Start constructing database for customer");
		CustomerDatabase customerDatabase = new CustomerDatabase();
		String filename = "CustomerDatabase.dat";
		ArrayList customerList = new ArrayList<>();
		customerDatabase.writeToDatabase(filename,customerList);
		System.out.println("Finish constructing database for Customer");
	}
	
	@Override
	public void writeToDatabase(String filename, ArrayList<Object> list) {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try{
			fos = new FileOutputStream(filename);
			bos = new BufferedOutputStream(fos);
			ObjectOutputStream os = new ObjectOutputStream(bos);
			os.writeObject(list);
			os.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

	@Override
	public ArrayList readFromDatabase(String filename) {
		ArrayList returnedList = null;
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		try{
			fis = new FileInputStream(filename);
			bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			returnedList = (ArrayList)ois.readObject();
			ois.close();
		}catch(IOException e){
			
		} catch (ClassNotFoundException e) {
			
		}
		return returnedList;
	}

}
