import java.util.ArrayList;
public class AIPlayer {
	String AIColour;
	String playerName;
	
	public AIPlayer(){
		AIColour = "B";
		playerName = "AI";
	}
	   
	public String getAIColour(){
		return AIColour;
	}
	
	public void setColour(String colour){
		AIColour = colour;
	}
	
}
