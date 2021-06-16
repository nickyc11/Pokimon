import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * InstructionPanel.java
 * The panel that displays game instructions
 * extends JPanel
 * @author Nicholas Chew
 * @version 1.0, June 14, 2021
 **/

public class InstructionPanel extends JPanel {
	public Image instructionImage1;
    public Image instructionImage2;
    public static int page = 1;

	/**
     * InstrctionPanel constructor
     * Loads in images from instruction panel
     */
	public InstructionPanel() {
        InstructionMouseListener mouseListener = new InstructionMouseListener();
		this.addMouseListener(mouseListener);
		
		this.setFocusable(true);
		this.requestFocusInWindow();
        try {
            instructionImage1 = ImageIO.read(new File("images/instructions1.png")).getScaledInstance(GameFrame.screenWidth - 16, GameFrame.screenHeight - 30, Image.SCALE_DEFAULT);
            instructionImage2 = ImageIO.read(new File("images/instructions2.png")).getScaledInstance(GameFrame.screenWidth - 16, GameFrame.screenHeight - 30, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread t = new Thread(new Runnable() {
			public void run() {
				animate();
			}
		}); // start the gameLoop
		t.start();
    }
	/**
     * animate
     * Main loop for instruction page animation
     */
    public void animate() {
		while (true) {

			try {
				Thread.sleep(10);
			} catch (Exception exc) {
				System.out.println("Thread Error");
			}
			// repaint request
			this.repaint();
		}
	}

	/**
     * paintComponent
	 * Draws all instruction components
     * @param g, graphics
     */
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawImage(instructionImage1, 0, 0, null);
        if (page == 2) {
            g.drawImage(instructionImage2, 0, 0, null);
        }
	}
}