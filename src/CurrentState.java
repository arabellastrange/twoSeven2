
public class CurrentState {

	double timer;
	public CurrentState(){
		timer = 0;
	}
	
	public boolean gameOver(CurrentState currentState){
		return true;
	}
	
	public void setCurrentState(GamePieces gamePieces, Board board, double timer){
		
	}
	
	public CurrentState getCurrentState(){
		return CurrentState;
	}
	
	public double getTime(){
		return timer;
	}
	
	public Piece getPiece(String colour, int id){
		return Piece;
	}
	
	public Board getBoard(int x, int y){
		return square;
	}
	
	public void saveCurrentState(CurrentState currentState){
		CurrentState savedState = currentState;
	}
}
