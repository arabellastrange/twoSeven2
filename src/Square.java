public class Square {
	String colour;
	String position;
	
	public Square(String pieceColour, String piecePosition){
		colour = pieceColour;
		position = piecePosition;
	}
	
	public String getColour(){
		return colour;
	}
	
	public String getSquarePosition(){
		return position; 
	}
	
	public boolean isEmpty(){
		return true;
		
		//add mechanics
	}
}
