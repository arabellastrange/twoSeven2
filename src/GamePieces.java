public class GamePieces {
	
	Piece pieces[] = null;
	String colours[] = {"Brown", "Green", "Red", "Yellow", "Purple", "Maroon", "Navy", "Orange"};
	Coordinates coord;
	
	public GamePieces(){
		for(int i = 0; i < 8; i++){
			pieces[i] = new Piece(colours[i], coord.getCoordinates()[i], "White");
			
		}
		
		for(int i = 0; i < 8; i++){
			pieces[i] = new Piece(colours[7-i], coord.getCoordinates()[i + 55], "Black");
		}
	}
	
	public Piece getPiece(String position){
		for(Piece p: pieces){
			if(p.getPiecePosition().equals(position)){
				return p;
			}
		}
		return null;
	}
}
