import java.util.ArrayList;
public class ArtificialOpponent {
	String AIColour;
	String playerName;
	String last;
	GamePieces pieces = new GamePieces();
	Observer observer = new Observer();
	CurrentState currentState = new CurrentState();
	Coordinates co = new Coordinates();
	Driver d;
	
	String move = null;
	
	public ArtificialOpponent(){
		AIColour = "B";
		playerName = "AI";
	}
	   
	public String getAIColour(){
		return AIColour;
	}
	
	public void setColour(String colour){
		AIColour = colour;
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
		String AiId;
		Piece p[] = pieces.getPieces();
		for(Piece i: p){
			if(i.getID().startsWith("B")){
				if(i.getColour().equals(lastCol)){
					return i;
				}
			}
		}
		return null;
	}
	
	public boolean possibleMoves(){
		co.stringToXY(getPiece().getPiecePosition());
		int x = co.getX();
		int y = co.getY();
		//find piece belonging to AI that is same colour as last landed on square
		String forwardSquare = co.XYtoString(x, y + 1); //AI move forward
		String leftDiagonalSquare = co.XYtoString(x - 1, y + 1); //AI move left diagonal
		String rightDiagonalSquare = co.XYtoString(x + 1, y + 1); //AI move right diagonal
		
		if(d.checkMove(getPiece().getPiecePosition(), forwardSquare, AIColour)){ //if forward move is okay, do that
			move = forwardSquare;
			return true;
		}
		else if(d.checkMove(getPiece().getPiecePosition(), leftDiagonalSquare, AIColour)){ //if not and left diagonal is, do that
			move = leftDiagonalSquare;
			return true;
		}
		else if(d.checkMove(getPiece().getPiecePosition(), rightDiagonalSquare, AIColour)){ //if not and right is, do that
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
