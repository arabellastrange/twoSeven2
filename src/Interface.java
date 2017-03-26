import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Interface {
	ArrayList<String> updatedSquares = new ArrayList<String>();
	static Stack<String> moves = new Stack<String>();
	static ArrayList<String> gameSettings = new ArrayList<String>();
	static ArrayList<String> allSquares = new ArrayList<String>(); // make a list of all squares and print them out if you, then print out all the updated squares, in a square is in both all squares and updated squares then clear it in all squares
	static Scanner s = new Scanner(System.in);
	static Player playerOne = new Player();
	static String playerOneName;
	static String playerTwoName; // add to array 
	static String opOption;
	static String time;
	static double timeLength = -1; // add to array
	static String playerColour;
	static Player playerTwo = new Player();
	static Settings set = new Settings();
	
	public static void main(String[] args){
		System.out.println("Do you wish to load saved game? [Y/N]");
		String load = s.nextLine().trim().toUpperCase();
		if(load.equals("Y")){
			if(playerOne.getSettings().size() == 3){
				playerOneName = playerOne.getSettings().get(0); //name
				opOption = playerOne.getSettings().get(1); //opponent 
				time = playerOne.getSettings().get(2); // check options and split depeneding 
				playerColour = playerOne.getSettings().get(3);
			}
			else if(playerOne.getSettings().size() == 4){
				playerOneName = playerOne.getSettings().get(0);
				playerTwoName = playerOne.getSettings().get(1);
				opOption = playerOne.getSettings().get(2);
				time = playerOne.getSettings().get(3);
				playerColour = playerOne.getSettings().get(4); //works as long as no one plays speed mode in AI lol
			}
			else{
				playerOneName = playerOne.getSettings().get(0);
				playerTwoName = playerOne.getSettings().get(1);
				opOption = playerOne.getSettings().get(2);
				time = playerOne.getSettings().get(3);
				timeLength = Double.parseDouble(playerOne.getSettings().get(4));
				playerColour = playerOne.getSettings().get(5);
			}
			playerOne.setColour(playerColour);
			playerOne.Load();
			
			System.out.println("Begin Game! Press Q to quit at any point");
			String start = s.nextLine().trim().toUpperCase();
			isQuit(start);
			
			if(opOption.equals("H")){
				do{
					play();
				}while(!start.equals("Q"));
			}
			
			else if(opOption.equals("A")){
				do{
					playAI();
				}while(!start.equals("Q"));
			}
			
		}
		
		System.out.println("Welcome to Kamisado, please enter your name: ");
		playerOneName = s.nextLine().trim().toUpperCase();
		playerOne.setName(playerOneName);
		
		
		System.out.println("Do you wish to play against AI [selelct A] or human [select H]?");
		opOption = s.nextLine().trim().toUpperCase();
		
		if(opOption.equals("A")){
			System.out.println("You are playing against AI");		
			System.out.println("Select a difficulty level ([E]asy or [D]ifficult): ");
			String lvl = s.nextLine().trim().toUpperCase();
			
			if(lvl.equals("E")){
				System.out.println("Easy");
				playAI();
			}
			else if(lvl.equals("D")){
				System.out.println("Difficult");
				playAI();
			}
			else{
				System.out.println("That is not a valid option");
			}
		}
		else if(opOption.equals("H")){
			System.out.println("You are playing against another human! Player two, enter your name: ");
			playerTwoName = s.nextLine().trim().toUpperCase();
			if(playerOne.getName().equals(playerTwoName)){
				System.out.println("Both players cannot use the same name. Please enter a unique account name:");
				playerTwoName = s.nextLine().trim().toUpperCase();
				playerTwo.setName(playerTwoName);			
			}
			else{
				playerTwo.setName(playerTwoName);
			}
		
			System.out.println("Player one select your colour! [White or Black]");
			playerColour = s.nextLine().trim().toUpperCase();
			playerOne.setColour(playerColour);
			
			if(playerColour.equals("W")){
				playerTwo.setColour("B");
			}
			else if(playerColour.equals("Q")){
				isQuit(playerColour);
			}
			else{
				playerTwo.setColour("W");
			}
			
			System.out.println("Do you wish to play in speed mode? [Y/N]");
			time = s.nextLine().trim().toUpperCase();
			
			if(time.equals("Y")){
				
				System.out.println("Set the timer value you would like to use (in seconds): ");
				timeLength = s.nextDouble();
				set.setTimer(timeLength);
				
				Interface i = new Interface();
				
				System.out.println("Begin Game! Press Q to quit at any point");
				String start = s.nextLine().trim().toUpperCase();
				isQuit(start);
				do{
					play();
				}while(!start.equals("Q"));
			}
			else{
				Interface board = new Interface();

				System.out.println("Begin Game! Press S to start or Q to quit at any point");
				String start = s.nextLine().trim().toUpperCase();
				isQuit(start);
				do{
					System.out.println("Player One make a move! Select the piece you wish to move: ");
					String piece = s.nextLine().trim().toUpperCase();
					
					isPieceValid(piece);
					isQuit(piece);
					
					System.out.println("Player One select the square you wish to move to: ");
					String square = s.nextLine().trim().toUpperCase();
					
					isSquareValid(square);
					isQuit(square);	
					
					if(playerOne.makeMove(piece, square)){
						board.updateInterface(piece, square);
					}
					
					System.out.println("Player Two make a move! Select the piece you wish to move: ");
					piece = s.nextLine().trim().toUpperCase();
					
					isPieceValid(piece);
					isQuit(piece);
					
					System.out.println("Player Two select the square you wish to move to: ");
					square = s.nextLine().trim().toUpperCase();
					
					isSquareValid(square);
					isQuit(square);	
					
					if(playerTwo.makeMove(piece, square)){
						board.updateInterface(piece, square);
					}
				}
				while(!start.equals("Q"));
			}
		}
		else{
			System.out.println("That is not a valid option");
		}
}
	
	public Interface(){

		allSquares.add("\u001b[1;45m|\u001b[0m表u001b[1;45m|\u001b[0m"); // purple
		allSquares.add("\u001b[1;44m|\u001b[0m表u001b[1;44m|\u001b[0m"); // blue
		allSquares.add("\u001b[1;46m|\u001b[0m表u001b[1;46m|\u001b[0m"); // cyan
		allSquares.add("\u001b[0;47m|\u001b[0m表u001b[0;47m|\u001b[0m"); // pink
		allSquares.add("\u001b[1;43m|\u001b[0m表u001b[1;43m|\u001b[0m"); // yellow
		allSquares.add("\u001b[1;41m|\u001b[0m表u001b[1;41m|\u001b[0m"); // red
		allSquares.add("\u001b[1;42m|\u001b[0m表u001b[1;42m|\u001b[0m"); // green
		allSquares.add("\u001b[1;40m|\u001b[0m表u001b[1;40m|\u001b[0m"); // black


		allSquares.add("\u001b[1;41m|_|\u001b[0m"); // row 1
		allSquares.add("\u001b[1;45m|_|\u001b[0m");
		allSquares.add("\u001b[0;47m|_|\u001b[0m");
		allSquares.add("\u001b[1;42m|_|\u001b[0m");
		allSquares.add("\u001b[1;44m|_|\u001b[0m");
		allSquares.add("\u001b[1;43m|_|\u001b[0m");
		allSquares.add("\u001b[1;40m|_|\u001b[0m");
		allSquares.add("\u001b[1;46m|_|\u001b[0m");
		
		allSquares.add("\u001b[1;42m|_|\u001b[0m"); //row 2
		allSquares.add("\u001b[0;47m|_|\u001b[0m"); 
		allSquares.add("\u001b[1;45m|_|\u001b[0m");
		allSquares.add("\u001b[1;41m|_|\u001b[0m");
		allSquares.add("\u001b[1;46m|_|\u001b[0m");
		allSquares.add("\u001b[1;40m|_|\u001b[0m");
		allSquares.add("\u001b[1;43m|_|\u001b[0m");
		allSquares.add("\u001b[1;44m|_|\u001b[0m");
		
		allSquares.add("\u001b[0;47m|_|\u001b[0m"); //row 3
		allSquares.add("\u001b[1;46m|_|\u001b[0m");
		allSquares.add("\u001b[1;44m|_|\u001b[0m");
		allSquares.add("\u001b[1;45m|_|\u001b[0m");
		allSquares.add("\u001b[1;40m|_|\u001b[0m");
		allSquares.add("\u001b[1;42m|_|\u001b[0m");
		allSquares.add("\u001b[1;41m|_|\u001b[0m");
		allSquares.add("\u001b[1;43m|_|\u001b[0m");
		
		allSquares.add("\u001b[1;43m|_|\u001b[0m"); //row 4
		allSquares.add("\u001b[1;41m|_|\u001b[0m");
		allSquares.add("\u001b[1;42m|_|\u001b[0m");
		allSquares.add("\u001b[1;40m|_|\u001b[0m");
		allSquares.add("\u001b[1;45m|_|\u001b[0m");
		allSquares.add("\u001b[1;44m|_|\u001b[0m");
		allSquares.add("\u001b[1;46m|_|\u001b[0m");
		allSquares.add("\u001b[0;47m|_|\u001b[0m");
		
		allSquares.add("\u001b[1;44m|_|\u001b[0m"); //row 5
		allSquares.add("\u001b[1;43m|_|\u001b[0m");
		allSquares.add("\u001b[1;40m|_|\u001b[0m");
		allSquares.add("\u001b[1;46m|_|\u001b[0m");
		allSquares.add("\u001b[1;41m|_|\u001b[0m");
		allSquares.add("\u001b[1;45m|_|\u001b[0m");
		allSquares.add("\u001b[0;47m|_|\u001b[0m");
		allSquares.add("\u001b[1;42m|_|\u001b[0m");
		
		allSquares.add("\u001b[1;46m|_|\u001b[0m"); //row 6
		allSquares.add("\u001b[1;40m|_|\u001b[0m");
		allSquares.add("\u001b[1;43m|_|\u001b[0m");
		allSquares.add("\u001b[1;44m|_|\u001b[0m");
		allSquares.add("\u001b[1;42m|_|\u001b[0m");
		allSquares.add("\u001b[0;47m|_|\u001b[0m");
		allSquares.add("\u001b[1;45m|_|\u001b[0m");
		allSquares.add("\u001b[1;41m|_|\u001b[0m");
		

		allSquares.add("\u001b[1;40m|\u001b[0mｰ\u001b[1;40m|\u001b[0m"); // black
		allSquares.add("\u001b[1;42m|\u001b[0mｰ\u001b[1;42m|\u001b[0m"); // green
		allSquares.add("\u001b[1;41m|\u001b[0mｰ\u001b[1;41m|\u001b[0m"); // red
		allSquares.add("\u001b[1;43m|\u001b[0mｰ\u001b[1;43m|\u001b[0m"); // yellow
		allSquares.add("\u001b[1;47m|\u001b[0mｰ\u001b[1;47m|\u001b[0m"); // pink
		allSquares.add("\u001b[1;46m|\u001b[0mｰ\u001b[1;46m|\u001b[0m"); // cyan
		allSquares.add("\u001b[1;44m|\u001b[0mｰ\u001b[1;44m|\u001b[0m"); // blue
		allSquares.add("\u001b[1;45m|\u001b[0mｰ\u001b[1;45m|\u001b[0m"); // purple

		printInterface();
	}
	
	public static void playAI(){
		System.out.println("Select the piece you wish to move: ");		
		String piece = s.nextLine().trim().toUpperCase();
		
		isPieceValid(piece);
		
		isQuit(piece);
		
		System.out.println("Player One select the square you wish to move to: ");
		String square = s.nextLine().trim().toUpperCase();
		
		isSquareValid(square);
		
		isQuit(square);
		
		System.out.println(set.getTime());
		if(set.getTime() >= timeLength){
			set.clearTimer();
			System.out.println("You have run out of time. Player Two make a move.");
		}else{
			if(playerOne.makeMove(piece, square)){
				updateInterface(piece, square);
				set.clearTimer();
			}
		}
	}
	
	public static void play(){
		System.out.println("Player One make a move! Select the piece you wish to move: ");		
		String piece = s.nextLine().trim().toUpperCase();
		
		isPieceValid(piece);
		
		isQuit(piece);
		
		System.out.println("Player One select the square you wish to move to: ");
		String square = s.nextLine().trim().toUpperCase();
		
		isSquareValid(square);
		
		isQuit(square);	
		
		System.out.println(set.getTime());
		if(set.getTime() >= timeLength){
			set.clearTimer();
			System.out.println("You have run out of time. Player Two make a move.");
		}else{
			if(playerOne.makeMove(piece, square)){
				updateInterface(piece, square);
				set.clearTimer();
			}
		}
		
		System.out.println("Player Two make a move! Select the piece you wish to move: ");
		piece = s.nextLine().trim().toUpperCase();

		isPieceValid(piece);
		
		isQuit(piece);
		
		System.out.println("Player Two select the square you wish to move to: ");
		square = s.nextLine().trim().toUpperCase();
		
		isSquareValid(square);
		
		isQuit(square);	
		
		System.out.println(set.getTime());
		if(set.getTime() >= timeLength){
			set.clearTimer();
			System.out.println("You have run out of time. Player Two make a move.");
		}else{
			if(playerTwo.makeMove(piece, square)){
				updateInterface(piece, square);
				set.clearTimer();
			}
		}
	}
	public static void printInterface(){
		System.out.println("         Kamisado");
		System.out.println("   A  B  C  D  E  F  G  H");
		int i = 0;
		for(int m = 0; m <8; m++){	
			System.out.print(m + " ");
			for(int n = 0; n < 8; n++){
				System.out.print(allSquares.get(i));
				i++;
			}
			System.out.println();
		}
	}
	
	public static void updateInterface(String piece, String square){
		Coordinates coord = new Coordinates();
		coord.stringToXY(square);
		int newx = coord.getX();
		int newy = coord.getY();
		int sqIndex = newx + (newy*8);
		
		if(piece.startsWith("W")){

			String newSq = allSquares.get(sqIndex).replace("|_|","|ｰ|");
			allSquares.set(sqIndex, newSq);
		}
		else if(piece.startsWith("B")){
			String newSq = allSquares.get(sqIndex ).replace("|_|","|怖");

			allSquares.set(sqIndex, newSq);
		}
		
		printInterface();
		
	}
	
	public static void isQuit(String input){
		String quit = input.toUpperCase();
		if(quit.equals("Q")){
			System.out.println("Do you wish to save your game before exit? [Y/N]: ");
			String save = s.nextLine().trim().toUpperCase();
			if(save.equals("Y")){
				gameSettings.add(playerOneName);
				if(!playerTwoName.isEmpty()){
					gameSettings.add(playerTwoName);
				}
				gameSettings.add(opOption);
				gameSettings.add(time);
				if(timeLength != -1){
					gameSettings.add(String.valueOf(timeLength));
				}
				gameSettings.add(playerColour);
				playerOne.storeSettings(gameSettings);
				playerOne.Save();
			}
			System.out.println("Goodbye");
			System.exit(0);
		}
	}
	
	public static void isSquareValid(String square){
		if(!square.matches("(A|B|C|D|E|F|G|H|Q).*")){
			System.out.println("Square numbers are coordinates ranging from A0 to H7. Please try again:");
			square = s.nextLine().trim().toUpperCase();
		}
	}
	
	public static void isPieceValid(String piece){
		if(!piece.startsWith("W") && !piece.startsWith("B") && !piece.startsWith("Q") ){
			System.out.println("This is not a valid piece ID. The piece id should be either 'W' or 'B' followed by the piece number.");
			System.out.println("Please try again:");
			piece = s.nextLine().trim().toUpperCase();
		}
	}
}