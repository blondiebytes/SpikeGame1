
package spikegame;

import net.slashie.libjcsi.ConsoleSystemInterface;


public class LivesLabel {
       
    int lives;
    
    public LivesLabel() {
        lives = 2;
    }
    
    public LivesLabel(int lives) {
        this.lives = lives;
    }
    
    public LivesLabel subtractLife() {
        return new LivesLabel(this.lives - 1);
    }
    
    public boolean gameOver() {
        return this.lives <= 0;
    }
    
    
    public void draw (ConsoleSystemInterface s) {
        String disp = "Lives: " + lives;
        s.print(69, 1, disp, s.WHITE);
        
    }
}
