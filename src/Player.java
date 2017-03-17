public class Player {
	Observer observer = new Observer();
	CurrentState now = observer.getCurrentState();
	String playerColour;
	String playerName;
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
	
	public boolean makeMove(String fromPiece, String toSquare){
		Square lastLandedOn = new Square("Default", "Defualt", false);
		Square movedTo = now.getBoard().getStringSquare(toSquare);
		Piece piece = now.getPieces().getPiece(fromPiece);
		char movingPieceID = piece.getID().charAt(0); 
		String movingPieceColour = piece.getColour();
		
		if(movingPieceID == getColour().charAt(0)){
			if(lastLandedOn.getColour().equals("Default")){
				if(movedTo.isEmpty()){
					if(co.isMoveForward(piece.getPiecePosition(), toSquare, movingPieceID)){
						lastLandedOn.setSquareColour(movedTo.getColour());
						lastLandedOn.setOccupied();
						now.getBoard().getStringSquare(toSquare).setOccupied();
						//clear old square
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
		}
		else if(movingPieceColour.equals(lastLandedOn.getColour())){
			if(toSquare.isEmpty()){
				if(co.isMoveForward(piece.getPiecePosition(), toSquare, movingPieceID)){
					lastLandedOn.setSquareColour(movedTo.getColour());
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
		else if(!lastLandedOn.getColour().equals("Default") || !movingPieceColour.equals(lastLandedOn.getColour())){
				System.out.println("You cannot move this piece as it is not the same colour as the last landed on square.");
				return false;
			}
		else{
			System.out.println("This is not one of you pieces.");
			return false;
		}
		return false;
	}
}