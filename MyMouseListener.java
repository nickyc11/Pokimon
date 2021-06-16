import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * MyMouseListener.java Mouse listener for movement and clicks implements
 * MouseListener
 * 
 * @author Mangat, Nicholas Chew, Toby Ahn
 * @version 1.0, June 14, 2021
 **/

public class MyMouseListener implements MouseListener {

	private Player player;
	private Shop shop;
	private Inventory inventory;
	private int[][] coords = { { 480, 169 }, { 600, 169 }, { 720, 169 }, { 840, 169 }, { 960, 169 }, { 480, 300 },
			{ 600, 300 }, { 720, 300 }, { 830, 300 }, { 960, 300 }, { 480, 431 }, { 60, 431 }, { 720, 431 },
			{ 830, 431 }, { 960, 431 } };

	/**
	 * MyMouseListenerConstructor
	 * @param p, the player
	 * @param h, the Bringer of Death enemy
	 * @param i, the inventory
	 * @param s, the shop
	 */
	public MyMouseListener(Player p, Inventory i, Shop s) {
		player = p;
		inventory = i;
		shop = s;
	}

	/**
     * mouseclicked
	 * Detects for mouse click
     * @param e, mouseEvent detector
     */
	public void mouseClicked(MouseEvent e) {
		if (GamePanel.status == "fight") {
			// press on attack
			if ((e.getX() > 538) && (e.getX() < 638) && (e.getY() > 50) && (e.getY() < 150)) {
				player.attack();
			}
			// press on inventory
			if ((e.getX() > 800) && (e.getX() < 900) && (e.getY() > 50) && (e.getY() < 150)) {
				player.openInventory();
			}
			// while inventory open
			if ((player.getFightState() == "inventory")) {
				if ((e.getX() > 1100) && (e.getX() < 1166) && (e.getY() > 18) && (e.getY() < 84)) {
					System.out.println("closing");
					player.closeInventory();
				}
				for (int i = 0; i < 15; i++) {
					if ((e.getX() > coords[i][0]) && (e.getX() < coords[i][0] + 100) && (e.getY() > coords[i][1])
							&& (e.getY() < coords[i][1] + 100)) {
						System.out.println(i);
						inventory.useItem(i);
						inventory.removeItem(i);

					}
				}
			}
		}
		// if the map is open and shop icon is selected
		if (GamePanel.status == "map") {
			if ((e.getX() > 1400) && (e.getX() < 1500) && (e.getY() > 750) && (e.getY() < 850)) {
				player.openShop();
			}
		}
		// if the shop is open
		if (GamePanel.status == "shop") {
			if ((e.getX() > 1010) && (e.getX() < 1070) && (e.getY() > 120) && (e.getY() < 175)) {
				player.closeShop();
			}
			if ((e.getX() > 930) && (e.getX() < 1050) && (e.getY() > 230) && (e.getY() < 350)) {
				shop.buy(player, player.getGold(), 0);
			} else if ((e.getX() > 930) && (e.getX() < 1050) && (e.getY() > 380) && (e.getY() < 500)) {
				shop.buy(player, player.getGold(), 1);
			} else if ((e.getX() > 930) && (e.getX() < 1050) && (e.getY() > 550) && (e.getY() < 680)) {
				shop.buy(player, player.getGold(), 2);
			}
		}
	}

	/**
     * mousePressed
	 * Detects for mouse press
     * @param e, mouseEvent detector
     */
	public void mousePressed(MouseEvent e) {
		if (GamePanel.status == "fight") {
			// press on shield
			if ((e.getX() > 1080) && (e.getX() < 1180) && (e.getY() > 50) && (e.getY() < 150)) {
				player.setFightState("defend");
			}
		}
	}

	/**
     * mouseReleased
	 * Detects for mouse release
     * @param e, mouseEvent detector
     */
	public void mouseReleased(MouseEvent e) {
		if ((GamePanel.status == "fight") && (player.getFightState() != "inventory")) {
			player.setFightState("idle");
		}
	}

	/**
     * mouseEntered
	 * Detects for mouse enter
     * @param e, mouseEvent detector
     */
	public void mouseEntered(MouseEvent e) {

	}

	/**
     * mouseExited
	 * Detects for mouse exit
     * @param e, mouseEvent detector
     */
	public void mouseExited(MouseEvent e) {

	}

}