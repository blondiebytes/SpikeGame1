
package spikegame;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.CharKey;
/**
 *
 * @author kathrynhodge
 */

public class Spike {
    
    static final int MAXH = 20;
    static final int MAXW = 78;
    int MAX = MAXW;
    int height = 0;
    int width;
    int deltaWidth;
    
    Spike() {
        this(MAXW/2, -1);
    }
    
    private Spike (int width, int deltaWidth) {
        this.width = width;
        this.deltaWidth = deltaWidth;
    }
    
    public Spike tick() {
        int newWidth = width + deltaWidth;
        if (newWidth < 0) {
            return new Spike(0, deltaWidth);
        } else if (newWidth >= MAX) {
            return new Spike(MAX, -deltaWidth);
        } else {
            return new Spike(newWidth, deltaWidth);
        }
    }
    
    public Spike react (CharKey k) {
        if (k.isRightArrow()) {
            return new Spike (width, Math.abs(deltaWidth)).tick();
        } if (k.isLeftArrow()) {
            return new Spike (width, -Math.abs(deltaWidth)).tick();
        } else {
            return this;
        }
    }
    
    public boolean isEqualTo(Spike s) {
        return (this.deltaWidth == s.deltaWidth) 
                && (this.height == s.height) 
                && (this.width == s.width);
    }
    
    public void draw (ConsoleSystemInterface s) {
        String disp = "\\/";
        s.print(width, height, disp, s.RED);
        
    }
    
    
    }

    
    
    

