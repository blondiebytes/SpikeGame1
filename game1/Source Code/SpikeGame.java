package spikegame;

import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.CharKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import static spikegame.TestException.checkGameOverLives;
import static spikegame.TestException.checkIfPositiveScore;
import static spikegame.TestException.testAdded;
import static spikegame.TestException.testCollisionLivesScore;
import static spikegame.TestException.testReactTickSpikeBalloon;

public class SpikeGame {

    static int sentinalH = 55;
    boolean gameOver = false;
    Spike spike = new Spike();
    ArrayList<Balloon> balloonDataStruct = new ArrayList();
    LivesLabel livesLabel = new LivesLabel();
    ScoreLabel scoreLabel = new ScoreLabel();
    
    public SpikeGame() {
        
    }
    
    public SpikeGame(Spike spike, ArrayList<Balloon> balloons, 
            LivesLabel lives, ScoreLabel score, boolean gameOver) {
        this.spike = spike;
        this.balloonDataStruct = balloons;
        this.gameOver = gameOver;
        this.livesLabel = lives;
        this.scoreLabel = score;
    }
    
   public boolean shouldStart(CharKey t) {
       return t.isUpArrow();
   }
   
   public boolean shouldRestart(CharKey t) {
       return t.isDownArrow();
   }
   
   

    public SpikeGame collision(Balloon b) {
        Spike newSpike = spike;
        ArrayList<Balloon> newBalloonDataStruct = balloonDataStruct;
        LivesLabel newLives = livesLabel;
        ScoreLabel newScoreLabel = scoreLabel;
        boolean newGameOver = gameOver; 
        
        if ((spike.width == b.width) && (spike.height == b.height)) {
            // Spike hits the bubble
            newLives = livesLabel.subtractLife();
            b.height = sentinalH;
            //  System.out.println("I HAVE NOT COLLIDED");
            if (newLives.gameOver()) {
                // Change gameOver to true;
                newGameOver = true;
            }
        }
        if ((spike.width != b.width) && (spike.height == b.height)) {
            // spike misses the bubble
            newScoreLabel = scoreLabel.addScore();
            b.height = sentinalH;
        }
        
        return new SpikeGame(newSpike, newBalloonDataStruct, newLives, newScoreLabel, newGameOver);
    }
    
    public void draw(ConsoleSystemInterface s) {
                s.cls();
                spike.draw(s);
                for (Balloon b : balloonDataStruct) {
                    b.draw(s);
                }
                livesLabel.draw(s);
                scoreLabel.draw(s);
                s.refresh();
    }
    
    
    public SpikeGame reactAndTick(CharKey k) {
       ArrayList<Balloon> newBalloonDataStruct = new ArrayList();
       Spike newSpike = spike.react(k);
       
//        Dealing with the new balloon
       if (!newSpike.isEqualTo(spike)) {
         newBalloonDataStruct.add(new Balloon().tick());
       } 
       
       LivesLabel newLivesLabel = livesLabel;
       ScoreLabel newScoreLabel = scoreLabel;
       boolean newGameOver = gameOver;
       
        // If spike moves, balloons move
        for (Balloon b: balloonDataStruct) {
            Balloon nb = b;
            if (!newSpike.isEqualTo(spike)) {
                nb = b.tick();
             }
                newBalloonDataStruct.add(nb);
        }
        
        
        
        for (Iterator<Balloon> it = newBalloonDataStruct.iterator(); it.hasNext();) {
                    Balloon b = it.next();
                    SpikeGame newSpikeGameLivesScoreGameOver = 
                            (new SpikeGame (newSpike, newBalloonDataStruct, livesLabel, scoreLabel, gameOver)).collision(b);
                    newLivesLabel = newSpikeGameLivesScoreGameOver.livesLabel;
                    newScoreLabel = newSpikeGameLivesScoreGameOver.scoreLabel;
                    newGameOver = newSpikeGameLivesScoreGameOver.gameOver;
                    if (b.height == sentinalH) {
                        it.remove();
                    }
        }
        return new SpikeGame(newSpike, newBalloonDataStruct, newLivesLabel, newScoreLabel, newGameOver);
    }
    
    public boolean isEqualToLiScGo(SpikeGame s) {
        return (this.livesLabel.lives == s.livesLabel.lives) 
                && (this.scoreLabel.score == s.scoreLabel.score) 
                && (this.gameOver == s.gameOver);
    }
        
//    public static void testConstructor() {
//         SpikeGame spikeGame = new SpikeGame();
//         if (spikeGame.spike.height == 0 && spikeGame.spike.width == spikeGame.spike.MAX / 2) {
//             System.out.println("SUCCESS: The spike starts in the middle of the screen!");
//         } else {
//             System.out.println("FAILURE: The spike does not start in the middle of the screen!");
//         }
//         if (spikeGame.balloonDataStruct.isEmpty()) {
//             System.out.println("SUCCESS: The balloonDataStruct starts empty!"); 
//         } else {
//             System.out.println("FAILURE: The balloonDataStruct doesn't start empty!"); 
//         }
//         if (spikeGame.livesLabel.lives == 2) {
//             System.out.println("SUCCESS: You start with 2 LIVES"); 
//         } else {
//             System.out.println("FAILURE: You don't start with 2 LIVES"); 
//         }
//         if (spikeGame.scoreLabel.score == 0) {
//             System.out.println("SUCCESS: You start with no score");
//         } else {
//             System.out.println("FAILURE: You start with a score");
//         }
//         
//         
//    }
    
