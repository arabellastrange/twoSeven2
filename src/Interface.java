import java.util.ArrayList;
import java.util.Scanner;


public class Interface {
	ArrayList<String> updatedSquares = new ArrayList<String>();
	ArrayList<String> allSquares = new ArrayList<String>(); // make a list of all squares and print them out if you, then print out all the updated squares, in a square is in both all squares and updated squares then clear it in all squares

	public static void main(String[] args){
		String account;
		String option;
		String playerColour;
		Settings set = new Settings();
		Player playerOne = new Player();
		Player playerTwo = new Player();
		System.out.println("Welcome to Kamisado, please enter your name: ");
		Scanner s = new Scanner(System.in);
		account = s.nextLine().trim().toUpperCase();
		playerOne.setName(account);

		System.out.println("Do you wish to play against AI [selelct A] or human [select H]?");
		option = s.nextLine().trim().toUpperCase();

		if(option.equals("A")){
			System.out.println("You are playing agint AI");
			Interface i = new Interface();
		}
		else if(option.equals("H")){
			System.out.println("You are playing against another human! Player two, enter your name: ");
			account = s.nextLine().trim().toUpperCase();
			if(playerOne.getName().equals(account)){
				System.out.println("Both players cannot use the same name. Please enter a unique account name:");
				account = s.nextLine().trim().toUpperCase();
				playerTwo.setName(account);
			}
			else{
				playerTwo.setName(account);
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
			String time = s.nextLine().trim().toUpperCase();

			if(time.equals("Y")){
				System.out.println("Set the timer value you would like to use (in minutes):");
				double length = s.nextDouble();
				set.setTimer(length);
			}

			Interface i = new Interface();

			System.out.println("Begin Game! Press S to start or Q to quit at any point");
			option = s.nextLine().trim().toUpperCase();
			isQuit(option);
			do{
				System.out.println("Player One make a move! Select the piece you wish to move: ");
				String piece = s.nextLine().trim().toUpperCase();
				isQuit(piece);
				System.out.println("Player One select the square you wish to move to: ");
				String square = s.nextLine().trim().toUpperCase();
				isQuit(square);

				if(playerOne.makeMove(piece, square)){
					i.updateInterface(piece, square);
				}
				else{
					System.out.println("That is not a valid move!");
				}

				System.out.println("Player Two make a move! Select the piece you wish to move: ");
				piece = s.nextLine().trim().toUpperCase();
				isQuit(piece);
				System.out.println("Player Two select the square you wish to move to: ");
				square = s.nextLine().trim().toUpperCase();
				isQuit(square);

				if(playerTwo.makeMove(piece, square)){
					i.updateInterface(piece, square);
				}
				else{
					System.out.println("That is not a valid move!");
				}
			}
			while(!option.equals("Q"));
		}
		else{
			System.out.println("That is not a valid option");
		}
	}

	public Interface(){
		System.out.println("         Kamisado");
		System.out.println("   A  B  C  D  E  F  G  H");
		for(int m = 0; m <8; m++){
			System.out.print(m + " ");
			for(int n = 0; n < 8; n++){
		        if(m == 0 && n == 0){ //purple(orange)
		          System.out.print("\u001b[1;45m|\u001b[0m表u001b[1;45m|\u001b[0m");
		        }
		        else if(m == 0 && n == 1){ //blue
		          System.out.print("\u001b[1;44m|\u001b[0m表u001b[1;44m|\u001b[0m");
		        }
		        else if(m == 0 && n == 2){ //cyan
		          System.out.print("\u001b[1;46m|\u001b[0m表u001b[1;46m|\u001b[0m");
		        }
		        else if(m == 0 && n == 3){ //supposed to be pink
		          System.out.print("\u001b[0;47m|\u001b[0m表u001b[0;47m|\u001b[0m");
		        }
		        else if(m == 0 && n == 4){ //yellow
		          System.out.print("\u001b[1;43m|\u001b[0m表u001b[1;43m|\u001b[0m");
		        }
				else if(m == 0 && n == 5){ //red
							System.out.print("\u001b[1;41m|\u001b[0m表u001b[1;41m|\u001b[0m");
						}
		        else if(m == 0 && n == 6){ //green
							System.out.print("\u001b[1;42m|\u001b[0m表u001b[1;42m|\u001b[0m");
						}
		        else if(m == 0 && n == 7){ //black
							System.out.print("\u001b[1;40m|\u001b[0m表u001b[1;40m|\u001b[0m");
						}
				else if(m == 7 && n == 7){ //purple(orange)
							System.out.print("\u001b[1;45m|\u001b[0mｰ\u001b[1;45m|\u001b[0m");
						}
		        else if(m == 7 && n == 6){ //blue
							System.out.print("\u001b[1;44m|\u001b[0mｰ\u001b[1;44m|\u001b[0m");
						}
		        else if(m == 7 && n == 5){ //cyan
							System.out.print("\u001b[1;46m|\u001b[0mｰ\u001b[1;46m|\u001b[0m");
						}
		        else if(m == 7 && n == 4){ //supposed to be pink
							System.out.print("\u001b[0;47m|\u001b[0mｰ\u001b[0;47m|\u001b[0m");
						}
		        else if(m == 7 && n == 3){ //yellow
							System.out.print("\u001b[1;43m|\u001b[0mｰ\u001b[1;43m|\u001b[0m");
						}
		        else if(m == 7 && n == 2){ //red
							System.out.print("\u001b[1;41m|\u001b[0mｰ\u001b[1;41m|\u001b[0m");
						}
		        else if(m == 7 && n == 1){ //green
							System.out.print("\u001b[1;42m|\u001b[0mｰ\u001b[1;42m|\u001b[0m");
						}
		        else if(m == 7 && n == 0){ //black
							System.out.print("\u001b[1;40m|\u001b[0mｰ\u001b[1;40m|\u001b[0m");
						}
						else if(m == 1 && n == 0 || m == 2 && n == 3 || m == 3 && n == 6 || m == 4 && n == 1 || m == 5 && n == 4 || m == 6 && n == 7){ //red
							System.out.print("\u001b[1;41m|_|\u001b[0m");
						}
		        else if(m == 1 && n == 1 || m == 2 && n == 2 || m == 3 && n == 3 || m == 4 && n == 4 || m == 5 && n == 5 || m == 6 && n == 6){ //purple(orange)
							System.out.print("\u001b[1;45m|_|\u001b[0m");
						}
		        else if(m == 1 && n == 2 || m == 2 && n == 1 || m == 3 && n == 0 || m == 4 && n == 7 || m == 5 && n == 6 || m == 6 && n == 5){ //supposed to be pink
							System.out.print("\u001b[0;47m|_|\u001b[0m");
						}
		        else if(m == 1 && n == 3 || m == 2 && n == 0 || m == 3 && n == 5 || m == 4 && n == 2 || m == 5 && n == 7 || m == 6 && n == 4){ //green
							System.out.print("\u001b[1;42m|_|\u001b[0m");
						}
		        else if(m == 1 && n == 4 || m == 2 && n == 7 || m == 3 && n == 2 || m == 4 && n == 5 || m == 5 && n == 0 || m == 6 && n == 3){ //blue
							System.out.print("\u001b[1;44m|_|\u001b[0m");
						}
		        else if(m == 1 && n == 5 || m == 2 && n == 6 || m == 3 && n == 7 || m == 4 && n == 0 || m == 5 && n == 1 || m == 6 && n == 2){ //yellow
							System.out.print("\u001b[1;43m|_|\u001b[0m");
						}
		        else if(m == 1 && n == 6 || m == 2 && n == 5 || m == 3 && n == 4 || m == 4 && n == 3 || m == 5 && n == 2 || m == 6 && n == 1){ //black
							System.out.print("\u001b[1;40m|_|\u001b[0m");
						}
		        else if(m == 1 && n == 7 || m == 2 && n == 4 || m == 3 && n == 1 || m == 4 && n == 6 || m == 5 && n == 3 || m == 6 && n == 0){ //cyan
							System.out.print("\u001b[1;46m|_|\u001b[0m");
						}
					}
			System.out.println();
		}
	}

	public void updateInterface(String piece, String square){
		updatedSquares.add(square);

		Coordinates coord = new Coordinates();
		coord.stringToXY(square);

		int colour = -1;

		if(piece.startsWith("W")){
			colour = 0;
		}
		else if(piece.startsWith("B")){
			colour = 1;
		}

		System.out.println("         Kamisado");
		System.out.println("   A  B  C  D  E  F  G  H");
		for(int m = 0; m <8; m++){
			System.out.print(m + " ");
			for(int n = 0; n < 8; n++){
				if(m == 0){
					System.out.print("||");
				}
				else if(m == 7){
					System.out.println("||");
				}
				else{
					System.out.print("|_|");
				}
			}
			System.out.println();
			}

	}

	public static void isQuit(String input){
		String quit = input.toUpperCase();
		if(quit.equals("Q")){
			System.out.println("Goodbye");
			System.exit(0);
		}
	}
}
