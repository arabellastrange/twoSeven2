public class Observer {
	
	CurrentState now = new CurrentState();
	
	public Observer(){
		now = getCurrentState();
	}
	
	public void setCurrentState(GamePieces gamePieces){
		for(int i = 0; i < 16; i++){
			now.getPositions();
		}
		
	}
	
	public CurrentState getCurrentState(){
		return now;
	}
}


