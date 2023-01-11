package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
import model.abilities.Ability;

public class SplashWindow extends JFrame {
	
	public SplashWindow() {
		this.setTitle("MARVEL: Ultimate War - Winning Screen");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(600,337);
		this.setIconImage(new ImageIcon("iconlogo.png").getImage());
		this.setLayout(null);
		this.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JLabel mainLabel = new JLabel();
		mainLabel.setIcon(new ImageIcon("thorintro.jpg"));
		mainLabel.setBounds(0,0,600,337);
		mainLabel.setHorizontalTextPosition(JLabel.LEFT);
		mainLabel.setVerticalTextPosition(JLabel.BOTTOM);
		mainLabel.setForeground(new Color(0xFFD700));
		mainLabel.setFont(new Font("Comic Sans",Font.BOLD,10));
		mainLabel.setLayout(null);
		this.add(mainLabel);
		
		JLabel loadingLabel = new JLabel("Starting . . .");
		loadingLabel.setBounds(20,300,100,25);
		loadingLabel.setFont(new Font("Comic Sans",Font.BOLD,12));
		loadingLabel.setBackground(new Color(0,0,0,0));
		loadingLabel.setForeground(Color.BLACK);
		loadingLabel.setOpaque(true);
		mainLabel.add(loadingLabel);
		
		this.setVisible(true);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				dispose();
				new NameSelectionWindow();
			}
		}, 5000);
				
		this.revalidate();
		this.repaint();
	}
}