     public void verifyInvariants(SpikeGame newSpikeGame, CharKey rnb) throws Exception {
         
         // Once we get rid of prints, use these tester with exceptions
         // These first five tests work
         checkGameOverLives(this, newSpikeGame);
         checkIfPositiveScore(this, newSpikeGame);
         TestException.testConstructor();
         testCollisionLivesScore(this, newSpikeGame);
         testReactTickSpikeBalloon(this, newSpikeGame,rnb);
         testAdded(this,newSpikeGame, rnb);
         
         
         // NewGame -> Testing Given Constructor
         // -------------------------------
         // A new game should start with:
         // A Spike positioned at the top middle of the screen
         // An empty Array<Balloon List>
         // 2 Lives
         // 0 Score
         // A boolean which says the game is not over 
         
         
         // PRINTING TESTERS:
         //  SpikeGame.testConstructor();
         
         
         // Testing gameOver & Lives
         // ---------------------------------
         // If the game is over, then lives should equal 0.
         // If the game is not over, then lives should be either 2 or 1. 
         
         // PRINTING TESTERS:
//         if (!this.gameOver) {
//             System.out.println("If the game is not over, then " + this.livesLabel.lives + " <= " + 2);
//         } else {
//             System.out.println("If the game is over, then " + this.livesLabel.lives + " == " + 0);
//         }
//         if (!newSpikeGame.gameOver) {
//             System.out.println("If the game is not over, then " + newSpikeGame.livesLabel.lives + " <= " + 2);
//         } else {
//             System.out.println("If the game is over, then " + newSpikeGame.livesLabel.lives + " == " + 0);
//         }
            
         
        
         // Testing Score
         // ---------------------------------
         // In every game, the score should never be negative
         
         // PRINTING TESTERS
//         if (this.scoreLabel.score >= 0 && this.scoreLabel.score >= 0) {
//             System.out.println("SUCCESS: The score is never less than 0!");
//         } else {
//              System.out.println("FAILURE: The score is less than 0!");
//         }
            
         
      
         // Testing React & Tick with spike & balloons
         // ---------------------------------
        // Button pressed = right; then spike moves over to the right (width++);
         // also balloons should all move up in this case
         
         //Button pressed = left; then spike moves over to the left (width--);
         // also balloons should all move up in this case
         
          // Button pressed = anything else; spike doesn't move (width = width); 
         // balloons should not move in this case
        
         /// &&&&&&&
         
         
         //Testing Collision & the iteration
         // ---------------------------------
         // Iterate through balloons -> if an old balloon has a height of 1, then it is
         // about to hit the spike or not hit the spike. Either way it disappears in the
         // next turn, so we should make sure it was removed. 
         
         // PRINTING TESTS
//        int dw = 0;
//        if (rnb.isRightArrow()) {
//            dw = 1;
//        } else if (rnb.isLeftArrow()) {
//            dw = -1;
//        }
//        System.out.println("MoveRight -> Old Spike Width: " + (this.spike.width + dw) + " = " 
//                    + "New Spike Width: " + newSpikeGame.spike.width);
//        
//        if (dw != 0) {
//            for (Balloon b : this.balloonDataStruct) {
//                boolean found = false;
//                for (Balloon nb : newSpikeGame.balloonDataStruct) {
//                    if (b.identity == nb.identity) {
//                        System.out.println("MoveRight -> Old Balloon Height: " + (b.height - 1) + " == " + 
//                            "New Balloon Height: " + nb.height);
//                        }
//                    }
//                }
//            }
        
         
     
         // One of two things should happen everytime there is a collision:
         // If the balloon hits the spike, then it should have one less life
         // If the balloon does not hit the spike, then its score should increase
         // If neither of these things happen, then the spike's lives, score, and gameOver should stay the same
        
         // PRINTING TESTS
//
//        for (Balloon b : this.balloonDataStruct) {
//             if (b.height == 1) {
//                 // ONE OF THESE THINGS ARE TRUE
//                if ((this.livesLabel.lives - 1) == newSpikeGame.livesLabel.lives) {
//                    System.out.println("SUCCESS! HIT THE SPIKE!");
//                } else if ((this.scoreLabel.score + 5) == newSpikeGame.scoreLabel.score) {
//                     System.out.println("SUCCESS! MISS THE SPIKE!");
//                } else if (this.isEqualToLiScGo(newSpikeGame)) {
//                        System.out.println("SUCCESS! NO COLLISION!");
//                } else {
//                    System.out.println("ERROR!");
//                }
//        }
//    }
        
     }
     
     public CharKey randomButton() {
         Random randGen = new Random();
         int randInt = Math.abs(randGen.nextInt()) % 5;
         if (randInt == 0) {
            return new CharKey(CharKey.UARROW);
         } else if (randInt == 1) {
             return new CharKey(CharKey.DARROW);
         } else if (randInt == 2) {
             return new CharKey(CharKey.RARROW);
         } else if (randInt == 3) {
             return new CharKey(CharKey.LARROW);
         } else {
             int randCharKey = Math.abs(randGen.nextInt() % 128);
             return new CharKey(randCharKey);
         }
     }

     
}