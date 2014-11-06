
package spikegame;

import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import static spikegame.SpikeGameTest.upArrowStartTest;
import static spikegame.TestException.checkGameOverLives;
import static spikegame.TestException.checkIfPositiveScore;
import static spikegame.TestException.testAdded;
import static spikegame.TestException.testCollisionLivesScore;
import static spikegame.TestException.testConstructor;
import static spikegame.TestException.testReactTickSpikeBalloon;
import static spikegame.TestException.testReactTickSpikeBalloon;


public class SpikeGameRun {
    
    public static void main(String[] args) throws Exception {

        ConsoleSystemInterface s = new WSwingConsoleInterface("Spike by Kathryn", true);

        while (true) {
            // Create a new SpikeGame
            SpikeGame spikeGame = new SpikeGame(); 
            // Set up initial view
            s.cls();
            s.print(2, 1, "Press the Up arrow to start the game!", s.CYAN);
            s.refresh();
            // Check key to start the game
            while (!spikeGame.shouldStart(s.inkey())) { }
            
            // Keep drawing, reacting, and ticking until the game is over
            while (!spikeGame.gameOver) {
                  spikeGame.draw(s);
                  CharKey k = s.inkey();
                  SpikeGame newSpikeGame = spikeGame.reactAndTick(k);
                  spikeGame.verifyInvariants(newSpikeGame,k);
                  spikeGame = newSpikeGame;
            }
            // Set up final view
            s.cls();
            s.print(2, 1, "GAME OVER! Press the Down arrow to start another game!", s.RED);
            s.refresh();
            // Check key to restart the game
             while (!spikeGame.shouldRestart(s.inkey())) { }
             System.out.println("checkGameOverLives success: " + checkGameOverLives + " times");
                System.out.println("checkIfPositiveScore success: " + checkIfPositiveScore + " times");
                System.out.println("testConstructor success: " + testConstructor + " times");
                System.out.println("testCollisionLivesScore success: " + testCollisionLivesScore + " times");
                System.out.println("testReactTickSpikeBalloon success: " + testReactTickSpikeBalloon + " times");
                System.out.println("testAdded success: " + testAdded + " times");
             }
               
    }
}