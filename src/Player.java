
public class Player {

	CurrentState board = new CurrentState();
	String playerColour;
	String playerName;
	String movingPiece;
	String movedTo;
	Square lastLandedOn;
	Coordinates co;
	public void getStats(){
		
	}
	
	public void setName(String name){
		playerName = name;
	}
	
	public void setColour(String colour){
		playerColour = colour;
	}
	
	public boolean makeMove(Piece fromPiece, String toSquare, String playerColour){
		board.getCurrentState();
		movedTo = toSquare;
		movingPiece = fromPiece.getPlayerColour();
		if(movingPiece.equals(playerColour)){
			if(movingPiece.equals(lastLandedOn.getColour())){
				if(toSquare.isEmpty()){
					if(co.isMoveForward(fromPiece.getPiecePosition(), movedTo, movingPiece)){
						lastLandedOn.setSquareColour(movedTo);
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
