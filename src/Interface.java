import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Interface {

	Settings set = new Settings();
	AIPlayer AI = new AIPlayer();
	ReadWrite save = new ReadWrite();
	Observer observer = new Observer();

	public ArrayList<String> loadGame(){
		if(save.loadCurrentState()){
			observer.getCurrentState().createState();
			return observer.getCurrentState().getSettings();
			}
		return null;
	}
	
	public void playAI(String piece, String square, String playColour){
		observer.getCurrentState().createState();
		if(observer.getCurrentState().makeMove(piece, square, playColour)){
			//updateInterface(piece, square);
		}
		else{
			//printInterface();
		}
		
		String AIMove = "";
		String AIPiece = "";
		if(observer.getCurrentState().makeAIMove(AI.getAIColour())){
			AIMove = observer.getCurrentState().getAIMove();
			AIPiece = observer.getCurrentState().getFreePiece().getID();
			//updateInterface(AIPiece, AIMove);
		}
	}
	
	public void playTimed(String piece, String square, String playColour, double timeLength){
		//deal with settings class
		observer.getCurrentState().createState();
		System.out.println(set.getTime());
		if(set.getTime() >= timeLength){
			set.clearTimer();
			System.out.println("You have run out of time. Player Two make a move.");
		}
		else{
			if(observer.getCurrentState().makeMove(piece, square, playColour)){
				//updateInterface(piece, square);
				set.clearTimer();
			}
		}
	}
	
	public void play(String piece, String square, String playColour){
		if(observer.getCurrentState().makeMove(piece, square, playColour)){
			//updateInterface(piece, square);
		}
		else{
			//printInterface();	
			//back to orignal square
		}
	}
	

	public void store(ArrayList<String> settings){
		observer.getCurrentState().storeSettings(settings);
		save.saveCurrentState();
	}
}
