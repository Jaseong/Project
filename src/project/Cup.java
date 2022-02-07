package project;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Cup extends JButton {

	public ImageIcon cupIcon = new ImageIcon("images/cup.png");
	public JLabel cups;
	public boolean isball;
	public int x;
	public int y = 300;
	public int w = 150;
	public int h = 100;
	public int now;
	public int next;
	public int[] road;
	
	
	public Cup() {
		
		cups = new JLabel(cupIcon);
		cups.setBounds(0, 0, 150, 100);
		
		this.setIcon(cupIcon);
	}
}