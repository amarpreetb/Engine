package Brick;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	public int map[][];
	public int brickWidth;
	public int brickHeight;
	
	public MapGenerator(int row, int color) {
		map = new int[row][color];
		for(int i = 0; i < map.length; i++) {
			for(int j  = 0; j < map[0].length; i++) {
				map[i][j] = 1;
			}
			
		}
		
		brickWidth = 540/color;
		brickHeight = 150/row;
	}
	
	public void draw(Graphics2D g) {
		for(int i = 0; i < map.length; i++) {
			for(int j  = 0; j < map[0].length; i++) {
				if(map[i][j] > 0) {
					g.setColor(Color.white);
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				    
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				    
				}
			}
			
		}
	}
	
	public void setBrickValue(int value) {
		
	}

}