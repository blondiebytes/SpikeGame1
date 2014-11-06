
package spikegame;

import net.slashie.libjcsi.ConsoleSystemInterface;

public class ScoreLabel {
    int score;
    
    
    public ScoreLabel() {
        score = 0;
    }
    
    public ScoreLabel(int score) {
        this.score = score;
    }
    
    public ScoreLabel addScore() {
        return new ScoreLabel(this.score + 5);
    }
    
     public ScoreLabel subtractScore() {
        return new ScoreLabel(this.score - 5);
    }
    
    
    public void draw(ConsoleSystemInterface s) {
        String disp = "Score: " + score;
        s.print(69, 3, disp, s.WHITE);
        
    }
}
