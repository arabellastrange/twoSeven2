
public class CurrentState {
	Settings timer;
	String[] positions;
	CurrentState currentState;
	CurrentState savedState;
	Square lastLandedOn = new Square("Default", "Defualt", false);
	GamePieces pieces;
	Board board;
	
	public CurrentState(){
		timer = new Settings();
		pieces = new GamePieces();
		board = new Board();
	}
	
	public boolean gameOver(Piece p){
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
	
	public Square getLastLandedOn(){
		return lastLandedOn;
	}
}