import java.io.Serializable;
import java.awt.geom.Point2D;

public class Square implements Serializable{
	String colour;
	String position;
	Boolean empty;
	Point2D sqLoc;
	
	public Square(String squareColour, String squarePosition, Boolean squareEmpty){
		colour = squareColour;
		position = squarePosition;
		empty = squareEmpty;
	} 
	
	public String getColour(){
		return colour;
	}
	
	public void setSquareLocation(Point2D p){
		sqLoc = p;
	}
	
	public Point2D getSquareLocation(){
		return sqLoc;
	}
	
	public String getSquarePosition(){
		return position; 
	}
	
	public void setSquareColour(String squareColour){
		colour = squareColour;
	}
	
	//TO DO -- i don't know if this actually works?? don't u have to loop thru all pieces
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