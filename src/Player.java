
public class Player {

	int[][] board;
	String playerColour;
	String playerName;
	
	public void getStats(){
		
	}
	
	public void setName(String name){
		playerName = name;
	}
	
	public void setColour(String colour){
		playerColour = colour;
	}
	
	public boolean makeMove(int fromRow, int fromCol, int toRow, int toCol){
		/*Only works one way - have to fix it for opposite - i.e pieces going in opposite direction
		if(toRow > fromRow && GamePieces..getPiece().getColour(fromRow, fromCol) == WHITE || toCol >= fromCol && GamePieces.getColour(fromRow, fromCol) == BLACK ){ 
			//New square must also be empty
			board[toRow][toCol] = board[fromRow][fromCol];
			//board[fromRow][fromCol] = NULL; Empty data from old square
		}
		
		/*getCurrentState.getBoard.getSquareYouWantToGoTo.isEmpty.getLastMovedPieceColour*/
		return true;
	}
	
	
}
