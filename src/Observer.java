import java.io.Serializable;
import java.util.ArrayList;

public class Observer implements Serializable{
	CurrentState cs = new CurrentState();
	
	public CurrentState getCurrentState(){
		return cs.getState();
	}
	
	public void createState(){
		cs.createState();
	}
}


