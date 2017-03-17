public class Square {
	String colour;
	String position;
	Boolean empty = false;
	
	public Square(String squareColour, String squarePosition, Boolean empty){
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
		return empty;
	}
	
	public void setOccupied(){
		empty = true;
	}
	
	public void clear(){
		empty = false;
	}
}