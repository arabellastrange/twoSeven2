import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class CurrentState implements Serializable{
	//make/ save players?? otherwise what is the point??
	Stack<CurrentState> states = new Stack<CurrentState>();
	Coordinates co = new Coordinates();
	Settings timer;
	Square lastLandedOn = new Square("Default", "Defualt", false);
	GamePieces pieces;
	Board board;
	
	public CurrentState(){
		timer = new Settings();
		pieces = new GamePieces();
		board = new Board();
	}
	
	public boolean gameOver(Piece p){
		states.add(this);
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
		
	public Piece getPiece(String id){
		return pieces.getPiece(id);
	}
	
	public GamePieces getPieces(){
		return pieces;
	}
	
	public Board getBoard(){
		return board;
	}
	
	public Settings getTime(){
		return timer;
	}
	
	public void setCurrentState(Settings time, Board boards, GamePieces piecesUsed){
		timer = time;
		board = boards;
		pieces = piecesUsed;
		
	}
	public boolean undoMove(){
		try{
			states.pop();
			states.pop();
			this.setCurrentState(states.peek().getTime(), states.peek().getBoard(), states.peek().getPieces());
			return true;
		}
		catch(Exception e){
			System.out.println("Not enough moves to undo");
			return false;
		}
	}
	
	public boolean makeMove(String fromPiece, String toSquare, String pColour){
		if(checkInRange(fromPiece, toSquare)){
			Square last = getLastLandedOn();
			Square movedTo = getBoard().getStringSquare(toSquare);
			Piece piece = getPieces().getPiece(fromPiece);
			char movingPieceID = piece.getID().charAt(0); 
			String movingPieceColour = piece.getColour();
			
			if(movingPieceID == pColour.charAt(0)){
				if(last.getColour().equals("Default")){
					if(movedTo.isEmpty()){
						if(co.isMoveForward(piece.getPiecePosition(), movedTo.getSquarePosition(), movingPieceID)){
							getLastLandedOn().setSquareColour(movedTo.getColour());
							getLastLandedOn().setOccupied();
							getBoard().getStringSquare(toSquare).setOccupied();
							getBoard().getStringSquare(piece.getPiecePosition()).clear();
							piece.setPiecePosition(toSquare);
							if(gameOver(piece)){
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
							getLastLandedOn().setSquareColour(movedTo.getColour());
							getLastLandedOn().setOccupied();
							getBoard().getStringSquare(toSquare).setOccupied();
							getBoard().getStringSquare(piece.getPiecePosition()).clear();
							piece.setPiecePosition(toSquare);
							if(gameOver(piece)){
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
	
	public Square getLastLandedOn(){
		return lastLandedOn;
	}
	
	// this should probs be in a diff class tbh 
	public Boolean checkInRange(String piece, String square){
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