public class Coordinates {
	String[] positions;
	int x;
	int y; 
	
	public Coordinates(){
		String[] local = {"A0", "B0", "C0", "D0", "E0", "F0", "G0", "H0", "A1", "B1", "C1", "D1", "E1", "F1", "G1", "H1", "A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2", "A3", "B3", "C3", "D3", "E3", "F3", "G3", "H3", "A4", "B4", "C4", "D4", "E4", "F4", "G4", "H4", "A5", "B5", "C5", "D5", "E5", "F5", "G5", "H5", "A6", "B6", "C6", "D6", "E6", "F6", "G6", "H6", "A7", "B7", "C7", "D7", "E7", "F7", "G7", "H7"};
		positions = new String[local.length];
		for(int i = 0; i < local.length; i++){
			positions[i] = local[i];
		} 
	}
	
	public String getCoordinates(int i){
		//System.out.println(positions[i]);
		return positions[i];	
	}
	
	public void stringToXY(String position){
		y = Integer.parseInt(position.substring(1));
		x = position.charAt(0) - 64;
//		System.out.println("Xy trans: " + y + "," + x);
	}
	
	public String XYtoString(int x, Integer y){
		int ascx = x + 64;
		char stx = (char) ascx;
		String sty = y.toString(y,10);
		String xyString  = stx + "" + sty;
//		System.out.println(xyString);
		return xyString;
	}
	
	//TO DO
	public Boolean isMoveForward(String from, String to, String playerColour){
		return true;
	}
	
	public String moveUp(String coord){
		stringToXY(coord);
		if(y - 1 >= 0){
			y = y - 1;
		}
		return XYtoString(x,y);
	}
	
	public String moveDown(String coord){
		stringToXY(coord);
		if(y + 1 <= 7){
			y = y + 1;
		}
		return XYtoString(x,y);
	}
	
	public String moveLeft(String coord){
		stringToXY(coord);
		if(x - 1 >= 0){
			x = x - 1;
		}
		return XYtoString(x,y);
	}
	
	public String moveRight(String coord){
		stringToXY(coord);
		if(x + 1 <= 8){
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
