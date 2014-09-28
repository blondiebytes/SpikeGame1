
package spikegame;

import javalib.appletworld.World;


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
