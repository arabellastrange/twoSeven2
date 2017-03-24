public class Player {
	String playerColour;
	String playerName;
	Driver d = new Driver();
	
	public Player(){
		playerName = "Default";
		playerColour = "Default";
	}
	
	public void getStats(){
		
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
	
	public boolean makeMove(String fromPiece, String toSquare){
		return d.checkMove(fromPiece, toSquare, getColour());
	}
		
}