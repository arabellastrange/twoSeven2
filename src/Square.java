public class Square {
	String colour;
	static String position;
	GamePieces gamePiece;
	
	public Square(String pieceColour, String piecePosition){
		colour = pieceColour;
		position = piecePosition;
	}
	
	public String getColour(){
		return colour;
	}
	
	public static String getSquarePosition(){
		return position; 
	}
	
	public void setSquareColour(String squareColour){
		colour = squareColour;
	}
	
	public boolean isEmpty(){
		String pos = Square.getSquarePosition();
		if(gamePiece.getPiece(pos) == null){
			return false;
		}
		else{
			return true;	
		}	
	}
}
