package User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.omg.CORBA.DynAnyPackage.TypeMismatch;

import Cinema.Cinema;
import Cineplex.Cineplex;
import Cineplex.CineplexDatabase;
import Movie.DateMovie;
import Movie.Movie;
import Movie.RatedMovie;
import Movie.RatedMovieDatabase;

public class UserApplication implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final int MAIN_MENU = 0;
	private static final int CINEPLEX_INTERFACE = 1;
	private static final int MOVIE_INTERFACE_FOR_SELECTED_CINEPLEX = 2;
	private static final int BOOKING_MAIN_INTERFACE = 3;
	private static final int RATING_INTERFACE = 4;
	private static final int SELECT_TIMING_INTERFACE = 5;
	private static final int SELECT_AGE_GROUP_INTERFACE = 6;
	private static final int SELECT_SEAT = 7;
	private static final int MOVIE_INTERFACE = 8;
	private static final int CHECK_MOVIE_INTERFACE = 9;
	
	private static CineplexDatabase userDatabase =  new CineplexDatabase();	
	private static ArrayList <Cineplex> cineplexList = userDatabase.readFromDatabase("CineplexDatabase.dat");
	private static Scanner sc = new Scanner(System.in);		
	private static ArrayList <Movie> movieListForSpecificCineplex = new ArrayList<Movie>();
	private static Customer customer;
	private static ArrayList<DateMovie> movieScheduleList = new ArrayList<DateMovie>();
	private static Movie movieChosen = new Movie();
	private static RatedMovieDatabase ratedMovieDatabase = new RatedMovieDatabase();
	private static ArrayList readMovie = ratedMovieDatabase.readFromDatabase("RatedMovie.dat");
	private static RatedMovie ratedMovie = (RatedMovie)readMovie.get(0);
	private static ArrayList<Customer> customerList = new ArrayList<Customer>();
	
	public static void main(String[] args) {			
		
		boolean carryOn = true;
		
		while(carryOn){
			
			int option = requestInput(MAIN_MENU);
			
			System.out.flush();
			
			switch(option){
				
				case 1:
					
					listMovies(false, 0);					
					break;
					
				case 2:
					
					listMovies(true, 0);
					break;
				
				case 3:
					ArrayList<String> searchResult = new ArrayList<String>();
					
					String searchMovie = "";
					
					System.out.println("Enter movie title: ");
					
					sc.nextLine();
					searchMovie = sc.nextLine();
					System.out.flush();
					for (int i = 0; i < cineplexList.size(); i++){
						
						searchResult = searchCineplex(searchMovie, cineplexList.get(i));
						if (searchResult.size() == 0){
							
							System.out.println(cineplexList.get(i).getCineplexName() + ":  No movie to show. \n");
							
						}
						else{
							
							for(int j = 0; j < searchResult.size(); j++){
								
								System.out.println(searchResult.get(j));
								
							}
							
						}
						System.out.println("");
						
					}
					
					break;
					
				case 4:	
					int cineplexChoice = 0;
					requestInput(BOOKING_MAIN_INTERFACE);
					if (customer.confirmCustomerInfo())
						cineplexChoice = requestInput(CINEPLEX_INTERFACE);
					else {
						
						System.out.println("Cancelled");
						break;
						
					}
						
					movieListForSpecificCineplex = cineplexList.get(cineplexChoice - 1).getMovieList();
					
					int movieChoice = requestInput(MOVIE_INTERFACE_FOR_SELECTED_CINEPLEX);					
					
					movieChosen = movieListForSpecificCineplex.get(movieChoice - 1);
					
					movieScheduleList = movieChosen.getArrayListOfDateMovie();
					
					int timingChoice = requestInput(SELECT_TIMING_INTERFACE);			
					
					int ageGroup = requestInput(SELECT_AGE_GROUP_INTERFACE);
					double ticketPrice = movieChosen.getPrice(ageGroup);				
					
					requestInput(SELECT_SEAT);		
					
					System.out.println("Ticket price is SGD " + ticketPrice + "."
							+ " Proceed to payment? (y / n)");
					
					String confirmation = sc.next();
					
					if(confirmation.equals("y") || confirmation.equals("Y"))
						displayTicket(timingChoice);				
					
					break;
				
				case 5:
					System.out.println("Select a movie to rate: ");
					requestInput(MOVIE_INTERFACE);
					break;
					
				case 6:
					requestInput(CHECK_MOVIE_INTERFACE);
					break;
					
				case 7:
					System.exit(0);					

			}
			
		}		
		
	}	
	
	private static void displayTicket(int timingChoice) {
			
		System.out.println("Transaction ID: " + customer.getTransactionId());		
		System.out.println("Your seat: " + customer.getSeat());
		System.out.println("Showing cinema: " + movieChosen.getArrayListOfDateMovie().get(0).getCinema().getCinemaName());
		System.out.println("Showing time: " + movieScheduleList.get(timingChoice - 1).getTime());
		System.out.println("\n");
		
	}

	private static int requestInput(int userInterface){
		
		switch(userInterface){
		
			case MAIN_MENU: 
				System.out.println("Choose one option:\n"
						+ "1) List the movies available\n"
						+ "2) List the movies with details\n"
						+ "3) Search for movies\n"
						+ "4) Book ticket\n"
						+ "5) Rate and see the top movie\n"
						+ "6) View your booking\n"
						+ "7) Exit\n");
				
				//input check
				while(true){   
				    if(sc.hasNextInt()){   
				    	int choice = sc.nextInt();
				    	if(choice > 0 && choice <= 7)
				    		return choice;
				    	else
				    		continue;
				    }
				    sc.nextLine(); 
				    System.out.println("Invalid Option, choose again: ");
				}
				

			case CINEPLEX_INTERFACE:
				System.out.println("Choose cineplex:");
				
				for (int i = 0; i < cineplexList.size(); i++)
				{
					
					System.out.printf("%d) ", i + 1);
					System.out.println(cineplexList.get(i).getCineplexName());
					
				}
				
				//input check
				while(true){   
				    if(sc.hasNextInt()){   
				    	int choice = sc.nextInt();
				    	if(choice > 0 && choice <= cineplexList.size() + 1)
				    		return choice;
				    	else
				    		continue;
				    }
				    sc.nextLine(); 
				    System.out.println("Invalid Option, choose again: ");
				}
								
				
			case MOVIE_INTERFACE_FOR_SELECTED_CINEPLEX:
				System.out.println("Choose movie:");
				
				for (int i = 0; i < movieListForSpecificCineplex.size(); i++){
				
					System.out.printf("%d) ", i + 1);
					System.out.println(movieListForSpecificCineplex.get(i).getTitle());
				
				}
				
				//input check
				while(true){   
				    if(sc.hasNextInt()){   
				    	int choice = sc.nextInt();
				    	if(choice > 0 && choice <= movieListForSpecificCineplex.size() + 1)
				    		return choice;
				    	else
				    		continue;
				    }
				    sc.nextLine(); 
				    System.out.println("Invalid Option, choose again: ");
				}				
				
				
			case MOVIE_INTERFACE:				
				//print all the movie from ratedmovie
				
				System.out.println("1) Top 5 Movie based on Rating");
				System.out.println("2) Top 5 movie based on Ticket Sales");
				System.out.println("3) Rate the movie");
				//input check
				int inputchoice;
				while(true){   
				    if(sc.hasNextInt()){   
				    	inputchoice = sc.nextInt();
				    	if(inputchoice > 0 && inputchoice <= 3)
				    		break;
				    	else
				    		continue;
				    }
				    sc.nextLine(); 
				    System.out.println("Invalid Option, choose again: ");
				}	
				switch(inputchoice){
				case 1:
					ratedMovie.printTopFiveMovieBasedOnRating();
					break;
				case 2:
					ratedMovie.printTopFiveMovieBasedOnTicket();
					break;
				case 3:
					ratedMovie.printTopFiveMovieBasedOnRating();
					System.out.println("Enter movie name: ");
					int movieRatedOption = sc.nextInt();
					System.out.println("Your rating (1 - 10): ");
					double rating = sc.nextDouble();
					ratedMovie.updateMovieRating(movieRatedOption, rating);
					
					break;
				}
				ratedMovieDatabase.writeToDatabase("RatedMovie.dat",readMovie);
				System.out.println();
				return 0;
				
				
			case BOOKING_MAIN_INTERFACE:
				String customerEmailAdd;
				int customerPhoneNumber;
				System.out.println("Please enter your name: ");
				String customerName = sc.next();				
				System.out.println("Please enter your phone number: ");
				do {
					
					customerPhoneNumber = sc.nextInt();
					
					if(customerPhoneNumber / 10000000 == 8 
							|| customerPhoneNumber / 10000000 == 9)
						break;
					
					else System.out.println("Invalid phone number, enter again: ");;
					
				} while (true);
				System.out.println("Please enter your e-mail address: ");
				do{					
					
					customerEmailAdd = sc.next();
					
					if(validateEmailAdd(customerEmailAdd) == false)						
						System.out.println("Invalid email address, enter again: ");						
					
					else break;
					
				}while (true);
				customer = new Customer(customerName, customerPhoneNumber, customerEmailAdd);
				customerList.add(customer);
				return 0;
				
			case RATING_INTERFACE:
				System.out.println("Select movie from list below to rate: ");
				listMovies(false, 0);
				System.out.print("Your selection: ");
				
				
				return 0;
				
			case SELECT_TIMING_INTERFACE:
				System.out.println("Choose timing:");
				
				for (int i = 0; i < movieScheduleList.size(); i++){
				
					System.out.printf("%d) ", i + 1);
					System.out.println(movieScheduleList.get(i).getTime());
				
				}
				
				//input check
				while(true){   
				    if(sc.hasNextInt()){   
				    	int choice = sc.nextInt();
				    	if(choice > 0 && choice <= movieScheduleList.size() + 1)
				    		return choice;
				    	else
				    		continue;
				    }
				    sc.nextLine(); 
				    System.out.println("Invalid Option, choose again: ");
				}	
				
				
			case SELECT_AGE_GROUP_INTERFACE:
				System.out.println("Select your age group:");
				
				System.out.println("1) Child (12 and below)"
						+ "\n2) Adult (13-64)"
						+ "\n3) Senior Citizen (65 and above)");			
				
				
				//input check
				while(true){   
				    if(sc.hasNextInt()){   
				    	int choice = sc.nextInt();
				    	if(choice > 0 && choice <= 4)
				    		return choice;
				    	else
				    		continue;
				    }
				    sc.nextLine(); 
				    System.out.println("Invalid Option, choose again: ");
				}							
				
			case SELECT_SEAT:
				Cinema showingCinema = movieChosen.getArrayListOfDateMovie().get(0).getCinema();
				boolean success = true;
				System.out.println(showingCinema.getSeatArrangement());
				System.out.println("Select seat from cinema layout above: ");
				System.out.print("Row (A - J): ");
				String row = sc.next();
				System.out.print("Column (1 - 10): ");
				int column = sc.nextInt();
				
				while(!showingCinema.requestSeat(row, column)){
					
					System.out.println("Seat taken, please select again");
					System.out.print("(To cancel, enter 'z') Row (A - J): ");
					row = sc.next();
					if(row.equals("z")){
						success = false;
						break;
					}
					System.out.print("Column (1 - 10): ");
					column = sc.nextInt();
					
				}
				
				if (success == true){
					
					System.out.println("Successfully booked! movie name: "+movieChosen.getTitle());	
					customer.assignSeat(row + Integer.toString(column));
					ratedMovie.updateMovieTicket(movieChosen.getTitle());
					customer.addToBookedMovie(movieChosen.getTitle());
					
				}
				
				else {
					
					System.out.println("Cancelled");
					
				}
				
				
				
				return 0;
				
			case CHECK_MOVIE_INTERFACE:
				System.out.println("Enter your email address: ");
				do{					
					
					customerEmailAdd = sc.next();
					
					if(validateEmailAdd(customerEmailAdd) == false)						
						System.out.println("Invalid email address, enter again: ");						
					
					else break;
					
				}while (true);
				
				Customer currentCustomer = null;
				
				for(int i = 0; i < customerList.size(); i++){
					
					if(customerList.get(i).getEmailAdd().equals(customerEmailAdd)){
						
						currentCustomer = customerList.get(i);
						break;
						
					}
					
				}
				
				ArrayList<String> temp = currentCustomer.getBookedMovieList();
				String currentTransactionId = currentCustomer.getTransactionId();
				
				printBookedMovie(temp, currentTransactionId);
				
				return 0;
				
			default: return 0;
				
		}
		
	}
	
	private static void printBookedMovie(ArrayList<String> temp, String txnid) {
		
		System.out.println("Your booked movie(s): ");
		
		for (int i = 0; i < temp.size(); i++){
			
			System.out.printf("%d: %s\t", i+1, temp.get(i));
			System.out.printf("Transaction ID: %s\n", txnid);
		}		
		
	}

	private static void listMovies(boolean withDetails, int cineplex){
		
		if (cineplex == 0) {
			
			for (int i = 0; i < cineplexList.size(); i++) {
				
				System.out.println(cineplexList.get(i).getCineplexName() + ":");

				cineplexList.get(i).listMovie(withDetails);

				System.out.println();

			} 
			
		} else {
				
			cineplexList.get(cineplex - 1).listMovie(withDetails);
			
			System.out.println();
			
		}
		
	}
	
	private static ArrayList<String> searchCineplex(String title, Cineplex cineplex){
		
		ArrayList<String> result = new ArrayList<String>();
		
		movieListForSpecificCineplex = cineplex.getMovieList();
		
		for(int i = 0; i < movieListForSpecificCineplex.size(); i++){
			Movie curMovie = new Movie();
			curMovie = movieListForSpecificCineplex.get(i);
			if((curMovie.getTitle().toLowerCase()).equals(title.toLowerCase())){
				
				result.add(cineplex.getCineplexName() + ":  " 
						+ "\n" + curMovie.getTitle() 
						+ "\nPrice: " + Double.toString(curMovie.getPrice(0))
						+ "\nDuration: " + curMovie.getDuration());
				
			}
			
		}
		
		return result;
		
	}
	
	private static boolean validateEmailAdd(String email){
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
		
	}
	
}
