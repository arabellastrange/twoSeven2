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
	Player playerOne = new Player();
	Player playerTwo = new Player();
	Player AI = new Player();
	
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
		
		welcome.add(message);
		welcome.add(accountName);
		welcome.add(start);
		welcome.setVisible(true);
		
		screen.add(welcome);
		//screen.add(updates); --> overwrites welcome panel deal with it
		screen.setVisible(true);
		//screen.setSize(300, 150);
		screen.pack();
		screen.setBackground(Color.darkGray);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
	public void choose(String account){
		JPanel choice = new JPanel();
		
		JLabel message = new JLabel();
		message.setText("Hi " + account + " do you wish to play against Human or AI?");
		
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
		screen.add(choice);
	}
	
	public void diffculty(){
		JPanel diffculty = new JPanel();
		JLabel message = new JLabel();
		message.setText("Select a diffculty level!");
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
		screen.add(diffculty);
		
	}
	
	public void playerTwo(){
		final JTextField oppName = new JTextField(20);
		JPanel playertwo = new JPanel();
		
		JLabel message = new JLabel();
		message.setText("Player two please enter you name: ");
		
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
		screen.add(playertwo);
		
	}
	
	public void drawBoard(){
		JPanel gameScreen = new JPanel();
		JPanel board = null;
		JPanel extra = null;
		
		JToolBar toolbar = new JToolBar();
		toolbar.add(new JButton("New Game"));
		toolbar.add(new JButton("Settings"));
		toolbar.add(new JButton("Reset")); 
	    toolbar.add(new JButton("Undo"));
	    toolbar.add(new JButton("Save"));
	    toolbar.add(new JButton("Quit"));
	    toolbar.setVisible(true);
	    toolbar.setFloatable(false);
	    try {
	    	final BufferedImage gboard = ImageIO.read(new File("cutsomGameBoard.jpg"));
	    	final BufferedImage featPanel = ImageIO.read(new File("extraPanel.png"));
	    	board = new JPanel(){
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                g.drawImage(gboard, 0, 0, null);
	            }
			};
			extra = new JPanel(){
				protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                g.drawImage(featPanel, 0, 0, null);
	            }
			};
			board.setSize(480, 480);
			extra.setSize(480, 320);
		}
	    catch (IOException e) {
			e.printStackTrace();
		}

	    gameScreen.add(toolbar, BorderLayout.PAGE_START);
	    gameScreen.add(board, BorderLayout.EAST);
	    gameScreen.add(extra, BorderLayout.WEST);
	    gameScreen.setVisible(true);
	    screen.add(gameScreen);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
