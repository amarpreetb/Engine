package Brick;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 The following creates the window.
		 */
		JFrame obj = new JFrame();
		Gameplay gamePlay = new Gameplay();//obj Gameplay class
		obj.setBounds(10, 10, 700, 600);
		obj.setTitle("Brick game");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gamePlay);

	}

}
