import javax.imageio.ImageIO;
import java.awt.Font;
import java.io.File;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Graphics2D;

/**
 * Player.java
 * The main player in the game
 * extends MovingObject, implements Health
 * @author Nicholas Chew, Mark Chen, Toby Ahn
 * @version 1.0, June 14, 2021
 **/

public class Player extends MovingObject implements Health {

	private Image sprite[] = new Image[16];
	private int[] walkDown = { 1, 2, 3, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }; // 1234
	private int[] walkLeft = { 5, 5, 5, 5, 5, 6, 7, 8, 5, 5, 5, 5, 5, 5, 5, 5 }; // 5678
	private int[] walkRight = { 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 11, 12, 9, 9, 9, 9 }; // 9 10 11 12
	private int[] walkUp = { 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 15, 13 }; // 13 14 15 16
	private String playerState = "standing";
	private Image fightImage;
	private Image projectile;
	private Image goldImage;
	private int projectileXPos;
	private String fightState;
	private int playerNum = 1;
	private int xp;
	private int gold;
	private Font font = new Font("Arial", 1, 25);
	private String mapState = "idle";
	private Image shopIcon;

	// healthbar
	private int health;

    /**Constructor for Player
    * @param x, the x position of the player
	* @param y, the y position of the player
    */
	public Player(int x, int y) {
		super(x, y);
		setXVelocity(0);
		setYVelocity(0);
		this.xp = 0;
		this.gold = 0;
		this.health = 300;
		this.projectileXPos = 50;
		this.fightState = "map";
		loadSprites();
	}

