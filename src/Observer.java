import java.io.Serializable;

public class Observer implements Serializable{
	
	CurrentState now = new CurrentState();
	
	public Observer(){
		now = getCurrentState();
	}
	
	public CurrentState getCurrentState(){
		return now;
	}
}


