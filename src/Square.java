public class Square {
	String colour;
	static String position;
	GamePieces gamePiece;
	
	public Square(String squareColour, String squarePosition){
		colour = squareColour;
		position = squarePosition;
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
	
	//TO DO
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