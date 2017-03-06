public class GamePieces {
	
	Piece pieces[] = null;
	
	public GamePieces(){
		for(int i = 0; i < 8; i++){
			pieces[i] = new Piece("Default", "Default", "White");
		}
		
		for(int i = 0; i < 8; i++){
			pieces[i] = new Piece("Default", "Default", "Black");
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
