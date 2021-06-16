import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * TimeTask.java
 * A timer that controls the attack speed of enemies
 * extends TimerTask
 * @author Nicholas Chew
 * @version 1.0, June 14, 2021
 **/

public class TimeTask extends TimerTask {
    public static Timer timer = new Timer();

    /**
     * run
     * Starts a timer for random enemy attacks
     */
    public void run() {
        int delay = (0 + new Random().nextInt(3)) * 1000;
        timer.schedule(new TimeTask(), delay);
        if (GamePanel.status == "fight") {
            if (GamePanel.currentEnemy == "BringerOfDeath") {
                GamePanel.enemy1.setFightState("attack");
            }
            if (GamePanel.currentEnemy == "Huntress") {
                GamePanel.enemy2.setFightState("attack");
            }
        }
    }
}