import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class GUI extends Frame implements ActionListener {
	Observer observer;
	ReadWrite rw = new ReadWrite();
	HumanPlayer playerOne = new HumanPlayer();
	HumanPlayer playerTwo = new HumanPlayer();
	AIPlayer AI = new AIPlayer();
	
	JFrame screen;
	JLabel updates;	
	
	public GUI(){
		welcome();
	}
	
	//One method per screen
	public void welcome(){	
		final JTextField accountName = new JTextField(20);
		updates = new JLabel();
		updates.setText("Watch this sace for feedback through out your game!");
		updates.setForeground(Color.WHITE);
		screen = new JFrame("Welcome");
		JPanel welcome =  new JPanel();
		
		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String account = accountName.getText();
				playerOne.setName(account);
				welcome.setVisible(false);
				updates.setText("Player one created!");
				choose(account);	
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
		
		screen.add(welcome);
		//screen.add(updates); --> overwrites welcome panel deal with it
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
				String opOption = "H";
				choice.setVisible(false);
				updates.setText("You are playing against another human.");
				playerTwo();
			}
		});
		
		ai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opOption = "A";
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
				diffculty.setVisible(false);
				drawBoard();
			}
		});
		
		hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call difficult ai
				diffculty.setVisible(false);
				drawBoard();
			}
		});
		
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
				String account = oppName.getText();
				playerTwo.setName(account);
				playertwo.setVisible(false);
				updates.setText("Player two created!");	
				drawBoard();
			}
		});
		
		playertwo.add(message);
		playertwo.add(oppName);
		playertwo.add(next);
		playertwo.setVisible(true);
		playertwo.setBackground(Color.DARK_GRAY);
		
		screen.add(playertwo);
		
	}
	
	public void drawBoard(){
		JPanel gameScreen = new JPanel();
		JPanel board = null;
		JPanel extra = null;
		JButton newGame = new JButton("New Game");
		JButton settings = new JButton("Settings");
		JButton reset = new JButton("Reset");
		JButton undo = new JButton("Undo");
		JButton save = new JButton("Save");
		JButton quit = new JButton("Quit");
		
		JToolBar toolbar = new JToolBar();
		
		ArrayList<BufferedImage> wPieces = new ArrayList<BufferedImage>();
		ArrayList<BufferedImage> bPieces = new ArrayList<BufferedImage>();
		
		toolbar.add(newGame);
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawBoard();
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
				observer.getCurrentState().undoMove();
				updates.setText("Move undoed");
			}
		});
	    toolbar.add(save);
	    save.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
				//rw.storeSettings(gameSettings);
				rw.saveCurrentState();
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
	            }
	            @Override
	            public Dimension getPreferredSize() {
	                return new Dimension(480, 480);
	            }
	            //call interface()?? maybe
	            
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

	    gameScreen.add(toolbar, BorderLayout.PAGE_START); 
	    gameScreen.add(extra, BorderLayout.LINE_START);
	    gameScreen.add(board, BorderLayout.LINE_END);
	    gameScreen.setVisible(true);
	    gameScreen.setBackground(Color.DARK_GRAY);
	    screen.add(gameScreen);
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
					double time = Integer.valueOf(timeSecs.getText());
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
