package Movie;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import Interface.Database;

public class RatedMovieDatabase implements Database{
	
	public static void main(String[] args){
		RatedMovieDatabase database = new RatedMovieDatabase();
		String fileName = "RatedMovie.dat";
		ArrayList listMovie = new ArrayList<RatedMovie>();
		//example for movie rating 
		Movie first = new Movie("The walk",10);
		Movie second= new Movie("Last Witch Hunter",10);
		RatedMovie ratedmovie = new RatedMovie();
		ratedmovie.addMovieList(first);
		ratedmovie.addMovieList(second);
		listMovie.add(ratedmovie);
		database.writeToDatabase(fileName, listMovie);
		//after put it into the database try to read it
		/*
		while(true){
			ArrayList readMovie = database.readFromDatabase(fileName);
			RatedMovie ratedMovie = (RatedMovie) readMovie.get(0);
			ArrayList<Movie> listMovieRated = ratedMovie.getRatedMovie();
		
			System.out.println("which one you want to rate:");
			Scanner scan = new Scanner(System.in);
			int index = scan.nextInt(); 
			System.out.println("Add your rating here: ");
			int rating = scan.nextInt();
			ratedMovie.updateMovieRating(index-1, rating);
			Movie test = ratedMovie.getMovieWithIndex(0);
			RatedMovie ratedMovie1 = (RatedMovie) readMovie.get(0);
			//System.out.println("name: "+ratedMovie1.getMovieWithIndex(0).getTitle()+" rating: "+ratedMovie1.getMovieWithIndex(0).getRating());
			database.writeToDatabase(fileName, readMovie);
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
