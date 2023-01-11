package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.Border;

import engine.Game;
import engine.Player;

public class NameSelectionWindow extends JFrame implements ActionListener, MouseListener {
	
	private Clip clip;
	private boolean soundStopped;
	private JButton soundButton;
	private JButton exitButton;
	private JTextArea name1;	//Area for entering first player's name
	private JTextArea name2;	//Area for entering second player's name
	private boolean name1flag;
	private boolean name2flag;
	private JButton button1;	//Button for confirming first player's name
	private JButton button2;	//Button for confirming second player's name
	private boolean firstNameAcquired = false;
	private boolean secondNameAcquired = false;
	private String finalName1 = "";
	private String finalName2 = "";
	private JButton continueButton;
	
	public NameSelectionWindow() {
		super();
		
		this.setTitle("MARVEL: Ultimate War - Launcher");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(505,900);
		this.setIconImage(new ImageIcon("iconlogo.png").getImage());
		this.getContentPane().setBackground(Color.RED);
		this.setLayout(null);
		this.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		Border border = BorderFactory.createLineBorder(Color.WHITE,3);
		Border buttonBorder = BorderFactory.createEtchedBorder();
		Border emptyBorder = BorderFactory.createEtchedBorder();
		
//		Theme song
		File file = new File("themesong.wav");
		
		AudioInputStream audioStream;
		try {
			audioStream = AudioSystem.getAudioInputStream(file);
			try {
				clip = AudioSystem.getClip();
				clip.open(audioStream);
				
				clip.start(); //play song
			} 
			catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		Main MOVEABLE panel
		JPanel mainPanel = new MovablePanel(this);
		mainPanel.setLayout(null);
		mainPanel.setBounds(0,0,505,900);
		this.add(mainPanel);
		
//		Label to hold background image
		ImageIcon backgroundImg = new ImageIcon("nameselectionimg.jpg");
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setIcon(backgroundImg);
		backgroundLabel.setBounds(0,0,505,900);
		mainPanel.add(backgroundLabel);
		
//		Panel for settings (up top)
		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(null);
		settingsPanel.setBackground(new Color(0,0,0,123)); // --> transparent black
		settingsPanel.setBounds(0,0,505,50);
		backgroundLabel.add(settingsPanel);
		
//		Button to mute/play background music
		soundButton = new JButton();
		soundButton.setIcon(new ImageIcon("loudspeaker.png"));
		soundButton.setBounds(420,9,33,33);
		soundButton.setFocusable(true);
		soundButton.addActionListener(this);
		soundButton.setBackground(Color.WHITE);
		settingsPanel.add(soundButton);
		
//		Exit button to close window if necessary
		exitButton = new JButton();
		exitButton.setIcon(new ImageIcon("redcross.png"));
		exitButton.setBounds(460,9,33,33);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);
		exitButton.setBackground(new Color(0,0,0,123));
		settingsPanel.add(exitButton);
		
//		Entering first player's name
		name1 = new JTextArea(" Enter first player's name");
		name1.setForeground(Color.GRAY);
		name1.setBackground(new Color(255,255,255,255));
		name1.setOpaque(true);
		name1.setFont(new Font("Tsuki",Font.ITALIC,15));
		name1.setBorder(emptyBorder);
		name1.setBounds(40,260,300,35); 
		name1.setFocusable(false);
		name1.addMouseListener(this);
		backgroundLabel.add(name1);
		
		button1 = new JButton("CONFIRM");
		button1.setBounds(350,260,100,35); 
		button1.setBorder(buttonBorder);
		button1.setFont(new Font("Tsuki",Font.BOLD,15));
		button1.setForeground(Color.WHITE);
		button1.setBackground(Color.CYAN);
		button1.setFocusable(false);
		button1.addActionListener(this);
		button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundLabel.add(button1);
		
//		Entering second player's name
		name2 = new JTextArea(" Enter second player's name");
		name2.setForeground(Color.GRAY);
		name2.setBackground(new Color(255,255,255,255));
		name2.setOpaque(true);
		name2.setFont(new Font("Tsuki",Font.ITALIC,15));
		name2.setBorder(emptyBorder);
		name2.setBounds(40,310,300,35); //x, y, width, height
		name2.setFocusable(false);
		name2.addMouseListener(this);
		backgroundLabel.add(name2);
		
		button2 = new JButton("CONFIRM");
		button2.setBounds(350,310,100,35); //x, y, width, height
		button2.setBorder(buttonBorder);
		button2.setFont(new Font("Tsuki",Font.BOLD,15));
		button2.setForeground(Color.WHITE);
		button2.setBackground(Color.CYAN);
		button2.setFocusable(false);
		button2.addActionListener(this);
		button2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundLabel.add(button2);
	
//		Continue Button
		continueButton = new JButton("CONTINUE");
		continueButton.setBounds(150,700,200,40); //x, y, width, height
		continueButton.setBorder(buttonBorder);
		continueButton.setFont(new Font("Comic Sans",Font.BOLD,15));
		continueButton.setForeground(Color.WHITE);
		continueButton.setBackground(new Color(0x4000ff));
		continueButton.setFocusable(false);
		continueButton.setEnabled(false);
		continueButton.addActionListener(this);
		continueButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundLabel.add(continueButton);
		
		this.setVisible(true);
	}
	
	public String getFinalName1() {
		return finalName1;
	}

	public String getFinalName2() {
		return finalName2;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button1) {
			name1.setFont(new Font("Comic Sans",Font.BOLD,17));
			name1.setForeground(Color.BLACK);
			finalName1 = name1.getText();
			button1.setEnabled(false);
			name1.setEditable(false);
			firstNameAcquired = true;
		}
		else if(e.getSource()==button2) {
			name2.setFont(new Font("Comic Sans",Font.BOLD,17));
			name2.setForeground(Color.BLACK);
			finalName2 = name2.getText();
			button2.setEnabled(false);
			name2.setEditable(false);
			secondNameAcquired = true;
		}
		else if(e.getSource()==soundButton) {
			if(soundStopped) {
				soundButton.setIcon(new ImageIcon("loudspeaker.png"));
				clip.start();
				soundStopped = false;
			}
			else {
				soundButton.setIcon(new ImageIcon("crossedloudspeaker.png"));
				clip.stop();
				soundStopped = true;
			}
		}
		else if(e.getSource()==exitButton) {
			clip.stop();
			this.setVisible(false);
			this.dispose();
		}
		
		if(firstNameAcquired && secondNameAcquired) {
			continueButton.setEnabled(true);
		}
		if(e.getSource()==continueButton) {
			clip.stop();
			
			Player player1 = new Player(finalName1);
			Player player2 = new Player(finalName2);
			
			this.setVisible(false);
			this.dispose();
			new ChampionSelectionWindow(player1, player2);
		}
		revalidate();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==name1) {
			if(name1flag==false) {
				name1.setText("");
				name1.setFont(new Font("Comic Sans",0,17));
				name1.setForeground(Color.BLACK);
				name1.setFocusable(true);
	
				name1flag = true;
			}
		}
		else if(e.getSource()==name2) {
			if(name2flag==false) {
				name2.setText("");
				name2.setFont(new Font("Comic Sans",0,17));
				name2.setForeground(Color.BLACK);
				name2.setFocusable(true);
				
				name2flag = true;
			}
		}
		revalidate();
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==exitButton) {
			exitButton.setBackground(Color.GRAY);
		}
		revalidate();
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource()==exitButton) {
			exitButton.setBackground(Color.WHITE);
		}
		revalidate();
		repaint();
	}
	
	public static void main(String[] args) {
		new NameSelectionWindow();
		
	}

}
