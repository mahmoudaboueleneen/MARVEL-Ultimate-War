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
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import engine.Game;
import engine.Player;
import model.world.Champion;
import model.abilities.*;

public class ChampionSelectionWindow extends JFrame implements ActionListener, MouseListener {
	
	private ArrayList<Champion> champions;
	//private ArrayList<>
	private JButton capAmerica;
	private JButton deadpool;
	private JButton drStrange;
	private JButton electro;
	private JButton ghostRider;
	private JButton hela;
	private JButton hulk;
	private JButton iceman;
	private JButton ironman;
	private JButton loki;
	private JButton quicksilver;
	private JButton spiderman;
	private JButton thor;
	private JButton venom;
	private JButton yellowJacket;
	
	private JLayeredPane topLayeredPane;
	private JLabel topChosen1;
	private JLabel topChosen2;
	private JLabel topChosen3;
	private ArrayList<JLabel> topChosenArray;
	
	private JLayeredPane botLayeredPane;
	private JLabel botChosen1;
	private JLabel botChosen2;
	private JLabel botChosen3;
	private ArrayList<JLabel> botChosenArray;
	
	private JPanel botInfoPanel;
	private JTextArea botDesc;

	private int nChosenChampions;
	
	private Player player1;
	private Player player2;
	
	public ChampionSelectionWindow(Player player1, Player player2) {
		super();
		
		//Update local variable to hold current game instance, to be used elsewhere in the class
		this.player1 = player1;
		this.player2 = player2;
		
		//Setup the window
		this.setTitle("MARVEL: Ultimate War - Champion Select");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(1600,900);
		this.setIconImage(new ImageIcon("iconlogo.png").getImage());
		this.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		Border border = BorderFactory.createEtchedBorder(); //color,width
		Border emptyBorder = BorderFactory.createEtchedBorder();
		Border panelBorder = BorderFactory.createEtchedBorder(Color.CYAN,Color.CYAN);
		
		/* --------------------------------------------------------------------------------------------------------------------------------- */
		
		//Creating topInfoPanel for info about the current window
		JPanel topInfoPanel = new JPanel();
		topInfoPanel.setBounds(0,0,400,175); //x, y, width, height
		topInfoPanel.setBackground(Color.BLACK);
		topInfoPanel.setLayout(null);
		//topInfoPanel.setBorder(panelBorder);
		this.add(topInfoPanel);
		
		
		ImageIcon marvelSmall = new ImageIcon("marvels.png");
		JLabel infoLogo = new JLabel();
		infoLogo.setIcon(marvelSmall);
		infoLogo.setBounds(50,20,200,79);
		//topInfoPanel.add(infoLogo);
		
		JTextArea info1 = new JTextArea();
		info1.setText(info1.getText() + "CHAMPION SELECTION" + "\nEach player selects three champions."
					+ "\nPlayer one picks his three champions then Player two."
					+ "\nHover over a champion to display their description" + "\n& abilities!");
		
		info1.setFont(new Font("Comic Sans",Font.BOLD,13));
		info1.setForeground(Color.WHITE);
		info1.setBackground(Color.BLACK);
		info1.setEditable(false);
		info1.setBounds(17,55,350,150);
		topInfoPanel.add(info1);
		
		//Creating botInfoPanel for info about the champions
		botInfoPanel = new JPanel();
		botInfoPanel.setBounds(0,175,400,725);
		botInfoPanel.setBackground(Color.GRAY);
		//botInfoPanel.setBorder(panelBorder);
		this.add(botInfoPanel);
		
		JLabel botInfo1 = new JLabel("Hover over a champion to view their description!");
		botInfo1.setForeground(Color.WHITE);
		botInfo1.setFont(new Font("Comic Sans",Font.BOLD,15));
		botInfo1.setBounds(50,0,300,50);
		botInfo1.setBackground(Color.GRAY);
		botInfoPanel.add(botInfo1);
		
		botDesc = new JTextArea();
		botDesc.setEditable(false);
		botDesc.setBounds(30,900,350,350);
		botDesc.setForeground(Color.WHITE);
		botDesc.setBackground(Color.GRAY);
		botDesc.setFont(new Font("Comic Sans",0,20));
		botInfoPanel.add(botDesc);
		
		/* --------------------------------------------------------------------------------------------------------------------------------- */
		
		//Creating topPanel for player1
		topChosenArray = new ArrayList<JLabel>();
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(400,0,1184,175); //x, y, width, height
		topPanel.setBackground(new Color(0x2249c1));
		topPanel.setLayout(null);
		//topPanel.setBorder(panelBorder);
		this.add(topPanel);
		
		JLabel top1 = new JLabel("Player One ");
		top1.setFont(new Font("Comic Sans", Font.BOLD, 20));
		top1.setForeground(Color.WHITE);
		top1.setBounds(150,50,150,50);
		topPanel.add(top1);
		
		JLabel top2 = new JLabel(player1.getName());
		top2.setFont(new Font("Comic Sans", Font.BOLD, 25));
		top2.setForeground(new Color(0xFFD700));
		top2.setBounds(270,50,700,50);
		topPanel.add(top2);
		
		topLayeredPane = new JLayeredPane();
		topLayeredPane.setBounds(900,13,300,175);
		topPanel.add(topLayeredPane);
		
		JLabel topChosen1 = new JLabel();
		topChosen1.setOpaque(true);
		topChosen1.setBackground(Color.WHITE);
		topChosen1.setBorder(emptyBorder);
		topChosen1.setBounds(0,0,80,100);
		topLayeredPane.add(topChosen1);
		topChosenArray.add(topChosen1);
		
		JLabel topChosen2 = new JLabel();
		topChosen2.setOpaque(true);
		topChosen2.setBackground(Color.WHITE);
		topChosen2.setBorder(emptyBorder);
		topChosen2.setBounds(25,25,80,100);
		topLayeredPane.add(topChosen2);
		topChosenArray.add(topChosen2);
		
		JLabel topChosen3 = new JLabel();
		topChosen3.setOpaque(true);
		topChosen3.setBackground(Color.WHITE);
		topChosen3.setBorder(emptyBorder);
		topChosen3.setBounds(50,50,80,100);
		topLayeredPane.add(topChosen3);
		topChosenArray.add(topChosen3);
		
		//Creating botPanel for player2
		botChosenArray = new ArrayList<JLabel>();
		
		JPanel botPanel = new JPanel();
		botPanel.setBounds(400,687,1184,175); //x, y, width, height
		botPanel.setBackground(new Color(0x5f1013));
		botPanel.setLayout(null);
		//botPanel.setBorder(panelBorder);
		this.add(botPanel);
		
		JLabel bot1 = new JLabel("Player Two ");
		bot1.setFont(new Font("Comic Sans", Font.BOLD, 20));
		bot1.setForeground(Color.WHITE);
		bot1.setBounds(150,50,150,50);
		botPanel.add(bot1);
		
		JLabel bot2 = new JLabel(player2.getName());
		bot2.setFont(new Font("Comic Sans", Font.BOLD, 25));
		bot2.setForeground(new Color(0xFFD700));
		bot2.setBounds(270,50,700,50);
		botPanel.add(bot2);
		
		botLayeredPane = new JLayeredPane();
		botLayeredPane.setBounds(900,10,300,175);
		botPanel.add(botLayeredPane);
		
		JLabel botChosen1 = new JLabel();
		botChosen1.setOpaque(true);
		botChosen1.setBackground(Color.WHITE);
		botChosen1.setBorder(emptyBorder);
		botChosen1.setBounds(0,0,80,100);
		botLayeredPane.add(botChosen1);
		botChosenArray.add(botChosen1);
		
		JLabel botChosen2 = new JLabel();
		botChosen2.setOpaque(true);
		botChosen2.setBackground(Color.WHITE);
		botChosen2.setBorder(emptyBorder);
		botChosen2.setBounds(25,25,80,100);
		botLayeredPane.add(botChosen2);
		botChosenArray.add(botChosen2);
		
		JLabel botChosen3 = new JLabel();
		botChosen3.setOpaque(true);
		botChosen3.setBackground(Color.WHITE);
		botChosen3.setBorder(emptyBorder);
		botChosen3.setBounds(50,50,80,100);
		botLayeredPane.add(botChosen3);
		botChosenArray.add(botChosen3);
		
		/* --------------------------------------------------------------------------------------------------------------------------------- */
		
		//Mid panel to hold champions
		JPanel midPanel = new JPanel();
		midPanel.setBounds(400,175,1200,550);
		midPanel.setLayout(null);
		this.add(midPanel);
		
//		Label to hold background img of champion select
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setIcon(new ImageIcon("champselectionbackground.jpg"));
		backgroundLabel.setBounds(0,0,1200,550);
		midPanel.add(backgroundLabel);
		
		
		//this.getContentPane().setBackground(new Color(0x092232));
		
		
		//Creating button to hold Captain America
		ImageIcon capAmericaImg = new ImageIcon("captainam.jpg");
		capAmerica = new JButton();
		capAmerica.setIcon(capAmericaImg);
		capAmerica.setBounds(325,80,80,100); //x, y, width, height
		capAmerica.setBorder(emptyBorder);
		capAmerica.addActionListener(this);
		capAmerica.addMouseListener(this);
		capAmerica.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundLabel.add(capAmerica);
		
		//Creating button to hold Deadpool
		ImageIcon deadpoolImg = new ImageIcon("deadpool.jpg");
		deadpool = new JButton();
		deadpool.setIcon(deadpoolImg);
		deadpool.setBounds(430,80,80,100); //x, y, width, height
		deadpool.setBorder(emptyBorder);
		deadpool.addActionListener(this);
		deadpool.addMouseListener(this);
		backgroundLabel.add(deadpool);
		
		//Creating button to hold Dr. Strange
		ImageIcon drStrangeImg = new ImageIcon("drstrange.jpg");
		drStrange = new JButton();
		drStrange.setIcon(drStrangeImg);
		drStrange.setBounds(535,80,80,100); //x, y, width, height
		drStrange.setBorder(emptyBorder);
		drStrange.addActionListener(this);
		drStrange.addMouseListener(this);
		drStrange.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundLabel.add(drStrange);
		
		//Creating button to hold Electro
		ImageIcon electroImg = new ImageIcon("electro.jpg");
		electro = new JButton();
		electro.setIcon(electroImg);
		electro.setBorder(emptyBorder);
		electro.setBounds(640,80,80,100); //x, y, width, height
		electro.addActionListener(this);
		electro.addMouseListener(this);
		electro.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundLabel.add(electro);
		
		//Creating button to hold Ghost Rider
		ImageIcon ghostRiderImg = new ImageIcon("ghostrider.jpg");
		ghostRider = new JButton();
		ghostRider.setIcon(ghostRiderImg);
		ghostRider.setBorder(emptyBorder);
		ghostRider.setBounds(745,80,80,100); //x, y, width, height
		ghostRider.addActionListener(this);
		ghostRider.addMouseListener(this);
		ghostRider.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundLabel.add(ghostRider);
		
		//Creating button to hold Hela
		ImageIcon helaImg = new ImageIcon("hela.jpg");
		hela = new JButton();
		hela.setIcon(helaImg);
		hela.setBorder(emptyBorder);
		hela.setBounds(325,195,80,100); //x, y, width, height
		hela.addActionListener(this);
		hela.addMouseListener(this);
		hela.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundLabel.add(hela);
		
		//Creating button to hold Hulk
		ImageIcon hulkImg = new ImageIcon("hulk.jpg");
		hulk = new JButton();
		hulk.setIcon(hulkImg);
		hulk.setBorder(emptyBorder);
		hulk.setBounds(430,195,80,100); //x, y, width, height
		hulk.addActionListener(this);
		hulk.addMouseListener(this);
		hulk.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundLabel.add(hulk);
		
		//Creating button to hold Iceman
		ImageIcon icemanImg = new ImageIcon("iceman.jpg");
		iceman = new JButton();
		iceman.setIcon(icemanImg);
		iceman.setBorder(emptyBorder);
		iceman.setBounds(535,195,80,100); //x, y, width, height
		iceman.addActionListener(this);
		iceman.addMouseListener(this);
		iceman.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundLabel.add(iceman);
		
		//Creating button to hold Ironman
		ImageIcon ironmanImg = new ImageIcon("ironman.jpg");
		ironman = new JButton();
		ironman.setIcon(ironmanImg);
		ironman.setBorder(emptyBorder);
		ironman.setBounds(640,195,80,100); //x, y, width, height
		ironman.addActionListener(this);
		ironman.addMouseListener(this);
		ironman.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundLabel.add(ironman);
		
		//Creating button to hold Loki
		ImageIcon lokiImg = new ImageIcon("loki.jpg");
		loki = new JButton();
		loki.setIcon(lokiImg);
		loki.setBorder(emptyBorder);
		loki.setBounds(745,195,80,100); //x, y, width, height
		loki.addActionListener(this);
		loki.addMouseListener(this);
		loki.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundLabel.add(loki);
		
		//Creating button to hold Quicksilver
		ImageIcon quicksilverImg = new ImageIcon("quicksilver.jpg");
		quicksilver = new JButton();
		quicksilver.setIcon(quicksilverImg);
		quicksilver.setBorder(emptyBorder);
		quicksilver.setBounds(325,310,80,100); //x, y, width, height
		quicksilver.addActionListener(this);
		quicksilver.addMouseListener(this);
		quicksilver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundLabel.add(quicksilver);

		//Creating button to hold Spiderman
		ImageIcon spidermanImg = new ImageIcon("spiderman.jpg");
		spiderman = new JButton();
		spiderman.setIcon(spidermanImg);
		spiderman.setBorder(emptyBorder);
		spiderman.setBounds(430,310,80,100); //x, y, width, height
		spiderman.addActionListener(this);
		spiderman.addMouseListener(this);
		spiderman.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundLabel.add(spiderman);
		
		//Creating button to hold Thor
		ImageIcon thorImg = new ImageIcon("thor.jpg");
		thor = new JButton();
		thor.setIcon(thorImg);
		thor.setBorder(emptyBorder);
		thor.setBounds(535,310,80,100); //x, y, width, height
		thor.addActionListener(this);
		thor.addMouseListener(this);
		thor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundLabel.add(thor);
		
		//Creating button to hold Venom
		ImageIcon venomImg = new ImageIcon("venom.jpg");
		venom = new JButton();
		venom.setIcon(venomImg);
		venom.setBorder(emptyBorder);
		venom.setBounds(640,310,80,100); //x, y, width, height
		venom.addActionListener(this);
		venom.addMouseListener(this);
		venom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundLabel.add(venom);
		
		//Creating button to hold Yellow Jacket
		ImageIcon yellowJacketImg = new ImageIcon("yellowJacket.jpg");
		yellowJacket = new JButton();
		yellowJacket.setIcon(yellowJacketImg);
		yellowJacket.setBorder(emptyBorder);
		yellowJacket.setBounds(745,310,80,100); //x, y, width, height
		yellowJacket.addActionListener(this);
		yellowJacket.addMouseListener(this);
		yellowJacket.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backgroundLabel.add(yellowJacket);
		

		//this.pack();
		revalidate();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==capAmerica) {
			capAmerica.setEnabled(false);
			if(nChosenChampions < 3) {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Captain America"))
						player1.getTeam().add(c);
				for(JLabel l : topChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("captainam.jpg"));
						break;
					}	
				}
			}
			else {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Captain America"))
						player2.getTeam().add(c);
				for(JLabel l : botChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("captainam.jpg"));
						break;
					}	
				}
			}
			nChosenChampions++;
		}
		else if(e.getSource()==deadpool) {
			deadpool.setEnabled(false);
			if(nChosenChampions < 3) {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Deadpool"))
						player1.getTeam().add(c);
				for(JLabel l : topChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("deadpool.jpg"));
						break;
					}	
				}
			}
			else {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Deadpool"))
						player2.getTeam().add(c);
				for(JLabel l : botChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("deadpool.jpg"));
						break;
					}	
				}
			}
			nChosenChampions++;
		}
		else if(e.getSource()==drStrange) {
			drStrange.setEnabled(false);
			if(nChosenChampions < 3) {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Dr Strange"))
						player1.getTeam().add(c);
				for(JLabel l : topChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("drstrange.jpg"));
						break;
					}	
				}
			}
			else {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Dr Strange"))
						player2.getTeam().add(c);
				for(JLabel l : botChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("drstrange.jpg"));
						break;
					}	
				}
			}
			nChosenChampions++;
		}
		else if(e.getSource()==electro) {
			electro.setEnabled(false);
			if(nChosenChampions < 3) {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Electro"))
						player1.getTeam().add(c);
				for(JLabel l : topChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("electro.jpg"));
						break;
					}	
				}
			}
			else {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Electro"))
						player2.getTeam().add(c);
				for(JLabel l : botChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("electro.jpg"));
						break;
					}	
				}
			}
			nChosenChampions++;
		}
		else if(e.getSource()==ghostRider) {
			ghostRider.setEnabled(false);
			if(nChosenChampions < 3) {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Ghost Rider"))
						player1.getTeam().add(c);
				for(JLabel l : topChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("ghostrider.jpg"));
						break;
					}	
				}
			}
			else {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Ghost Rider"))
						player2.getTeam().add(c);
				for(JLabel l : botChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("ghostrider.jpg"));
						break;
					}	
				}
			}
			nChosenChampions++;
		}
		else if(e.getSource()==hela) {
			hela.setEnabled(false);
			if(nChosenChampions < 3) {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Hela"))
						player1.getTeam().add(c);
				for(JLabel l : topChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("hela.jpg"));
						break;
					}	
				}
			}
			else {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Hela"))
						player2.getTeam().add(c);
				for(JLabel l : botChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("hela.jpg"));
						break;
					}	
				}
			}
			nChosenChampions++;
		}
		else if(e.getSource()==hulk) {
			hulk.setEnabled(false);
			if(nChosenChampions < 3) {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Hulk"))
						player1.getTeam().add(c);
				for(JLabel l : topChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("hulk.jpg"));
						break;
					}	
				}
			}
			else {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Hulk"))
						player2.getTeam().add(c);
				for(JLabel l : botChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("hulk.jpg"));
						break;
					}	
				}
			}
			nChosenChampions++;
		}
		else if(e.getSource()==iceman) {
			iceman.setEnabled(false);
			if(nChosenChampions < 3) {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Iceman"))
						player1.getTeam().add(c);
				for(JLabel l : topChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("iceman.jpg"));
						break;
					}	
				}
			}
			else {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Iceman"))
						player2.getTeam().add(c);
				for(JLabel l : botChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("iceman.jpg"));
						break;
					}	
				}
			}
			nChosenChampions++;
		}
		else if(e.getSource()==ironman) {
			ironman.setEnabled(false);
			if(nChosenChampions < 3) {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Ironman"))
						player1.getTeam().add(c);
				for(JLabel l : topChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("ironman.jpg"));
						break;
					}	
				}
			}
			else {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Ironman"))
						player2.getTeam().add(c);
				for(JLabel l : botChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("ironman.jpg"));
						break;
					}	
				}
			}
			nChosenChampions++;
		}
		else if(e.getSource()==loki) {
			loki.setEnabled(false);
			if(nChosenChampions < 3) {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Loki"))
						player1.getTeam().add(c);
				for(JLabel l : topChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("loki.jpg"));
						break;
					}	
				}
			}
			else {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Loki"))
						player2.getTeam().add(c);
				for(JLabel l : botChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("loki.jpg"));
						break;
					}	
				}
			}
			nChosenChampions++;
		}
		else if(e.getSource()==quicksilver) {
			quicksilver.setEnabled(false);
			if(nChosenChampions < 3) {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Quicksilver"))
						player1.getTeam().add(c);
				for(JLabel l : topChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("quicksilver.jpg"));
						break;
					}	
				}
			}
			else {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Quicksilver"))
						player2.getTeam().add(c);
				for(JLabel l : botChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("quicksilver.jpg"));
						break;
					}	
				}
			}
			nChosenChampions++;
		}
		else if(e.getSource()==spiderman) {
			spiderman.setEnabled(false);
			if(nChosenChampions < 3) {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Spiderman"))
						player1.getTeam().add(c);
				for(JLabel l : topChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("spiderman.jpg"));
						break;
					}	
				}
			}
			else {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Spiderman"))
						player2.getTeam().add(c);
				for(JLabel l : botChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("spiderman.jpg"));
						break;
					}	
				}
			}
			nChosenChampions++;
		}
		else if(e.getSource()==thor) {
			thor.setEnabled(false);
			if(nChosenChampions < 3) {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Thor"))
						player1.getTeam().add(c);
				for(JLabel l : topChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("thor.jpg"));
						break;
					}	
				}
			}
			else {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Thor"))
						player2.getTeam().add(c);
				for(JLabel l : botChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("thor.jpg"));
						break;
					}	
				}
			}
			nChosenChampions++;
		}
		else if(e.getSource()==venom) {
			venom.setEnabled(false);
			if(nChosenChampions < 3) {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Venom"))
						player1.getTeam().add(c);
				for(JLabel l : topChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("venom.jpg"));
						break;
					}	
				}
			}
			else {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Venom"))
						player2.getTeam().add(c);
				for(JLabel l : botChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("venom.jpg"));
						break;
					}	
				}
			}
			nChosenChampions++;
		}
		else if(e.getSource()==yellowJacket) {
			yellowJacket.setEnabled(false);
			if(nChosenChampions < 3) {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Yellow Jacket"))
						player1.getTeam().add(c);
				for(JLabel l : topChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("yellowjacket.jpg"));
						break;
					}	
				}
			}
			else {
				for(Champion c : Game.getAvailableChampions())
					if(c.getName().equals("Yellow Jacket"))
						player2.getTeam().add(c);
				for(JLabel l : botChosenArray) {
					if(l.getIcon()==null) {
						l.setIcon(new ImageIcon("yellowjacket.jpg"));
						break;
					}	
				}
			}
			nChosenChampions++;
		}
		if(nChosenChampions == 6) {		
			//Preparing icon and Response String Arrays for the leader selection option panes
			ImageIcon icon = new ImageIcon("leaderselecticon.png");
			String[] team1 = {player1.getTeam().get(0).getName(), 
							  player1.getTeam().get(1).getName(),
							  player1.getTeam().get(2).getName()};
			String[] team2 = {player2.getTeam().get(0).getName(), 
							  player2.getTeam().get(1).getName(),
							  player2.getTeam().get(2).getName()};
			
			//Pick first player's leader
			int firstLeaderInt = -1;
			while(firstLeaderInt == -1)
				firstLeaderInt = JOptionPane.showOptionDialog(null, player1.getName() + ", select your Leader!", "Leader Selection", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, team1, 0); //parent component, message, title, optionType, messageType, icon, options, initial value(what is initially selected)
			player1.setLeader(player1.getTeam().get(firstLeaderInt));
			
			//Pick second player's leader
			int secondLeaderInt = -1;
			while(secondLeaderInt == -1)
				secondLeaderInt = JOptionPane.showOptionDialog(null, player2.getName() + ", select your Leader!", "Leader Selection", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, team2, 0); //parent component, message, title, optionType, messageType, icon, options, initial value(what is initially selected)
			player2.setLeader(player2.getTeam().get(secondLeaderInt));
			
			
			/*//Adding abilities to selected champions from availableAbilities
			ArrayList<Champion> selectedChampions = new ArrayList<Champion>();
			selectedChampions.add(player1.getTeam().get(0));
			selectedChampions.add(player1.getTeam().get(1));
			selectedChampions.add(player1.getTeam().get(2));
			selectedChampions.add(player2.getTeam().get(0));
			selectedChampions.add(player2.getTeam().get(1));
			selectedChampions.add(player2.getTeam().get(2));
			
			for(Champion c : selectedChampions) {
				if(c.getName().equals("Captain America")) {
					Ability a1 = Game.findAbilityByName("Shield throw");
					Ability a2 = Game.findAbilityByName("I can do this all day");
					Ability a3 = Game.findAbilityByName("Shield Up"); 
					
					c.getAbilities().add(0,a1);
					c.getAbilities().add(1,a2);
					c.getAbilities().add(2,a3);
				}
				else if(c.getName().equals("Deadpool")) {
					Ability a1 = Game.findAbilityByName("Try Harder");
					Ability a2 = Game.findAbilityByName("8 bullets left");
					Ability a3 = Game.findAbilityByName("Can't Catch Me"); 
					
					c.getAbilities().add(0,a1);
					c.getAbilities().add(1,a2);
					c.getAbilities().add(2,a3);
				}	
				else if(c.getName().equals("Dr Strange")) {
					Ability a1 = Game.findAbilityByName("The eye of agamotto");
					Ability a2 = Game.findAbilityByName("Thousand Hand");
					Ability a3 = Game.findAbilityByName("Mirror Dimension"); 
					
					c.getAbilities().add(0,a1);
					c.getAbilities().add(1,a2);
					c.getAbilities().add(2,a3);
				}
				else if(c.getName().equals("Electro")) {
					Ability a1 = Game.findAbilityByName("Fully Charged");
					Ability a2 = Game.findAbilityByName("EMP");
					Ability a3 = Game.findAbilityByName("Lightning Strike"); 
					
					c.getAbilities().add(0,a1);
					c.getAbilities().add(1,a2);
					c.getAbilities().add(2,a3);
				}
				else if(c.getName().equals("Ghost Rider")) {
					Ability a1 = Game.findAbilityByName("Death stare");
					Ability a2 = Game.findAbilityByName("Fire Breath");
					Ability a3 = Game.findAbilityByName("Chain Whip"); 
					
					c.getAbilities().add(0,a1);
					c.getAbilities().add(1,a2);
					c.getAbilities().add(2,a3);
				}
				else if(c.getName().equals("Hela")) {
					Ability a1 = Game.findAbilityByName("Godess of Death");
					Ability a2 = Game.findAbilityByName("Thorn Shield");
					Ability a3 = Game.findAbilityByName("Thorn Shower"); 
					
					c.getAbilities().add(0,a1);
					c.getAbilities().add(1,a2);
					c.getAbilities().add(2,a3);
				}
				else if(c.getName().equals("Hulk")) {
					Ability a1 = Game.findAbilityByName("Rage");
					Ability a2 = Game.findAbilityByName("Hulk Smash");
					Ability a3 = Game.findAbilityByName("Sun is getting real low"); 
					
					c.getAbilities().add(0,a1);
					c.getAbilities().add(1,a2);
					c.getAbilities().add(2,a3);
				}
				else if(c.getName().equals("Iceman")) {
					Ability a1 = Game.findAbilityByName("Frost bite");
					Ability a2 = Game.findAbilityByName("SubZero");
					Ability a3 = Game.findAbilityByName("Hail Storm"); 
					
					c.getAbilities().add(0,a1);
					c.getAbilities().add(1,a2);
					c.getAbilities().add(2,a3);
				}
				else if(c.getName().equals("Ironman")) {
					Ability a1 = Game.findAbilityByName("I am Ironman");
					Ability a2 = Game.findAbilityByName("Unibeam");
					Ability a3 = Game.findAbilityByName("3000"); 
					
					c.getAbilities().add(0,a1);
					c.getAbilities().add(1,a2);
					c.getAbilities().add(2,a3);
				}
				else if(c.getName().equals("Loki")) {
					Ability a1 = Game.findAbilityByName("God of Mischief");
					Ability a2 = Game.findAbilityByName("The Hidden Dagger");
					Ability a3 = Game.findAbilityByName("Fake Death"); 
					
					c.getAbilities().add(0,a1);
					c.getAbilities().add(1,a2);
					c.getAbilities().add(2,a3);
				}
				else if(c.getName().equals("Quicksilver")) {
					Ability a1 = Game.findAbilityByName("Time in a bottle");
					Ability a2 = Game.findAbilityByName("Good as new");
					Ability a3 = Game.findAbilityByName("1 sec 100 punch"); 
					
					c.getAbilities().add(0,a1);
					c.getAbilities().add(1,a2);
					c.getAbilities().add(2,a3);
				}
				else if(c.getName().equals("Spiderman")) {
					Ability a1 = Game.findAbilityByName("give me that");
					Ability a2 = Game.findAbilityByName("web trap");
					Ability a3 = Game.findAbilityByName("Spiderverse"); 
					
					c.getAbilities().add(0,a1);
					c.getAbilities().add(1,a2);
					c.getAbilities().add(2,a3);
				}
				else if(c.getName().equals("Thor")) {
					Ability a1 = Game.findAbilityByName("God of Thunder");
					Ability a2 = Game.findAbilityByName("Mjollnir Throw");
					Ability a3 = Game.findAbilityByName("Bring Me Thanos"); 
					
					c.getAbilities().add(0,a1);
					c.getAbilities().add(1,a2);
					c.getAbilities().add(2,a3);
				}
				else if(c.getName().equals("Venom")) {
					Ability a1 = Game.findAbilityByName("Head Bite");
					Ability a2 = Game.findAbilityByName("We are venom");
					Ability a3 = Game.findAbilityByName("Symbiosis"); 
					
					c.getAbilities().add(0,a1);
					c.getAbilities().add(1,a2);
					c.getAbilities().add(2,a3);
				}
				else if(c.getName().equals("Yellow Jacket")) {
					Ability a1 = Game.findAbilityByName("Laser Sting");
					Ability a2 = Game.findAbilityByName("QuANTaMANia");
					Ability a3 = Game.findAbilityByName("Pym Particle Upsize"); 
					
					c.getAbilities().add(0,a1);
					c.getAbilities().add(1,a2);
					c.getAbilities().add(2,a3);
				}
			}*/
			
			//Calling game constructor with its interior methods & creating instance of game to pass onto next windows
			Game currentGame = new Game(player1, player2);
			
			
			this.setVisible(false); //can't see frame
			this.dispose(); //kill frame
			new PregameWindow(currentGame);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//
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
		if(e.getSource() == capAmerica) {
			botDesc.setText("Captain America" + "\nType: Hero" + "\nMax HP: 1500" + "\nMana: 1000" 
					   + "\nActions per turn: 6" + "\nSpeed: 80" + "\nAttack Range: 1" + "\nAttack Damage: 100" 
					   + "\nAbility 1: Shield Throw" + "\nAbility 2: I can do this all day" + "\nAbility 3: Shield Up");
		}
		else if(e.getSource() == deadpool) {
			botDesc.setText("Deadpool" + "\nType: Anti Hero" + "\nMax HP: 1350" + "\nMana: 700" 
					   + "\nActions per turn: 6" + "\nSpeed: 80" + "\nAttack Range: 3" + "\nAttack Damage: 90" 
					   + "\nAbility 1: Try Harder" + "\nAbility 2: 8 bullets left" + "\nAbility 3: Can't Catch Me");
		}
		else if(e.getSource() == drStrange) {
			botDesc.setText("Dr Strange" + "\nType: Hero" + "\nMax HP: 1100" + "\nMana: 1500" 
					   + "\nActions per turn: 6" + "\nSpeed: 60" + "\nAttack Range: 2" + "\nAttack Damage: 60" 
					   + "\nAbility 1: The eye of agamotto" + "\nAbility 2: Thousand Hand" + "\nAbility 3: Mirror Dimension");
		}
		else if(e.getSource() == electro) {
			botDesc.setText("Electro" + "\nType: Villain" + "\nMax HP: 1200" + "\nMana: 1200" 
					   + "\nActions per turn: 5" + "\nSpeed: 75" + "\nAttack Range: 3" + "\nAttack Damage: 110" 
					   + "\nAbility 1: Fully Charged" + "\nAbility 2: EMP" + "\nAbility 3: Lightning Strike");
		}
		else if(e.getSource() == ghostRider) {
			botDesc.setText("Ghost Rider" + "\nType: Anti Hero" + "\nMax HP: 1800" + "\nMana: 600" 
					   + "\nActions per turn: 6" + "\nSpeed: 85" + "\nAttack Range: 1" + "\nAttack Damage: 140" 
					   + "\nAbility 1: Death stare" + "\nAbility 2: Fire Breath" + "\nAbility 3: Chain Whip");
		}
		else if(e.getSource() == hela) {
			botDesc.setText("Hela" + "\nType: Villain" + "\nMax HP: 1500" + "\nMana: 750" 
					   + "\nActions per turn: 5" + "\nSpeed: 75" + "\nAttack Range: 1" + "\nAttack Damage: 150" 
					   + "\nAbility 1: Goddess of Death" + "\nAbility 2: Thorn Shield" + "\nAbility 3: Thorn Shower");
		}
		else if(e.getSource() == hulk) {
			botDesc.setText("Hulk" + "\nType: Hero" + "\nMax HP: 2250" + "\nMana: 550" 
					   + "\nActions per turn: 5" + "\nSpeed: 55" + "\nAttack Range: 1" + "\nAttack Damage: 200" 
					   + "\nAbility 1: Rage" + "\nAbility 2: Hulk Smash" + "\nAbility 3: Sun is getting real low");
		}
		else if(e.getSource() == iceman) {
			botDesc.setText("Iceman" + "\nType: Hero" + "\nMax HP: 1000" + "\nMana: 900" 
					   + "\nActions per turn: 5" + "\nSpeed: 65" + "\nAttack Range: 2" + "\nAttack Damage: 120" 
					   + "\nAbility 1: Frost bite" + "\nAbility 2: SubZero" + "\nAbility 3: Hail Storm");
		}
		else if(e.getSource() == ironman) {
			botDesc.setText("Ironman" + "\nType: Hero" + "\nMax HP: 1200" + "\nMana: 800" 
					   + "\nActions per turn: 7" + "\nSpeed: 85" + "\nAttack Range: 3" + "\nAttack Damage: 90" 
					   + "\nAbility 1: I am Ironman" + "\nAbility 2: Unibeam" + "\nAbility 3: 3000");
		}
		else if(e.getSource() == loki) {
			botDesc.setText("Loki" + "\nType: Villain" + "\nMax HP: 1150" + "\nMana: 900" 
					   + "\nActions per turn: 5" + "\nSpeed: 70" + "\nAttack Range: 1" + "\nAttack Damage: 150" 
					   + "\nAbility 1: God of Mischief" + "\nAbility 2: The Hidden Dagger" + "\nAbility 3: Fake Death");
		}
		else if(e.getSource() == quicksilver) {
			botDesc.setText("Quicksilver" + "\nType: Villain" + "\nMax HP: 1200" + "\nMana: 650" 
					   + "\nActions per turn: 8" + "\nSpeed: 99" + "\nAttack Range: 1" + "\nAttack Damage: 70" 
					   + "\nAbility 1: Time in a bottle" + "\nAbility 2: Good as new" + "\nAbility 3: 1 sec 100 punch");
		}
		else if(e.getSource() == spiderman) {
			botDesc.setText("Spiderman" + "\nType: Hero" + "\nMax HP: 1400" + "\nMana: 750" 
					   + "\nActions per turn: 5" + "\nSpeed: 85" + "\nAttack Range: 1" + "\nAttack Damage: 120" 
					   + "\nAbility 1: give me that" + "\nAbility 2: web trap" + "\nAbility 3: Spiderverse");
		}
		else if(e.getSource() == thor) {
			botDesc.setText("Thor" + "\nType: Hero" + "\nMax HP: 1800" + "\nMana: 800" 
					   + "\nActions per turn: 7" + "\nSpeed: 90" + "\nAttack Range: 1" + "\nAttack Damage: 130" 
					   + "\nAbility 1: God of Thunder" + "\nAbility 2: Mjollnir Throw" + "\nAbility 3: Bring Me Thanos");
		}
		else if(e.getSource() == venom) {
			botDesc.setText("Venom" + "\nType: Anti Hero" + "\nMax HP: 1650" + "\nMana: 700" 
					   + "\nActions per turn: 5" + "\nSpeed: 70" + "\nAttack Range: 1" + "\nAttack Damage: 140" 
					   + "\nAbility 1: Head Bite" + "\nAbility 2: We are venom" + "\nAbility 3: Symbiosis");
		}
		else if(e.getSource() == yellowJacket) {
			botDesc.setText("Yellow Jacket" + "\nType: Villain" + "\nMax HP: 1050" + "\nMana: 800" 
					   + "\nActions per turn: 6" + "\nSpeed: 60" + "\nAttack Range: 2" + "\nAttack Damage: 80" 
					   + "\nAbility 1: Laser Sting" + "\nAbility 2: QuANTaMANia" + "\nAbility 3: Pym Particle Upsize");
		}
		botInfoPanel.repaint();
		botInfoPanel.revalidate();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == capAmerica) {
			botDesc.setText("");
		}
		else if(e.getSource() == deadpool) {
			botDesc.setText("");
		}
		else if(e.getSource() == drStrange) {
			botDesc.setText("");
		}
		else if(e.getSource() == electro) {
			botDesc.setText("");
		}
		else if(e.getSource() == ghostRider) {
			botDesc.setText("");
		}
		else if(e.getSource() == hela) {
			botDesc.setText("");
		}
		else if(e.getSource() == hulk) {
			botDesc.setText("");
		}
		else if(e.getSource() == iceman) {
			botDesc.setText("");
		}
		else if(e.getSource() == ironman) {
			botDesc.setText("");
		}
		else if(e.getSource() == loki) {
			botDesc.setText("");
		}
		else if(e.getSource() == quicksilver) {
			botDesc.setText("");
		}
		else if(e.getSource() == spiderman) {
			botDesc.setText("");
		}
		else if(e.getSource() == thor) {
			botDesc.setText("");
		}
		else if(e.getSource() == venom) {
			botDesc.setText("");
		}
		else if(e.getSource() == yellowJacket) {
			botDesc.setText("");
		}
		botInfoPanel.repaint();
		botInfoPanel.revalidate();
	}
	
	public static void main(String[] args) {
		
		Player p1 = new Player("1");
		Player p2 = new Player("2");
		new ChampionSelectionWindow(p1,p2);
		
	}
	
}
