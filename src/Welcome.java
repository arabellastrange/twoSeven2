import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.ArrayList;

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

public class Welcome extends Frame implements ActionListener {
	Player playerOne = new Player();
	JFrame first;
	JPanel welcome;
	JTextField accountName;
	
	public Welcome(){
		welcome();
	}
	
	public void welcome(){	
		first = new JFrame("Welcome");
		welcome =  new JPanel();
		
		JButton start = new JButton("Start");
		start.addActionListener(this);
		
		accountName = new JTextField(20);
		JLabel message = new JLabel();
		message.setText("Welcome to Kamisado, please enter your name");
		
		welcome.add(message);
		welcome.add(accountName);
		welcome.add(start);
		welcome.setVisible(true);
		
		first.add(welcome);
		first.setVisible(true);
		first.setSize(300, 150);
		first.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String account = accountName.getText();
		playerOne.setName(account);
		first.setVisible(false);
		//close this window and open next one
		Choice gui = new Choice(account);	
	}
}
