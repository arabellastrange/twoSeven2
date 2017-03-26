import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class CurrentState implements Serializable{
	Stack<CurrentState> states = new Stack<CurrentState>();
	ArrayList<String> gamesSettings = new ArrayList();
	Settings timer;
	String[] positions;
	Square lastLandedOn = new Square("Default", "Defualt", false);
	GamePieces pieces;
	Board board;
	
	public CurrentState(){
		timer = new Settings();
		pieces = new GamePieces();
		board = new Board();
	}
	
	public boolean gameOver(Piece p){
		states.add(this);
		if(p.getID().startsWith("W") && p.getPiecePosition().charAt(1) == 0){
			return true;
		}
		else if(p.getID().startsWith("B") && p.getPiecePosition().charAt(1) == 7){
			return true;
		}
		else{
			return false;
		}
	}
		
	public Piece getPiece(String id){
		return pieces.getPiece(id);
	}
	
	public GamePieces getPieces(){
		return pieces;
	}
	
	public Board getBoard(){
		return board;
	}
	
	public Settings getTime(){
		return timer;
	}
	
	public void setCurrentState(Settings time, Board boards, GamePieces piecesUsed){
		Settings timer = time;
		Board board = boards;
		GamePieces pieces = piecesUsed;
		
	}
	public void saveCurrentState(){
		CurrentState savedState = new CurrentState();
		savedState.setCurrentState(timer, this.getBoard(), this.getPieces());
		try{
			File save = new File("savedGame.txt");
			save.createNewFile();
			FileOutputStream fout = new FileOutputStream(save, false); 
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(savedState);  
			out.flush();
			System.out.println("File saved successfully");
		}
		catch(IOException e){
			System.out.println("Output Stream failed");
		} 
	}
	
	public void storeSettings(ArrayList<String> settings){
		gamesSettings = settings;
	}
	
	public ArrayList<String> getSettings(){
		return gamesSettings;
	}
	public void loadCurrentState(){
		CurrentState savedState = new CurrentState();
		try{
			ObjectInputStream in= new ObjectInputStream(new FileInputStream("savedGame.txt"));
			savedState = (CurrentState) in.readObject();
			this.setCurrentState(savedState.getTime(), savedState.getBoard(), savedState.getPieces());
		}
		catch(IOException e){
			System.out.println("Input Stream failed");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
		}
		  
	}
	public Square getLastLandedOn(){
		return lastLandedOn;
	}
	public void undo(){
		try{
			states.pop();
			states.pop();
		}
		catch(Exception e){
			System.out.println("Not enough moves to undo");
		}
	}
	
}