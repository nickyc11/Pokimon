import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.awt.Graphics;
import javax.sound.sampled.*;
import javax.swing.JPanel;

/**
 * GamePanel.java
 * The main panel of the game
 * extends JPanel
 * @author Mangat, Nicholas Chew, Toby Ahn, Mark Chen
 * @version 1.0, June 14, 2021
 **/

public class GamePanel extends JPanel {

	public static String status;
	public static Map map;
	public static FightingScreen fightingScreen;
	public static int currMap;
	public static Player player;
	public static BringerOfDeath enemy1;
	public static Huntress enemy2;
	public static String currentEnemy;
	public static ArrayList<MovingObject> objectList;
	public static Inventory inventory;
	public static Shop shop;

	public static AudioInputStream audioStream;
    public static Clip fightSceneMusic;

	public static void setCurrMap(String s) {
		map = new Map(s);
	}

	/**
     * GamePanel constructor
     * Builds all objects needed for playing along with key/mouse listeners
     */

	public GamePanel() {
		//Game objects
		new TimeTask().run();
		status = "map";
		setPreferredSize(new Dimension(GameFrame.screenWidth, GameFrame.screenHeight));
		map = new Map("./maps/mapTop.txt");
		fightingScreen = new FightingScreen();
		inventory = new Inventory();
		shop = new Shop(inventory);
		objectList = new ArrayList<MovingObject>();
		player = new Player(200, 200);
		File bodLeft = new File("./sprites/Bringer-Of-Death/Individual Sprite/Idle/Bringer-of-Death_Idle_Left.png");
		File bodRight = new File("./sprites/Bringer-Of-Death/Individual Sprite/Idle/Bringer-of-Death_Idle_Right.png");
		objectList.add(new NPC(0, 0, bodLeft, bodRight, "BringerOfDeath"));
		File huntressLeft = new File("sprites/Huntress/run/left.png");
		File huntressRight = new File("sprites/Huntress/run/right.png");
		objectList.add(new NPC(GameFrame.screenWidth, 0, huntressLeft, huntressRight, "Huntress"));
		enemy2 = new Huntress(1100, 490);
		enemy1 = new BringerOfDeath(200, 200);

		// Listeners
		PlayerKeyListener keyListener = new PlayerKeyListener(player);
		this.addKeyListener(keyListener);

		MyMouseListener mouseListener = new MyMouseListener(player, inventory, shop);
		this.addMouseListener(mouseListener);

		this.setFocusable(true);
		this.requestFocusInWindow();

		//Gameloop threads at different speeds
		Thread t = new Thread(new Runnable() {
			public void run() {
				animate();
			}
		}); 
		t.start();
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				animate2();
			}
		});
		t2.start();
		Thread t3 = new Thread(new Runnable() {
			public void run() {
				animate3();
			}
		});
		t3.start();
	}

	/**
     * animate
     * Main game loop for audio, player movement, screen switching, and overall gameflow
     */
	public void animate() {
		try{
			File fightMusic = new File("audio/music.wav");
			audioStream = AudioSystem.getAudioInputStream(fightMusic);
			fightSceneMusic = AudioSystem.getClip();
			fightSceneMusic.open(audioStream);
		} catch (Exception e){
			e.printStackTrace();
		}
		while (true) {
			player.update(); 
			if (player.getFightState() == "attack") {
				player.attack();
			}

			if (status == "fight"){
				fightSceneMusic.start();
				fightSceneMusic.loop(Clip.LOOP_CONTINUOUSLY);
			}
			if (status == "map"){
				fightSceneMusic.stop();
				fightSceneMusic.flush();
				fightSceneMusic.setFramePosition(0);
			}
			// Screen switch control + fight control
			for (MovingObject o : GamePanel.objectList) {
				if ((o != null) && (o instanceof NPC) && (Collision.playerCollidingNPC(player, ((NPC) o).getBounds()))) {
					if ((GamePanel.status != "fight")) {
						GamePanel.status = "fight";
					}
					
					if (((NPC) o).getName() == "Huntress") {
						currentEnemy = "Huntress";
						for (MovingObject ob : GamePanel.objectList) {
							((NPC) ob).changeAllowedToMove(false);
						}
					} else if (((NPC) o).getName() == "BringerOfDeath") {
						currentEnemy = "BringerOfDeath";
						for (MovingObject ob : GamePanel.objectList) {
							((NPC) ob).changeAllowedToMove(false);
						}
					}
				}
				if ((enemy1.getHealth() < 0) || (enemy2.getHealth() < 0)) {
					for (MovingObject ob : GamePanel.objectList) {
						if (((NPC) ob).getName() == "BringerOfDeath") {
							((NPC) ob).setXPos(0);
							((NPC) ob).setYPos(0);
						} else if (((NPC) ob).getName() == "Huntress") {
							((NPC) ob).setXPos(GameFrame.screenWidth);
							((NPC) ob).setYPos(0);
						}
						((NPC) ob).changeAllowedToMove(true);
					}
					status = "map";
					player.setHealth(300);
					enemy1.setEnemyFrame(1);
					enemy1.setHealth(400);
					enemy2.setEnemyFrame(1);
					enemy2.setHealth(400);						
					player.increaseXP(5);
					player.increaseGold(10);
					if (player.getGold() >= 5){
						player.loseGold(5);
					} else if(player.getGold() < 5){
						player.setGold(0);
					}
				}
				if ((player.getHealth() < 0)) {
					status = "map";
					enemy1.setEnemyFrame(1);
					enemy1.setHealth(400);
					enemy2.setEnemyFrame(1);
					enemy2.setHealth(400);
					player.setHealth(300);
					for (MovingObject ob : GamePanel.objectList) {
						if (((NPC) ob).getName() == "BringerOfDeath") {
							((NPC) ob).setXPos(0);
							((NPC) ob).setYPos(0);
						} else if (((NPC) ob).getName() == "Huntress") {
							((NPC) ob).setXPos(GameFrame.screenWidth);
							((NPC) ob).setYPos(0);
						}
						((NPC) ob).changeAllowedToMove(true);
					}

				}
				if (status == "shop"){
					for (MovingObject ob : GamePanel.objectList) {
						((NPC) ob).changeAllowedToMove(false);
					}
				}
				if (status == "map"){
					for (MovingObject ob : GamePanel.objectList) {
						((NPC) ob).changeAllowedToMove(true);
					}
				}
				
			}
			try {
				Thread.sleep(10);
			} catch (Exception exc) {
				System.out.println("Thread Error");
			}
			this.repaint();
		}
	}
	/**
     * animate2
     * game loop for enemy1 attack animation
     */
	public void animate2() {
		while (true) {
			if ((status == "fight") && (enemy1.getFightState() == "attack")) {
				enemy1.attack();
			}
			try {
				Thread.sleep(50);
			} catch (Exception exc) {
				System.out.println("Thread Error");
			}
			this.repaint();
		}
	}

	/**
     * animate3
     * game loop for enemy2 attack animation
     */
	public void animate3() {
		while (true) {
			if ((status == "fight") && (enemy2.getFightState() == "attack")) {
				enemy2.attack();
			}
			try {
				Thread.sleep(100);
			} catch (Exception exc) {
				System.out.println("Thread Error");
			}
			this.repaint();
		}
	}

	/**
     * paintComponent
	 * Draws all game components
     * @param g, graphics
     */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		map.draw(g);
		player.draw(g);
		for (MovingObject o : objectList) {
			if ((o != null) && (o instanceof NPC)) {
				((NPC) o).travel(player.getXPos(), player.getYPos());
				((NPC) o).draw(g);
			}
		}
		if (status == "fight") {
			fightingScreen.draw(g);
			player.drawBig(g);

			player.drawHealth(g);
			if (currentEnemy == "BringerOfDeath") {
				enemy1.draw(g);
				enemy1.drawHealth(g);
			} else if (currentEnemy == "Huntress") {
				enemy2.draw(g);
				enemy2.drawHealth(g);
			}

			if (player.getFightState() == "attack") {
				player.drawProjectile(g);
			}
			if (player.getFightState() == "inventory"){
				inventory.drawInventory(g);
			}
			if (player.getFightState() == "defend") {
				player.drawShield(g);
			}
		}
		if (status == "map"){
			player.drawShopIcon(g);
		}
		if (GamePanel.status == "shop"){
			shop.draw(g);
		}

		player.drawXP(g);
		player.drawGold(g);
	}
}