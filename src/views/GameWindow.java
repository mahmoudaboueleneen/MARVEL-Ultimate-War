package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.text.Position;

import engine.*;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;
import model.world.*;

public class GameWindow extends JFrame implements ActionListener {
	private JPanel grid;
	private JPanel move;
	private JPanel attack;
	private JPanel turns;
	private JTextArea turntitle;
	private JPanel head1;
	private JPanel head2;
	private JPanel team1;
	private JPanel team2;
	private JPanel abilityControls;
	private JPanel buttomP;
	private JTextArea num;
	private JButton req;
	private JButton leader;
	private Game currentGame;
    private JButton moveUp;
	private JButton moveDown;
	private JButton moveRight;
	private JButton	moveLeft;
	private JButton atkUp;
	private JButton atkDown;
	private JButton atkRight;
	private JButton atkLeft;
	private JButton endTurnButton;
	private JTextArea turnDesc;
	private ArrayList<JLabel> turnComponents;
	private ArrayList<Champion> turnTemp;
	private JTextArea leaderusage;
	//private JButton 
	private JLabel b00 = new JLabel();
	private JLabel b10 = new JLabel();
	private JLabel b20 = new JLabel();
	private JLabel b30 = new JLabel();
	private JLabel b40 = new JLabel();
	//y zeros
	private JLabel b01 = new JLabel();
	private JLabel b11 = new JLabel();
	private JLabel b21 = new JLabel();
	private JLabel b31 = new JLabel();
	private JLabel b41 = new JLabel();
	//y ones
	private JLabel b02 = new JLabel();
	private JLabel b12 = new JLabel();
	private JLabel b22 = new JLabel();
	private JLabel b32 = new JLabel();
	private JLabel b42 = new JLabel();
	//y twos
	private JLabel b03 = new JLabel();
	private JLabel b13 = new JLabel();
	private JLabel b23 = new JLabel();
	private JLabel b33 = new JLabel();
	private JLabel b43 = new JLabel();
	//y threes
	private JLabel b04 = new JLabel();
	private JLabel b14 = new JLabel();
	private JLabel b24 = new JLabel();
	private JLabel b34 = new JLabel();
	private JLabel b44 = new JLabel();
	//y fours
	private ArrayList<JLabel> places = new ArrayList<JLabel>();
	Border blackline = BorderFactory.createLineBorder(Color.black);
	Border blueline = BorderFactory.createLineBorder(Color.blue,3);
	Border redline = BorderFactory.createLineBorder(Color.red,3);
	
