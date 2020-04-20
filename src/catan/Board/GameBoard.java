package catan.Board;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

import catan.Board.StdDraw;

import java.util.Collections;

public class GameBoard {

	
	private static int[] originalTileValues = {2,3,3,4,4,5,5,6,6,8,8,9,9,10,10,11,11,12};
	private static Color[] tileColors = {StdDraw.GRAY,StdDraw.GRAY,StdDraw.GRAY,StdDraw.RED,StdDraw.RED,StdDraw.RED,StdDraw.YELLOW,StdDraw.YELLOW,StdDraw.YELLOW,StdDraw.YELLOW,StdDraw.GREEN,StdDraw.GREEN,StdDraw.GREEN,StdDraw.GREEN,StdDraw.WHITE,StdDraw.WHITE,StdDraw.WHITE,StdDraw.WHITE,StdDraw.BLACK};
	private static Tile[] tiles = new Tile[tileColors.length];
	private static Tile[][] gameBoard = new Tile[7][7];
	public static void genBoard(double centerX, double centerY, double hexagonRadius) {
		drawBackground();
		
		//tile coordinates Created
		double[] xCoords= {centerX,centerX+2*hexagonRadius,centerX-2*hexagonRadius,centerX-hexagonRadius,centerX+1*hexagonRadius,centerX-3*hexagonRadius,centerX+3*hexagonRadius,centerX,centerX+2*hexagonRadius,centerX-2*hexagonRadius,centerX+4*hexagonRadius,centerX-4*hexagonRadius,centerX-hexagonRadius,centerX+1*hexagonRadius,centerX-3*hexagonRadius,centerX+3*hexagonRadius,centerX,centerX+2*hexagonRadius,centerX-2*hexagonRadius};
		double[] yCoords= {centerY+6*(hexagonRadius*(Math.sqrt(2)))/2,centerY+6*(hexagonRadius*(Math.sqrt(2)))/2,centerY+6*(hexagonRadius*(Math.sqrt(2)))/2,centerY+3*(hexagonRadius*(Math.sqrt(2)))/2,centerY+3*(hexagonRadius*(Math.sqrt(2)))/2,centerY+3*(hexagonRadius*(Math.sqrt(2)))/2,centerY+3*(hexagonRadius*(Math.sqrt(2)))/2, centerY, centerY, centerY, centerY, centerY,centerY-3*(hexagonRadius*(Math.sqrt(2)))/2,centerY-3*(hexagonRadius*(Math.sqrt(2)))/2,centerY-3*(hexagonRadius*(Math.sqrt(2)))/2,centerY-3*(hexagonRadius*(Math.sqrt(2)))/2,centerY-6*(hexagonRadius*(Math.sqrt(2)))/2,centerY-6*(hexagonRadius*(Math.sqrt(2)))/2,centerY-6*(hexagonRadius*(Math.sqrt(2)))/2,
				};
		
		int[] newTileValues = shuffle(originalTileValues);
		Collections.shuffle(Arrays.asList(tileColors));		
		 
		//places tiles on board with the random order and value for above
		//if the desert is placed give it a value of zero and offset further values
		int robberOffset =0;
		for (int i=0; i<xCoords.length;i++) {
			if(tileColors[i]!=StdDraw.BLACK) {
					tiles[i]= new Tile(xCoords[i],yCoords[i],newTileValues[i-robberOffset],tileColors[i],hexagonRadius);	
			} 
			else {
					tiles[i]= new Tile(xCoords[i],yCoords[i],0,tileColors[i],hexagonRadius);
					robberOffset=1;
			}
			//tiles[i].drawTile();
		}
		Arrays.sort(tiles, (a,b) -> a.comparePoint(b.getPoint()));
		System.out.println(tiles.length);
		
		//HEXAGONAL GRID OF TILES
		Tile[][] gameboardInit = {
				{null,null,null,null,null,null,null},
				{null,null,tiles[0],tiles[1],tiles[2],null,null},
				{null,null,tiles[3],tiles[4],tiles[5],tiles[6],null},
				{null,tiles[7],tiles[8],tiles[9],tiles[10],tiles[11],null},
				{null,null,tiles[12],tiles[13],tiles[14],tiles[15],null},
				{null,null,tiles[16],tiles[17],tiles[18],null,null},
				{null,null,null,null,null,null,null},
				
		};
		gameBoard =gameboardInit;
		
		for (int j = 0; j<gameBoard.length; j++){
		     for (int i = 0; i<gameBoard[0].length; i++){
		    	 if(gameBoard[j][i]!=null) {
		    		 gameBoard[j][i].drawTile();
		    		 System.out.println("x:   " + gameBoard[j][i].getPoint().x + "  y:   " + gameBoard[j][i].getPoint().y );	
		    	 }
		     }
		}
	}
	
	public static void drawBackground() {
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledSquare(0, 0, 1);
	}
	
	public static void giveRobber(Tile newRobberTile) {
		for (Tile tile : tiles) 
		{ 
		   if (tile.hasRobber()) {
			   tile.setRobber(false);
		   }
		   if (tile == newRobberTile) {
			   newRobberTile.setRobber(true);
		   }
		}
	}
	
	public static Tile[] getTiles() {
		return tiles;
	}
	
	//https://www.programcreek.com/2012/02/java-method-to-shuffle-an-int-array-with-random-order/
	public static int[] shuffle(int[] array) {
		Random rgen = new Random();  	
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
      System.out.println("SDFSDF");
		double centerX = .5;
		double centerY= .5;
		double hexagonRadius=.07;
		StdDraw.setCanvasSize(700, 700);
		genBoard(centerX, centerY, hexagonRadius);
	}
}
