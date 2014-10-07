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
    
   public void checkCharKeyUp(CharKey t, ConsoleSystemInterface s) {
         while (!t.isUpArrow()) {
                CharKey a = s.inkey();
                if (a.isUpArrow()) {
                    return;
                }
        }
    }
   
   public void checkCharKeyUpTest(CharKey t, ConsoleSystemInterface s) {
         while (!t.isUpArrow()) {
                CharKey a = this.randomButton();
                if (a.isUpArrow()) {
                    System.out.println("The random button is Up and now "
                            + "we start the game!");
                    return;
                }
        }
    }
   
   public void checkCharKeyDown(CharKey t, ConsoleSystemInterface s) {
       while (!t.isDownArrow()) {
                CharKey a = s.inkey();
                if (a.isDownArrow()) {
                    return;
                }
        }
   }
   
   public void checkCharKeyUpDownTest(CharKey t, ConsoleSystemInterface s) {
         while (!t.isUpArrow()) {
                CharKey a = this.randomButton();
                if (a.isUpArrow()) {
                     System.out.println("The random button is Down and now "
                            + "we restart the session!");
                    return;
                }
        }
    }
   

    public void collision(Balloon b) {
        if ((spike.width == b.width) && (spike.height == b.height)) {
            // Spike hits the bubble
            livesLabel.subtractLife();
            scoreLabel.subtractScore();
            b.height = sentinalH;
            //  System.out.println("I HAVE NOT COLLIDED");
            if (livesLabel.gameOver()) {
                // Change gameOver to true;
                gameOver = true;
            }
        }

        if ((spike.width != b.width) && (spike.height == b.height)) {
            // spike misses the bubble
            scoreLabel.addScore();
            b.height = sentinalH;
        }
    }
    
    public void draw(ConsoleSystemInterface s) {
                s.cls();
                balloonDataStruct.add(new Balloon());
                spike.draw(s);
                for (Balloon b : balloonDataStruct) {
                    b.draw(s);
                }
                livesLabel.draw(s);
                scoreLabel.draw(s);
                s.refresh();
    }
    
    
    public void reactAndTick(CharKey k, ConsoleSystemInterface s) {

        Spike oldSpike = spike;
        Spike newSpike = spike.react(k);
        // If spike moves, balloons move
        if (!NewSpike.isEqualTo(spike)) {
            for (Balloon b: balloonDataStruct) {
                newArray.add(b.tick());
             }
        }
        
        for (Iterator<Balloon> it = balloonDataStruct.iterator(); it.hasNext();) {
                    Balloon b = it.next();
                    this.collision(b);
                    if (b.height == sentinalH) {
                        it.remove();
                    }
             }
        
        return new SpikeGame( newSpike, newBalloons );
    }
        
    
    
     public void verifyInvariants() {
        
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

    void checkCharKeyDownTest(CharKey randomButton, ConsoleSystemInterface s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}