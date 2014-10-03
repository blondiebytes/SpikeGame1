
package spikegame;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
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
    int deltaHeight;
    
    Balloon() {
        this(MAX, -1, -500);
    }
    
    private Balloon (int height, int deltaHeight, int width) {
        this.height = height;
        this.deltaHeight = deltaHeight;
        // Using Sentinal w = -500
        if (width == -500) {
            Random random = new Random();
            this.width = Math.abs(random.nextInt()) % MAXW;
        } else 
        {
            this.width = width;
        }
    }
    
    public Balloon tick() {
        int newHeight = height + deltaHeight;
        if (newHeight < 0) {
            return new Balloon(0, deltaHeight, width);
        } else if (newHeight > MAX) {
            return new Balloon(MAX, -deltaHeight, width);
        } else {
            return new Balloon(newHeight, deltaHeight, width);
        }
    }
    
    public Balloon react (CharKey k) {
     // UI doesn't touch balloons
        return this;
    }
    
    public void draw (ConsoleSystemInterface s) {
        String disp = "()";
        s.print(width, height, disp, s.WHITE);
        
    }
    
    public static void test() {
        
    }
     
}
