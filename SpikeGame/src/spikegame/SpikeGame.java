package spikegame;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.CharKey;
import java.util.ArrayList;

// STILL NEED: How to Erase Bubble
//             DONE: Game Over Function
//             Bubble in range where spike can hit it
//             Bubble data structure for all the bubbles (Queue? Array?)
//             DONE: Add label for score
//             DONE: Add label for lives
//             Get game to restart after the player looses 


public class SpikeGame {
    static int balloonTurnCount = 0;
    static int currentBalloon = 0;
    static int numberOfBalloonsOnScreen = 5;
    static boolean gameOver = false;
    
    public static void collision (Spike s, Balloon b, LivesLabel livesCount, ScoreLabel scoreCount) {
        if ((s.width == b.width) && (s.height == b.height)) {
          // Spike hits the bubble
            livesCount.subtractLife();
            scoreCount.subtractScore();
            b.height = 55;
         //  System.out.println("I HAVE NOT COLLIDED");
            if (livesCount.gameOver()) {
                // Change gameOver to true;
                gameOver = true;
            }
        }
        
        if ((s.width != b.width) && (s.height == b.height)) {
            // spike misses the bubble
            scoreCount.addScore();
            b.height = 55;
        }
    }
    
    
    public static void main ( String[] args ) {
        
        ConsoleSystemInterface s = new WSwingConsoleInterface("Spike by Kathryn", true);
        
        while (true) {
        s.cls();
        s.print(1, 0, "Press something to start the game!", s.GREEN);
        s.refresh();
        s.inkey();
        
        // Creating a new data 
        ArrayList<Balloon> balloonDataStruct = new ArrayList();
        gameOver = false;
        
        Balloon balloon = new Balloon();
        balloonDataStruct.add(balloon);
        Spike spike = new Spike();
        LivesLabel livesLabel = new LivesLabel();
        ScoreLabel scoreLabel = new ScoreLabel();

        while (!gameOver) {
            s.cls();
            spike.draw(s);
            for (Balloon b : balloonDataStruct) {
              b.draw(s);
              System.out.println("Drawing a balloon: " + balloonDataStruct.indexOf(b));
            }
            livesLabel.draw(s);
            scoreLabel.draw(s);
            balloonTurnCount++;
            s.refresh();
            CharKey k = s.inkey();
            spike = spike.react(k);
            // If the spike moves, balloons move
            if (!spike.isEqualTo(spike.react(k))) {
                for (Balloon b : balloonDataStruct) {
                      b = b.tick();
                      }
            }
            if (balloonTurnCount == 3) {
                balloonDataStruct.add(new Balloon());
                balloonTurnCount = 0;
            }
            for (Balloon b : balloonDataStruct) {
               collision(spike, b, livesLabel, scoreLabel);
               if (b.height == 55) {
                   balloonDataStruct.remove(b);
                   System.out.println("I removed a balloon!");
               }
            }
          }
       s.cls(); 
       s.print(1, 0, "GAME OVER! Press any key to start another game", s.RED);
       s.refresh();
       s.inkey();
        }   
    }
}
