package Staff;

import java.awt.List;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Interface.Database;

public class DatabaseStaff implements Database{
	
	
	public static void main(String args[]){
		System.out.println("Start constructing original database for User");
		ArrayList list = new ArrayList<Staff>();
		Staff newStaff = new Staff("EdwardSujono","12345");
		list.add(newStaff);
		String filename = "staff.dat";
		DatabaseStaff dbs = new DatabaseStaff();
		dbs.writeToDatabase(filename, list);
		System.out.println("Finish constructing original database for User");
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
	public ArrayList<Staff> readFromDatabase(String filename) {
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
