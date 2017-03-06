
public class Player {

	int[][] board;
	public void getStats(){
		
	}
	
	public void setName(String name){
		
	}
	
	public void setColour(String colour){
		
	}
	
	public boolean makeMove(int fromRow, int fromCol, int toRow, int toCol){
		//Only works one way - have to fix it for opposite - i.e pieces going in opposite direction
		if(toRow > fromRow && GamePieces.getColour(fromRow, fromCol) == WHITE || toCol >= fromCol && GamePieces.getColour(fromRow, fromCol) == BLACK ){ 
			//New square must also be empty
			board[toRow][toCol] = board[fromRow][fromCol];
			//board[fromRow][fromCol] = NULL; Empty data from old square
		}
		
		/*Determines that game is over
		if (toRow == 0 && board[toRow][toCol] == WHITE){
            board[toRow][toCol] = ;
        }
		
		if (toRow == 7 && board[toRow][toCol] == BLACK){
            board[toRow][toCol] = ;
        }
        */
		return true;
	}
	
	
}
