
package spikegame;

import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

public class SpikeGameTest {
    
  public static void main(String[] args) {
        while (true) {
            // Create a new SpikeGame
            SpikeGame spikeGame = new SpikeGame(); 
           
            // Testing Key to Start the Game!
             for (int turn = 0; turn < 50; turn++) {
                 CharKey rk = spikeGame.randomButton();
                 if (rk.isUpArrow()) {
                       System.out.println("Our random button is the Up "
                               + "Arrow so the "
                               + "game should start and " 
                               + spikeGame.shouldStart(rk) + " == true");
                         } else {
                     System.out.println("Our random button is not the Up Arrow"
                             + "so the game should not start and "
                             + spikeGame.shouldStart(rk) + " == false");
                 }
             }
                 

            
            // Keep drawing, reacting, and ticking until the game is over
            while (!spikeGame.gameOver) {
                  SpikeGame newSpikeGame = spikeGame.reactAndTick(spikeGame.randomButton());
                  spikeGame.verifyInvariants(spikeGame, newSpikeGame);
                  spikeGame = newSpikeGame;
                  System.out.println("I reacted to thingz");
            }
           
            
            
            // Testing Key to Restart the Game!
             for (int turn = 0; turn < 50; turn++) {
                 CharKey rk = spikeGame.randomButton();
                 if (rk.isDownArrow()) {
                       System.out.println("Our random button is the Up "
                               + "Arrow so the "
                               + "game should start and " 
                               + spikeGame.shouldRestart(rk) + " == true");
                         } else {
                     System.out.println("Our random button is not the Up Arrow"
                             + "so the game should not start and "
                             + spikeGame.shouldRestart(rk) + "== false");
                 }
             }
             
             }
    }   
}




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
