
package spikegame;

import net.slashie.libjcsi.ConsoleSystemInterface;

public class ScoreLabel {
    int score;
    
    
    public ScoreLabel() {
        score = 0;
    }
    
    public void addScore() {
        this.score = this.score + 5;
    }
    
     public void subtractScore() {
        this.score = this.score - 5;
    }
    
    
    public void draw(ConsoleSystemInterface s) {
        String disp = "Score: " + score;
        s.print(69, 3, disp, s.WHITE);
        
    }
}
