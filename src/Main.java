package Project2;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		MineSweeperGUI myFrame = new MineSweeperGUI();
		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.pack();
		myFrame.setVisible(true);
	}
}