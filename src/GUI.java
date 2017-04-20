import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class GUI extends Frame implements ActionListener, MouseMotionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	
	Observer observer;
	//HumanPlayer playerOne = new HumanPlayer();
	//HumanPlayer playerTwo = new HumanPlayer();
	//AIPlayer AI = new AIPlayer();
	Settings timerSettings = new Settings();
	Interface gameInterface = new Interface();
	
	ArrayList<Rectangle> gamePieces =  new ArrayList<Rectangle>();
	ArrayList<BufferedImage> draggedPieces = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> wPieces = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> bPieces = new ArrayList<BufferedImage>();
	BufferedImage dragged;
	Point lastLoc;
	int piY;
	int piX;
	
	ArrayList<String> gameSettings = new ArrayList<String>();
	String playerOneName;
	String playerTwoName; // add to array 
	String opOption;
	String aiDiff;
	String time;
	double timeLength; // add to array
	String playerColour;
	
	
	String gameMode;
	String piece; //translate x,y to piece id and square position
	String square; 
	
	JFrame screen;
	JLabel updates;	
	
	
	public GUI(){
		welcome();
	}
	
	//One method per screen
	public void welcome(){	
		final JTextField accountName = new JTextField(20);
		updates = new JLabel();
		updates.setText("Watch this space for feedback through out your game!");
		updates.setForeground(Color.WHITE);
		screen = new JFrame("Welcome");
		
		JPanel welcome =  new JPanel();
		JPanel footer = new JPanel();
		
		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerOneName = accountName.getText();
				observer.getCurrentState().createPlayer(1);
				observer.getCurrentState().getPlayer(1).setName(playerOneName);
				welcome.setVisible(false);
				updates.setText("Player one created!");
				choose(playerOneName);	
			}
		});
		
		JLabel message = new JLabel();
		message.setText("Welcome to Kamisado, please enter your name");
		message.setForeground(Color.WHITE);
		
		welcome.add(message);
		welcome.add(accountName);
		welcome.add(start);
		welcome.setVisible(true);
		welcome.setBackground(Color.darkGray);
		
		footer.add(updates);
		footer.setVisible(true);
		footer.setBackground(Color.darkGray);
		
		screen.add(welcome);
		screen.add(footer, BorderLayout.PAGE_END); //--> overwrites welcome panel deal with it?
		screen.setVisible(true);
		//screen.setSize(300, 150);
		screen.pack();
		//screen.getContentPane().setBackground(Color.darkGray);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
	public void choose(String account){
		JPanel choice = new JPanel();
		
		JLabel message = new JLabel();
		message.setText("Hi " + account + " do you wish to play against Human or AI?");
		message.setForeground(Color.WHITE);
		
		JButton human = new JButton("Human");
		JButton ai = new JButton("AI");
		human.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opOption = "H";
				choice.setVisible(false);
				updates.setText("You are playing against another human.");
				playerTwo();
			}
		});
		
		ai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opOption = "A";
				choice.setVisible(false);
				updates.setText("You are playing against AI");
				diffculty();
			}
		});
		
		choice.add(message);
		choice.add(human);
		choice.add(ai);
		choice.setVisible(true);
		choice.setBackground(Color.DARK_GRAY);
		
		screen.add(choice);
	}
	
	public void diffculty(){
		JPanel diffculty = new JPanel();
		JLabel message = new JLabel();
		message.setText("Select a diffculty level!");
		message.setForeground(Color.WHITE);
		
		JButton easy = new JButton("Easy");
		JButton hard = new JButton("Hard");
		
		easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// call easy ai
				aiDiff = "E";
				diffculty.setVisible(false);
				chooseColour();
			}
		});
		
		hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call difficult ai
				aiDiff = "D";
				diffculty.setVisible(false);
				chooseColour();
			}
		});
		
		gameMode = "A";
		
		diffculty.add(message);
		diffculty.add(easy);
		diffculty.add(hard);
		diffculty.setVisible(true);
		diffculty.setBackground(Color.DARK_GRAY);
		
		screen.add(diffculty);
		
	}
	
	public void playerTwo(){
		final JTextField oppName = new JTextField(20);
		JPanel playertwo = new JPanel();
		
		JLabel message = new JLabel();
		message.setText("Player two please enter you name: ");
		message.setForeground(Color.WHITE);
		
		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerTwoName = oppName.getText();
				observer.getCurrentState().createPlayer(2);
				observer.getCurrentState().getPlayer(2).setName(playerTwoName);
				playertwo.setVisible(false);
				updates.setText("Player two created!");	
				chooseColour();
			}
		});
		
		gameMode = "H";
		
		playertwo.add(message);
		playertwo.add(oppName);
		playertwo.add(next);
		playertwo.setVisible(true);
		playertwo.setBackground(Color.DARK_GRAY);
		
		screen.add(playertwo);
		
	}
	
	public void chooseColour(){
		JPanel colour = new JPanel();
		JButton white = new JButton("White");
		JButton black = new JButton("Black");
		white.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerColour = "W";
				observer.getCurrentState().getPlayer(1).setColour(playerColour);
				observer.getCurrentState().getPlayer(2).setColour("B");
				observer.getCurrentState().getPlayer(0).setColour("B");
				colour.setVisible(false);
				drawBoard();
			}
		});
		
		black.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerColour = "B";
				observer.getCurrentState().getPlayer(1).setColour(playerColour);
				observer.getCurrentState().getPlayer(2).setColour("W");
				observer.getCurrentState().getPlayer(0).setColour("W");
				colour.setVisible(false);
				drawBoard();
			}
		});
		
		JLabel message = new JLabel();
		message.setText(playerOneName + " choose your colour!");
		message.setForeground(Color.white);
		
		colour.add(message);
		colour.add(white);
		colour.add(black);
		colour.setBackground(Color.darkGray);
		colour.setVisible(true);
		screen.add(colour);
		
	}
	
	public void drawBoard(){
		JPanel gameScreen = new JPanel();
		JPanel board = null;
		JPanel extra = null;
		JButton loadGame = new JButton("Load Game");
		JButton settings = new JButton("Settings");
		JButton reset = new JButton("Reset");
		JButton undo = new JButton("Undo");
		JButton save = new JButton("Save");
		JButton quit = new JButton("Quit");
		
		JToolBar toolbar = new JToolBar();
		
		toolbar.add(loadGame);
		loadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();
				updates.setText("Game loaded!");
			}
		});
		toolbar.add(settings);
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settings();
			}
		});
		toolbar.add(reset); 
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawBoard();
			}
		});
	    toolbar.add(undo);
	    undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gameMode.equals("A")){
					observer.getCurrentState().undoMove();
					updates.setText("Move undone");
				}
				else{
					updates.setText("Sorry you cant undo moves in two player mode");
				}
				
			}
		});
	    toolbar.add(save);
	    save.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		save();
				updates.setText("Game saved");
	    	}
	    });
	    toolbar.add(quit);
	    quit.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
				//rw.storeSettings(gameSettings);
	    		updates.setText("Goodbye");
				System.exit(0);
	    	}
	    });
	    
	    toolbar.setVisible(true);
	    toolbar.setFloatable(false);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    toolbar.setBounds(0, 0, screenSize.width, 100);
	    
	    try {
	    	final BufferedImage gboard = ImageIO.read(new File("cutsomGameBoard.jpg"));
	    	final BufferedImage featPanel = ImageIO.read(new File("extraPanel.png"));
	    	
	    	//load white game pieces
	    	wPieces.add(ImageIO.read(new File("rect-lightOrange.png")));
	    	wPieces.add(ImageIO.read(new File("rect-navy.png")));
	    	wPieces.add(ImageIO.read(new File("rect-maroon.png")));	
	    	wPieces.add(ImageIO.read(new File("rect-lilac.png")));
	    	wPieces.add(ImageIO.read(new File("rect-yellow.png")));
	    	wPieces.add(ImageIO.read(new File("rect-orange.png")));
	    	wPieces.add(ImageIO.read(new File("rect-green.png")));
	    	wPieces.add(ImageIO.read(new File("rect-brown.png")));

	    	//load black game pieces
	    	bPieces.add(ImageIO.read(new File("rev-brown.png")));
	    	bPieces.add(ImageIO.read(new File("rev-green.png")));
	    	bPieces.add(ImageIO.read(new File("rev-orange.png")));
	    	bPieces.add(ImageIO.read(new File("rev-yellow.png")));
	    	bPieces.add(ImageIO.read(new File("rev-lilac.png")));
	    	bPieces.add(ImageIO.read(new File("rev-maroon.png")));
	    	bPieces.add(ImageIO.read(new File("rev-navy.png")));
	    	bPieces.add(ImageIO.read(new File("rev-lighOrange.png")));
	    	
	    	board = new JPanel(){
	            protected void paintComponent(Graphics g) {
	            	Graphics2D g2 = (Graphics2D) g;
	                super.paintComponent(g);
	                g.drawImage(gboard, 0, 0, this);
	                int x = 5;
	                int y = 5;
	                for(int i = 0; i <8; i++){
	                	g.drawImage(wPieces.get(i), x, y, this);
	                	x += 60;
	                }
	                x = 5;
	                y = 425;
	                for(int i = 0; i <8; i++){
	                	g.drawImage(bPieces.get(i), x, y, this);
	                	x += 60;
	                }
	                
	                for(int i = 0; i < draggedPieces.size()-1; i++)
	        			if(draggedPieces.get(i) != null){
	        				g.drawImage(draggedPieces.get(i), piX, piY, this);
	        		}
	                
	        		//draw rect pieces
	        		for(int i = 5; i < 480; i+= 60){	
	        			Rectangle b = new Rectangle(i, 425, 40, 40);
	        			gamePieces.add(b);
	        			g2.draw(b);
	        		}
	        		
	        		for(int i = 5; i < 480; i+= 60){
	        			Rectangle w = new Rectangle(i, 5, 40, 40);
	        			gamePieces.add(w);
	        			g2.draw(w);
	        		}
	                
	            }
	            @Override
	            public Dimension getPreferredSize() {
	                return new Dimension(480, 480);
	            }   
			};
			extra = new JPanel(){
				protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                g.drawImage(featPanel, 0, 0, this);
	            }
				@Override
	            public Dimension getPreferredSize() {
	                return new Dimension(480, 480);
	            }
			};
			
		}
	    catch (IOException e) {
			e.printStackTrace();
		}
	    
	    JPanel keyInput = new JPanel();
	    
	    JLabel message = new JLabel();
	    message.setText("Enter the piece to move and the square to move to, comma seperated: ");
	    message.setForeground(Color.white);
	    
	    JTextField pieceSquare = new JTextField(10);
	    
	    JButton move = new JButton("Move");
	    move.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		piece = pieceSquare.getText().substring(0,2);
	    		square = pieceSquare.getText().substring(3,5);
	    		System.out.println("Piece from text field " + piece);
	    		System.out.println("Square from text field " + square);
	    		if(!gameMode.startsWith("A")){
	    			if(movePiece()){
	    				updates.setText("Success!");
	    			}
	    			switchPlayer();
	    		}
	    		else{
	    			if(moveAIPiece()){
	    				updates.setText("Success!");
	    			}
	    		}
	    		
	    	}
	    });
	    
	    keyInput.add(message);
	    keyInput.add(pieceSquare);
	    keyInput.add(move);
	    keyInput.setBackground(Color.darkGray);
	    keyInput.setVisible(true);
	    gameScreen.add(toolbar, BorderLayout.PAGE_START); 
	    //gameScreen.add(extra, BorderLayout.LINE_START);
	    gameScreen.add(board, BorderLayout.LINE_END);
	    gameScreen.add(keyInput, BorderLayout.LINE_END);
	    gameScreen.setVisible(true);
	    gameScreen.setBackground(Color.DARK_GRAY);
	    gameScreen.setMinimumSize(new Dimension(520,520));
	    screen.add(gameScreen);
	}
	
	public void switchPlayer(){
		//deactivate current player activate next player
		if(gameMode.equals("H") || gameMode.equals("T") ){
			updates.setText("Its " + observer.getCurrentState().getActivePlayer() + "'s turn");
			playerColour = observer.getCurrentState().getActivePlayer().getColour();
		}
		else{
			updates.setText("It's the AI's turn");
		}	
	}
	
	public void gameOver(){
		JPanel congrats = new JPanel();
		JLabel message = new JLabel();
		message.setText("Congrats! You won.");
		message.setForeground(Color.WHITE);
		
		congrats.add(message);
		congrats.setVisible(true);
		congrats.setBackground(Color.DARK_GRAY);
		screen.add(congrats);
		System.exit(0);
	}
	
	public void settings(){
		JFrame settings = new JFrame("Settings");
		JPanel timer = new JPanel();
		JLabel message = new JLabel();
		message.setText("Enter the timer value you would like to use (in seconds):");
		message.setForeground(Color.WHITE);
		
		final JTextField timeSecs = new JTextField(5);
		JButton set = new JButton("Set");
		set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					time = "Y";
					timeLength = Integer.valueOf(timeSecs.getText()); //doesnt handle unexpected input well yet
					timerSettings.setTimer(timeLength);
					if(gameMode.equals("A")){
						gameMode = "AT";
					}
					else{
						gameMode = "T";
					}
					
					settings.setVisible(false);
			}
		});
		
		timer.add(message);
		timer.add(timeSecs);
		timer.add(set);
		timer.setBackground(Color.DARK_GRAY);
		
		settings.add(timer);
		settings.pack();
		settings.setBackground(Color.darkGray);
		settings.setVisible(true);
		settings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void load(){
		//loads settings but after you did all the work so?
		gameSettings = gameInterface.loadGame();
		
		if(gameSettings.size() == 4){
			playerOneName = gameSettings.get(0); //name
			opOption = gameSettings.get(1); //opponent 
			time = gameSettings.get(2); // check options and split depeneding 
			playerColour = gameSettings.get(3);
			if(playerColour.equals("W")){
				observer.getCurrentState().getPlayer(0).setColour("B");
			}
			else{
				observer.getCurrentState().getPlayer(0).setColour("W");
			}
			gameMode = "A";
		}
		
		else if(gameSettings.size() == 5){
			playerOneName = gameSettings.get(0);
			playerTwoName = gameSettings.get(1);
			opOption = gameSettings.get(2);
			time = gameSettings.get(3);
			playerColour = gameSettings.get(4); //works as long as no one plays speed mode in AI lol
			if(playerColour.equals("W")){
				observer.getCurrentState().getPlayer(2).setColour("B");
			}
			else{
				observer.getCurrentState().getPlayer(2).setColour("W");
			}
			gameMode = "H";
		}
		else{
			playerOneName = gameSettings.get(0);
			playerTwoName = gameSettings.get(1);
			opOption = gameSettings.get(2);
			time = gameSettings.get(3);
			timeLength = Double.parseDouble(gameSettings.get(4));
			playerColour = gameSettings.get(5);
			if(playerColour.equals("W")){
				observer.getCurrentState().getPlayer(2).setColour("B");
			}
			else{
				observer.getCurrentState().getPlayer(2).setColour("W");
			}
			
			timerSettings.setTimer(timeLength);
			gameMode = "T";
		}		
		observer.getCurrentState().getPlayer(1).setColour(playerColour);
	}
	
	public void save(){
		gameInterface.store(gameSettings);
	}
	
	public boolean movePiece(){
		if(gameMode.equals("H")){
			System.out.println("human mode piece from move piece method" + piece);
	    	System.out.println("human mode square from move piece method" + square);
			if(gameInterface.play(piece, square, playerColour)){
				//return gameInterface.isGameOver(); that will return false even when move is valid sort it out
				return true;
			}
		}
		else if(gameMode.equals("T")){
			System.out.println("timed mode piece from move piece method " + piece);
	    	System.out.println("timed mode square from move piece method " + square);
			if(gameInterface.playTimed(piece, square, playerColour, timeLength)){
				//return gameInterface.isGameOver(); 
				return true;
			}
		}
		return false;
	}

	public boolean moveAIPiece(){
		if(gameMode.equals("A")){
			if(gameInterface.play(piece, square, playerColour)){
				//update interface here??
				if(aiDiff.equals("E")){
					return gameInterface.playAI(piece, square, playerColour);
				}
				else{
					return gameInterface.playHardAI(piece, square, playerColour);
				}
			}
		}
		else{
			if(gameInterface.playTimed(piece, square, playerColour, timeLength)){
				if(aiDiff.equals("E")){
					return gameInterface.playAI(piece, square, playerColour);
				}
				else{
					return gameInterface.playHardAI(piece, square, playerColour);
				}
			}
		}
		return false;
	}
	
	public void updateBroad(){
		//idek
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	public void mouseClicked(MouseEvent e) {

		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent e) {      
        for(BufferedImage img: wPieces){
     	   for(Rectangle r: gamePieces){
         	   if(r.contains(e.getPoint())){
         		   System.out.println("in");
         		   dragged = img;
         		   //piece =  this one but to string
         		   lastLoc = e.getPoint();
         		   break;
         	   }
         	   else{
         		   System.out.println("in");
         	   }
     	   }
        }
     }
	
 	public void mouseReleased(MouseEvent e) {
 			piX = e.getX(); 
     		piY = e.getY();
     		//square = this one but to string
     		
     		//if(gameInterface().movePiece()){
     			draggedPieces.add(dragged);
     		//}
     		dragged = null;
     		lastLoc = null;
            System.out.println("release");
 		
 	}
 	
 	public void mouseDragged(MouseEvent e) {
 		System.out.println("X is: " + e.getX()); 
 		System.out.println("y is: " + e.getY());
 		Graphics g = this.getGraphics();
			if (dragged != null) {
				Graphics2D g2 = (Graphics2D) g;
				AffineTransform at = new AffineTransform();
				at.translate(e.getX() - lastLoc.x, e.getY() - lastLoc.y);
				lastLoc = e.getPoint();
				g2.drawImage(dragged, at, null);
				System.out.println("dragged");
			}	
 	}
 
}
