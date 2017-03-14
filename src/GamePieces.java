public class GamePieces {
	
	Piece pieces[];
	String colours[] = {"Brown", "Green", "Red", "Yellow", "Purple", "Maroon", "Navy", "Orange"};
	Coordinates coord  = new Coordinates();
	
	public GamePieces(){
		pieces = new Piece[16];
		
		for(int i = 0; i < 8; i++){
			pieces[i] = new Piece(colours[i], coord.getCoordinates(i), "W" + i);
			
		}
		
		for(int i = 8; i < 16; i++){
			pieces[i] = new Piece(colours[i-8], coord.getCoordinates(i+47), "B" + i);
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
	
	public Piece[] getPieces(){
		return pieces;
	}
	
}
