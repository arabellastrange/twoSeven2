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
	
	public void Save(){
		d.Save();
	}
	public void Load(){
		d.Load();
	}
	public boolean makeMove(String fromPiece, String toSquare){
		return d.checkMove(fromPiece, toSquare, getColour());
	}
		
}