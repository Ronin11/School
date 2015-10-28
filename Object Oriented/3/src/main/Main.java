package main;

import gui.GUI;

public class Main {
	public static void main(String args[]){
		System.out.println("Program Start");
		
		GUI gui = new GUI();
		//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui.createAndShowGUI();
            }
        });
	}
}