
public class CurrentState {

	double timer;
	Board board; 
	GamePieces pieces;
	CurrentState currentState;
	CurrentState savedState;
	
	public CurrentState(){
		timer = 0;
		board = new Board();
		pieces = new GamePieces();
		
	}
	
	public boolean gameOver(CurrentState currentState){
		return true;
	}
	
	public void setCurrentState(GamePieces gamePieces, Board board, double timer){
		
	}
	
	public CurrentState getCurrentState(){
		return currentState;
	}
	
	public double getTime(){
		return timer;
	}
	
	public GamePieces getPiece(String colour, int id){
		return pieces;
	}
	
	public Board getBoard(){
		return board;
	}
	
	public void saveCurrentState(CurrentState currentState){
		savedState = currentState;
	}
}
