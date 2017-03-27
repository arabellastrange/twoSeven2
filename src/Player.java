import java.util.ArrayList;

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
	public boolean Load(){
		return d.Load();
	}
	public void storeSettings(ArrayList<String> settings){
		d.storeSettings(settings);
	}
	
	public ArrayList<String> getSettings(){
		return d.getSettings();
	}
	
	public boolean makeMove(String fromPiece, String toSquare){
		return d.checkMove(fromPiece, toSquare, getColour());
	}
	
	public boolean UndoMove(){
		return d.Undo();
	}
		
}