package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.text.Position;

import engine.*;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.world.*;

public class EndWindow extends JFrame implements ActionListener{
	
	private JButton endButton;
	
	public EndWindow(Player winningPlayer) {
		this.setTitle("MARVEL: Ultimate War - Winning Screen");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(700,393);
		this.setIconImage(new ImageIcon("iconlogo.png").getImage());
		this.setLayout(null);
		this.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
//		Main MOVEABLE panel
		JPanel mainPanel = new MovablePanel(this);
		mainPanel.setLayout(null);
		mainPanel.setBounds(0,0,700,393);
		this.add(mainPanel);
		
		JLabel mainLabel = new JLabel(winningPlayer.getName() + " is VICTORIOUS!");
		mainLabel.setIcon(new ImageIcon("victorious.jpg"));
		mainLabel.setBounds(0,0,700,393);
		mainLabel.setHorizontalTextPosition(JLabel.CENTER);
		mainLabel.setVerticalTextPosition(JLabel.CENTER);
		mainLabel.setForeground(new Color(0xFFD700));
		mainLabel.setFont(new Font("Comic Sans",Font.BOLD,30));
		mainLabel.setLayout(null);
		mainPanel.add(mainLabel);
		
		endButton = new JButton("Close Game");
		endButton.setBounds(300,300,100,35);
		endButton.setFont(new Font("Comic Sans",0,15));
		endButton.setBackground(Color.CYAN);
		endButton.setForeground(Color.WHITE);
		endButton.setFocusable(false);
		endButton.setBorder(BorderFactory.createEtchedBorder());
		endButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		endButton.addActionListener(this);
		mainLabel.add(endButton);
		
		this.revalidate();
		this.repaint();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Player p = new Player("Mahmoud");
		new EndWindow(p);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==endButton) {
			this.setVisible(false); //can't see frame
			this.dispose(); //kill frame
		}
		
	}

}
