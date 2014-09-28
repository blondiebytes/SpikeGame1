package spikegame;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.CharKey;


/**
 *
 * @author kathrynhodge
 */

public class SpikeGame {

    public static void main ( String[] args ) throws InterruptedException {
        ConsoleSystemInterface s = new WSwingConsoleInterface("Spikey the Spike by Kathryn", true);

        s.cls();
        s.print(1, 0, "Press any key to start the game!.", s.RED);
        s.refresh();
        s.inkey();

        Balloon balloon = new Balloon();
        Spike spike = new Spike();

        while (true) {
            s.cls();
            spike.draw(s);
            balloon.draw(s);
            s.refresh();
            CharKey k - s.inkey();
            spike = spike.react(k);
            spike = spike.tick();
            balloon = balloon.tick();
        }   
}
}
