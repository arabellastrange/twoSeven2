public class Piece {
	String colour;
	String position;
	String pColour;
	
	public Piece(String pieceColour, String piecePosition, String playerColour){
		colour = pieceColour;
		position = piecePosition;
		pColour = playerColour;
	}
	
	public void setColour(String sColour){
		colour = sColour;
	}
	
	public String getColour(){
		return colour;
	}
	
	public String getPlayerColour(){
		return pColour;
	}
	
	public String getPiecePosition(){
		return position; 
	}
	
	public void setPiecePosition(String piecePosition){
		position = piecePosition; 
	}
	
}
