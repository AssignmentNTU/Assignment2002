package Cinema;

import java.io.Serializable;
import java.util.ArrayList;

import Movie.Movie;
import Movie.Ticket;

public class Cinema implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	private String cinemaName = "";
	private String type = ""; 
	private ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
	private CinemaSeat[][] seatArray = new CinemaSeat[10][10];
	public int emptySeatLeft;
	private String theaterType;
	
	public static void main(String[] args) {
		Cinema cinema = new Cinema("");
		//debug to get the class type		
		Cinema cin = new Cinema();		
		System.out.println(cin.getSeatArrangement());
		
	}
	
	public Cinema(){		
		emptySeatLeft = 100;
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				
				seatArray[i][j] = new CinemaSeat(i + 1, j + 1, false);
			}		
			
		}
	}
	
	public Cinema(String cinemaName , String theaterType){
		this.cinemaName = cinemaName;
		this.theaterType = theaterType;
		
		emptySeatLeft = 100;
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				
				seatArray[i][j] = new CinemaSeat(i + 1, j + 1, false);
			}			
		}
	}
	
	

	
	public Cinema(String cinemaName){
		this.cinemaName = cinemaName;
		emptySeatLeft = 100;
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				seatArray[i][j] = new CinemaSeat(i + 1, j + 1, false);
			}		
			
		}					
	}
	
	//getter and setter for cinemaName
	
	public void setCinemaName(String cinemaName){
		this.cinemaName = cinemaName;
	}
	
	public String getCinemaName(){
		
		return this.cinemaName;
		
	}
	
	//getter and setter for the class type
	
	public void setCinemaClass(String theaterType){
		this.theaterType = theaterType;
	}
	
	
	public String getTheaterType(){
		return this.theaterType;
	}
	
			
	public String getSeatArrangement(){
		
		String result = "";
		int row = 0;
		int column = 0;
		
		result += "\t\t    ____________________________ \n"
				+ "\t\t   |           Screen           |\n"
				+ "\t\t   |____________________________|\n";
		
		result += "\n    ___________     ___________________________________     ___________\n";
				
		for (row = 0; row < 10; row++){			
			result += seatArray[row][column].getRow();
			
			for (int j = 0; j < 10; j++){
				
				if(j > 7){
					if (seatArray[row][j].isOccupied())
						result += "  | X ";					
					else
						result += "  |   ";
				}
				else if (j > 2 && j < 8){
					if (seatArray[row][j].isOccupied())
						result += "   | X";					
					else 
						result += "   |  ";
					if (j == 7)
						result += "   | ";
				}
				else{
					
					if (seatArray[row][j].isOccupied())
						result += "  |  X";					
					else 
						result += "  |   ";
					if (j == 2)
						result += "|  ";
					
				}
				
				
			}
			result += "  |";
			result += "\n   |_____|_____|   |_____|_____|_____|_____|_____|_____|   |_____|_____|\n";
			
		}
		
		result += "\n "
				+ "     1"
				+ "     2"
				+ "         3"
				+ "     4"
				+ "     5"
				+ "     6"
				+ "     7"
				+ "     8 "
				+ "       9"
				+ "      10";
		
		return result;
		
	}
		
	public boolean requestSeat(String row, int column){
		
		switch(row){
		
			case "A": 				
				if (seatArray[0][column -1].isOccupied()){
					return false;
				}
				seatArray[0][column -1].setOccupied(true);
				break;
			case "B": 
				if (seatArray[1][column -1].isOccupied()){
					return false;
				}
				seatArray[1][column -1].setOccupied(true);
				break;
			case "C": 
				if (seatArray[2][column -1].isOccupied()){
					return false;
				}
				seatArray[2][column -1].setOccupied(true);
				break;
			case "D": 
				if (seatArray[3][column -1].isOccupied()){
					return false;
				}
				seatArray[3][column -1].setOccupied(true);
				break;
			case "E": 
				if (seatArray[4][column -1].isOccupied()){
					return false;
				}
				seatArray[4][column -1].setOccupied(true);
				break;
			case "F": 
				if (seatArray[5][column -1].isOccupied()){
					return false;
				}
				seatArray[5][column -1].setOccupied(true);
				break;
			case "G": 
				if (seatArray[6][column -1].isOccupied()){
					return false;
				}
				seatArray[6][column -1].setOccupied(true);
				break;
			case "H": 
				if (seatArray[7][column -1].isOccupied()){
					return false;
				}
				seatArray[7][column -1].setOccupied(true);
				break;
			case "I": 
				if (seatArray[8][column -1].isOccupied()){
					return false;
				}
				seatArray[8][column -1].setOccupied(true);
				break;
			case "J": 
				if (seatArray[9][column -1].isOccupied()){
					return false;
				}
				seatArray[9][column -1].setOccupied(true);
				break;
				
		
		}
		
		
		return true;
		
	}
		
}
