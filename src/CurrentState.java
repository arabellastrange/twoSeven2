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
	ArrayList<String> gamesSettings = new ArrayList();
	Stack<CurrentState> states = new Stack<CurrentState>();
	Coordinates co = new Coordinates();
	Settings timer;
	Square lastLandedOn; 	
	GamePieces pieces;
	Board board;
	String AImove;
	//AIPlayer AI;
	
	public CurrentState(){
		lastLandedOn = new Square("Default", "Defualt", false);
		timer = new Settings();
		pieces = new GamePieces();
		board = new Board();
		//AI = new AIPlayer();

	}
	
	public void createState(){
		new CurrentState();
	}
	
	public CurrentState getState(){
		return this;
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
	
	public void setCurrentState(Settings time, Board boards, GamePieces piecesUsed, ArrayList<String> Settings, Square last){
		timer = time;
		board = boards;
		pieces = piecesUsed;
		gamesSettings = Settings;
		lastLandedOn = last;
		
		
	}
	public boolean undoMove(){
		try{
			states.pop();
			states.pop();
			setCurrentState(states.peek().getTime(), states.peek().getBoard(), states.peek().getPieces(), states.peek().getSettings(), states.peek().getLastLandedOn());
			return true;
		}
		catch(Exception e){
			System.out.println("Not enough moves to undo");
			return false;
		}
	}
	public Piece getFreePiece(String AIColour){
		String lastCol;
		Square last = getLastLandedOn(); //gets the last landed on square
		lastCol = last.getColour(); //gets the colour of that square

		if(lastCol.equals("Default")){
			Piece p[] = getPieces().getPieces();
			for(Piece i: p){
				if(AIColour.equals("B")){
					if(i.getID().startsWith("B")){
						if(i.getColour().equals("Red")){
							return i;
						}
					}
				}	
				else{
					if(i.getID().startsWith("W")){
						if(i.getColour().equals("Red")){
							return i;
						}
					}
				}
			}
			return null;
		}
		else{
			Piece p[] = getPieces().getPieces();
			for(Piece i: p){
				if(AIColour.equals("B")){
					if(i.getID().startsWith("B")){
						if(i.getColour().equals(lastCol)){
							return i;
						}
					}
				}
				else{
					if(i.getID().startsWith("W")){
						if(i.getColour().equals(lastCol)){
							return i;
						}
					}
				}
			}
			return null;				
		}
	}
	
	public boolean makeAIMove(String AIColour){
		String forwardSquare;
		String leftDiagonalSquare;
		String rightDiagonalSquare;
		
		Piece free = getFreePiece(AIColour);
		co.stringToXY(free.getPiecePosition());
		int x = co.getX();
		int y = co.getY();
		//find piece belonging to AI that is same colour as last landed on square
		if(AIColour.equals("B")){
			forwardSquare = co.XYtoString(x, y + 1); //AI move forward
			leftDiagonalSquare = co.XYtoString(x - 1, y + 1); //AI move left diagonal
			rightDiagonalSquare = co.XYtoString(x + 1, y + 1); //AI move right diagonal
		}
		else{
			forwardSquare = co.XYtoString(x, y - 1); //AI move forward
			leftDiagonalSquare = co.XYtoString(x + 1, y - 1); //AI move left diagonal
			rightDiagonalSquare = co.XYtoString(x - 1, y - 1); //AI move right diagonal
		}
		
		if(makeMove(free.getID(), forwardSquare, AIColour)){ //if forward move is okay, do that
			AImove = forwardSquare;
			return true;
		}
		else if(makeMove(free.getID(), leftDiagonalSquare, AIColour)){ //if not and left diagonal is, do that
			AImove = leftDiagonalSquare;
			return true;
		}
		else if(makeMove(free.getID(), rightDiagonalSquare, AIColour)){ //if not and right is, do that
			AImove = rightDiagonalSquare;
			return true;
		}
		else{
			System.out.println("The AI couldn't make any legal moves."); //else return message and move on
			return false;
		}
			
	}
	
	public boolean makeHardAIMove(String AIColour){
		String forwardSquare = "";
		String leftDiagonalSquare = "";
		String rightDiagonalSquare = "";
		
		Piece free = getFreePiece(AIColour);
		co.stringToXY(free.getPiecePosition());
		int x = co.getX();
		int y = co.getY();
		
		if(AIColour.equals("B")){
			forwardSquare = co.moveDown(free.getPiecePosition());
			leftDiagonalSquare = co.moveDiagonalLeftDown(free.getPiecePosition());
			rightDiagonalSquare = co.moveDiagonalRightDown(free.getPiecePosition());
			
			Square movedForward = getBoard().getStringSquare(forwardSquare);
			Square movedL = getBoard().getStringSquare(leftDiagonalSquare);
			Square movedR = getBoard().getStringSquare(rightDiagonalSquare);
			
			int i = co.getY();
			int j = co.getX(); 
			
			while(i < (6 - co.getY())){
				boolean empty = movedForward.isEmpty();
				if(empty){
					co.stringToXY(forwardSquare);
					x = co.getX();
					y = co.getY();
					forwardSquare = co.XYtoString(x, y + 1); //AI move forward
					movedForward = getBoard().getStringSquare(forwardSquare);
				}
			}
			while(movedL.isEmpty()  && i < (6 - co.getY()) && j >= (6 - co.getX())){
				co.stringToXY(leftDiagonalSquare);
				x = co.getX();
				y = co.getY();
				leftDiagonalSquare = co.XYtoString(x - 1, y + 1); //AI move left diagonal
				movedL = getBoard().getStringSquare(leftDiagonalSquare);
			}
			while(movedR.isEmpty()  && i < (6 - co.getY()) && j < (6 - co.getX())){
				co.stringToXY(rightDiagonalSquare);
				x = co.getX();
				y = co.getY();
				rightDiagonalSquare = co.XYtoString(x + 1, y + 1); //AI move right diagonal
				movedR = getBoard().getStringSquare(rightDiagonalSquare);
			}
		}
		else{
			forwardSquare = co.moveUp(free.getPiecePosition());
			leftDiagonalSquare = co.moveDiagonalLeftUp(free.getPiecePosition());
			rightDiagonalSquare = co.moveDiagonalRightUp(free.getPiecePosition());
			
			Square movedForward = getBoard().getStringSquare(forwardSquare);
			Square movedL = getBoard().getStringSquare(leftDiagonalSquare);
			Square movedR = getBoard().getStringSquare(rightDiagonalSquare);
			
			int i = co.getY();
			int j = co.getX();
			
			while(movedForward.isEmpty()  && i > (6 - co.getY())){
				co.stringToXY(forwardSquare);
				x = co.getX();
				y = co.getY();
				forwardSquare = co.XYtoString(x, y - 1); //AI move forward
			}
			while(movedL.isEmpty()  && i > (6 - co.getY()) && j < (6 - co.getX())){
				co.stringToXY(leftDiagonalSquare);
				x = co.getX();
				y = co.getY();
				leftDiagonalSquare = co.XYtoString(x + 1, y - 1); //AI move left diagonal
			}
			while(movedR.isEmpty()  && i > (6 - co.getY()) && j >= (6 - co.getX())){
				co.stringToXY(rightDiagonalSquare);
				x = co.getX();
				y = co.getY();
				rightDiagonalSquare = co.XYtoString(x - 1, y - 1); //AI move right diagonal
			}
		}
		
		if(makeMove(free.getID(), forwardSquare, AIColour)){ //if forward move is okay, do that
			AImove = forwardSquare;
			return true;
		}
		else if(makeMove(free.getID(), leftDiagonalSquare, AIColour)){ //if not and left diagonal is, do that
			AImove = leftDiagonalSquare;
			return true;
		}
		else if(makeMove(free.getID(), rightDiagonalSquare, AIColour)){ //if not and right is, do that
			AImove = rightDiagonalSquare;
			return true;
		}
		else{
			System.out.println("The AI couldn't make any legal moves."); //else return message and move on
			return false;
		}
		
	}
	
	public void storeSettings(ArrayList<String> settings){
		gamesSettings = settings;
	}
	
	public ArrayList<String> getSettings(){
		return gamesSettings;
	}
	public String getAIMove(){
		return AImove;
	}
	
	public boolean makeMove(String fromPiece, String toSquare, String pColour){
		if(checkInRange(fromPiece, toSquare)){
			Square last = getLastLandedOn();
			Square movedTo = getBoard().getStringSquare(toSquare);
			GamePieces pieces = getPieces();
			Piece piece = pieces.getPiece(fromPiece);
			char movingPieceID = piece.getID().charAt(0);
			String movingPieceColour = piece.getColour();
			
			//System.out.println(movedTo.empty);
			
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
					if(movedTo.isEmpty()){
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