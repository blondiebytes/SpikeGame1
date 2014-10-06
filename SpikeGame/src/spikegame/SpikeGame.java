package spikegame;

import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.CharKey;
import java.util.ArrayList;
import java.util.Iterator;

// TO DO :     DONE: How to Erase Bubble
//             DONE: Game Over Function
//             DONE: Bubble data structure for all the bubbles (ArrayList)
//             DONE: Add label for score
//             DONE: Add label for lives
//             DONE: Get game to restart after the player looses 
//             WAHOOOOOO. Now Testing. GAH

public class SpikeGame {

    static int balloonTurnCount = 0;
    static boolean gameOver = false;
    static int sentinalH = 55;

    public static void collision(Spike s, Balloon b, LivesLabel livesCount, ScoreLabel scoreCount) {
        if ((s.width == b.width) && (s.height == b.height)) {
            // Spike hits the bubble
            livesCount.subtractLife();
            scoreCount.subtractScore();
            b.height = sentinalH;
            //  System.out.println("I HAVE NOT COLLIDED");
            if (livesCount.gameOver()) {
                // Change gameOver to true;
                gameOver = true;
            }
        }

        if ((s.width != b.width) && (s.height == b.height)) {
            // spike misses the bubble
            scoreCount.addScore();
            b.height = sentinalH;
        }
    }

    public static void main(String[] args) {

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
                // Adding a balloon every turns 
                // This if may seem unneccessary, but it makes it easier if 
                // I ever wanted to make the game easier
                if (balloonTurnCount == 1) {
                    balloonDataStruct.add(new Balloon());
                    balloonTurnCount = 0;
                }
              
                for (Iterator<Balloon> it = balloonDataStruct.iterator(); it.hasNext();) {
                    Balloon b = it.next();
                    collision(spike, b, livesLabel, scoreLabel);
                    if (b.height == sentinalH) {
                        it.remove();
                    }
                }
            }
            s.cls();
            s.print(1, 0, "GAME OVER! Press any key to start another game!", s.RED);
            s.refresh();
            s.inkey();
        }
    }
}
