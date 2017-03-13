
public class Player {

	CurrentState now = new CurrentState();
	String playerColour;
	String playerName;
	String movingPiece;
	String movedTo;
	Square lastLandedOn;
	Coordinates co = new Coordinates();
	public void getStats(){
		
	}
	
	public void setName(String name){
		playerName = name;
	}
	
	public void setColour(String colour){
		playerColour = colour;
	}
	
	public String getColour(){
		return playerColour;
	}
	
	public boolean makeMove(String fromPiece, String toSquare, String playerColour){
		now.getCurrentState();
		Piece piece = now.getCurrentState().getPieces().getPiece(fromPiece);
		movedTo = toSquare;
		movingPiece = piece.getPlayerColour();
		if(movingPiece.equals(playerColour)){
			if(movingPiece.equals(lastLandedOn.getColour())){
				if(toSquare.isEmpty()){
					if(co.isMoveForward(piece.getPiecePosition(), movedTo, movingPiece)){
						lastLandedOn.setSquareColour(movedTo);
						board.gameOver(board);
						return true;
					}
					else{
						return false;
					}
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
}
