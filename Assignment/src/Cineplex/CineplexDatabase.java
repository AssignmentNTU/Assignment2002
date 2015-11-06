package Cineplex;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import Cinema.Cinema;
import Interface.Database;
import Movie.DateMovie;
import Movie.Movie;

//Basically this class will make the database for Cineplex Class and read it 
//configure the initial database value from the Main method

public class CineplexDatabase implements Database{
	
	public static void main(String args[]){
		StartCineplex();
	}
	
	private static void StartCineplex(){
		//Change code in here to configure the database 
		//automatically configure 3 Cineplex from 1 company which is Cathat 
		ArrayList<Cineplex> listCineplex = new ArrayList<Cineplex>();
		
		//in this case cathay cineplex will be acts as vendor
		//inside cathay cineplex there will be several cineplex (3 or more in this case)
		//inside several cineplex there will have several cinema which is room theater 
		//so inside the room theater there must be have list of movie that will be shown there
		
		//in the first case we make 3 cineplex 
		Cineplex AMKHub = new Cineplex("AMKHub");
		Cineplex Jem    = new Cineplex("JEM");
		Cineplex WestMall = new Cineplex("West Mall");
		/********************************************AMKHub******************************************************/
		//each cineplex there has 3 cinema 
		Cinema amkHubFirst = new Cinema("Room 1","Reguler");
		Cinema amkHubSecond = new Cinema("Room 2","Velvet");
		Cinema amkHubThird = new Cinema("Room 3","Platinum");
		
		AMKHub.addCinema(amkHubFirst);
		AMKHub.addCinema(amkHubSecond);
		AMKHub.addCinema(amkHubThird);
		
		DateMovie walkDate = new DateMovie(amkHubFirst,"Not Showing",2015,12,10,12,20);
		ArrayList<String> castList = new ArrayList<String>();
		castList.add("Joseph Gordon-Levitt");
		castList.add("Ben Kingsley");
		Movie theWalk = new Movie("The walk","123 min","PG some intense sequence","Action",walkDate,10,"Robert Zemeckis",castList,
				"Twelve people have walked on the moon, but only one man has ever, or will ever, walk in the immense void between the World Trade Center towers."
				+ " Guided by his real-life mentor, Papa Rudy (Ben Kingsley), and aided by an unlikely band of international recruits,"
				+ " Petit and his gang overcome long odds, betrayals, dissension and countless close calls to conceive and execute their mad plan.");
		
		
		AMKHub.addMovie(theWalk);
		
		/*****************************************JEM**************************************************************/
		Cinema firstCinemaJem = new Cinema("Room1");
		Cinema secondCinemaJem = new Cinema("Room2");
		Cinema thirdCinemaJem = new Cinema("Room3");
				 
		Jem.addCinema(firstCinemaJem);
		Jem.addCinema(secondCinemaJem);
		Jem.addCinema(thirdCinemaJem);
		
		DateMovie LastWitchHunterDate = new DateMovie(firstCinemaJem,"Not Showing",2015,10,10,13,20);
		ArrayList<String> castList1 = new ArrayList<String>();
		castList.add("Vin Diesel");
		castList.add("Elijah Wood");
		Movie LastWitchHunter = new Movie("Last Witch Hunter","107 min","PG 13-some violence","Action",LastWitchHunterDate,10,"Breck Eisner",castList1,
				"Five hundred years ago, war raged between humankind and witches, vicious creatures that unleashed a Black Death upon the world."
				+ " Armies of witch hunters followed their leader, the great and mighty KAULDER, into a final battle that would bring the war to a close."
				+ " In a rage, Kaulder managed to kill the all-powerful QUEEN WITCH and capture the mysterious source of her power, the Plague Box. "
				+"However, in the moments before her death, the Queen cursed Kaulder with immortality. Forever separated from his beloved wife and daughter in the afterlife, "
				+ "Kaulder has spent centuries hunting down rogue witches, all the while yearning for his long-lost loved ones."
				+"Today, he is the only one of his kind—the last witch hunter. With the help of FATHER DOLAN, a Catholic priest, and a mystical arsenal hidden beneath New York’s St. "
				+ "Patrick’s Cathedral, he continues to pursue the renegades and outlaws who endanger humanity. Chief among their adversaries is BELIAL,"
				+ " a powerful rebel witch who Kaulder believes has found the long-hidden Plague Box and is preparing to use it to destroy humankind and restore the witches to power."
				+ " With the help of CHLOE, a goodhearted young witch, Kaulder sets out to stop Belial before he can recover the final element he needs to set his plan in motion. "
				+ "But the item Belial seeks is hidden in the last place anyone has thought to look…and seeming friends are not at all what they seem.");
		
		Jem.addMovie(LastWitchHunter);
		Jem.addMovie(LastWitchHunter);
		Jem.addMovie(LastWitchHunter);
		Jem.addMovie(LastWitchHunter);
		Jem.addMovie(LastWitchHunter);
		AMKHub.addMovie(LastWitchHunter);
		//implementation of the movie list will be added later by StaffApplication
		
		/*****************************************WestMall********************************************************/
		
		Cinema firstWestMall = new Cinema("Room1");
		Cinema secondWestMall = new Cinema("Room2");
		Cinema thirdWestMall = new Cinema("Room3");
		
		WestMall.addCinema(firstWestMall);
		WestMall.addCinema(secondWestMall);
		WestMall.addCinema(thirdWestMall);
		
		//implementation of the movie list will be added later by Staff Application
		
		listCineplex.add(AMKHub);
		listCineplex.add(Jem);
		listCineplex.add(WestMall);
		WestMall.addMovie(LastWitchHunter);
		WestMall.addMovie(LastWitchHunter);
		
		
		CineplexDatabase cDatabase =  new CineplexDatabase();
		System.out.println("size: "+listCineplex.size());
		cDatabase.writeToDatabase("CineplexDatabase.dat", listCineplex);
	
	}
	
	@Override
	public void writeToDatabase(String filename, ArrayList list) {
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
	public ArrayList<Cineplex> readFromDatabase(String filename) {
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
