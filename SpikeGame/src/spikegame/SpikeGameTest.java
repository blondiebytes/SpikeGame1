
package spikegame;

import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import static spikegame.TestException.checkGameOverLives;
import static spikegame.TestException.checkIfPositiveScore;
import static spikegame.TestException.testAdded;
import static spikegame.TestException.testCollisionLivesScore;
import static spikegame.TestException.testConstructor;
import static spikegame.TestException.testReactTickSpikeBalloon;

public class SpikeGameTest {
    static int upArrowStartTest = 0;
    static int downArrowRestartTest = 0;
    
  public static void main(String[] args) throws Exception {
       for (int game = 0; game <= 1000; game++) {
            // Create a new SpikeGame
            SpikeGame spikeGame = new SpikeGame(); 
           
            // Testing Key to Start the Game!
             for (int turn = 0; turn < 10; turn++) {
                 CharKey rk = spikeGame.randomButton();
                 if (!spikeGame.shouldStart(rk) && rk.isUpArrow()) {
                       throw new Exception("Our spikeGame isn't starting even"
                               + "though we are pressing the up arrow");
                         }
                 if (spikeGame.shouldStart(rk) && !rk.isUpArrow() ){
                     throw new Exception("Our spikeGame is starting even though"
                             + "we aren't pressing the up arrow");
                 }
                 upArrowStartTest++;
             }
                 

            
            // Keep drawing, reacting, and ticking until the game is over
            while (!spikeGame.gameOver) {
                  CharKey rndB = spikeGame.randomButton(); 
                  System.out.flush();
                  SpikeGame newSpikeGame = spikeGame.reactAndTick(rndB);
                  spikeGame.verifyInvariants(newSpikeGame, rndB);
                  spikeGame = newSpikeGame;
            }
           
            
            
            // Testing Key to Restart the Game!
            for (int turn = 0; turn < 25; turn++) {
                 CharKey rk = spikeGame.randomButton();
                 if (!spikeGame.shouldRestart(rk) && rk.isDownArrow()) {
                       throw new Exception("Our spikeGame isn't restarting even"
                               + "though we are pressing the down arrow");
                         }
                 if (spikeGame.shouldRestart(rk) && !rk.isDownArrow() ){
                     throw new Exception("Our spikeGame is restarting even though"
                             + "we aren't pressing the down arrow");
                 }
                 
                 downArrowRestartTest++;
                 }
            
             }
       
                System.out.println("upArrowStartTest run: " + upArrowStartTest + " times");
                System.out.println("downArrowRestartTest run: " + downArrowRestartTest + " times");
                System.out.println("checkGameOverLives run: " + checkGameOverLives + " times");
                System.out.println("checkIfPositiveScore run: " + checkIfPositiveScore + " times");
                System.out.println("testConstructor run: " + testConstructor + " times");
                System.out.println("testCollisionLivesScore run: " + testCollisionLivesScore + " times");
                System.out.println("testReactTickSpikeBalloon run: " + testReactTickSpikeBalloon + " times");
                System.out.println("testAdded run: " + testAdded + " times");
  }
  
    }  
         