	/**
    * loadSprites
    * Loads all the sprites required
    */
	public void loadSprites() {
		for (int i = 1; i < 16; i++) {
			try {
				sprite[i] = ImageIO.read(new File("walk/walk" + i + ".png")).getScaledInstance(32, 32,
						Image.SCALE_DEFAULT);
				fightImage = ImageIO.read(new File("walk/walk12.png")).getScaledInstance(250, 250, Image.SCALE_DEFAULT);
				projectile = ImageIO.read(new File("images/bullet.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
				goldImage = ImageIO.read(new File("sprites/Icons/coin.png")).getScaledInstance(35, 35, Image.SCALE_DEFAULT);
				shopIcon = ImageIO.read(new File("sprites/Icons/coin.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
			} catch (Exception e) {
				System.out.println("error loading sprite");
			}
		}
	}
	/**
    * draw
    * Draws the player
    * @param g, graphics
    */
	public void draw(Graphics g) {
		if (playerState.equals("movingDown")) {
			playerNum = walkDown[playerNum];
		} else if (playerState.equals("movingLeft")) {
			playerNum = walkLeft[playerNum];
		} else if (playerState.equals("movingRight")) {
			playerNum = walkRight[playerNum];
		} else if (playerState.equals("movingUp")) {
			playerNum = walkUp[playerNum];
		} else if (playerState.equals("standingLeft")) {
			playerNum = 8;
		} else if (playerState.equals("standingRight")) {
			playerNum = 12;
		} else if (playerState.equals("standingUp")) {
			playerNum = 15;
		} else if (playerState.equals("standingDown")) {
			playerNum = 4;
		}
		g.drawImage(sprite[playerNum], getXPos(), getYPos(), null);
	}


	/**
    * draw
    * Draws the xp bar
    * @param g, graphics
    */
	public void drawXP(Graphics g) {
		g.setColor(new Color(255, 255, 255));
		g.setFont(font);
		g.drawString("XP", 1250, 70);
		g.drawString("LVL: " + (getLvl() + 1), 1475, 70);
		g.setColor(new Color(255, 255, 255));
		g.drawRect(1300, 50, 150, 20);
		g.setColor(new Color(0, 255, 255));
		g.fillRect(1300, 50, getXP()*10 - (getXP()/15)*150, 20);
	}	
	/**
    * draw
    * Draws the gold and how much gold the player has
    * @param g, graphics
    */
	public void drawGold(Graphics g){
		g.drawImage(goldImage, 1250, 85, null);
		g.setColor(new Color(255, 255, 255));
		g.setFont(font);
		g.drawString("" + getGold(), 1300, 110);

	}
	/**
    * draw
    * Draws the shop icon
    * @param g, graphics
    */
	public void drawShopIcon(Graphics g){
		g.drawImage(shopIcon, 1400, 750, null);
	}
	/**
    * draw
    * Draws the bigger image of the characters fighting
    * @param g, graphics
    */
	public void drawBig(Graphics g) {
		g.drawImage(fightImage, 200, 550, null);
	}

	/**
    * draw
    * Draws the projectile
    * @param g, graphics
    */
	public void drawProjectile(Graphics g) {
		g.drawImage(projectile, projectileXPos, 700, null);
	}
	/**
    * openInventory
    * Changes the fight state to inventory if the inventory is opened
    */
	public void openInventory() {
		this.fightState = "inventory";
	}
	/**
    * closeInventory
    * Changes the fight state to idle if the inventory is closed
    */
	public void closeInventory(){
		this.fightState = "idle";
	}
	/**
    * openShop
    * Changes the status to shop if the shop is opened
    */
	public void openShop(){
		GamePanel.status = "shop";
	}
	/**
    * closeShop
    * Changes the status to map if the shop is closed
    */
	public void closeShop(){
		GamePanel.status = "map";
	}
	/**
    * getMapState
    * Returns the map state
    * @return String, the map state
    */
	public String getMapState(){
		return mapState;
	}

	/**
    * setMapState
    * Sets the map state
    * @param mapState, the new mapState
    */
	public void setMapState(String mapState){
		this.mapState = mapState;
	}
	/**
    * attack
    * Sends out a projectile to deal damage
    */
	public void attack() {
		this.fightState = "attack";
		this.projectileXPos += 100;
		if (projectileXPos > 1100) {
			// System.out.println("done");
			projectileXPos = 50;
			this.fightState = "idle";
			if (GamePanel.currentEnemy == "BringerOfDeath") {
				GamePanel.enemy1.incrementHealth(-20 - getLvl()*5);
			} else if (GamePanel.currentEnemy == "Huntress") {
				GamePanel.enemy2.incrementHealth(-20 - getLvl()*5);
			}
			
		}
	}
	/**
    * getFightState
    * Returns fight state
    * @return String, the fight state
    */
	public String getFightState() {
		return fightState;
	}
	/**
    * setFightState
    * Sets the fight state
    * @param state, the new fightState
    */
	public void setFightState(String state) {
		this.fightState = state;
	}

	/**
    * draw
    * Draws the health bar
    * @param g, graphics
    */
	public void drawHealth(Graphics g) {
		Graphics2D rect = (Graphics2D) g;
		rect.setColor(new Color(0, 255, 0));
		rect.fillRect(200, 800, health, 40);
		rect.drawRect(200, 800, health, 40);
	}
	/**
    * draw
    * Draws the shield
    * @param g, graphics
    */
	public void drawShield(Graphics g) {
		Graphics2D rect = (Graphics2D) g;
		rect.setColor(new Color(15, 15, 15));
		rect.fillRect(550, 475, 40, 350);
		rect.drawRect(550, 475, 40, 350);
	}
	/**
    * getHealth
    * Returns health
    * @return int, the health
    */
	public int getHealth() {
		return this.health;
	}
	/**
    * incremetHealth
    * Changes the health by a certain amount
	* @param health, the amount of health to change by
    */
	public void incrementHealth(int health) {
		this.health += health;
	}
	/**
    * setHealth
    * Sets the health
    * @param health, the new health
    */
	public void setHealth(int health) {
		this.health = health;
	}
	/**
    * getXP
    * Returns XP
    * @return int, the XP
    */
	public int getXP(){
		return this.xp;
	}
	/**
    * increaseXP
    * increases the XP by a certain amount
	* @param xp, the amount of XP to increase by
    */
	public void increaseXP(int xp) {
		this.xp += xp;
	}
	/**
    * getGold
    * Returns the gold
    * @return int, the gold the player has
    */
	public int getGold(){
		return this.gold;
	}
	/**
    * setGold
    * Sets the gold
    * @param gold, the new gold
    */
	public void setGold(int gold){
		this.gold = gold;
	}
	/**
    * increaseGold
    * increases the gold by a certain amount
	* @param gold, the amount of gold to increase by
    */
	public void increaseGold(int gold){
		this.gold += gold;
	}
	/**
    * loseGold
    * decreases the gold by a certain amount
	* @param gold, the amount of gold to decrease by
    */
	public void loseGold(int gold){
		this.gold -= gold;
	}
	/**
    * getLvl
    * Returns the level of the player (xp/15)
    * @return int, the level of the player
    */
	public int getLvl(){
		return xp/15;
	}

	public void update() {
		if (GamePanel.currMap == 0) {
			updatePlayerMovement(GamePanel.map.getTileWalls());
		} else if (GamePanel.currMap == 1) {
			updatePlayerMovement(GamePanel.map.getTileWalls());
		}

		if (getXPos() < 0) {
			setXPos(0);
		} else if (getXPos() > GameFrame.screenWidth - 44) {
			setXPos(GameFrame.screenWidth - 44);
		}
		if ((getYPos() < 0) && (GamePanel.currMap == 0)) {
			setYPos(0);
		} else if ((getYPos() > GameFrame.screenHeight - 74) && (GamePanel.currMap == 0)) {
			GamePanel.currMap = 1;
			GamePanel.setCurrMap("./maps/mapBottom.txt");
			for (MovingObject o : GamePanel.objectList) {
				((NPC) o).setYPos(((NPC) o).getYPos() - GameFrame.screenHeight - 74);
			}
			setYPos(0);
		} else if ((getYPos() < 0) && (GamePanel.currMap == 1)) {
			GamePanel.currMap = 0;
			GamePanel.setCurrMap("./maps/mapTop.txt");
			for (MovingObject o : GamePanel.objectList) {
				((NPC) o).setYPos(((NPC) o).getYPos() + GameFrame.screenHeight - 74);
			}
			setYPos(GameFrame.screenHeight - 74);
		} else if ((getYPos() > GameFrame.screenHeight - 74) && (GamePanel.currMap == 1)) {
			setYPos(GameFrame.screenHeight - 74);
		}
	}
	/**
    * stopLeft
    * stops the player from moving while looking left
    */
	public void stopLeft() {
		if (playerState.equals("movingLeft")) {
			playerState = "standingLeft";
		}
		setXVelocity(0);
	}
	/**
    * stopRight
    * stops the player from moving while looking right
    */
	public void stopRight() {
		if (playerState.equals("movingRight")) {
			playerState = "standingRight";
		}
		setXVelocity(0);
	}
	/**
    * stopUp
    * stops the player from moving while looking up
    */
	public void stopUp() {
		if (playerState.equals("movingUp")) {
			playerState = "standingUp";
		}
		setYVelocity(0);
	}
	/**
    * stopDown
    * stops the player from moving while looking down
    */
	public void stopDown() {
		if (playerState.equals("movingDown")) {
			playerState = "standingDown";
		}
		setYVelocity(0);
	}
	/**
    * moveLeft
    * moves the player left
    */
	public void moveLeft() {
		setXVelocity(-2);
		playerState = "movingLeft";
	}
	/**
    * moveRight
    * moves the player right
    */
	public void moveRight() {
		setXVelocity(2);
		playerState = "movingRight";
	}
	/**
    * moveUp
    * moves the player up
    */
	public void moveUp() {
		setYVelocity(-2);
		playerState = "movingUp";
	}
	/**
    * moveDown
    * moves the player down
    */
	public void moveDown() {
		setYVelocity(2);
		playerState = "movingDown";
	}
	/**
    * updatePlayerMovement
	* Updates player movement and checks for any collisions
    * @param tileWalls, an arraylist of all the Tile objects that are obstacles
    */
	public void updatePlayerMovement(ArrayList<Tile> tileWalls) {
		changeXPos(getXVelocity());
		for (Tile t : tileWalls) {
			if (Collision.playerCollidingTile(this, t)) {
				changeXPos(-getXVelocity());
			}
		}
		for (MovingObject o : GamePanel.objectList) {
			if ((o != null) && (o instanceof NPC) && (Collision.playerCollidingNPC(this, ((NPC) o).getBounds()))) {
				changeXPos(-getXVelocity());
			}
		}
		changeYPos(getYVelocity());
		for (Tile t : tileWalls) {
			if (Collision.playerCollidingTile(this, t)) {
				changeYPos(-getYVelocity());
			}
		}
		for (MovingObject o : GamePanel.objectList) {
			if ((o != null) && (o instanceof NPC) && (Collision.playerCollidingNPC(this, ((NPC) o).getBounds()))) {
				changeYPos(-getYVelocity());
			}
		}
	}
	/**
    * getBounds
    * @return Rectangle, the bounding rectangle around the player
    */
	public Rectangle getBounds() {
		return new Rectangle(getXPos(), getYPos(), 28, 28);
	}
	/**
    * getInteractionsBounds
    * @return Rectangle, the bounding rectangle around the player for interaction with NPCs
    */
	public Rectangle getInteractionBounds() {
		return new Rectangle(getXPos() - 5, getYPos() - 5, 38, 38);
	}
}
