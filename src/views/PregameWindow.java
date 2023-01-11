package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import engine.Game;
import engine.Player;
import model.world.Champion;

public class PregameWindow extends JFrame implements ActionListener, MouseListener {
	
	private Game currentGame;
	private JButton startButton;
	
	public PregameWindow(Game currentGame) {
		super();
		
		//Update local variable to hold current game instance, to be used elsewhere in the class
		this.currentGame = currentGame;
		
		//Setup the window
		this.setTitle("MARVEL: Ultimate War - Pregame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(800,449);
		this.setIconImage(new ImageIcon("iconlogo.png").getImage());
		this.setBackground(new Color(0xb13d6c));
		this.setLayout(null);
		this.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		Border border = BorderFactory.createEtchedBorder(); //color,width
		Border emptyBorder = BorderFactory.createEtchedBorder();
		
		/* --------------------------------------------------------------------------------------------------------------------------------- */
		
//		Main MOVEABLE panel
		JPanel mainPanel = new MovablePanel(this);
		mainPanel.setLayout(null);
		mainPanel.setBounds(0,0,800,449);
		this.add(mainPanel);
		
//		Ironman header at the top
		ImageIcon headerIcon = new ImageIcon("ironmanspaceheader.jpg");
		JLabel header = new JLabel();
		header.setIcon(headerIcon);
		header.setFont(new Font("Comic Sans", Font.BOLD, 20));
		header.setForeground(Color.WHITE);
		header.setOpaque(true);
		header.setVerticalTextPosition(JLabel.BOTTOM);
		header.setBounds(0,0,800,449);
		mainPanel.add(header);
		
		JLabel description = new JLabel();
		description.setBounds(120,0,600,300); 
		description.setText("<html><font size=14 color=\"#f4eee2\">Tutorial:</font> <br><br> Our game has a color based theme. Player one is <font color=\"#003eb2\">blue</font> and player two is <font color=\"#FF0000\">red.</font> <br> The champion with the current turn to play will have a <font color=\"#00FF00\">green</font> color. <br> Inactive champions will have a <font color=\"FFFF00\">yellow</font> color.<br>Hover over any of the abilities or effects in the team panels to view their information, and hover over any of the covers on the board to view their HP.<br><br><br> Press the button below to start! </html>");
		description.setOpaque(true);
		description.setForeground(Color.WHITE);
		description.setBackground(new Color(0,0,0,0));	//new Color(0xb13d6c)
		//description.setBorder(emptyBorder);
		description.setVerticalTextPosition(JLabel.TOP);
		description.setHorizontalTextPosition(JLabel.LEFT);
		description.setFont(new Font("Comic Sans",Font.BOLD,15));
		header.add(description);
		
		JProgressBar progressBar = new JProgressBar(); 
		progressBar.setValue(0);
		progressBar.setForeground(new Color(0xFFD700));
		progressBar.setBackground(new Color(255,255,255,170));
		progressBar.setBounds(50,288,700,25);
		progressBar.setStringPainted(true);
		progressBar.setBorder(BorderFactory.createEtchedBorder());
		progressBar.setBorderPainted(false);
		header.add(progressBar);
		
		startButton = new JButton("Start Game");
		startButton.setBounds(350,340,100,50);
		startButton.setBorder(emptyBorder);
		startButton.setFont(new Font("Comic Sans",Font.BOLD,13));
		startButton.setBackground(new Color(0x4000ff));	//new Color(0xFFD700)
		startButton.setForeground(Color.WHITE);
		startButton.setFocusable(false);
		startButton.addActionListener(this);
		startButton.addMouseListener(this);
		startButton.setEnabled(false);
		startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		header.add(startButton);
		
		this.setVisible(true);
		
		Timer timer1 = new Timer();
		timer1.schedule(new TimerTask() {
			public void run() {
				progressBar.setValue(15);
				revalidate();	
				repaint();
			}
		}, 1500);
		
		Timer timer2 = new Timer();
		timer2.schedule(new TimerTask() {
			public void run() {
				progressBar.setValue(33);
				revalidate();	
				repaint();
			}
		}, 2500);
		
		Timer timer3 = new Timer();
		timer3.schedule(new TimerTask() {
			public void run() {
				progressBar.setValue(50);
				revalidate();	
				repaint();
			}
		}, 3500);
		
		Timer timer4 = new Timer();
		timer4.schedule(new TimerTask() {
			public void run() {
				progressBar.setValue(75);
				revalidate();	
				repaint();
			}
		}, 4500);
		
		Timer timer5 = new Timer();
		timer5.schedule(new TimerTask() {
			public void run() {
				progressBar.setValue(100);
				progressBar.setString("Ready to start");
				startButton.setEnabled(true);
				startButton.setFocusable(true);
			}
		}, 5500);
		
		revalidate();	
		repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==startButton) {
			this.setVisible(false); //can't see frame
			this.dispose(); //kill frame
			new GameWindow(currentGame);
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new PregameWindow(null);
	}

}
