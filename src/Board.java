public class Board {
//REMEmBER Y X NOT XY
	Square squares[][] = new Square[8][8];
	
	String colours[] = {"Brown", "Green", "Red", "Yellow", "Purple", "Maroon", "Navy", "Orange"};
	
	Coordinates coord = new Coordinates();
	
	public Board(){
			int n = 0;
			for(int i = 0; i < 8; i++){
				for(int m = 0; m < 8; m++){
					String j =  coord.getCoordinates(n);
					squares[i][m] = new Square("Default", j);
//					System.out.println("Assigned Square ["+ i + "] [" + m + "] position: " + squares[i][m].getSquarePosition());
					n++;
					}
				}
			
			colourBoard();	
	}
	
	public void colourBoard(){
		for(int i = 0; i < 8; i++){
			getSquare(i,i).setSquareColour(colours[7]);	
//			System.out.println("Postion of square A0 is " + getStringSquare("A0").getSquarePosition());
//			System.out.println("Coloring Square ["+ i + "] [" + i + "] position: " + squares[i][i].getSquarePosition());
			}
	
		for(int i = 7; i >= 0; i--){
			getSquare(i,i).setSquareColour(colours[1]);		
		}
		
		String start = getSquare(0,3).getSquarePosition();
//		System.out.println("Start: " + start);
		String currentPosition = start;	
		for(int i = 0; i < 4; i++){
			Square s = getStringSquare(currentPosition);
//			System.out.println(currentPosition);
			s.setSquareColour(colours[4]);
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
		
		start = getSquare(0,4).getSquarePosition();
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
		
		getSquare(0,2).setSquareColour("Maroon");
		getSquare(1,7).setSquareColour("Maroon");
		getSquare(2,4).setSquareColour("Maroon");
		getSquare(3,1).setSquareColour("Maroon");
		getSquare(4,6).setSquareColour("Maroon");
		getSquare(5,3).setSquareColour("Maroon");
		getSquare(6,0).setSquareColour("Maroon");
		getSquare(7,5).setSquareColour("Maroon");
		getSquare(0,1).setSquareColour("Navy");
		getSquare(1,4).setSquareColour("Navy");
		getSquare(2,7).setSquareColour("Navy");
		getSquare(3,2).setSquareColour("Navy");
		getSquare(4,5).setSquareColour("Navy");
		getSquare(5,0).setSquareColour("Navy");
		getSquare(6,3).setSquareColour("Navy");
		getSquare(7,6).setSquareColour("Navy");
		getSquare(0,5).setSquareColour("Red");
		getSquare(1,0).setSquareColour("Red");
		getSquare(2,4).setSquareColour("Red");
		getSquare(3,6).setSquareColour("Red");
		getSquare(4,1).setSquareColour("Red");
		getSquare(5,4).setSquareColour("Red");
		getSquare(6,7).setSquareColour("Red");
		getSquare(7,2).setSquareColour("Red");
		getSquare(0,6).setSquareColour("Green");
		getSquare(1,3).setSquareColour("Green");
		getSquare(2,0).setSquareColour("Green");
		getSquare(3,5).setSquareColour("Green");
		getSquare(4,2).setSquareColour("Green");
		getSquare(5,7).setSquareColour("Green");
		getSquare(6,4).setSquareColour("Green");
		getSquare(7,1).setSquareColour("Green");
	}
	
	public Square getSquare(int y, int x){
		return squares[y][x];
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