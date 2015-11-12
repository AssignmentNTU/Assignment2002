package Movie;

import java.io.Serializable;
import java.util.ArrayList;

import Cinema.Cinema;
import Cineplex.Cineplex;

public class Movie implements Serializable{
	
	private String title = "";
	private String duration="";
	private String ratingPG;
	private double totalRating = 0; 			//control by user not staff
	private int counterRating = 0 ;
	private double price = 0;
	private String genre = "";
	private String director = "";
	private ArrayList<String> castList = new ArrayList<String>();
	private String synopsis = "";
	private ArrayList<DateMovie> scheduleMovie = new ArrayList<DateMovie>();
	private int totalTicketSales = 0 ;
	private String review = "";
	private static HolidayDataBase holidayDatabase = new HolidayDataBase();
	@SuppressWarnings("unchecked")
	private static ArrayList<DateMovie> listHoliday = holidayDatabase.readFromDatabase("HolidayDatabase.dat");
	private String notificationMessage = "";
	
	
	public static void main(String args[]){
		/*
		Movie movie = new Movie();
		movie.setSynopsis("Twelve people have walked on the moon, but only one man has ever, or will ever, walk in"
				+ " the immense void between the World Trade Center towers."
				+ " Guided by his real-life mentor, Papa Rudy (Ben Kingsley), and aided "
				+ "by an unlikely band of international recruits,"
				+ " Petit and his gang overcome long odds, betrayals, dissension and "
				+ "countless close calls to conceive and execute their mad plan.");
		ArrayList<String> list = new ArrayList<String>();
		list.add("first");
		list.add("second");
		movie.setCastList(list);
		System.out.println(movie.printSynopsis());
		System.out.println(movie.printCast());
		*/
		System.out.println(listHoliday.size());
	}
	//constrouctor for testing
	public Movie(){
		
	}
	
	public Movie(String title){
		this.title = title;
	}
	
	public Movie(String title, float rating){
		this.title = title;
		this.totalRating = rating;
		this.counterRating++;
	}
	
	//constructor for 
	
	
	
	public Movie(String title, String duration , String ratingPG , String genre,
			DateMovie datemovie,double price,String director,ArrayList<String> listCast,String synopsis){
		this.title = title;
		this.duration = duration;
		this.ratingPG = ratingPG;
		this.genre = genre;
		this.price = price;
		scheduleMovie.add(datemovie);
		this.director = director;
		this.synopsis = synopsis;
		this.castList = listCast;
	}
	
	//getter and setter for director 
	public void setDirector(String director){
		this.director = director;
	}
	
	public String getDirector(){
		return director;
	}
	//getter and setter for ticket sales
	public void increaseTicket(){
		totalTicketSales++;
	}
	
	public int getTicketSales(){
		return totalTicketSales;
	}
	
	
	//getter and setter for synopsis
	
	public void setSynopsis(String synopsis){
		this.synopsis = synopsis;
	}
	
	public String getSynopsis(){
		return synopsis;
	}
	
	public String printSynopsis(){
		StringBuilder strBuild = new StringBuilder();
		boolean pass = false;
		int counter = 0;
		for(int i = 0 ; i < synopsis.length() ; i++){
			if(i > 20 && i % 30 < 10 && synopsis.charAt(i) == ' ' && counter == 0){
				strBuild.append("\n");
				pass = true;
			}
			if(pass){
				counter++;
			}
			if(counter > 10){
				counter = 0;
				pass  = false;
			}
			strBuild.append(synopsis.charAt(i));
		}
		return  strBuild.toString();
	}
	
	
	//getter and setter for cast
	
	public void setCastList(ArrayList<String> castList){
		this.castList =  castList;
	}
	
	public ArrayList<String> getCastList(){	
		return castList;
	}
	
	public String printCast(){
		String cast ="";
		for(int i = 0 ; i < castList.size() ; i++){
			cast+=i+1+") ";
			cast+= castList.get(i)+"\n"; 
		}
		return cast;
	}
	
	//add cast to the castList
	
	public void addCastToCastList(String cast){
		castList.add(cast);
	}
	
	public void RemoveCastFromCastList(int index){
		castList.remove(index);
	}
	
	//getter and setter for title
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	//getter and setter for duration
	
	public void setDuration(String duration){
		this.duration = duration;
	}
	
	public String getDuration(){
		return duration;
	}
	
	//getter and setter for revliew
	
	public void setReview(String review){
		this.review = review;
	}
	
	public String getReview(){
		return review;
	}
	
	//getter and setter for rating 
	
	public void setRating(double rating){
		this.totalRating+= rating;
		this.counterRating++;
	}
	
	public double getRating(){
		//total point divide by the total voter
		double averageRating = totalRating/counterRating;
		return averageRating;
	}
	
	
	public int getCounterRating(){
		return this.counterRating;
	}
	
	//getter and setter for genre
	
	public void setGenre(String genre){
		this.genre = genre;
	}
	
	public String getGenre(){
		return genre;
	}
	
	//getter and setter for date
	
	public void setTimeStatus(DateMovie datemovie){
		this.scheduleMovie.add(datemovie);
	}
	
	
	
	//getter and setter for the rating of parental guidance
	public void setRatingPG(String ratingPG){
		this.ratingPG = ratingPG;
	}
	
	public String getRatingPG(){
		return ratingPG;
	}
	
	//getter and setter for price

	//getter and setter for price
	public void setPrice(double price){
		this.price = price;
	}
	
