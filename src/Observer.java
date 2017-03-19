public class Observer {
	
	CurrentState now = new CurrentState();
	
	public Observer(){
		now = getCurrentState();
	}
	
	public CurrentState getCurrentState(){
		return now;
	}
}


