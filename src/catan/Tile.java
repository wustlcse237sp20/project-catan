package catan;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Tile {
	private double centerX;
	private double centerY;
	private int value;
	private CardType cardType;
	private Color color;
	private double width;
	private boolean hasRobber;
	private  ArrayList<Tile> adjacent;
	
	
	private Structure[] tileRoads; //represents the EDGES of each tile where roads are
	private Structure[] tileSettlements; //represents CORNERS of each tile where settlements are
	
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
	public Tile(double x, double y, int tileValue, Color tileColor, double tileWidth, CardType type) {
		centerX = x;
		centerY = y;
		value = tileValue;
		color = tileColor;
		width = tileWidth;
		cardType = type;
		
		tileRoads = new Structure[6]; 
		tileSettlements = new Structure[6];
		
		adjacent = new ArrayList<Tile>();
		
		if (tileValue==0) {
			hasRobber = true;
		} else {
			hasRobber = false;
		}
	}
	
	public void setAdjacent( ArrayList<Tile> set ) {
		adjacent = set;
	}
	
	public boolean isAdjacent( Tile otherTile ) {
		return this.adjacent.contains(otherTile);
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

	
	
	public int getValue() {
		return value;
	}
	
	 
	
	public Structure[] getRoads() {
		return tileRoads;
	}
	
	
	public Structure[] getSettlements() {
		return tileSettlements;
	}
	
	/**
	 * Updates Tile's tileRoads[] array with a new road from a Player.
	 * @return 0 if index is taken, 1 if index is available and road inserted
	*/
	public int buildRoad( int index, Player builder) {
		if(index < 0 || index > 5) { //index out of bounds
			return 0;
		}
		if(this.getRoads()[index] != null) { //if someone has built there
			return 0;
		}
		else {
			this.getRoads()[index] = new RoadStructure(builder);
			return 1;
		}
	}
	
	/**
	 * Updates Tile's tileSettlements[] array with a new settlement from a Player.
	 * @return 0 if index is taken, 1 if index is available and settlement built
	*/
	public int buildSettlement( int index, Player builder) {
		if(index < 0 || index > 5) { //index out of bounds
			return 0;
		}
		if(this.tileSettlements[index] != null) { //if someone has built there
			return 0;
		}
		else {
			this.tileSettlements[index] = new SettlementStructure(builder);
			return 1;
		}
	}
	
	public int buildCity( int index, Player builder) {
		if(index < 0 || index > 5) { //index out of bounds
			return 0;
		}
		this.tileSettlements[index] = new CityStructure(builder);
		return 1;
	}
	
	public void payout() {
		for (Structure structure:tileSettlements) {
			if (structure != null) {
				structure.payout(cardType);
			}
		}
	}
	
  public static void main(String[] args) {
		// TODO Auto-generated method stub 
      System.out.println("SDFSDF");
	}
}

