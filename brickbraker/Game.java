package brickbraker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, ActionListener{
//key for slider and action for moment of ball//
	private boolean play = false;
	private int score = 0;
	private int totalBricks = 21;
	private Timer timer;
	private int delay = 8;
	
	private int playerX = 310;
	private int BallPositionX = 120;
	private int BallPositionY = 350;
	private int BallXdir = -1;
	private int BallYdir = -2;
	
	private Bricks map;
		
	
	
	public Game() {
		map = new Bricks(4,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
	}
	public void paint(Graphics g) {
		//background//
		g.setColor(Color.ORANGE);
		g.fillRect(2, 2, 700, 600);
		
		//draw map
		map.draw((Graphics2D)g);
		//border//
		g.setColor(Color.white);
		g.fillRect(0, 0, 3, 600);
		g.fillRect(0, 0, 600, 3);
		g.fillRect(701, 0, 3, 600);
		
		//scores
		g.setColor(Color.red);
		g.setFont(new Font("Serif", Font.BOLD,25));
		g.drawString(""+score, 590, 30);
		
		//paddle//
		g.setColor(Color.black);
		g.fillRect(playerX, 550, 100, 8);
		
		//ball//
		g.setColor(Color.green);
		g.fillOval(BallPositionX, BallPositionY, 30 ,30);
		
		if(totalBricks <= 0) {
			play = false;
			BallXdir = 0;
			BallYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("Serif", Font.BOLD,30));
			g.drawString("You Won:", 260, 300);
			
			g.setFont(new Font("Serif", Font.BOLD,20));
			g.drawString("Press Enter to Restart", 230, 350);
			
		}
		if(BallPositionY > 570) {
			play = false;
			BallXdir = 0;
			BallYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("Serif", Font.BOLD,30));
			g.drawString("Game Over:", 190, 300);
			
			g.setFont(new Font("Serif", Font.BOLD,20));
			g.drawString("Press Enter to Restart", 230, 350);
			
			g.setFont(new Font("Serif", Font.BOLD,20));
			g.drawString("Scores:", 500, 30);
		}
		g.dispose();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			if(new Rectangle(BallPositionX, BallPositionY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				BallYdir = -BallYdir;
			}
			A:for(int i =0;i<map.map.length;i++) {//first map 29 line second map class bricks
				for(int j=0;j<map.map[0].length;j++) {
					if(map.map[i][j]>0) {
						int brickX = j*map.brickwidth + 80;
						int brickY = i*map.brickheight + 50;
						int brickwidth = map.brickwidth;
						int brickheight = map.brickheight;
						
						Rectangle rect = new Rectangle(brickX,brickY,brickwidth,brickheight);
						Rectangle ballRect = new Rectangle(BallPositionX,BallPositionY,20,20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0,i,j);
							totalBricks--;
							score += 5;
							
							if(BallPositionX + 19<=brickRect.x || BallPositionX + 1>=brickRect.x + brickRect.width) {
								BallXdir = -BallXdir;
	
							}else {
								BallYdir = -BallYdir;
								
							}
							break A;
						}
						
					}
				}
					
			}
		
		
		
			
			BallPositionX += BallXdir;
			BallPositionY += BallYdir;
			if(BallPositionX < 0) {
				BallXdir = -BallXdir;    //left border
			}
			if(BallPositionY < 0) {
				BallYdir = -BallYdir;   //top
			
		}if(BallPositionX > 670) {
			BallXdir = -BallXdir;      //right
			}
		}
			repaint(); 
	

	}


	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyReleased(KeyEvent e) { }
		
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >=600) {
				playerX = 600;
				
		}else {
			moveRight();
		}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				if(playerX < 10) {
					playerX = 10;
					
			}else {
				moveLeft();
		}
			}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				BallPositionX = 120;
				BallPositionY = 350;
				BallXdir = -1;
				BallYdir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 21;
				map = new Bricks(4,7);
				
				repaint();
				
			}
		}
}
	public void moveRight() {
		play = true;
		playerX+=20;
	}
	public void moveLeft() {
		play = true;
		playerX-=20;
	}



		
	
	

}
