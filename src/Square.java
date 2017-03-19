public class Square {
	String colour;
	String position;
	Boolean empty;
	
	public Square(String squareColour, String squarePosition, Boolean squareEmpty){
		colour = squareColour;
		position = squarePosition;
		empty = squareEmpty;
		
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
		return empty;
	}
	
	public void setOccupied(){
		empty = false;
	}
	
	public void clear(){
		empty = true;
	}
}