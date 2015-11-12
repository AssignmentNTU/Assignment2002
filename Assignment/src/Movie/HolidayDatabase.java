package Movie;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Interface.Database;

public class HolidayDataBase implements Database{
	
	public static void main(String args[]){
		System.out.println("Start constructing the original database for holiday date");
		HolidayDataBase holidayDatabase = new HolidayDataBase();
		DateMovie holiday1 = new DateMovie(2012,10,10);
		DateMovie holiday2 = new DateMovie(2014,10,10);
		ArrayList dateList = new ArrayList<DateMovie>();
		dateList.add(holiday1);
		dateList.add(holiday2);
		String fileName = "HolidayDatabase.dat";
		holidayDatabase.writeToDatabase(fileName,dateList);
		System.out.println("Finish constructing the original database for holiday date");
		//trying to retrieve data 
		/*
		ArrayList<DateMovie> list = holidayDatabase.readFromDatabase(fileName);
		for(int i = 0 ; i < list.size() ; i++){
			System.out.println(list.get(i).getYearMonthDay());
		}
		 */
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
