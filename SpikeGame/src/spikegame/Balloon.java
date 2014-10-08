
package spikegame;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.CharKey;
import java.util.Random;


public class Balloon {
    static final int MAXH = 25;
    static final int MAXW = 79;
    static final int MAX = MAXH;
    // Make width random later
    int width = 30;
    int height;
    int identity; 
    static int count;
    
    Balloon() {
        this(MAX, -500, count);
        count++;
    }
    
    private Balloon (int height, int width, int identity) {
        this.height = height;
        // Using Sentinal w = -500
        if (width == -500) {
            Random random = new Random();
            this.width = Math.abs(random.nextInt()) % MAXW;
        } else 
        {
            this.width = width;
        }
        this.identity = identity;
        
    }
    
    public Balloon tick() {
        return new Balloon(this.height - 1, this.width, this.identity);
    }
    
    public Balloon react (CharKey k) {
     // UI doesn't touch balloons
        return this;
    }
    
    public void draw (ConsoleSystemInterface s) {
        String disp = "()";
            s.print(width, height, disp, s.CYAN);
        
    }
     
}
