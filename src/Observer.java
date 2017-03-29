import java.io.Serializable;
import java.util.ArrayList;

public class Observer implements Serializable{
	CurrentState now;
	
	public void createState(){
		now = new CurrentState();
	}
	
	public CurrentState getCurrentState(){
		return now;
	}
}


