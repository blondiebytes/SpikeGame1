package net.slashie.libjcsi.examples;

import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

public class SCBExample {
	public static void main(String[] args) {
		ConsoleSystemInterface csi = null;
		try{
			csi = new WSwingConsoleInterface();
		}
        catch (ExceptionInInitializerError eiie){
        	System.out.println("Fatal Error Initializing Swing Console Box");
        	eiie.printStackTrace();
            System.exit(-1);
        }
        csi.cls();
        csi.print(1,1,"Hello, Hello");
        csi.print(2,3,"This is printed using the CSI lib, Swing Console Box Implementation!");
	}
}