	public double getNormalPrice(){
		return price;
	}
	
	public void notificationMessage(){
		System.out.println(notificationMessage);
		notificationMessage= "";
	}
	
	public double getPrice(int ageGroup,DateMovie dateMovie){
		//get the cinema if the movie is played in the platinum teater the price will be increase by $5 
		int increasePrice = 0;
		Cinema cinemaChoosen = dateMovie.getCinema();
		if(cinemaChoosen.getTheaterType().equals("Platinum")){
			notificationMessage+="It is platinum cinema, the price will be increase by 5 dollars\n";
			increasePrice += 5;
		}
		
		//check the public holiday

		DateMovie first = new DateMovie(dateMovie.getYear(),dateMovie.getMonth(),dateMovie.getDay());
		for(int i = 0 ; i < listHoliday.size();i++){
			if(first.getYear() == listHoliday.get(i).getYear() && first.getMonth() == listHoliday.get(i).getMonth() 
					&& first.getDay() == listHoliday.get(i).getDay()){
				notificationMessage+="It is public Holiday, the price will be increase by 2 dollars\n";
				increasePrice+=2;
				break;
			}
		}
		
		
		//if the movie is 3D then the price will be increase too
		if(this.genre.equals("3D")){
			notificationMessage+="This movie is 3D movie, the price will be increase by 2 dollars\n";
			increasePrice+=2;
		}
		
		switch (ageGroup){
			case 1:
				break;
			case 3:
				System.out.println("Senior citizen, the price will be reduced by 2 dollars");
				increasePrice-=2;
				break;
			default: break;
		
		}
		return price+increasePrice;
	}
	
	
	
	
	//getting array of dateMovie
	public ArrayList<DateMovie> getArrayListOfDateMovie(){
		return scheduleMovie;
	}
	
	/* printting function */
	///////////////////////////////////////////////////////////////////
	
	public void getListDateStatusMovie(){
		//to display the schedule first we are going to sorted the schedule first 
		//sorting using insertion sort
		
		for(int i = 1 ; i < scheduleMovie.size() ; i++){
			for(int j = i ; j > 0 ; j--){
				DateMovie buffer;
				if(scheduleMovie.get(j).compareTo(scheduleMovie.get(j-1)) == -1){
					buffer = scheduleMovie.get(j);
					scheduleMovie.set(j, scheduleMovie.get(j-1));
					scheduleMovie.set(j-1,buffer);
				}else{
					break;
				}
			}
		}
		for(int i = 0 ; i < scheduleMovie.size() ;i++){
			String statusTime = scheduleMovie.get(i).getStatusTimeMovie();
			System.out.printf("%d) %s\n",i+1,statusTime);
		}
	}
	
	//without rating
	public String printDescription(){
		String movieDescription = "Title Movie: "+this.title+"\n"
								   +"Duration: "+this.duration+" min\n"
								   +"RatingPG: "+this.ratingPG+"\n"
								   +"Genre: "+this.genre+"\n"
								   +"Price: "+"$"+this.price+"\n"
								   +"Director: "+this.director+"\n"
								   +"cast: \n"+printCast()+"\n"
								   +"Synopsis: \n"+printSynopsis()+"\n"
								   +"Schedule Movie: \n\n";
		String listTime = "";
		//to display the schedule first we are going to sorted the schedule first 
		//sorting using insertion sort
		for(int i = 1 ; i < scheduleMovie.size() ; i++){
			for(int j = i ; j > 0 ; j--){
				DateMovie buffer;
				if(scheduleMovie.get(j).compareTo(scheduleMovie.get(j-1)) == -1){
					buffer = scheduleMovie.get(j);
					scheduleMovie.set(j, scheduleMovie.get(j-1));
					scheduleMovie.set(j-1,buffer);
				}else{
					break;
				}
			}
		}
		
		for(int i = 0 ; i < scheduleMovie.size() ; i++){
			if(i >= 1){
				DateMovie current = scheduleMovie.get(i);
				DateMovie before = scheduleMovie.get(i-1);
				if(!((current.getYear() == before.getYear()) && (current.getMonth() == before.getMonth()) 
						&& (current.getDay() == before.getDay()))){
					String bannerDate = "----------"+current.getYearMonthDay()+"----------\n";
					listTime+=bannerDate;
				}
			}else{
				String bannerDate = "----------"+scheduleMovie.get(0).getYearMonthDay()+"----------\n";
				listTime+=bannerDate;
			}
			listTime += scheduleMovie.get(i).getStatusTimeMovie()+"\n";
		}
		
		movieDescription+=listTime;
		return movieDescription;
	}
	
	//with rating
	public String printFullDescription(){	
			String movieDescription = printDescription();
			movieDescription+="\nRating: "+this.totalRating+"\n";
		
		return movieDescription;
	}

	
	public String printMovieListingUser(){
		String movieDescription = "Title Movie: "+this.title+"\n"
				   +"Duration: "+this.duration+" min\n"
				   +"RatingPG: "+this.ratingPG+"\n"
				   +"Genre: "+this.genre+"\n"
				   +"Price: "+"$"+this.price+"\n"
				   +"Director: "+this.director+"\n"
				   +"cast: \n"+printCast()+"\n"
				   +"Synopsis: \n"+printSynopsis()+"\n"
				   +"Schedule Movie: \n\n";
	
	
		return movieDescription;
	}
	
	
	
	//////////////////////////////////
}
