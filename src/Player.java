import java.io.Serializable;

public class Player implements Serializable{
	String playerColour;
	String playerName;
	boolean currentlyPlaying;
	String status;
	int points;
	
	public Player(){
		playerName = "Default";
		playerColour = "Default";
		currentlyPlaying = false;
		status = "Player";
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
	
	public void disablePlayer(){
		currentlyPlaying = false;
	}
	
	public void activatePlaye(){
		currentlyPlaying = true;
	}
	
	public boolean isActive(){
		return currentlyPlaying;
	}
	
	public void updateStatus(String newStatus){
		status = newStatus;
	}
	
	public void addPoints(int point){
		points += point;
	}

}
