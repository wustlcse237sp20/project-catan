package catan.Board;
import java.awt.Color;
import java.awt.Point;

public class Tile {
	private double centerX;
	private double centerY;
	private int value;
	private Color color;
	private double width;
	private boolean hasRobber;
	
	/**
	 * Initializes Tile with the following parameters:
	 * @param x x-coordinate of center of hexagon tile
	 * @param y y-coordinate of center of hexagon tile
	 * @param tileValue number associated with the tile: if dice rolls this number, tile is "activated"
	 * @param tileColor
	 * @param tileWidth
	 */
	public Tile(double x, double y, int tileValue, Color tileColor, double tileWidth) {
		centerX = x;
		centerY = y;
		value = tileValue;
		color = tileColor;
		width = tileWidth;
		if (tileValue==0) {
			hasRobber = true;
		} else {
			hasRobber = false;
		}
	}
	
	/**
	 * Draws a hexagonal tile based on its x/y coordinates, color, and value
	 */
	public void drawTile() {
		double hypotenuse = Math.sqrt(2);
		double multiple = width*hypotenuse;
			double[] xCoord = {centerX+width, centerX +width, centerX, centerX-width,centerX-width, centerX };
			double[] yCoord = {centerY+multiple/2, centerY - multiple/2, centerY-2*multiple/2, centerY-multiple/2, centerY+multiple/2, centerY+2*multiple/2};
			StdDraw.setPenColor(color);
			StdDraw.filledPolygon(xCoord, yCoord);
			StdDraw.setPenColor(StdDraw.PINK);
			
			
			StdDraw.filledCircle(centerX, centerY, width/2);
			StdDraw.setPenColor(StdDraw.WHITE);
			if( hasRobber) {
				StdDraw.text(centerX, centerY, "R")	;
			} else {
				
				StdDraw.text(centerX, centerY, value+"");
				
			}
	}
	
	public boolean hasRobber() {
		return hasRobber;
	}
	
	public Point.Double getPoint() {
		Point.Double pointDouble = new Point.Double(centerX, centerY);
		return pointDouble;
	}
	public int comparePoint (Point.Double compared) {
		if(compared.x >  centerX) {
			return -1;
		} else if (compared.x <  centerX) {
			return 1;
		} else {
			if(compared.y >  centerY) {
				return -1;
			} else if (compared.y <  centerY) {
				return 1;
			} else {
				return 0;
			}
			
			
		}
		
	}
	
	public void setRobber(boolean newValue) {
		hasRobber = newValue;
	}
  public static void main(String[] args) {
		// TODO Auto-generated method stub 
      System.out.println("SDFSDF");
	}
}

