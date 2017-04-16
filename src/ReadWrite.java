import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ReadWrite {
	ArrayList<String> gamesSettings = new ArrayList();
	Observer o = new Observer();
	
	public void saveCurrentState(){
		try{
			File save = new File("savedGame.txt");
			save.createNewFile();
			FileOutputStream fout = new FileOutputStream(save, false); 
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(this);  
			out.flush();
			System.out.println("File saved successfully");
		}
		catch(IOException e){
			System.out.println("Output Stream failed");
		} 
	}
	
	public void storeSettings(ArrayList<String> settings){
		gamesSettings = settings;
	}
	
	public ArrayList<String> getSettings(){
		return gamesSettings;
	}
	
	public boolean loadCurrentState(){
		CurrentState savedState = new CurrentState();
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("savedGame.txt"));
			savedState = (CurrentState) in.readObject();
			o.getCurrentState().setCurrentState(savedState.getTime(), savedState.getBoard(), savedState.getPieces());
			storeSettings(getSettings());
			return true;
		}
		catch(IOException e){
			System.out.println("Input Stream failed");
			return false;
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
			return false;
		}
		  
	}
}
