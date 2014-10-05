
package spikegame;

import net.slashie.libjcsi.ConsoleSystemInterface;


public class LivesLabel {
       
    int lives;
    
    public LivesLabel() {
        lives = 3;
    }
    
    public void subtractLife() {
        this.lives--;
    }
    
    public boolean gameOver() {
        return this.lives <= 0;
    }
    
    
    public void draw (ConsoleSystemInterface s) {
        String disp = "Lives: " + lives;
        s.print(69, 1, disp, s.WHITE);
        
    }
}
