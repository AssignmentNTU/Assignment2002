package Movie;

import java.io.Serializable;
import java.util.ArrayList;

public class RatedMovie implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String movieTitle;	
	private double rating;
	private ArrayList<Movie> listMovieRated = new ArrayList<Movie>();
	
	//getter and setter for the listMovieRated
	public void addMovieList(Movie movie){
		//checking whether the movie has been in the list or not
		for(int i = 0 ; i < listMovieRated.size() ; i++){
			if(listMovieRated.get(i).getTitle().equals(movie.getTitle())){
				return;
			}
		}
		listMovieRated.add(movie);
	}
	
	public ArrayList<Movie> getRatedMovie(){
		return listMovieRated;
	}
	
	public void removeMovie(Movie movie){
		listMovieRated.remove(movie);
	}
	
	public Movie getMovieWithIndex(int index){
		return listMovieRated.get(index);
	}
	
	public void updateMovieRating(int index,int rating){
		Movie movie = listMovieRated.get(index);
		movie.setRating(rating);
		//System.out.println("test rating: "+listMovieRated.get(index).getRating());
	}
	
	public void updateMovieTicket(String movieName){
		Movie bufferMovie = null;
		for(int i = 0 ; i < listMovieRated.size() ; i++){
			if(listMovieRated.get(i).getTitle().equals(movieName)){
				bufferMovie = listMovieRated.get(i);
				break;
			}
		}
		if(bufferMovie!= null){
			bufferMovie.increaseTicket();
		}
	}
	
	
	public void printTopFiveMovieBasedOnTicket(){
		//insertion sort to sort the movie  
		for(int i = 1 ; i < listMovieRated.size() ; i++){
			for(int j = i ; j > 0 ;j--){
				double first = listMovieRated.get(j).getTicketSales();
				double second = listMovieRated.get(j-1).getTicketSales();
				if(first < second){
					//perform swap process
					Movie buffer = listMovieRated.get(j);
					listMovieRated.set(j, listMovieRated.get(j-1));
					listMovieRated.set(j-1,buffer);
				}
			}
		}
		for(int i = 0 ; i< listMovieRated.size() && i <5 ;i++){
			System.out.println(i+1+")"+"Title: "+listMovieRated.get(i).getTitle()+" Rating: "+listMovieRated.get(i).getRating());
		}
	}
	
	public void printTopFiveMovieBasedOnRating(){
		//insertion sort to sort the movie  
		for(int i = 1 ; i < listMovieRated.size() ; i++){
			for(int j = i ; j > 0 ;j--){
				double first = listMovieRated.get(j).getRating();
				double second = listMovieRated.get(j-1).getRating();
				if(first < second){
					//perform swap process
					Movie buffer = listMovieRated.get(j);
					listMovieRated.set(j, listMovieRated.get(j-1));
					listMovieRated.set(j-1,buffer);
				}
			}
		}
		for(int i = 0 ; i< listMovieRated.size() && i <5 ;i++){
			System.out.println(i+1+")"+"Title: "+listMovieRated.get(i).getTitle()+" Rating: "+listMovieRated.get(i).getRating());
		}
	}
	
}
