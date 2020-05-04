package catan;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import catan.StdDraw;
import catan.CardType;
import catan.Player;
import catan.CityStructure;
import catan.RoadStructure;
import catan.SettlementStructure;
import catan.Structure;

public class Tile {
	private double centerX;
	private double centerY;
	private int value;
	private CardType cardType;
	private Color color;
	private double width;
	private boolean hasRobber;
	private  ArrayList<Tile> adjacent;
	private String name;
	
	
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
	
	public Tile(double x, double y, int tileValue, Color tileColor, double tileWidth, CardType type, String tileName) {
		centerX = x;
		centerY = y;
		value = tileValue;
		color = tileColor;
		width = tileWidth;
		cardType = type;
		name = tileName;
		
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
			drawRoads();
			drawStructures();
			
			if( hasRobber) {
				StdDraw.text(centerX, centerY, "R-"+name)	;
			} else {
				
				StdDraw.text(centerX, centerY, value+"-"+name);
			}
	}
	
	public void drawRoads() {
		double hypotenuse = Math.sqrt(2);
		double multiple = width*hypotenuse;
		double[] xCoord = {centerX+width, centerX +width, centerX, centerX-width,centerX-width, centerX };
		double[] yCoord = {centerY+multiple/2, centerY - multiple/2, centerY-2*multiple/2, centerY-multiple/2, centerY+multiple/2, centerY+2*multiple/2};
		for(int i=0;i< tileRoads.length; i++ ) {
			//check if road is there
			//Set pen color using roads players player Type
			StdDraw.setPenColor(StdDraw.CYAN);
			StdDraw.setPenRadius(.02);
			StdDraw.line(xCoord[i], yCoord[i],xCoord[(i+1)%6], yCoord[(i+1)%6]);
		}
		//Set pen color using roads players player Type
	}
	
	public void drawStructures() {
		double hypotenuse = Math.sqrt(2);
		double multiple = width*hypotenuse;
		double[] xCoord = {centerX+width, centerX +width, centerX, centerX-width,centerX-width, centerX };
		double[] yCoord = {centerY+multiple/2, centerY - multiple/2, centerY-2*multiple/2, centerY-multiple/2, centerY+multiple/2, centerY+2*multiple/2};
		for(int i=0;i< tileSettlements.length; i++ ) {
			//Set pen color using roads players player Type
			StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
			StdDraw.filledCircle(xCoord[i], yCoord[i], .025);
			StdDraw.setPenColor(StdDraw.BLACK);
			//check if city or settlement then set string
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
	
	public String getName() {
		return name;
	}
	 
	public Structure[] getRoads() {
		return tileRoads;
	}
	
	
	public Structure[] getSettlements() {
		return tileSettlements;
	}
	
	//@ TODO: add in adjacency rules
	public boolean canPlaceRoad(int index, Player builder) {
		if(index < 0 || index > 5) { //index out of bounds
			return false;
		}
		else if(this.getRoads()[index] != null) { //if someone has built there
			return false;
		}
		else {
			return true;
		}
	}
	
	//@ TODO: add in adjacency rules
	public boolean canPlaceSettlement(int index, Player builder) {
		if(index < 0 || index > 5) { //index out of bounds
			return false;
		}
		if(this.tileSettlements[index] != null) { //if someone has built there
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean canPlaceCity(int index, Player builder) {
		if(index < 0 || index > 5) { //index out of bounds
			return false;
		}
		else if(this.tileSettlements[index] == null) {
			return false;
		}
		else if(this.tileSettlements[index].getOwner() != builder) { //builder does not have settlement 
			return false;
		}
		else if(this.tileSettlements[index].getType() == StructureType.CITY) { //if is already a city
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Updates Tile's tileRoads[] array with a new road from a Player.
	*/
	public void buildRoad( int index, Player builder) {
		this.tileRoads[index] = new RoadStructure(builder);
	}

	public void buildSettlement( int index, Player builder) {
		this.tileSettlements[index] = new SettlementStructure(builder);
	}
	
	public void buildCity( int index, Player builder) {
		this.tileSettlements[index] = new CityStructure(builder);
	}
	
	public void payout() {
		for (Structure structure:tileSettlements) {
			if (structure != null) {
				structure.payout(cardType);
			}
		}
	}
	
	
	
  
}