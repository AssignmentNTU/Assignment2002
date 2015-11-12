package Cineplex;

import java.io.Serializable;
import java.util.ArrayList;

import Cinema.Cinema;
import Movie.DateMovie;
import Movie.Movie;

//Each of cineplex will have a list of cinema

public class Cineplex implements Serializable{
	
	private String cineplexName="";
	private int cinemaSize = 0;
	private ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();
	private ArrayList<Movie> movieList = new ArrayList<Movie>();
	public ArrayList<Movie> ratingMovieList = new ArrayList<Movie>();

	//we have 2 option here using 1 more movie list again or 
	//constructor
	
	public Cineplex(){
		
	}
	
	public Cineplex(String cineplexName){
		this.cineplexName = cineplexName;
	}
	
	//end of constructor
	
	//add and remove from movieRating
	public  ArrayList<Movie> getMovieRatingList(){
		return ratingMovieList;
	}
	
	public void addMovieForRating(Movie movie){
		for(int i = 0 ; i < ratingMovieList.size() ; i++){
			if(movie.getTitle().equals(ratingMovieList.get(i).getTitle())){
				System.out.println("returning");
				return;
			}
		}
		ratingMovieList.add(movie);
	}
	
	public Movie getMovieRating(int index){
		return ratingMovieList.get(index);
	}
	
	public void addRatingForSpecificMovie(int index,double rating){
		ratingMovieList.get(index).setRating(rating);
	}
	
	public void removeMovieForRating(Movie movie){
		ratingMovieList.remove(movie);
	}
	
	public void topFiveMovieBasedOnRating(){
		if(ratingMovieList.size() > 1){
			for(int i = 1 ; i < ratingMovieList.size() ; i++){
				double rating = ratingMovieList.get(i-1).getRating();
				double rating1 = ratingMovieList.get(i).getRating();
				if(rating < rating1){
					Movie bufferMovie = ratingMovieList.get(i-1);
					ratingMovieList.set(i-1,ratingMovieList.get(i));
					ratingMovieList.set(i, bufferMovie);
				}
			}
		}
		for(int i = 0 ; i < ratingMovieList.size() && i <= 5 ; i++){
			System.out.printf("%d) Title: %s\n,  Rating: %f",i+1,this.ratingMovieList.get(i).getTitle(),this.getMovieRating(i).getRating());
		}
	}
	
	//add and remove  movie 
	public void addMovie(Movie movie){
		movieList.add(movie);
	}
	
	public ArrayList<Movie> getMovieList(){
		return movieList;
	}
	
	public Movie getMovie(int index){
		return movieList.get(index);
	}
	
	public void removeMovie(Movie movie){
		movieList.remove(movie);
	}
	//end of add and remove movie
	
	
	
	public String getCineplexName(){
		return cineplexName;
	}
	
	public ArrayList<Cinema> getCinemaList(){
		return cinemaList;
	}
	
	
	public void addCinema(Cinema cinema){
		cinemaList.add(cinema);
	}
	
	public Cinema getCinema(int index){
		return cinemaList.get(index);
	}
	
	public void RemoveCinema(Cinema cinema){
		for(int i = 0 ; i < cinemaList.size() ; i++){
			if(cinemaList.get(i).equals(cinema)) {
				cinemaList.remove(i);
				break;
			}
		}
	}

	public static void printMovieForUser(ArrayList<Movie> bufferMoviePreview){
		for(int i = 0 ; i < bufferMoviePreview.size()  ;i++){
			System.out.println("---------------------------------------------------");
			System.out.println(i+1+")");
			System.out.println(bufferMoviePreview.get(i).printMovieListingUser());
			System.out.print("\nStatus of Movie: ");
			String status = bufferMoviePreview.get(i).getArrayListOfDateMovie().get(0).getStatus();
			if(!status.equals("End of Showing")){
				status = "Now Showing";
			}
			System.out.println(status);	
			System.out.println("---------------------------------------------------");
		}
	}
	
	
	public void listMovie(){
		
		if(movieList.size() == 0) return;
		{
			for (int i = 0; i < movieList.size(); i++){
				
				System.out.printf("%d) ", i + 1);
				System.out.println(movieList.get(i).getTitle());
				
			}
		}	
	}
	
	
	
	
	
	
}
