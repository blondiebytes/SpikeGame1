
package spikegame;


import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.CharKey;
/**
 *
 * @author kathrynhodge
 */

public class Spike {
    
      static final int MAXH = 100;
    static final int MAXW = 100;
    static final int MAX = MAXW;
    static final int height = 0;
    int width;
    int deltaWidth;
    
    Spike() {
        this(MAX, -1);
    }
    
    private Spike (int width, int deltawidth) {
        this.width = width;
        this.deltaWidth = width;
    }
    
    public Spike tick() {
        int newWidth = width + deltaWidth;
        if (newWidth < 0) {
            return new Spike(0, deltaWidth);
        } else if (newWidth > MAX) {
            return new Spike(MAX, -deltaWidth);
        } else {
            return new Spike(newWidth, deltaWidth);
        }
    }
    
    public Spike react (CharKey k) {
        if (k.isRightArrow()) {
            return new Spike (width, deltaWidth);
        } if (k.isLeftArrow()) {
            return new Spike (width, -deltaWidth);
        } else {
            return this;
        }
    }
    
    public void draw (ConsoleSystemInterface s) {
        String disp = "\\_/";
        s.print(width, height, disp, s.WHITE);
        
    }
    
    public static void test() {
        
    }
    
    }

    
    
    

