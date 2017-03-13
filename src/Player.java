public class Player {
	CurrentState board = new CurrentState();
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
		board.getCurrentState();
		Piece piece = board.getCurrentState().getPieces().getPiece(fromPiece);
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
						System.out.println("You cannot move in this direction - only straight and diagonally forward.");
						return false;
					}
				}
				else{
					System.out.println("You cannot put your piece here as the square is already occupied.");
					return false;
				}
			}
			else{
				System.out.println("You cannot move this piece as it is not the same colour as the last landed on square.");
				return false;
			}
		}
		else{
			System.out.println("This is not one of you pieces.");
			return false;
		}
	}
}
