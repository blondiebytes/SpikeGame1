package spikegame;

import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.CharKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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
                    System.out.println("?");
            
                    b.draw(s);
                }
                livesLabel.draw(s);
                scoreLabel.draw(s);
                s.refresh();
    }
    
    
    public SpikeGame reactAndTick(CharKey k) {
       Spike newSpike = spike.react(k);
       ArrayList<Balloon> newBalloonDataStruct = new ArrayList();
       LivesLabel newLivesLabel = livesLabel;
       ScoreLabel newScoreLabel = scoreLabel;
       boolean newGameOver = gameOver;

       newBalloonDataStruct.add(new Balloon());
       
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
                    SpikeGame newSpikeGameLivesScoreGameOver = this.collision(b);
                    newSpikeGameLivesScoreGameOver.livesLabel = newLivesLabel;
                    newSpikeGameLivesScoreGameOver.scoreLabel = newScoreLabel;
                    newSpikeGameLivesScoreGameOver.gameOver = newGameOver;
                    if (b.height == sentinalH) {
                        it.remove();
                    }
        }
        return new SpikeGame(newSpike, newBalloonDataStruct, newLivesLabel, newScoreLabel, newGameOver);
    }
        
    
    
     public void verifyInvariants(SpikeGame oldSpikeGame, SpikeGame newSpikeGame, CharKey rnb) {
         
         // NewGame -> Testing Given Constructor
         // -------------------------------
         // A new game should start with:
         // A Spike positioned at the top middle of the screen
         // An empty Array<Balloon List>
         // 2 Lives
         // 0 Score
         // A boolean which says the game is not over 
         
         
         // Testing gameOver & Lives
         // ---------------------------------
         // If the game is over, then lives should equal 0.
         // If the game is not over, then lives should be either 2 or 1. 
         
           // Testing Score
         // ---------------------------------
         // In every game, the score should never be negative
      
         // Testing React & Tick with spike & balloons
         // ---------------------------------
        // Button pressed = right; then spike moves over to the right (width++);
         // also balloons should all move up in this case
         
         //Button pressed = left; then spike moves over to the left (width--);
         // also balloons should all move up in this case
         
         // Button pressed = anything else; spike doesn't move (width = width); 
         // balloons should not move in this case
         
         
         //Testing Collision & the iteration
         // ---------------------------------
         //Iterate through balloons -> if an old balloon has a height of 1, then it is
         // about to hit the spike or not hit the spike. Either way it disappears in the
         // next turn, so we should make sure it was removed. 
         
         // One of two things should happen everytime there is a collision
         // If the balloon hits the spike, then it should have one less life
         // If the balloon does not hit the spike, then its score should increase
         

         
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