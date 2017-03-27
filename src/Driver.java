import java.util.ArrayList;

public class Driver {
	Coordinates co = new Coordinates();
	Observer observer = new Observer();
	
	public void storeSettings(ArrayList<String> settings){
		observer.getCurrentState().storeSettings(settings);
	}
	public ArrayList<String> getSettings(){
		return observer.getCurrentState().getSettings();
	}
	public void Save(){
		observer.getCurrentState().saveCurrentState();
	}
	
	public boolean Load(){
		return observer.getCurrentState().loadCurrentState();
	}
	public boolean Undo(){
		return observer.getCurrentState().undoMove();
	}
	public boolean checkMove(String fromPiece, String toSquare, String pColour){
		if(checkValid(fromPiece, toSquare)){
			Square last = observer.getCurrentState().getLastLandedOn();
			Square movedTo = observer.getCurrentState().getBoard().getStringSquare(toSquare);
			Piece piece = observer.getCurrentState().getPieces().getPiece(fromPiece);
			char movingPieceID = piece.getID().charAt(0); 
			String movingPieceColour = piece.getColour();
			
			if(movingPieceID == pColour.charAt(0)){
				if(last.getColour().equals("Default")){
					if(movedTo.isEmpty()){
						if(co.isMoveForward(piece.getPiecePosition(), movedTo.getSquarePosition(), movingPieceID)){
							observer.getCurrentState().getLastLandedOn().setSquareColour(movedTo.getColour());
							observer.getCurrentState().getLastLandedOn().setOccupied();
							observer.getCurrentState().getBoard().getStringSquare(toSquare).setOccupied();
							observer.getCurrentState().getBoard().getStringSquare(piece.getPiecePosition()).clear();
							piece.setPiecePosition(toSquare);
							if(observer.getCurrentState().gameOver(piece)){
								System.out.println("Congrats! You won!");
								System.exit(0);
							}
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
				else if(movingPieceColour.equals(last.getColour())){
					if(toSquare.isEmpty()){
						if(co.isMoveForward(piece.getPiecePosition(), toSquare, movingPieceID)){
							observer.getCurrentState().getLastLandedOn().setSquareColour(movedTo.getColour());
							observer.getCurrentState().getLastLandedOn().setOccupied();
							observer.getCurrentState().getBoard().getStringSquare(toSquare).setOccupied();
							observer.getCurrentState().getBoard().getStringSquare(piece.getPiecePosition()).clear();
							piece.setPiecePosition(toSquare);
							if(observer.getCurrentState().gameOver(piece)){
								System.out.println("Congrats! You won!");
								System.exit(0);
							}
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
				else if(!last.getColour().equals("Default") || !movingPieceColour.equals(last.getColour())){
						System.out.println("You cannot move this piece as it is not the same colour as the last landed on square - " + last.getColour());
						return false;
				}
				else{
					System.out.println("This is not one of you pieces.");
					return false;
				}
				
			}
		}
		else{
			System.out.println("Piece or Square out of range");
			return false;
		}
		return false;
	}

	
	public Boolean checkValid(String piece, String square){
		if(Integer.parseInt(piece.substring(1)) > 8 || Integer.parseInt(piece.substring(1)) < 0){
			return false;
		}
		
		co.stringToXY(square);
		if(co.getY() > 8 || co.getY() < 0){
			return false;
		}
		
		return true;
	}


}

