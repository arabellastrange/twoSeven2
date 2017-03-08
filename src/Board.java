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
				getSquare(i,i).setSquareColour(colours[7]);		
		}
		
		for(int i = 7; i >= 0; i--){
			getSquare(i,i).setSquareColour(colours[1]);		
		}
		
		
		String start = getSquare(0,3).getSquarePosition();
		String currentPosition = getSquare(0,3).getSquarePosition();
		
		for(int i = 0; i < 4; i++){
			getStringSquare(currentPosition).setSquareColour("Purple");
			String nextPosition = coord.moveDiagonalLeftDown(currentPosition);
			currentPosition = nextPosition; 
			
		}
		
	}
	
	public Square getSquare(int x, int y){
		return squares[x][y];
	}
	
	public Square getStringSquare(String position){
		for(Square[] sq : squares){
			for(Square s: sq){
				if(s.getSquarePosition().equals(position)){
					return s;
				}
			}
		}
		return null;
	}
	
}
