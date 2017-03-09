
public class Stats {

	int highScore;
	double bestTime;
	String achievements[];
	
	public int getHighScore(){
		return highScore;
	}
	
	public double getBestTime(){
		return bestTime;
	}
	
	public String[] getAchievements(){
		return achievements;
	} 
	
	public void setHighScore(int highScore){
		
	}
	
	public void addAchievements(String achievement){
		int i = 0;
		achievements[i] = achievement;
		i++;
	}
}
