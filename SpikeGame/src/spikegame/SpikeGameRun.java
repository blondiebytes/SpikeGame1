
package spikegame;

import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
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
             }
    }   
}