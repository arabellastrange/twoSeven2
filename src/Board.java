public class Board {
	
	Square squares[][] = new Square[8][8];
	
	String colours[] = {"Brown", "Green", "Red", "Yellow", "Purple", "Maroon", "Navy", "Orange"};
	
	Coordinates coord = new Coordinates();
	
	public Board(){
			int n = 0;
			for(int i = 0; i < 8; i++){
				for(int m = 0; m < 8; m++){
					String j =  coord.getCoordinates(n);
					squares[i][m] = new Square("Default", j);
					n++;
					}
				}
			
		for(int i = 0; i < 8; i++){
				getSquare(i,i).setSquareColour(colours[7]);		
		}
		
		for(int i = 7; i >= 0; i--){
			getSquare(i,i).setSquareColour(colours[1]);		
		}
		
		
		String start = getSquare(3,0).getSquarePosition();
		System.out.println(start);
		String currentPosition = start;
		;
		for(int i = 0; i < 4; i++){
			Square s = getStringSquare(currentPosition);
			System.out.println(currentPosition);
			
			//s.setSquareColour(colours[4]);
			String nextPosition = coord.moveDiagonalLeftDown(currentPosition);
			currentPosition = nextPosition; 	
		}
		
		start = getSquare(7,4).getSquarePosition();
		currentPosition = start;
		
		for(int i = 0; i < 4; i++){
			getStringSquare(currentPosition).setSquareColour(colours[4]);
			String nextPosition = coord.moveDiagonalLeftDown(currentPosition);
			currentPosition = nextPosition; 	
		}
		
		start = getSquare(3,7).getSquarePosition();
		currentPosition = start;
		
		for(int i = 0; i < 4; i++){
			getStringSquare(currentPosition).setSquareColour("Yellow");
			String nextPosition = coord.moveDiagonalLeftUp(currentPosition);
			currentPosition = nextPosition; 	
		}
		
		start = getSquare(4,0).getSquarePosition();
		currentPosition = start;
		for(int i = 0; i < 4; i++){
			getStringSquare(currentPosition).setSquareColour("Yellow");
			String nextPosition = coord.moveDiagonalRightDown(currentPosition);
			currentPosition = nextPosition; 	
		}
		
		getSquare(2,0).setSquareColour("Maroon");
		getSquare(7,1).setSquareColour("Maroon");
		getSquare(4,2).setSquareColour("Maroon");
		getSquare(1,3).setSquareColour("Maroon");
		getSquare(6,4).setSquareColour("Maroon");
		getSquare(3,5).setSquareColour("Maroon");
		getSquare(0,6).setSquareColour("Maroon");
		getSquare(5,7).setSquareColour("Maroon");
		getSquare(1,0).setSquareColour("Navy");
		getSquare(4,1).setSquareColour("Navy");
		getSquare(7,2).setSquareColour("Navy");
		getSquare(2,3).setSquareColour("Navy");
		getSquare(5,4).setSquareColour("Navy");
		getSquare(0,5).setSquareColour("Navy");
		getSquare(3,6).setSquareColour("Navy");
		getSquare(6,7).setSquareColour("Navy");
		getSquare(5,0).setSquareColour("Red");
		getSquare(0,1).setSquareColour("Red");
		getSquare(4,2).setSquareColour("Red");
		getSquare(6,3).setSquareColour("Red");
		getSquare(1,4).setSquareColour("Red");
		getSquare(4,5).setSquareColour("Red");
		getSquare(7,6).setSquareColour("Red");
		getSquare(2,7).setSquareColour("Red");
		getSquare(6,0).setSquareColour("Green");
		getSquare(3,1).setSquareColour("Green");
		getSquare(0,2).setSquareColour("Green");
		getSquare(5,3).setSquareColour("Green");
		getSquare(2,4).setSquareColour("Green");
		getSquare(7,5).setSquareColour("Green");
		getSquare(4,6).setSquareColour("Green");
		getSquare(1,7).setSquareColour("Green");
		
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