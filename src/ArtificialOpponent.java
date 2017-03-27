import java.util.ArrayList;
public class ArtificialOpponent {
	String playerColour;
	String playerName;
	String last;
	Piece AIPiece;
	GamePieces pieces = new GamePieces();
	Observer observer = new Observer();
	CurrentState currentState = new CurrentState();
	Driver d;
	
	String move = null;
	
	public ArtificialOpponent(){
		playerColour = "Black";
		playerName = "AI";
	}
	
	public String getAIColour(){
		return playerColour;
	}
	
	public void setColour(String colour){
		playerColour = colour;
	}
	
	public Piece getPiece(){
		String lastCol;
		if(observer.getCurrentState().getLastLandedOn().equals("Default")){
			Square last = observer.getCurrentState().getLastLandedOn(); //gets the last landed on square
			lastCol = last.getColour(); //gets the colour of that square
		}
		else{
			lastCol = "Green";
		}
		String AiId = null;
		Piece p[] = pieces.getPieces();
		for(Piece i: p){
			if(i.getColour().equals(lastCol)){
				AIPiece = pieces.getPiece(i.getID());
				if(AIPiece.getID().startsWith(playerColour)){
					AiId = i.getID(); //get id of piece that has same colour as last landed on square
					AIPiece = pieces.getPiece(AiId);
				}
			}
		}
		return AIPiece;
	}
	
	public boolean possibleMoves(){
//		Boolean forwardBlocked = false;
//		Boolean leftDiagonalBlocked = false;
//		Boolean rightDiagonalBlocked = false;
		
		
		int x = 0;
		int y = 0;
		
		//find piece belonging to AI that is same colour as last landed on square
		
		
		getPiece();
		Coordinates co = new Coordinates();
		co.stringToXY(AIPiece.getPiecePosition()); //get coordinates of correct AI piece
		String forwardSquare = co.XYtoString(x, y + 1); //AI move forward
		String leftDiagonalSquare = co.XYtoString(x - 1, y + 1); //AI move left diagonal
		String rightDiagonalSquare = co.XYtoString(x + 1, y + 1); //AI move right diagonal
		
		if(d.checkMove(AIPiece.getPiecePosition(), forwardSquare, playerColour)){ //if forward move is okay, do that
			move = forwardSquare;
			return true;
		}
		else if(d.checkMove(AIPiece.getPiecePosition(), leftDiagonalSquare, playerColour)){ //if not and left diagonal is, do that
			move = leftDiagonalSquare;
			return true;
		}
		else if(d.checkMove(AIPiece.getPiecePosition(), rightDiagonalSquare, playerColour)){ //if not and right is, do that
			move = rightDiagonalSquare;
			return true;
		}
		else{
			System.out.println("The AI couldn't make any legal moves."); //else return message and move on
			return false;
		}
			
	}
	
	public String getMove(){
		//possibleMoves();
		return move;
	}
}
