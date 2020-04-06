package Catan;
import java.awt.Color;

public class Tile {

	private double centerX;
	private double centerY;
	private int value;
	private Color color;
	private double width;
	private boolean hasRobber;
	
	
	public Tile(double x, double y, int tileValue, Color tileColor, double tileWidth) {
		centerX =x;
		centerY = y;
		value =tileValue;
		color = tileColor;
		width = tileWidth;
		if (tileValue==0) {
			hasRobber = true;
		} else {
			hasRobber = false;
		}
	}
	
	/**
     * Draws Hexagonal Tile
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
	public void setRobber(boolean newValue) {
		hasRobber = newValue;
	}
}

