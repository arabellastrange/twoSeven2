
public class Settings {
	long startTime;
	double current;
	double elapsed;
	double limit;
	
	public Settings(){
		startTime = System.currentTimeMillis();
	}
	
	public double getTime(){
		current = System.currentTimeMillis();
		elapsed = (current - startTime) / 1000.00; /// 1000.00;
		
		return elapsed;
	}
	
	public void setTimer(double time){
		limit = time;
	}
	
	public double clearTimer(){
		elapsed = 0.0;
		return elapsed;
	}
}
