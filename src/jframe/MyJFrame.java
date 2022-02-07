package jframe;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import project.CardGame;
import project.CupGame;

public class MyJFrame extends JFrame {
	
	Container contentPan;

	public MyJFrame() {
		
		this.setTitle("CardGame");
		this.setSize(1024, 768);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		contentPan = getContentPane();
	
//		contentPan.add(new CardGame());
		contentPan.add(new CupGame());
		
		
	}

	public static void main(String[] args) {
		new MyJFrame().setVisible(true);
	}
}