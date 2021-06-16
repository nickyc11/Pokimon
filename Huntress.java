import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Graphics2D;

/**
 * Huntress.java
 * The huntress enemy displayed in the fighting screen
 * implements Health
 * @author Toby Ahn, Nicholas Chew
 * @version 1.0, June 14, 2021
 **/

public class Huntress implements Health {
    private int x, y;
    private Image sprite[] = new Image[6];
    private int[] attack = {1, 2, 3, 4, 5, 6};
    private int enemyFrame;
    private int health;
    private String fightState;

    /**
     * Huntress constructor
     * @param x, x coordinate of the NPC
     * @param y, y coordinate of the NPC
     */
    public Huntress(int x, int y) {
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
		for (int i = 1; i <= 6; i++) {
			try {
				sprite[i] = ImageIO.read(new File("sprites/Huntress/attack/attack"+ i +".png")).getScaledInstance(375, 375, Image.SCALE_DEFAULT);
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
        g.drawImage(sprite[enemyFrame], x, y, null);
	}
	/**
    * attack
    * Deals damage
    */
    public void attack() {
        enemyFrame = attack[enemyFrame];
        this.fightState = "attack";
        if ((enemyFrame == 3) && (GamePanel.player.getFightState() != "defend")) {
            GamePanel.player.incrementHealth(-60);
        }
        if (enemyFrame == 6) {
            enemyFrame = 1;
            this.fightState = "idle";
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
    * getFightState
    * Returns fight state
    * @return String, the fight state
    */
    public void setFightState(String fightState) {
        this.fightState = fightState;
	}
    /**
    * getEnemyFrame
    * Returns the frame the enemy animation is in
    * @return int, the frame the enemy animation is in
    */
    public int getEnemyFrame() {
        return enemyFrame;
    }
	/**
    * setEnemyFrame
    * Sets the frame the enemy animation is in
    * @param f, the new frame the enemy animation is in
    */
    public void setEnemyFrame(int f) {
        this.enemyFrame = f;
    }

    /**
     * getHealth
     * Returns the health value of the Enemy
     * @return int health, the health value of the Enemy
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
     */
    public void incrementHealth(int health) {
        this.health += health;
    }
}
