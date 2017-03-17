public class GamePieces {
	
	Piece pieces[] = new Piece[16];
	String colours[] = {"Brown", "Green", "Red", "Yellow", "Purple", "Maroon", "Navy", "Orange"};
	Coordinates coord  = new Coordinates();
	
	public GamePieces(){
		pieces = new Piece[16];
		
		for(int i = 0; i < 8; i++){
			pieces[i] = new Piece(colours[7 - i], coord.getCoordinates(i), "BL" + i);
			
		}
		
		for(int i = 8; i < 16; i++){
			pieces[i] = new Piece(colours[i - 8], coord.getCoordinates(i + 48), "W" + (i - 8));
		}
	}
	
	public Piece getPiece(String id){
		for(Piece p: pieces){
			if(p.getID().equals(id)){
				return p;
			}
		}
		return null;
	}
	
	public Piece[] getPieces(){
		return pieces;
	}
	
}
