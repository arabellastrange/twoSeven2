import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Choice extends Frame implements ActionListener{
	JFrame second;
	JPanel choice;
	public Choice(String account){
		choose(account);
	}
	
	public void choose(String account){
		
		second = new JFrame("Choose your opponent");
		choice = new JPanel();
		JLabel message = new JLabel();
		message.setText("Hi " + account + " do you wish to play against Human or AI?");
		
		JButton human = new JButton("Human");
		JButton ai = new JButton("AI");
		human.addActionListener(this);
		ai.addActionListener(this);
		
		choice.add(message);
		choice.add(human);
		choice.add(ai);
		second.add(choice);
		
		second.setVisible(true);
		second.setSize(350, 150);
		second.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
