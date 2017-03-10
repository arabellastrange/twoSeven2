public class Coordinates {
	
	String positions[] = {"A0", "B0", "C0", "D0", "E0", "F0", "G0", "H0", "A1", "B1", "C1", "D1", "E1", "F1", "G1", "H1", "A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2", "A3", "B3", "C3", "D3", "E3", "F3", "G3", "H3", "A4", "B4", "C4", "D4", "E4", "F4", "G4", "H4", "A5", "B5", "C5", "D5", "E5", "F5", "G5", "H5", "A6", "B6", "C6", "D6", "E6", "F6", "G6", "H6", "A7", "B7", "C7", "D7", "E7", "F7", "G7", "H7"};
	CurrentState currentState;
	int x;
	int y; 
	
	public String[] getCoordinates(){
		return positions;
	}
	
	public void stringToXY(String position){
		for(int i = 0; i < 8; i++){
			for(int n = 0; n < 8; n++){
				if(currentState.getCurrentState().getBoard().getSquare(i,n).getSquarePosition().equals(position)){//i think you would have to get the board object from the current state object, how to force update all current state objects tho???
					x = i;
					y = n;
				}
			}
		}
	}
	
	public String XYtoString(int x, int y){
		return currentState.getCurrentState().getBoard().getSquare(x,y).getSquarePosition();
	}
	//is move forward method to check valid coordinate for player colour
	public Boolean isMoveForward(String from, String to, String playerColour){
		return true;
	}
	public String moveUp(String coord){
		stringToXY(coord);
		if(y + 1 != 8){
			y = y + 1;
		}
		return XYtoString(x,y);
	}
	
	public String moveDown(String coord){
		stringToXY(coord);
		if(y - 1 != 0){
			y = y -1;
		}
		return XYtoString(x,y);
	}
	
	public String moveLeft(String coord){
		stringToXY(coord);
		if(x - 1 != 0){
			x = x - 1;
		}
		return XYtoString(x,y);
	}
	
	public String moveRight(String coord){
		stringToXY(coord);
		if(x + 1 != 8){
			x = x + 1;
		}
		return XYtoString(x,y);
	}
	
	public String moveDiagonalLeftDown(String coord){	
		return moveLeft(moveDown(coord));
	}
	
	public String moveDiagonalRightDown(String coord){
		return moveRight(moveDown(coord));
	}
	
	public String moveDiagonalLeftUp(String coord){
		return moveLeft(moveUp(coord));
	}
	
	public String moveDiagonalRightUp(String coord){
		return moveRight(moveUp(coord));
	}
}
