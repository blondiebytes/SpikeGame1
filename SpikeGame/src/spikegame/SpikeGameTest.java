
package spikegame;

import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

public class SpikeGameTest {
    
  public static void main(String[] args) {
      //ConsoleSystemInterface s = new WSwingConsoleInterface("Spike by Kathryn", true);
        while (true) {
            // Create a new SpikeGame
            SpikeGame spikeGame = new SpikeGame(); 
            // Set up initial view
            s.cls();
            s.print(2, 1, "Press the Up arrow to start the game!", s.CYAN);
            s.refresh();
            // Check key to start the game
            spikeGame.checkCharKeyUpTest(spikeGame.randomButton(), s);
            
             for ( 10 tests ) {
                 CharKey rk = spikeGame.randomButton();
                 if ( rk isn't up arrow ') {
                 spikeGame.shouldUnpause(rk) == false
                         }
             }
             spikeGame.shouldUnpause(UpArrow) == true
                 

            
            // Keep drawing, reacting, and ticking until the game is over
            while (!spikeGame.gameOver) {
                  spikeGame.draw(s);
                  System.out.println("I drew thingz");
                  spikeGame.reactAndTick(spikeGame.randomButton(), s);
                  spikeGame.verifyInvariants();
                  System.out.println("I reacted to thingz");
            }
            // Set up final view
            s.cls();
            s.print(2, 1, "GAME OVER! Press the Down arrow to start another game!", s.RED);
            s.refresh();
            // Check key to restart the game
            spikeGame.checkCharKeyDownTest(spikeGame.randomButton(), s);
             }
    }   
}




// new CharKey(CharKey.UARROW);
    //    public static void main(String[] args) {
//      SpikeGame spikeGame = new SpikeGame();
//        for (int turn = 0; turn < 100: turn++) {
//         spikeGame = spikeGame.randomButton().tick();
//         spikeGame.verifyInvariants(); 
//    }
//    }
//
//}
         
         
         
         
         
// where .verifyInvariants is a method that does tests like making sure 
//the player is on the screen and not inside a block, or something like 
//that. 
//
//This is essentially a way of taking properties like "For all sequences 
//of input from the player, X should be true" and approximating "all 
//sequences" to a set of randomly chosen sequences. 
