import java.io.Serializable;
import java.util.ArrayList;
public class AIPlayer implements Serializable{
	String AIColour;
	String playerName;
	boolean currentlyPlaying;
	String status;
	int points;
	
	public AIPlayer(){
		AIColour = "B";
		playerName = "AI";
		currentlyPlaying = false;
		status = "Player";
	}
	   
	public String getAIColour(){
		return AIColour;
	}
	
	public void setColour(String colour){
		AIColour = colour;
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
