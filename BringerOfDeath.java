import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Graphics2D;

/**
 * BringerOfDeath.java
 * The BringerOfDeath enemy displayed in the fighting screen
 * implements Health
 * @author Toby Ahn, Nicholas Chew
 * @version 1.0, June 14, 2021
 **/

public class BringerOfDeath implements Health {
    private int x, y;
    private Image sprite[] = new Image[10];
    private int[] attack = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private int enemyFrame;
    private int health;
    private String fightState;

    /**
     * BringerOfDeath constructor
     * @param x, x coordinate of the NPC
     * @param y, y coordinate of the NPC
     */
    public BringerOfDeath(int x, int y) {
        loadSprites();
        this.x = x;
        this.y = y;
        this.enemyFrame = 1;
        this.health = 400;
    }

    /**
    * loadSprites
    * Loads all the sprites required
    */
    public void loadSprites() {
		for (int i = 1; i <= 10; i++) {
			try {
				sprite[i] = ImageIO.read(new File("sprites/Bringer-Of-Death/Individual Sprite/Attack/Bringer-of-Death_Attack_"+ i +".png")).getScaledInstance(1400, 700, Image.SCALE_DEFAULT);
			} catch (Exception e) {
				System.out.println("error loading sprite");
			}
		}
	}
	/**
    * draw
    * Draws the BringerOfDeath
    * @param g, graphics
    */
    public void draw(Graphics g) {
        g.drawImage(sprite[enemyFrame], x, y, null);
	}
	/**
    * attack
    * Plays the attack animation and decreases player health
    */
    public void attack() {
        enemyFrame = attack[enemyFrame];
        if ((enemyFrame == 5) && (GamePanel.player.getFightState() != "defend")) {
            GamePanel.player.incrementHealth(-60);
        }
        if (enemyFrame == 10) {
            enemyFrame = 1;
            this.fightState = "idle";
        }
    }
    /**
    * getFightState
    * @return String, the fight state
    */
    public String getFightState() {
        return fightState;
	}
	/**
    * setFightState
    * Sets the fight state
    * @param fightState, the new fightState
    */
    public void setFightState(String fightState) {
        this.fightState = fightState;
	}
    /**
    * getEnemyFrame
    * @return int, the frame the enemy animation is in
    */
    public int getEnemyFrame() {
        return enemyFrame;
    }
	/**
    * setEnemyFrame
    * @param f, sets the new frame the enemy animation is in
    */
    public void setEnemyFrame(int f) {
        this.enemyFrame = f;
    }

    /**
     * getHealth
     * @return int, the health value of the Enemy
     */
    public int getHealth() {
        return this.health;
    }
	/**
    * setHealth
    * Sets the enemy health
    * @param health, the new health
    */
    public void setHealth(int health) {
        this.health = health;
    }
	/**
    * drawHealth
    * Draws the enemy health bar
    * @param g, graphics
    */
    public void drawHealth(Graphics g) {
        Graphics2D rect = (Graphics2D) g;
		rect.setColor(new Color(0, 255, 0));
		rect.fillRect(1100, 800, health, 40);
		rect.drawRect(1100, 800, health, 40);
    }
    /**
     * incrementHealth
     * increments the health of the object (increase or decrease)
     * @param int, the health amount to increment/decrement by
     */
    public void incrementHealth(int health) {
        this.health += health;
    }
}
