package spikegame;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.CharKey;


// STILL NEED: How to Erase Bubble
//             Game Over Function
//             Bubble in range where spike can hit it
//             Bubble data structure for all the bubbles (Queue?)
//             Add label for score
//             Add label for lives


public class SpikeGame {
    static int balloonCount = 0;
    static int livesCount = 3; 
    
    public static void collision (Spike s, Balloon b) {
        if ((s.width == b.width) && (s.height == b.height)) {
            // spike his bubble
            // Erase the bubble somehow? look at documentation?
          //  System.out.println("I HAVE COLLIDED");
        }
        
        if ((s.width != b.width) && (s.height == b.height)) {
            // spike misses the bubble
            // Erase the bubble somehow?
            livesCount--;
          //  System.out.println("I HAVE NOT COLLIDED");
            if (livesCount == -1) {
                // Call game over function
            }
        }
    }
    
    public static void main ( String[] args ) {
        ConsoleSystemInterface s = new WSwingConsoleInterface("Spike by Kathryn", true);

        s.cls();
        s.print(1, 0, "Press something to start the game!.", s.GREEN);
        s.refresh();
        s.inkey();

        Balloon balloon = new Balloon();
        Spike spike = new Spike();

        while (true) {
            balloonCount++;
            s.cls();
            spike.draw(s);
            balloon.draw(s);
            s.refresh();
            CharKey k = s.inkey();
            spike = spike.react(k);
            balloon = balloon.tick();
            if (balloonCount == 5) {
                // NEED TO: Create a data structure that will hold all
                // of the balloons and then iterate through each balloon for
                // draw & tick
            }
            collision(spike, balloon);
        }   
}
}
