package Catan;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;
import java.util.Collections;

public class GameBoard {

	
	
	
	public static Tile[] tiles;
	
	/**
     * Draws Game Boatd
     * @param centerX - the center x coordinate of the screen
     * @param centerX - the center y coordinate of the screen
     * @param hexagonRadius - the horizontal radius of the hexagonal tiles
     */
	public static void genMap (double centerX, double centerY, double hexagonRadius) {
		
		
		//draw background
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledSquare(0, 0, 1);
		 
		
		//tile coordinates Created
		double[] xCoord= {centerX,centerX+2*hexagonRadius,centerX-2*hexagonRadius,centerX-hexagonRadius,centerX+1*hexagonRadius,centerX-3*hexagonRadius,centerX+3*hexagonRadius,centerX,centerX+2*hexagonRadius,centerX-2*hexagonRadius,centerX+4*hexagonRadius,centerX-4*hexagonRadius,centerX-hexagonRadius,centerX+1*hexagonRadius,centerX-3*hexagonRadius,centerX+3*hexagonRadius,centerX,centerX+2*hexagonRadius,centerX-2*hexagonRadius};
		double[] yCoord= {centerY+6*(hexagonRadius*(Math.sqrt(2)))/2,centerY+6*(hexagonRadius*(Math.sqrt(2)))/2,centerY+6*(hexagonRadius*(Math.sqrt(2)))/2,centerY+3*(hexagonRadius*(Math.sqrt(2)))/2,centerY+3*(hexagonRadius*(Math.sqrt(2)))/2,centerY+3*(hexagonRadius*(Math.sqrt(2)))/2,centerY+3*(hexagonRadius*(Math.sqrt(2)))/2, centerY, centerY, centerY, centerY, centerY,centerY-3*(hexagonRadius*(Math.sqrt(2)))/2,centerY-3*(hexagonRadius*(Math.sqrt(2)))/2,centerY-3*(hexagonRadius*(Math.sqrt(2)))/2,centerY-3*(hexagonRadius*(Math.sqrt(2)))/2,centerY-6*(hexagonRadius*(Math.sqrt(2)))/2,centerY-6*(hexagonRadius*(Math.sqrt(2)))/2,centerY-6*(hexagonRadius*(Math.sqrt(2)))/2,
				};
		//tile colors chosen the shuffled
		int [] originalTileValues = {2,3,3,4,4,5,5,6,6,8,8,9,9,10,10,11,11,12};
		int[] tileValues =shuffle(originalTileValues);
		Color[] colors = {StdDraw.GRAY,StdDraw.GRAY,StdDraw.GRAY,StdDraw.RED,StdDraw.RED,StdDraw.RED,StdDraw.YELLOW,StdDraw.YELLOW,StdDraw.YELLOW,StdDraw.YELLOW,StdDraw.GREEN,StdDraw.GREEN,StdDraw.GREEN,StdDraw.GREEN,StdDraw.WHITE,StdDraw.WHITE,StdDraw.WHITE,StdDraw.WHITE,StdDraw.BLACK};
		Collections.shuffle(Arrays.asList(colors));
		
		 tiles = new Tile[colors.length];
		//generate tiles
		
		 
		//places tiles on board with the random order and value for above
		//if the desert is placed give it a value of zero and offset further values
		int robberOffset =0;
		for (int i=0; i<xCoord.length;i++) {
			
			if(colors[i]!=StdDraw.BLACK) {
				
					tiles[i]= new Tile(xCoord[i],yCoord[i],tileValues[i-robberOffset],colors[i],hexagonRadius);	
				} else {
					tiles[i]= new Tile(xCoord[i],yCoord[i],0,colors[i],hexagonRadius);}
			if(colors[i]==StdDraw.BLACK) {
				robberOffset=1;
			}
			tiles[i].drawTile();
		}
	}
	/**
     * Moves Robber from one tile to another
     * @param giveRobber - the tile to recieve the robber
     */
	public static void giveRobber (Tile giveRobber) {
		for (Tile tile : tiles) 
		{ 
		   if (tile.hasRobber()) {
			   tile.setRobber(false);
		   }
		   if (tile == giveRobber) {
			   giveRobber.setRobber(true);
		   }
		}
		
		
		
	}
	/**
     * Shuffles an array
     * @param array - array to be shuffled
     */
	//https://www.programcreek.com/2012/02/java-method-to-shuffle-an-int-array-with-random-order/
	public static int[] shuffle(int[] array) {
		Random rgen = new Random();  // Random number generator			
		 
		for (int i=0; i<array.length; i++) {
		    int randomPosition = rgen.nextInt(array.length);
		    int temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
 
		return array;
	}
	
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		double centerX = .5;
		double centerY= .5;
		double hexagonRadius=.07;
		StdDraw.setCanvasSize(700, 700);
		genMap(centerX, centerY, hexagonRadius);

		
	}
}