	public GameWindow(Game currentGame) {
		super();
		this.currentGame = currentGame;
		this.setTitle("MARVEL: Ultimate War - Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(1440,800+28);
		this.setIconImage(new ImageIcon("iconlogo.png").getImage());
		this.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);	
		
//		Adding the grid top-left
		
		//Grid buttons
		grid = new JPanel();
		grid.setBounds(0,0,650,650);
		grid.setLayout(new GridLayout(5,5));
		grid.setBackground(Color.BLACK);
		grid.setBorder(blackline);
		
		places.add(b00);
		places.add(b01);
		places.add(b02);
		places.add(b03);
		places.add(b04);
		places.add(b10);
		places.add(b11);
		places.add(b12);
		places.add(b13);
		places.add(b14);
		places.add(b20);
		places.add(b21);
		places.add(b22);
		places.add(b23);
		places.add(b24);
		places.add(b30);
		places.add(b31);
		places.add(b32);
		places.add(b33);
		places.add(b34);
		places.add(b40);
		places.add(b41);
		places.add(b42);
		places.add(b43);
		places.add(b44);
		
//		Move control panel
		move = new JPanel(new GridLayout(1,2));
		move.setBounds(0,650,260,150);
		move.setBorder(blackline);
		move.setBackground(new Color(0xf4eee2));
		move.setLayout(null);

		JLabel moveLabel = new JLabel("Move Controls");
		moveLabel.setBounds(4,4,100,15);
		moveLabel.setOpaque(true);
		moveLabel.setFont(new Font("Comic Sans", Font.BOLD, 12));
		moveLabel.setForeground(new Color(0x964B00));
		moveLabel.setBackground(new Color(0xf4eee2));
		move.add(moveLabel);
		
		JLabel moveIconLabel = new JLabel();
		moveIconLabel.setIcon(new ImageIcon("footsteps.png"));
		moveIconLabel.setOpaque(true);
		moveIconLabel.setBounds(110,50,40,40);
		move.add(moveIconLabel);
		
		moveUp = new JButton();
		moveUp.setIcon(new ImageIcon("moveup.png"));
		moveUp.setBounds(110,10,40,40);
		moveUp.setPreferredSize(new Dimension(40,40));
		moveUp.setBackground(new Color(0xf4eee2));
		moveUp.setBorder(null);
		moveUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
		moveUp.addActionListener(this);
		move.add(moveUp);
		
		moveDown = new JButton();
		moveDown.setIcon(new ImageIcon("movedown.png"));
		moveDown.setBounds(110,90,40,40);
		moveDown.setPreferredSize(new Dimension(40,40));
		moveDown.setBackground(new Color(0xf4eee2));
		moveDown.setBorder(null);
		moveDown.setCursor(new Cursor(Cursor.HAND_CURSOR));
		moveDown.addActionListener(this);
		move.add(moveDown);

		moveLeft = new JButton();
		moveLeft.setIcon(new ImageIcon("moveleft.png"));
		moveLeft.setBounds(70,50,40,40);
		moveLeft.setPreferredSize(new Dimension(40,40));
		moveLeft.setBackground(new Color(0xf4eee2));
		moveLeft.setBorder(null);
		moveLeft.setCursor(new Cursor(Cursor.HAND_CURSOR));
		moveLeft.addActionListener(this);
		move.add(moveLeft);
		
		moveRight = new JButton();
		moveRight.setIcon(new ImageIcon("moveright.png"));
		moveRight.setBounds(150,50,40,40);
		moveRight.setPreferredSize(new Dimension(40,40));
		moveRight.setBackground(new Color(0xf4eee2));
		moveRight.setBorder(null);
		moveRight.setCursor(new Cursor(Cursor.HAND_CURSOR));
		moveRight.addActionListener(this);
		move.add(moveRight);

//		Attack control panel
		attack = new JPanel();
		attack.setBounds(260,650,260,150);
		attack.setBorder(blackline);
		attack.setBackground(Color.WHITE);
		attack.setLayout(null);
		
		JLabel atkLabel = new JLabel("Attack Controls");
		atkLabel.setOpaque(true);
		atkLabel.setBounds(4,4,100,15);
		atkLabel.setFont(new Font("Comic Sans", Font.BOLD, 12));
		atkLabel.setForeground(Color.RED);
		atkLabel.setBackground(Color.WHITE);
		attack.add(atkLabel);
		
		JLabel atkIconLabel = new JLabel();
		atkIconLabel.setIcon(new ImageIcon("sword.png"));
		atkIconLabel.setOpaque(true);
		atkIconLabel.setBounds(110,50,40,40);
		attack.add(atkIconLabel);
		
		atkUp = new JButton();
		atkUp.setIcon(new ImageIcon("moveup.png"));
		atkUp.setBounds(110,10,40,40);
		atkUp.setPreferredSize(new Dimension(40,40));
		atkUp.setBackground(new Color(0xf4eee2));
		atkUp.setBorder(null);
		atkUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
		atkUp.addActionListener(this);
		attack.add(atkUp);
		
		atkDown = new JButton();
		atkDown.setIcon(new ImageIcon("movedown.png"));
		atkDown.setBounds(110,90,40,40);
		atkDown.setPreferredSize(new Dimension(40,40));
		atkDown.setBackground(new Color(0xf4eee2));
		atkDown.setBorder(null);
		atkDown.setCursor(new Cursor(Cursor.HAND_CURSOR));
		atkDown.addActionListener(this);
		attack.add(atkDown);

		atkLeft = new JButton();
		atkLeft.setIcon(new ImageIcon("moveleft.png"));
		atkLeft.setBounds(70,50,40,40);
		atkLeft.setPreferredSize(new Dimension(40,40));
		atkLeft.setBackground(new Color(0xf4eee2));
		atkLeft.setBorder(null);
		atkLeft.setCursor(new Cursor(Cursor.HAND_CURSOR));
		atkLeft.addActionListener(this);
		attack.add(atkLeft);
		
		atkRight = new JButton();
		atkRight.setIcon(new ImageIcon("moveright.png"));
		atkRight.setBounds(150,50,40,40);
		atkRight.setPreferredSize(new Dimension(40,40));
		atkRight.setBackground(new Color(0xf4eee2));
		atkRight.setBorder(null);
		atkRight.setCursor(new Cursor(Cursor.HAND_CURSOR));
		atkRight.addActionListener(this);
		attack.add(atkRight);
		
		
		
//		Ability control panel
		abilityControls = new JPanel();
		abilityControls.setBounds(520,650,260,150);
		abilityControls.setLayout(null);
		abilityControls.setBackground(Color.WHITE);
		abilityControls.setBorder(blackline);
		
	//	Title holder
		JTextArea abtitle = new JTextArea("Ability Controls :");
		abtitle.setEditable(false);
		abtitle.setFont(new Font("Comic Sans", Font.BOLD, 12));
		abtitle.setBounds(4,4,100,15);
		abilityControls.add(abtitle);

	//	Button for using leader ability
		leader = new JButton("Use Leader Ability");
		leader.setBounds(0,20,260,60);
		leader.addActionListener(this);
		leader.setBackground(Color.CYAN);
		leader.setForeground(Color.WHITE);
		leader.setFont(new Font("Comic Sans", Font.BOLD, 14));
		leader.setFocusable(false);
		abilityControls.add(leader);
		
	//	Panel to house castAbilityBtn & enter ability no. text area
		buttomP = new JPanel();
		buttomP.setLayout(null);
		buttomP.setBounds(0,80,260,70);
		buttomP.setBackground(Color.WHITE);
		abilityControls.add(buttomP);
		
		req = new JButton("Cast ability num :");
		req.setBounds(0,0,160,60);
		req.setFocusable(false);
		req.setBackground(Color.BLUE);
		req.setForeground(Color.WHITE);
		req.addActionListener(this);
		buttomP.add(req);
		
		num = new JTextArea("Enter number (digit) of" + "\nability to be used, as" + "\nlisted in the team" + "\npanels on the right");
		num.setBorder(blackline);
		num.setFont(new Font("Comic Sans",0,9));
		num.setBounds(160,0,100,60);
		num.setFocusable(true);
		buttomP.add(num);
	
	//	Turn order section
		turntitle = new JTextArea("        Turn order");
		turntitle.setFont(new Font("Tsuki",Font.BOLD,13));
		turntitle.setBackground(new Color(0x88d8c0));
		turntitle.setEditable(false);
		turntitle.setBounds(650,0,130,15);
		
	//	Turn order panel
		turnComponents = new ArrayList<JLabel>();
		
		turns = new JPanel();
		turns.setLayout(null);
		turns.setBounds(650,15,130,645);
		turns.setBorder(blackline);
		turns.setBackground(new Color(0x93afa7));
		
		turnDesc = new JTextArea(currentGame.getCurrentChampion().getName() + "\n");
		turnDesc.setEditable(false);
		turnDesc.setForeground(Color.WHITE);
		turnDesc.setFont(new Font("Comic Sans",0,9));
		turnDesc.setBackground(new Color(0x93afa7));
		turnDesc.setBounds(1,1,50,100);
		turnDesc.setOpaque(true);
		turns.add(turnDesc);
		
		updateCurrentChampionDesc();
		
		JLabel turnLabel0 = new JLabel();
		turnLabel0.setBounds(50,2,80,100);
		turnLabel0.setBorder(BorderFactory.createLineBorder(new Color(0x5aac3b),4));
		turnLabel0.setBackground(Color.WHITE);
		turnLabel0.setOpaque(true);
		turnComponents.add(turnLabel0);
		turns.add(turnLabel0);
		
		JLabel turnLabel1 = new JLabel();
		turnLabel1.setBounds(25,103,80,100);
		turnLabel1.setBorder(blackline);
		turnLabel1.setBackground(Color.WHITE);
		turnLabel1.setOpaque(true);
		turnComponents.add(turnLabel1);
		turns.add(turnLabel1);
		
		JLabel turnLabel2 = new JLabel();
		turnLabel2.setBounds(25,204,80,100);
		turnLabel2.setBorder(blackline);
		turnLabel2.setBackground(Color.WHITE);
		turnLabel2.setOpaque(true);
		turnComponents.add(turnLabel2);
		turns.add(turnLabel2);
		
		JLabel turnLabel3 = new JLabel();
		turnLabel3.setBounds(25,305,80,100);
		turnLabel3.setBorder(blackline);
		turnLabel3.setBackground(Color.WHITE);
		turnLabel3.setOpaque(true);
		turnComponents.add(turnLabel3);
		turns.add(turnLabel3);
		
		JLabel turnLabel4 = new JLabel();
		turnLabel4.setBounds(25,406,80,100);
		turnLabel4.setBorder(blackline);
		turnLabel4.setBackground(Color.WHITE);
		turnLabel4.setOpaque(true);
		turnComponents.add(turnLabel4);
		turns.add(turnLabel4);
		
		JLabel turnLabel5 = new JLabel();
		turnLabel5.setBounds(25,507,80,100);
		turnLabel5.setBorder(blackline);
		turnLabel5.setBackground(Color.WHITE);
		turnLabel5.setOpaque(true);
		turnComponents.add(turnLabel5);
		turns.add(turnLabel5);
		
		updateTurnOrderDisplay();
		
		endTurnButton = new JButton("End Turn");
		endTurnButton.setBounds(0,610,130,25);
		endTurnButton.setFont(new Font("Comic Sans",Font.BOLD,15));
		endTurnButton.setBackground(Color.MAGENTA);
		endTurnButton.setForeground(Color.BLACK);
		endTurnButton.setFocusable(false);
		endTurnButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		endTurnButton.addActionListener(this);
		turns.add(endTurnButton);
		
//		Head one
		head1 = new JPanel(new BorderLayout(30,0));
		head1.setBounds(780,0,660,19);
		head1.setBorder(blackline);
		head1.setBackground(Color.blue);
		
	//	Team area
		JTextArea first = new JTextArea("Team 1");
		first.setEditable(false);
		first.setBorder(blackline);
		head1.add(first, BorderLayout.WEST);
		
	//	Name area
		JTextArea second = new JTextArea((currentGame.getFirstPlayer().getName()));
		second.setEditable(false);
		head1.add(second, BorderLayout.CENTER);
		
		
//		Head two
		head2 = new JPanel(new BorderLayout(30,0));
		head2.setBounds(780,400,660,19);
		head2.setBorder(blackline);
		head2.setBackground(Color.red);
		
	//	Team area
	    JTextArea first1 = new JTextArea("Team 2");
		first1.setEditable(false);
		first1.setBorder(blackline);
		head2.add(first1, BorderLayout.WEST);
		
	//	Name area
		JTextArea second1 = new JTextArea((currentGame.getSecondPlayer().getName()));
		second1.setEditable(false);
		head2.add(second1, BorderLayout.CENTER);
		
	//	Adjusting leader ability usage for both teams
		JTextArea leaderusage = new JTextArea("leader ability used");
		leaderusage.setEditable(false);
		head1.add(leaderusage, BorderLayout.EAST);
		head2.add(leaderusage, BorderLayout.EAST);
		leaderUsed();
		
//		Team panels
		team1 = new JPanel(new GridLayout(3, 1));
		team1.setBounds(780,19,660,381);
		team1.setBorder(blackline);
		
		team2 = new JPanel(new GridLayout(3, 1));
		team2.setBounds(780,419,660,381);
		team2.setBorder(blackline);
		
		loadTeams();
		
			//127 height for each champion
			//381 height for each team
		//19 height for each head
		
//		Adding champions panels to teams panels

	//	Placing buttons on the grid
		for(JLabel b : places) {
			b.setOpaque(true);
			b.setBorder(blackline);
			grid.add(b,-1);
		}
		scan();
		leaderUsed();
		this.add(grid);
		this.add(move);
		this.add(attack);
		this.add(abilityControls);
		this.add(turns);
		this.add(turntitle);
		this.add(head1);
		this.add(head2);
		this.add(team1);
		this.add(team2);
		this.setVisible(true);
		this.revalidate();
		this.repaint();
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==moveUp) {
			try {
					currentGame.move(Direction.DOWN);
			} 
			catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, "You do not have enough action points to move!", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			} 
			catch (UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(null, "Looks like you've wandered off where you shouldn't! You cannot move into this space.", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			}
			scan();
		}
		else if(e.getSource()==moveDown) {
			try {
					currentGame.move(Direction.UP);
			} 
			catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, "You do not have enough action points to move!", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			} 
			catch (UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(null, "Looks like you've wandered off where you shouldn't! You cannot move into this space.", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			}
			scan();
		}
		else if(e.getSource()==moveLeft) {
			try {
				currentGame.move(Direction.LEFT);
			} 
			catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, "You do not have enough action points to move!", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			} 
			catch (UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(null, "Looks like you've wandered off where you shouldn't! You cannot move into this space.", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			}
			scan();
		}
		else if(e.getSource()==moveRight) {
			try {
				currentGame.move(Direction.RIGHT);
			} 
			catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, "You do not have enough action points to move!", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			} 
			catch (UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(null, "Looks like you've wandered off where you shouldn't! You cannot move into this space.", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			}
			scan();
		}
		else if(e.getSource()==atkUp) {
			try {
				currentGame.attack(Direction.DOWN);
			} 
			catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, "You do not have enough action points to attack!", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			} 
			catch (ChampionDisarmedException e1) {
				JOptionPane.showMessageDialog(null, "Looks like your champion is disarmed, you can't attack right now!", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			} 
			catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, "Looks like you can't attack there, pick a valid target to attack!", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			}
			scan();
		}
		else if(e.getSource()==atkDown) {
			try {
				currentGame.attack(Direction.UP);
			} 
			catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, "You do not have enough action points to attack!", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			} 
			catch (ChampionDisarmedException e1) {
				JOptionPane.showMessageDialog(null, "Looks like your champion is disarmed, you can't attack right now!", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			} 
			catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, "Looks like you can't attack there, pick a valid target to attack!", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			}
			scan();
		}
		else if(e.getSource()==atkLeft) {
			try {
				currentGame.attack(Direction.LEFT);
			} 
			catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, "You do not have enough action points to attack!", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			} 
			catch (ChampionDisarmedException e1) {
				JOptionPane.showMessageDialog(null, "Looks like your champion is disarmed, you can't attack right now!", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			} 
			catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, "Looks like you can't attack there, pick a valid target to attack!", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			}
			scan();
		}
		else if(e.getSource()==atkRight) {
			try {
				currentGame.attack(Direction.RIGHT);
			} 
			catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, "You do not have enough action points to attack!", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			} 
			catch (ChampionDisarmedException e1) {
				JOptionPane.showMessageDialog(null, "Looks like your champion is disarmed, you can't attack right now!", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			} 
			catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, "Looks like you can't attack there, pick a valid target to attack!", "Whoops!", JOptionPane.ERROR_MESSAGE); //parent component, message, title, optionType
			}
			scan();
		}
		else if(e.getSource()==endTurnButton) {
			currentGame.endTurn();
			updateTurnOrderDisplay();
		}
		else if(e.getSource()==req) {
			int abn = Integer.parseInt(num.getText());
			
			if(abn<=currentGame.getCurrentChampion().getAbilities().size()) {	
				Ability a = currentGame.getCurrentChampion().getAbilities().get(abn-1);
				
				if(a.getCastArea().equals(AreaOfEffect.DIRECTIONAL)) {
					String [] ds = {"UP","DOWN","RIGHT","LEFT"}; 
					int k = -9 ;
					while(k==-9) {
					k = JOptionPane.showOptionDialog(null, "Pick a Direction","Direction Selection", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, ds, 0);
					}
					Direction d  = Direction.UP ;
					switch (k) {
					case 0 : d = Direction.DOWN ;break;
					case 1 : d = Direction.UP ;break;
					case 2 : d = Direction.RIGHT ;break;
					case 3 : d = Direction.LEFT ;break;
					}
					try {
							currentGame.castAbility(currentGame.getCurrentChampion().getAbilities().get(abn), d);
			    	}
					catch(NotEnoughResourcesException e1) {
						if(a.getManaCost()>currentGame.getCurrentChampion().getMana()) {
							JOptionPane.showMessageDialog(null, "You do not have enough mana to cast this ability!", "Whoops!", JOptionPane.ERROR_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "You do not have enough action points to cast this ability!", "Whoops!", JOptionPane.ERROR_MESSAGE);
						}
					}
					catch(AbilityUseException e1) {
						if(a.getCurrentCooldown()>0) {
							JOptionPane.showMessageDialog(null, "This ability is on cooldown, wait until you can cast it again!", "Whoops!", JOptionPane.ERROR_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "You can not cast an ability while you are silenced!", "Whoops!", JOptionPane.ERROR_MESSAGE);
						}
					}
					catch(CloneNotSupportedException e1) {
						//
						e1.printStackTrace();
					}
				}
				else if (a.getCastArea().equals(AreaOfEffect.SINGLETARGET)){
					JTextField field1 = new JTextField("0",5);
					JTextField field2 = new JTextField("0",5);

					JPanel myPanel = new JPanel();
					myPanel.add(new JLabel("( "));
					myPanel.add(field1);
					myPanel.add(Box.createHorizontalStrut(15)); // a spacer
					myPanel.add(new JLabel(" , "));
					myPanel.add(field2);
					myPanel.add(new JLabel(" )"));

					int result = JOptionPane.showConfirmDialog(null,myPanel,"Please Enter coordinates of the target",JOptionPane.OK_CANCEL_OPTION);
					
					if (result == JOptionPane.OK_OPTION) {
						try {
							currentGame.castAbility(a, Integer.parseInt(field2.getText()) , Integer.parseInt(field1.getText()));
					    }
					    catch(NotEnoughResourcesException e1){
					    	if(a.getManaCost()>currentGame.getCurrentChampion().getMana()) {
					    		JOptionPane.showMessageDialog(null, "You do not have enough mana to cast the ability","Whoops!", JOptionPane.ERROR_MESSAGE);
					    	}
					    	else {
					    		JOptionPane.showMessageDialog(null, "You do not have enough action points to cast the ability","Whoops!", JOptionPane.ERROR_MESSAGE);
							}
					    }
					    catch(AbilityUseException e1) {
					    	boolean flag = false;
					    	for(Effect efo : currentGame.getCurrentChampion().getAppliedEffects()) {
					    		if(efo.getName().equals("Silence")) {
					    			flag=true;
					    		}
					    	}
					    	if(a.getCurrentCooldown()>0) {
					    		JOptionPane.showMessageDialog(null, "You can not cast an ability while it is in cooldown","Whoops!", JOptionPane.ERROR_MESSAGE);
					    	}
					    	else if(flag) {
					    		JOptionPane.showMessageDialog(null, "You can not cast an ability while being silenced","Whoops!", JOptionPane.ERROR_MESSAGE);
					    	}
					    	else {
					    		JOptionPane.showMessageDialog(null, "Target out of the ability's cast range","Whoops!", JOptionPane.ERROR_MESSAGE);
					    	}
					    }
					    catch(InvalidTargetException e1) {
					    	String errorMessage = e1.getMessage(); 
					    	JOptionPane.showMessageDialog(null, errorMessage, "Whoops!", JOptionPane.ERROR_MESSAGE);
					    }
						catch(CloneNotSupportedException e1) {
							//
							e1.printStackTrace();
						}
					}
				}
				else {
					try {
						currentGame.castAbility(a);
					} catch (NotEnoughResourcesException e1) {
						if(a.getManaCost()>currentGame.getCurrentChampion().getMana()) {
							JOptionPane.showMessageDialog(null, "You do not have enough mana to cast this ability!", "Whoops!", JOptionPane.ERROR_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "You do not have enough action points to cast this ability!", "Whoops!", JOptionPane.ERROR_MESSAGE);
						}
					} catch (AbilityUseException e1) {
						if(a.getCurrentCooldown()>0) {
							JOptionPane.showMessageDialog(null, "This ability is on cooldown, wait until you can cast it again!", "Whoops!", JOptionPane.ERROR_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "You can not cast an ability while you are silenced!", "Whoops!", JOptionPane.ERROR_MESSAGE);
						}
					} catch (CloneNotSupportedException e1) {
						//
						e1.printStackTrace();
					}
				}
			}
		}
		else if(e.getSource()==leader) {
        	try {
				currentGame.useLeaderAbility();
			} catch (LeaderNotCurrentException e1) {
				JOptionPane.showMessageDialog(null, "A leader champion is not currently playing, you can't use the leader ability right now!", "Whoops!", JOptionPane.ERROR_MESSAGE);
			} catch (LeaderAbilityAlreadyUsedException e1) {
				JOptionPane.showMessageDialog(null, "You've already used your leader ability, you can't use it again right now!", "Whoops!", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(currentGame.getCurrentChampion().getCurrentActionPoints() <= 0) {
			currentGame.endTurn();
			updateTurnOrderDisplay();
		}
		
		updateCurrentChampionDesc();
		
		if(currentGame.checkGameOver() != null) {
			Player winningPlayer = currentGame.checkGameOver();
			
			this.setVisible(false);
			this.dispose();
			new EndWindow(winningPlayer);
		}
		
		//checks if leader ability is used for both teams
		leaderUsed();
		
		//updates teams panel
		loadTeams();
		
		revalidate();
		repaint();
	}
	
	//loading team pannels , should be called after casting ability or attacking
	public void loadTeams() {
		leaderUsed();
		//team 1 
		team1.removeAll();
			for(Champion ch : currentGame.getFirstPlayer().getTeam()) {
			JPanel ch0t1 = new JPanel(new GridLayout(3,1));
			ch0t1.setBounds(780,19,660,127);
			ch0t1.setBorder(blackline);
			ch0t1.setBackground(Color.blue);
			
			//green is the part responsible for general info
			
			JPanel green = new JPanel(new GridLayout(2,0));
			
			//green
			
			
			JTextArea name = new JTextArea(""+ch.getName());
			name.setEditable(false);
			name.setBorder(blackline);
			JTextArea hp = new JTextArea("HP:"+ch.getCurrentHP());
			hp.setEditable(false);
			hp.setBorder(blackline);
			JTextArea mana = new JTextArea("Mana:"+ch.getMana());
			mana.setEditable(false);
			mana.setBorder(blackline);
			JTextArea speed = new JTextArea("speed:"+ch.getSpeed());
			speed.setEditable(false);
			speed.setBorder(blackline);
			JTextArea maxActions = new JTextArea("maxAct:"+ch.getMaxActionPointsPerTurn());
			maxActions.setEditable(false);
			maxActions.setBorder(blackline);
			JTextArea damage = new JTextArea("dmg:"+ch.getAttackDamage());
			damage.setEditable(false);
			damage.setBorder(blackline);
			JTextArea range = new JTextArea("range:"+ch.getAttackRange());
			range.setEditable(false);
			range.setBorder(blackline);
			JTextArea type = new JTextArea() ;
			type.setBorder(blackline);
			JTextArea leader = new JTextArea() ;
			leader.setBorder(blackline);
			if(ch instanceof Hero) {
				type.setText("Hero");
			}else if(ch instanceof Villain) {
				type.setText("Villain");
			}else {
				type.setText("AntiHero");
			}
			type.setEditable(false);
			if(ch.equals(currentGame.getFirstPlayer().getLeader())) {
				leader.setText("Leader");
			}else {
				leader.setText("notLeader");
			}
			leader.setEditable(false);
			type.setEditable(false);
			green.add(name);
			green.add(hp);
			green.add(mana);
			green.add(speed);
			green.add(maxActions);
			green.add(damage);
			green.add(range);
			green.add(type);
			green.add(leader);
			
			ch0t1.add(green);
			
			//blue
			
			JPanel abilities = new JPanel(new GridLayout(2,0));
			JTextArea title = new JTextArea("Abilities : ");
			title.setBorder(blackline);
			title.setEditable(false);
			//JPanel ab = new JPanel(new GridLayout(1,0));
			abilities.add(title);
			//abilities.add(ab);
			int i = 1 ;
			for(Ability a : ch.getAbilities()) {
				
				String typea = null ;
				JTextArea a1 = new JTextArea(i+"- "+a.getName());
				a1.setBorder(blackline);
				if(a instanceof CrowdControlAbility) {
					CrowdControlAbility c = (CrowdControlAbility) a;
					typea = "CrowdControlAbility" ;
					a1.setToolTipText(""+typea+", "+a.getCastArea()+" ,range:"+a.getCastRange()+
							" ,mana:"+a.getManaCost()+", act.points:"+a.getRequiredActionPoints()+
							", currentCool:"+a.getCurrentCooldown()+" ,baseCool:"+a.getBaseCooldown()+
							" ,effect:"+c.getEffect().getName());
				}else if(a instanceof DamagingAbility) {
					DamagingAbility d = (DamagingAbility) a;
					typea = "DamagingAbility" ;
					a1.setToolTipText(""+typea+", "+a.getCastArea()+" ,range:"+a.getCastRange()+
							" ,mana:"+a.getManaCost()+", act.points:"+a.getRequiredActionPoints()+
							", currentCool:"+a.getCurrentCooldown()+" ,baseCool:"+a.getBaseCooldown()+
							", Dmg:"+d.getDamageAmount());
				}else {
					HealingAbility h = (HealingAbility) a;
					typea = "DamagingAbility" ;
					a1.setToolTipText(""+typea+", "+a.getCastArea()+" ,range:"+a.getCastRange()+
							" ,mana:"+a.getManaCost()+", act.points:"+a.getRequiredActionPoints()+
							", currentCool:"+a.getCurrentCooldown()+" ,baseCool:"+a.getBaseCooldown()+
							", heal:"+h.getHealAmount());
				}
				abilities.add(a1);
				i++;
			}
			ch0t1.add(abilities);
			
			JPanel effects = new JPanel(new GridLayout(2,0));
			JTextArea title1 = new JTextArea("Effects : ");
			title1.setEditable(false);
			title1.setBorder(blackline);
			effects.add(title1);
			for(Effect ef : ch.getAppliedEffects()) {
				JTextArea effect = new JTextArea(""+ef.getName());
				effect.setEditable(false);
				effect.setBorder(blackline);
		        effect.setToolTipText("duration:"+ef.getDuration());
		        effects.add(effect);
			}
			ch0t1.add(effects);
			team1.add(ch0t1);
		}
			
		//team 2 
		team2.removeAll();
		for(Champion ch : currentGame.getSecondPlayer().getTeam()) {
			JPanel ch0t2 = new JPanel(new GridLayout(3,1));
			ch0t2.setBounds(780,19,660,127);
			ch0t2.setBorder(blackline);
			ch0t2.setBackground(Color.blue);
			
			//green is the part responsible for general info
			
			JPanel green = new JPanel(new GridLayout(2,0));
			
			//green
			
			
			JTextArea name = new JTextArea(""+ch.getName());
			name.setEditable(false);
			name.setBorder(blackline);
			JTextArea hp = new JTextArea("HP:"+ch.getCurrentHP());
			hp.setEditable(false);
			hp.setBorder(blackline);
			JTextArea mana = new JTextArea("Mana:"+ch.getMana());
			mana.setEditable(false);
			mana.setBorder(blackline);
			JTextArea speed = new JTextArea("speed:"+ch.getSpeed());
			speed.setEditable(false);
			speed.setBorder(blackline);
			JTextArea maxActions = new JTextArea("maxAct:"+ch.getMaxActionPointsPerTurn());
			maxActions.setEditable(false);
			maxActions.setBorder(blackline);
			JTextArea damage = new JTextArea("dmg:"+ch.getAttackDamage());
			damage.setEditable(false);
			damage.setBorder(blackline);
			JTextArea range = new JTextArea("range:"+ch.getAttackRange());
			range.setEditable(false);
			range.setBorder(blackline);
			JTextArea type = new JTextArea() ;
			type.setBorder(blackline);
			JTextArea leader = new JTextArea() ;
			leader.setBorder(blackline);
			if(ch instanceof Hero) {
				type.setText("Hero");
			}else if(ch instanceof Villain) {
				type.setText("Villain");
			}else {
				type.setText("AntiHero");
			}
			type.setEditable(false);
			if(ch.equals(currentGame.getSecondPlayer().getLeader())) {
				leader.setText("Leader");
			}else {
				leader.setText("notLeader");
			}
			leader.setEditable(false);
			type.setEditable(false);
			green.add(name);
			green.add(hp);
			green.add(mana);
			green.add(speed);
			green.add(maxActions);
			green.add(damage);
			green.add(range);
			green.add(type);
			green.add(leader);
			
			ch0t2.add(green);
			
			//blue
			
			JPanel abilities = new JPanel(new GridLayout(2,0));
			JTextArea title = new JTextArea("Abilities : ");
			title.setEditable(false);
			title.setBorder(blackline);
			//JPanel ab = new JPanel(new GridLayout(1,0));
			abilities.add(title);
			//abilities.add(ab);
			int i = 1;
			for(Ability a : ch.getAbilities()) {
				String typea = null ;
				JTextArea a1 = new JTextArea(i+"- "+a.getName());
				a1.setBorder(blackline);
				if(a instanceof CrowdControlAbility) {
					CrowdControlAbility c = (CrowdControlAbility) a;
					typea = "CrowdControlAbility" ;
					a1.setToolTipText(""+typea+", "+a.getCastArea()+" ,range:"+a.getCastRange()+
							" ,mana:"+a.getManaCost()+", act.points:"+a.getRequiredActionPoints()+
							", currentCool:"+a.getCurrentCooldown()+" ,baseCool:"+a.getBaseCooldown()+
							" ,effect:"+c.getEffect().getName());
				}else if(a instanceof DamagingAbility) {
					DamagingAbility d = (DamagingAbility) a;
					typea = "DamagingAbility" ;
					a1.setToolTipText(""+typea+", "+a.getCastArea()+" ,range:"+a.getCastRange()+
							" ,mana:"+a.getManaCost()+", act.points:"+a.getRequiredActionPoints()+
							", currentCool:"+a.getCurrentCooldown()+" ,baseCool:"+a.getBaseCooldown()+
							", Dmg:"+d.getDamageAmount());
				}else {
					HealingAbility h = (HealingAbility) a;
					typea = "DamagingAbility" ;
					a1.setToolTipText(""+typea+", "+a.getCastArea()+" ,range:"+a.getCastRange()+
							" ,mana:"+a.getManaCost()+", act.points:"+a.getRequiredActionPoints()+
							", currentCool:"+a.getCurrentCooldown()+" ,baseCool:"+a.getBaseCooldown()+
							", heal:"+h.getHealAmount());
				}
				abilities.add(a1);
				i++;
			}
			ch0t2.add(abilities);
			
			JPanel effects = new JPanel(new GridLayout(2,0));
			JTextArea title1 = new JTextArea("Effects : ");
			title1.setBorder(blackline);
			title1.setEditable(false);
			effects.add(title1);
			for(Effect ef : ch.getAppliedEffects()) {
				JTextArea effect = new JTextArea(""+ef.getName());
				effect.setEditable(false);
				effect.setBorder(blackline);
		        effect.setToolTipText("duration:"+ef.getDuration());
		        effects.add(effect);
			}
			ch0t2.add(effects);
			team2.add(ch0t2);
		}
	}
	
	//Should call after any player uses leader ability
	public void leaderUsed() {
		//head1
		if(currentGame.isFirstLeaderAbilityUsed()) {
			JTextArea leaderusage = new JTextArea("leader ability used");
			leaderusage.setEditable(false);
			head1.add(leaderusage,BorderLayout.EAST);
		}else {
			JTextArea leaderusage = new JTextArea("leader ability not used");
			leaderusage.setEditable(false);
			head1.add(leaderusage,BorderLayout.EAST);
		}
		
		//head2
		if(currentGame.isSecondLeaderAbilityUsed()) {
			JTextArea leaderusage = new JTextArea("leader ability used");
			leaderusage.setEditable(false);
			head2.add(leaderusage,BorderLayout.EAST);
		}else {
			JTextArea leaderusage = new JTextArea("leader ability not used");
			leaderusage.setEditable(false);
			head2.add(leaderusage,BorderLayout.EAST);
		}
		repaint();
		revalidate();
	}
	
	//Should call this method after whatever action that may effect grid orientation
	public void scan() {
		int x1 , y1 ;
		// seticon null , settext null
		for(JLabel bxx : places) {
			int r = places.indexOf(bxx);
			if(r>19) {
				x1 = 4;
				y1 = r-19-1;
			}else if(r>14) {
				x1 = 3;
				y1 = r-14-1;
			}else if(r>9) {
				x1 = 2;
				y1 = r-9-1;
			}else if(r>4) {
				x1 = 1;
				y1 = r-4-1;
			}else {
				x1 = 0;
				y1 = r;
			}
			//may need to flip x and y again
			if(currentGame.getBoard()[x1][y1] instanceof Cover) {
			    bxx.setText(null);
			    bxx.setIcon(null);
			    bxx.setBorder(blackline);
				bxx.setIcon(new ImageIcon("COVER.jpeg"));
				Cover c = (Cover) currentGame.getBoard()[x1][y1];
				bxx.setToolTipText("HP: "+c.getCurrentHP());
			}else if(currentGame.getBoard()[x1][y1] instanceof Champion) {
				if(currentGame.getFirstPlayer().getTeam().contains((Champion)currentGame.getBoard()[x1][y1])) {
					bxx.setBorder(blueline);
				}else {
					bxx.setBorder(redline);
				}
				 bxx.setText(null);
				 bxx.setIcon(null);
				String name = ((Champion)currentGame.getBoard()[x1][y1]).getName();
				if(name.equals("Captain America")) {
					bxx.setIcon(new ImageIcon("captainam.jpg"));
				}else if(name.equals("Deadpool")) {
					bxx.setIcon(new ImageIcon("deadpool.jpg"));
				}else if(name.equals("Dr Strange")) {
					bxx.setIcon(new ImageIcon("drstrange.jpg"));
				}else if(name.equals("Electro")) {
					bxx.setIcon(new ImageIcon("electro.jpg"));
				}else if(name.equals("Ghost Rider")) {
					bxx.setIcon(new ImageIcon("ghostrider.jpg"));
				}else if(name.equals("Hela")) {
					bxx.setIcon(new ImageIcon("hela.jpg"));
				}else if(name.equals("Hulk")) {
					bxx.setIcon(new ImageIcon("hulk.jpg"));
				}else if(name.equals("Iceman")) {
					bxx.setIcon(new ImageIcon("iceman.jpg"));
				}else if(name.equals("Ironman")) {
					bxx.setIcon(new ImageIcon("ironman.jpg"));
				}else if(name.equals("Loki")) {
					bxx.setIcon(new ImageIcon("loki.jpg"));
				}else if(name.equals("Quicksilver")) {
					bxx.setIcon(new ImageIcon("quicksilver.jpg"));
				}else if(name.equals("Spiderman")) {
					bxx.setIcon(new ImageIcon("spiderman.jpg"));
				}else if(name.equals("Thor")) {
					bxx.setIcon(new ImageIcon("thor.jpg"));
				}else if(name.equals("Venom")) {
					bxx.setIcon(new ImageIcon("venom.jpg"));
				}else {
					//Yellow Jacket
					bxx.setIcon(new ImageIcon("yellowjacket.jpg"));
				}
				bxx.setToolTipText(name);
			}else {
				String c = "          ( "+x1+" , " +y1+" )" ;
				bxx.setText(c);
				bxx.setIcon(null);
				bxx.setBorder(blackline);
			}
		}
		repaint();
		revalidate();
	}
	
	
	/*
	 * Should call after ending any turn, either with endTurnButton or automatically
	 * ONLY WORKS WITH 6 CHAMPIONS SELECTED
	 */
	
	public void updateTurnOrderDisplay() {

		turnTemp = new ArrayList<Champion>();
		
		//Placeholder champion for null places in turnTemp
		Hero placeHolder = new Hero("Empty",0,0,0,0,0,0);
		turnTemp.add(0, placeHolder);
		turnTemp.add(1, placeHolder);
		turnTemp.add(2, placeHolder);
		turnTemp.add(3, placeHolder);
		turnTemp.add(4, placeHolder);
		turnTemp.add(5, placeHolder);

		//Filling turnTemp by emptying the turnOrder priority queue
		int length = currentGame.getTurnOrder().size();
		for(int i = 0; i<length; i++) {
			Champion c = (Champion) currentGame.getTurnOrder().remove();
			turnTemp.add(i, c);
			}
		
		//Filling turnOrder back again
		for(int i = 0; i<length; i++)
			currentGame.getTurnOrder().insert(turnTemp.get(i));
		
		for(int j = 0; j<6; j++) {
			if(turnTemp.get(j).getName().equals("Empty"))
				turnComponents.get(j).setIcon(null);
			else if(turnTemp.get(j).getName().equals("Captain America"))
				turnComponents.get(j).setIcon(new ImageIcon("captainam.jpg"));
			else if(turnTemp.get(j).getName().equals("Deadpool"))
				turnComponents.get(j).setIcon(new ImageIcon("deadpool.jpg"));
			else if(turnTemp.get(j).getName().equals("Dr Strange"))
				turnComponents.get(j).setIcon(new ImageIcon("drstrange.jpg"));
			else if(turnTemp.get(j).getName().equals("Electro"))
				turnComponents.get(j).setIcon(new ImageIcon("electro.jpg"));
			else if(turnTemp.get(j).getName().equals("Ghost Rider"))
				turnComponents.get(j).setIcon(new ImageIcon("ghostrider.jpg"));
			else if(turnTemp.get(j).getName().equals("Hela"))
				turnComponents.get(j).setIcon(new ImageIcon("hela.jpg"));
			else if(turnTemp.get(j).getName().equals("Hulk"))
				turnComponents.get(j).setIcon(new ImageIcon("hulk.jpg"));
			else if(turnTemp.get(j).getName().equals("Iceman"))
				turnComponents.get(j).setIcon(new ImageIcon("iceman.jpg"));
			else if(turnTemp.get(j).getName().equals("Ironman"))
				turnComponents.get(j).setIcon(new ImageIcon("ironman.jpg"));
			else if(turnTemp.get(j).getName().equals("Loki"))
				turnComponents.get(j).setIcon(new ImageIcon("loki.jpg"));
			else if(turnTemp.get(j).getName().equals("Quicksilver"))
				turnComponents.get(j).setIcon(new ImageIcon("quicksilver.jpg"));
			else if(turnTemp.get(j).getName().equals("Spiderman"))
				turnComponents.get(j).setIcon(new ImageIcon("spiderman.jpg"));
			else if(turnTemp.get(j).getName().equals("Thor"))
				turnComponents.get(j).setIcon(new ImageIcon("thor.jpg"));
			else if(turnTemp.get(j).getName().equals("Venom"))
				turnComponents.get(j).setIcon(new ImageIcon("venom.jpg"));
			else if(turnTemp.get(j).getName().equals("Yellow Jacket"))
				turnComponents.get(j).setIcon(new ImageIcon("yellowjacket.jpg"));
		}
		revalidate();
	}
	
	public void updateCurrentChampionDesc() {
		
		String sPlayerName = "";
		
		if(currentGame.getFirstPlayer().getTeam().contains(currentGame.getCurrentChampion()))
			sPlayerName = currentGame.getFirstPlayer().getName();
		else
			sPlayerName = currentGame.getSecondPlayer().getName();
		
		String sChampName = currentGame.getCurrentChampion().getName();
		
		String sType = "";
		if(currentGame.getCurrentChampion() instanceof Hero)
			sType = "Hero";
		else if(currentGame.getCurrentChampion() instanceof Villain)
			sType = "Villain";
		else if (currentGame.getCurrentChampion() instanceof AntiHero)
			sType = "AntiHero";
		
		String sHP = "HP " + currentGame.getCurrentChampion().getCurrentHP();
		
		String sMana = "Mana " + currentGame.getCurrentChampion().getMana();
		
		String sActionPoints = "Action pt " + currentGame.getCurrentChampion().getCurrentActionPoints();
		
		String sAttackDamage = "Dmg " + currentGame.getCurrentChampion().getAttackDamage();
		
		String sAttackRange = "Range " + currentGame.getCurrentChampion().getAttackRange() ;
		
		String finalString = sPlayerName + "\n" + sChampName + "\n" + sType + "\n" + sHP + "\n" + sMana + "\n" + sActionPoints + "\n" + sAttackDamage + "\n" + sAttackRange;
		
		turnDesc.setText(finalString);
		
	}
	
	public static void main(String[] args) throws NotEnoughResourcesException, UnallowedMovementException {
		Game g = new Game(new Player("LOLO"),new Player("FOFO"));
		g.getFirstPlayer().getTeam().add(new Hero("Captain America", 1, 1,500, 10, 1, 1));
		g.getFirstPlayer().getTeam().get(0).getAbilities().add(new DamagingAbility("hi", 12, 13, 20, AreaOfEffect.SURROUND, 0,
			50));
		g.getFirstPlayer().getTeam().add(new Hero("Captain America", 1, 1,500, 10, 1, 1));
		g.getFirstPlayer().getTeam().add(new Hero("Captain America", 1, 1,500, 10, 1, 1));
		g.getSecondPlayer().getTeam().add(new Hero("Hela", 1, 1, 500, 9, 1, 1));
		g.getSecondPlayer().getTeam().add(new Hero("Hela", 1, 1, 500, 9, 1, 1));
		g.getSecondPlayer().getTeam().add(new Hero("Hela", 1, 1, 500, 9, 1, 1));
		g.placeChampions();
		g.getTurnOrder().insert(g.getFirstPlayer().getTeam().get(0));
		g.getTurnOrder().insert(g.getFirstPlayer().getTeam().get(1));
		g.getTurnOrder().insert(g.getFirstPlayer().getTeam().get(2));
		g.getTurnOrder().insert(g.getSecondPlayer().getTeam().get(0));
		g.getTurnOrder().insert(g.getSecondPlayer().getTeam().get(1));
		g.getTurnOrder().insert(g.getSecondPlayer().getTeam().get(2));
		g.getBoard()[0][0]=null;
		g.getBoard()[1][0]=null;
		g.getBoard()[2][0]=null;
		g.getBoard()[3][0]=null;
		g.getBoard()[4][0]=null;
		GameWindow g1 = new GameWindow(g);
	}
}
