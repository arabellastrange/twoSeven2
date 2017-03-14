public class Square {
	String colour;
	String position;
	GamePieces gamePiece;
	
	public Square(String squareColour, String squarePosition){
		colour = squareColour;
		position = squarePosition;
	}
	
	public String getColour(){
		return colour;
	}
	
	public String getSquarePosition(){
		return position; 
	}
	
	public void setSquareColour(String squareColour){
		colour = squareColour;
	}
	
	//TO DO -- i dont know if this acually works?? dont u have to loop thru all pieces
	public boolean isEmpty(){
		String pos = getSquarePosition();
		if(gamePiece.getPiece(pos) == null){
			return false;
		}
		else{
			return true;	
		}	
	}
}