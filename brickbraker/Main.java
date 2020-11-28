package brickbraker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame obj = new JFrame();
		Game game = new Game();
		obj.setBounds(10, 10, 700, 600);
		obj.setTitle("Brick Braker");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(game);
	}

}
