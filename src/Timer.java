
public class Timer {
	long start;
	
	public Timer(){
		start = System.currentTimeMillis();
	}
	
	public double getTime(){
		double current = System.currentTimeMillis();
		double elapsed = (current - start) / 1000.00;
		
		return elapsed;
	}
}
