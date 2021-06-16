import java.awt.Graphics;
import java.io.File;
import java.util.Arrays;
import java.awt.Image;
import javax.imageio.ImageIO;

/**
 * Inventory.java
 * The in-game inventory
 * @author Toby Ahn
 * @version 1.0, June 14, 2021
 **/

public class Inventory {
    private int[] inventory = new int[15];
    private Image inv;
    private Image[] potions = new Image[3];
    private int[][] coords = {{480, 169}, {600, 169}, {720, 169}, {840, 169}, {960, 169},
                                {480, 300}, {600, 300}, {720, 300}, {830, 300}, {960, 300},
                                {480, 431}, {60, 431}, {720, 431}, {830, 431}, {960, 431}};

    /**
     * Inventory Constructor
     * @param p, the player
     */
    public Inventory(){
        Arrays.fill(inventory, -1);
        loadSprites();
    }

    /**
     * loadSprites
     * Loads all the sprites required
     */
    public void loadSprites(){
        try {
            inv = ImageIO.read(new File("images/inventory.png"));
            potions[0] = ImageIO.read(new File("sprites/icons/glass01orange.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            potions[1] = ImageIO.read(new File("sprites/icons/glass02blue.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            potions[2] = ImageIO.read(new File("sprites/icons/glass03yellow.png")).getScaledInstance(100, 100, Image.SCALE_DEFAULT);

        } catch (Exception e) {
            System.out.println("failed to load image");
        }
    }

    /**
     * drawInventory
     * Draws the inventory on the screen
     * @param g, graphics
     */
	public void drawInventory(Graphics g){
		g.drawImage(inv, 0, 0, null);
        for (int i = 0; i < 15; i++){
            if (inventory[i] != -1){
                g.drawImage(potions[inventory[i]], coords[i][0], coords[i][1], null);
                
            }
        }
	}

    /**
     * addItem
     * Adds an item to the inventory array
     * @param potionID, the ID of the potion to determine which potion is being added
     */
    public void addItem(int potionID){
        for (int i = 0; i < 15; i++){
            if (inventory[i] == -1){
                inventory[i] = potionID;
                break;
            }
        }
    }

    /**
     * removeItem
     * Removes an item from the inventory array
     * @param itemSlot, used to determine which slot the item is in
     */
    public void removeItem(int itemSlot){
        inventory[itemSlot] = -1;
    }

    /**
     * useItem
     * Comsumes a potion
     * @param itemSlot, used to determine which slot the item is in
     */
    public void useItem(int itemSlot){
        switch(inventory[itemSlot]){
            case -1:
                break;
            case 0:
                GamePanel.player.incrementHealth(50);
                break;
            case 1:
                GamePanel.player.incrementHealth(100);
                break;
            case 2:
                GamePanel.player.incrementHealth(150);
                break;
        }
    }
}