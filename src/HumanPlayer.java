import java.util.ArrayList;

public class HumanPlayer extends PlayersInterface {
	String playerColour;
	String playerName;
	
	public HumanPlayer(){
		playerName = "Default";
		playerColour = "Default";
	}
	
	public void setName(String name){
		playerName = name;
	}
	
	public String getName(){
		return playerName;
	}
	
	public void setColour(String colour){
		playerColour = colour;
	}
	
	public String getColour(){
		return playerColour;
	}
		
}