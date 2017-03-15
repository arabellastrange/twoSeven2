public class Player {
	Observer observer = new Observer();
	CurrentState now = observer.getCurrentState();
	String playerColour;
	String playerName;
	String movingPiece;
	String movedTo;
	Square lastLandedOn = new Square("Default", "Defualt");
	Coordinates co = new Coordinates();
	
	public Player(){
		playerName = "Default";
		playerColour = "Default";
	}
	
	public void getStats(){
		
	}
	
	public void setName(String name){
		playerName = name;
	}
	
	public String getName(){
		return playerName;
	}

	
	public void setColour(String colour){
		playerColour = colour;
	}
	
	public String getColour(){
		return playerColour;
	}
	
	public boolean makeMove(String fromPiece, String toSquare, String playerColour){
		Piece piece = now.getPieces().getPiece(fromPiece);
		movedTo = toSquare;
		movingPiece = piece.getID().substring(0,0);
		if(movingPiece.equals(getColour()) && movingPiece.equals(playerColour)){
			if(movingPiece.equals(lastLandedOn.getColour())){
				if(toSquare.isEmpty()){
					if(co.isMoveForward(piece.getPiecePosition(), movedTo, movingPiece)){
						lastLandedOn.setSquareColour(movedTo);
						//board.gameOver(board);
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