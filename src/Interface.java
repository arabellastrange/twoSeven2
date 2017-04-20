import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Interface {
	Settings set = new Settings();
	//AIPlayer AI = new AIPlayer();
	ReadWrite save = new ReadWrite();
	Observer observer = new Observer();

	public ArrayList<String> loadGame(){
		if(save.loadCurrentState()){
			observer.createState();	
		}
		return observer.getCurrentState().getSettings();
	}
	
	public boolean playAI(String piece, String square, String playColour){
		System.out.println("AI mode piece from interface " + piece);
		System.out.println("AI mode square from interface " + square);
		String AIMove = "";
		String AIPiece = "";
		if(observer.getCurrentState().makeAIMove(observer.getCurrentState().getPlayer(0).getColour())){
			AIMove = observer.getCurrentState().getAIMove();
			AIPiece = observer.getCurrentState().getFreePiece(observer.getCurrentState().getPlayer(0).getColour()).getID();
			return true;
		}
		return false;
	}
	
	
	public boolean playHardAI(String piece, String square, String playColour){
		String AIMove = "";
		String AIPiece = "";
		if(observer.getCurrentState().makeHardAIMove(observer.getCurrentState().getPlayer(0).getColour())){
			AIMove = observer.getCurrentState().getAIMove();
			AIPiece = observer.getCurrentState().getFreePiece(observer.getCurrentState().getPlayer(0).getColour()).getID();
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean playTimed(String piece, String square, String playColour, double timeLength){
		System.out.println("timed mode piece from interace " + piece);
		System.out.println("timed mode square from interface " + square);
		System.out.println(set.getTime());
		if(set.getTime() >= timeLength){
			set.clearTimer();
			return false;
		}
		else{
			if(observer.getCurrentState().makeMove(piece, square, playColour)){
				set.clearTimer();
				return true;
			}
		}
		return false;
	}
	
	public boolean play(String piece, String square, String playColour){
		if(observer.getCurrentState().makeMove(piece, square, playColour)){
			return true;
		}
		else{
			return false;
			//back to original square?
		}
	}
	
//	public boolean isGameOver(){
//		return observer.getCurrentState().gameOver();
//	}
	
	public void store(ArrayList<String> settings){
		observer.getCurrentState().storeSettings(settings);
		save.saveCurrentState();
	}
}
