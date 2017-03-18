
public class Settings {
	long start;
	double current;
	double elapsed;
	double limit;
	
	public Settings(){
		start = System.currentTimeMillis();
	}
	
	public double getTime(){
		current = System.currentTimeMillis();
		elapsed = (current - start) / 1000.00;
		
		return elapsed;
	}
	public void setTimer(double time){
		limit = time;
	}
}
