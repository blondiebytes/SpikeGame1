
package spikegame;

import net.slashie.libjcsi.CharKey;


public class TestException {
    public static void checkGameOverLives (SpikeGame oSG, SpikeGame nSG) throws Exception {
        if (oSG.gameOver) {
            if (oSG.livesLabel.lives != 0) {
                throw new Exception("Still have lives & gameOver");
            }
        } else {
            if (oSG.livesLabel.lives == 0) {
                throw new Exception("0 Lives & gameNotOver");
            }
        if (nSG.gameOver) {
            if (nSG.livesLabel.lives != 0) {
                throw new Exception("Still have lives & gameOver");
            }
        } else {
            if (nSG.livesLabel.lives == 0) {
                throw new Exception("0 Lives & gameNotOver");
            }
        }
}
    }
  public static void checkIfPositiveScore (SpikeGame oSG, SpikeGame nSG) throws Exception {
    if (oSG.scoreLabel.score < 0 || nSG.scoreLabel.score < 0) {
             throw new Exception ("Score is not positive");
         }
     }
  
  public static void testConstructor() throws Exception {
         SpikeGame spikeGame = new SpikeGame();
         if (spikeGame.spike.height != 0 && spikeGame.spike.width != spikeGame.spike.MAX / 2) {
             throw new Exception ("FAILURE: The spike does not start in the middle of the screen!");
         }
         if (!spikeGame.balloonDataStruct.isEmpty()) {
             throw new Exception("FAILURE: The balloonDataStruct doesn't start empty!");
         }
         if (spikeGame.livesLabel.lives != 2) {
             throw new Exception ("FAILURE: You don't start with 2 LIVES"); 
         }
         if (spikeGame.scoreLabel.score != 0) {
             throw new Exception ("FAILURE: You start with a score");
         }
         
    }
  
  public static void testCollisionLivesScore(SpikeGame oldSpikeGame, SpikeGame newSpikeGame) throws Exception {
           for (Balloon b : oldSpikeGame.balloonDataStruct) {
             if (b.height == 1) {
                 // ONE OF THESE THINGS ARE TRUE
                if ((oldSpikeGame.livesLabel.lives - 1) == newSpikeGame.livesLabel.lives) {
                } 
                else 
                    if ((oldSpikeGame.scoreLabel.score + 5) == newSpikeGame.scoreLabel.score) {
                } 
                else 
                    if (oldSpikeGame.isEqualToLiScGo(newSpikeGame)) {
                } 
                else {
                    throw new Exception("ERROR! Lives & Score & Collision Messed Up");
                }
         }
    }
  }
  
  public static void testReactTickSpikeBalloon(SpikeGame oldSpikeGame, SpikeGame newSpikeGame, CharKey rnb) throws Exception {
        if(rnb.isRightArrow()) {
            if ((oldSpikeGame.spike.width + 1) != newSpikeGame.spike.width) {
                throw new Exception("MoveRight Spike doesn't work");
            }
            for (Balloon b : oldSpikeGame.balloonDataStruct) {
                for (Balloon nb : newSpikeGame.balloonDataStruct) {
                    if ((b.height - 1) != nb.height) {
                        throw new Exception("Balloons don't move up!");
                    }
                    break;
                }
            }
         } else if(rnb.isLeftArrow()) {
            if ((oldSpikeGame.spike.width - 1) != newSpikeGame.spike.width) {
                throw new Exception("MoveLeft Spike doesn't work");
            }
            for (Balloon b : oldSpikeGame.balloonDataStruct) {
                for (Balloon nb : newSpikeGame.balloonDataStruct) {
                    if ((b.height - 1) != nb.height) {
                        throw new Exception("Balloons don't move up!");
                    }
                    break;
                }
            }
        } else {
              if (oldSpikeGame.spike.width != newSpikeGame.spike.width) {
                throw new Exception("NoMove Spike doesn't work");
            }
            for (Balloon b : oldSpikeGame.balloonDataStruct) {
                for (Balloon nb : newSpikeGame.balloonDataStruct) {
                    if (b.height == nb.height) {
                        throw new Exception("Balloons move!");
                    }
                    break;
                }
            }
         }
  }
  
    public static void testCollisionBubbleIteration(SpikeGame oldSpikeGame, SpikeGame newSpikeGame) throws Exception {
        int i = 0;
        for (Balloon b : oldSpikeGame.balloonDataStruct) {
             if (b.height == 1) {
                i++;
             }
         }
        if (i == 0) {
            if (oldSpikeGame.balloonDataStruct.size() != newSpikeGame.balloonDataStruct.size()) {
                throw new Exception("NOTHING COLLIDED but the balloon data size change");
            }
        } else {
            if (oldSpikeGame.balloonDataStruct.size() != newSpikeGame.balloonDataStruct.size()) {
                throw new Exception("SOMETHING COLLIDED but balloon data size didn't change");
            }
        }
         
           
  }
}
