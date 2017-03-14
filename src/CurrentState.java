
public class CurrentState {
	Timer timer;
	String[] positions;
	CurrentState currentState;
	CurrentState savedState;
	Piece piece;
	Square lastLandedOn;
	GamePieces pieces;
	Board board;
	
	public CurrentState(){
		timer = new Timer();
		pieces = new GamePieces();
		board = new Board();
	}
	
	public boolean gameOver(CurrentState currentState){
		if(piece.getPlayerColour().equals("White") && piece.getPiecePosition().startsWith("A")){
			return true;
		}
		else if(piece.getPlayerColour().equals("Black") && piece.getPiecePosition().startsWith("H")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void setCurrentState(GamePieces gamePieces, Board board){
		double time = timer.getTime();
		for(int i = 0; i < 16; i++){
			getPositions();
		}
		
	}
	
	public void getPositions() {
		int m = 0;
		Piece p[] = pieces.getPieces();
		for(Piece i: p){
			positions[m] = i.getPiecePosition();
			m++;
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
	
	public void saveCurrentState(CurrentState currentState){
		savedState = currentState;
	}
}