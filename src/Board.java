public class Board {
	
	Square squares[][] = null;
	
	String colours[] = {"Brown", "Green", "Red", "Yellow", "Purple", "Maroon", "Navy", "Orange"};
	
	Coordinates coord;
	
	public Board(){
		for(int n = 0; n < 64; n++){
			for(int i = 0; i < 8; i++){
				for(int m = 0; m < 8; m++){
					squares[i][m] = new Square("Default", coord.getCoordinates()[n]);
					}
				}
			}
		for(int i = 0; i < 8; i++){
				moveDiagonalLeftDown(getSquarePosition());//im confused
		}
	}
	
	public Square getSquare(int x, int y){
		return squares[x][y];
	}
	
}
