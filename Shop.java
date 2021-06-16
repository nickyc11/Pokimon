import java.awt.Graphics;
import java.io.File;
import java.awt.Image;
import javax.imageio.ImageIO;
/**
 * Shop.java
 * A class for the in-game shop
 * @author Toby Ahn
 * @version 1.0, June 14, 2021
 **/
public class Shop {
    Image shop;
    private int[] shopPrices = {5, 10, 20};
    private Inventory inventory;

    /**
     * Shop Constructor
     * @param i, the inventory
    */
    public Shop(Inventory i){
        inventory = i;
        loadSprites();
    }

    /**
    * loadSprites
    * Loads all the sprites required
    */
    public void loadSprites(){
        try {
            shop = ImageIO.read(new File("images/shop.png"));

        } catch (Exception e) {
            System.out.println("error loading sprite");
        }
    }

    /**
    * draw
    * Draws the shop
    * @param g, graphics
    */
    public void draw(Graphics g){
        g.drawImage(shop, 100, 100, null);
    }

    /**
    * buy
    * Removes gold and adds items to the inventory
    * @param p, the player
    * @param playerGold, the player's gold
    * @param itemID, the item type
    */
    public void buy(Player p, int playerGold, int itemID){
        if (playerGold >= shopPrices[itemID]){
            p.loseGold(shopPrices[itemID]);
            inventory.addItem(itemID);

        }
    }
}