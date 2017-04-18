import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ReadWrite {
	Observer o = new Observer();
	
	public void saveCurrentState(){
		try{
			File save = new File("savedGame.txt");
			save.createNewFile();
			FileOutputStream fout = new FileOutputStream(save, false); 
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(o.getCurrentState());  
			out.flush();
			System.out.println("File saved successfully");
		}
		catch(IOException e){
			System.out.println("Output Stream failed");
		} 
	}
	
	public boolean loadCurrentState(){
		CurrentState savedState = new CurrentState();
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("savedGame.txt"));
			savedState = (CurrentState) in.readObject();
			o.getCurrentState().setCurrentState(savedState.getTime(), savedState.getBoard(), savedState.getPieces(), savedState.getSettings(), savedState.getLastLandedOn());
			o.getCurrentState().storeSettings(o.getCurrentState().getSettings());
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