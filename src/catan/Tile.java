package catan;
import java.awt.Color;
import java.awt.Point;

public class Tile {
	private double centerX;
	private double centerY;
	private int value;
	private Color color;
	private double width;
	private boolean hasRobber;
	
	
	private Player[] tileRoads; //represents the EDGES of each tile where roads are
	private Player[] tileSettlements; //represents CORNERS of each tile where settlements are
	
	/**
	 * Initializes Tile with the following parameters:
	 * @param x x-coordinate of center of hexagon tile
	 * @param y y-coordinate of center of hexagon tile
	 * @param tileValue number associated with the tile: if dice rolls this number, tile is "activated"
	 * @param tileColor
	 * @param tileWidth
	 * @param tileRoads array of index 6. Each cell represents an edge on the tile, with index 0 referencing middle-left edge, going clockwise
	 * @param tileSettlements is same as tileRoads but representing corner of tile, with index 0 referencing the top corner, going clockwise
	 */
	public Tile(double x, double y, int tileValue, Color tileColor, double tileWidth) {
		centerX = x;
		centerY = y;
		value = tileValue;
		color = tileColor;
		width = tileWidth;
		
		tileRoads = new Player[6]; 
		tileSettlements = new Player[6];
		
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

	
	
	
	
	 
	
	public Player[] getRoads() {
		return tileRoads;
	}
	
	
	public Player[] getSettlements() {
		return tileSettlements;
	}
	
	/**
	 * Updates Tile's tileRoads[] array with a new road from a Player.
	 * @return 0 if index is taken, 1 if index is available and road inserted
	*/
	public int buildRoad(Tile tile, int index, Player builder) {
		if(index < 0 || index > 5) { //index out of bounds
			return 0;
		}
		if(tile.getRoads()[index] != null) { //if someone has built there
			return 0;
		}
		else {
			tile.getRoads()[index] = builder;
			return 1;
		}
	}
	
	/**
	 * Updates Tile's tileSettlements[] array with a new settlement from a Player.
	 * @return 0 if index is taken, 1 if index is available and settlement built
	*/
	public int buildSettlement(Tile tile, int index, Player builder) {
		if(index < 0 || index > 5) { //index out of bounds
			return 0;
		}
		if(tile.getSettlements()[index] != null) { //if someone has built there
			return 0;
		}
		else {
			tile.getSettlements()[index] = builder;
			return 1;
		}
	}
	
	
	
  public static void main(String[] args) {
		// TODO Auto-generated method stub 
      System.out.println("SDFSDF");
	}
}

