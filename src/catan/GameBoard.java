package catan;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import catan.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameBoard {
	
	
	
	
	private int[] originalTileValues = {2,3,3,4,4,5,5,6,6,8,8,9,9,10,10,11,11,12};
	private Color[] tileColors = {StdDraw.GRAY,StdDraw.GRAY,StdDraw.GRAY,StdDraw.RED,StdDraw.RED,StdDraw.RED,StdDraw.YELLOW,StdDraw.YELLOW,StdDraw.YELLOW,StdDraw.YELLOW,StdDraw.GREEN,StdDraw.GREEN,StdDraw.GREEN,StdDraw.GREEN,StdDraw.WHITE,StdDraw.WHITE,StdDraw.WHITE,StdDraw.WHITE,StdDraw.BLACK};
	private Tile[] tiles = new Tile[tileColors.length];
	private Tile[][] gameBoard = new Tile[7][7];
	public  Map <Integer,ArrayList<Tile>> tileValueMap = new HashMap<Integer,ArrayList<Tile>>();
	private double centerX, centerY, hexagonRadius;
	public  Map <String,int[]> tileNameMap = new HashMap<String,int[]>();
	private boolean inSetupPhase;
	
	public GameBoard(double gameCenterX,double gameCenterY,double gameHexagonRadius) {
		centerX = gameCenterX;
		centerY= gameCenterY;
		hexagonRadius=gameHexagonRadius;
		inSetupPhase = true;
	}
	
	public void genBoard() {
		drawBackground();
		double[] xCoords= {centerX,centerX+2*hexagonRadius,centerX-2*hexagonRadius,centerX-hexagonRadius,centerX+1*hexagonRadius,centerX-3*hexagonRadius,centerX+3*hexagonRadius,centerX,centerX+2*hexagonRadius,centerX-2*hexagonRadius,centerX+4*hexagonRadius,centerX-4*hexagonRadius,centerX-hexagonRadius,centerX+1*hexagonRadius,centerX-3*hexagonRadius,centerX+3*hexagonRadius,centerX,centerX+2*hexagonRadius,centerX-2*hexagonRadius};
		double[] yCoords= {centerY+6*(hexagonRadius*(Math.sqrt(2)))/2,centerY+6*(hexagonRadius*(Math.sqrt(2)))/2,centerY+6*(hexagonRadius*(Math.sqrt(2)))/2,centerY+3*(hexagonRadius*(Math.sqrt(2)))/2,centerY+3*(hexagonRadius*(Math.sqrt(2)))/2,centerY+3*(hexagonRadius*(Math.sqrt(2)))/2,centerY+3*(hexagonRadius*(Math.sqrt(2)))/2, centerY, centerY, centerY, centerY, centerY,centerY-3*(hexagonRadius*(Math.sqrt(2)))/2,centerY-3*(hexagonRadius*(Math.sqrt(2)))/2,centerY-3*(hexagonRadius*(Math.sqrt(2)))/2,centerY-3*(hexagonRadius*(Math.sqrt(2)))/2,centerY-6*(hexagonRadius*(Math.sqrt(2)))/2,centerY-6*(hexagonRadius*(Math.sqrt(2)))/2,centerY-6*(hexagonRadius*(Math.sqrt(2)))/2,};
		String[] tileNames = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","Q","R","S","T"};
		int[] newTileValues = shuffle(originalTileValues);
		Collections.shuffle(Arrays.asList(tileColors));		
		 
		//places tiles on board with the random order and value for above
		//if the desert is placed give it a value of zero and offset further values
		int robberOffset =0;
		for (int i=0; i<xCoords.length;i++) {
			if(tileColors[i]!=StdDraw.BLACK) {
					tiles[i]= new Tile(xCoords[i],yCoords[i],newTileValues[i-robberOffset],tileColors[i],hexagonRadius,CardType.ORE,tileNames[i]);	
			} 
			else {
					tiles[i]= new Tile(xCoords[i],yCoords[i],0,tileColors[i],hexagonRadius, CardType.WHEAT,tileNames[i]);
					robberOffset=1;
			}
		}
		Arrays.sort(tiles, (a,b) -> a.comparePoint(b.getPoint()));
		System.out.println(tiles.length);
		Tile[][] gameboardInit = {
				{null,null,null,null,null,null,null},
				{null,null,tiles[0],tiles[1],tiles[2],null,null},
				{null,null,tiles[3],tiles[4],tiles[5],tiles[6],null},
				{null,tiles[7],tiles[8],tiles[9],tiles[10],tiles[11],null},
				{null,null,tiles[12],tiles[13],tiles[14],tiles[15],null},
				{null,null,tiles[16],tiles[17],tiles[18],null,null},
				{null,null,null,null,null,null,null},	
		};
		gameBoard = gameboardInit;
		
		for (int j = 0; j<gameBoard.length; j++){
		     for (int i = 0; i<gameBoard[0].length; i++){
		    	 if(gameBoard[j][i]!=null) {
		    		 gameBoard[j][i].drawTile();
		    		 int[] pos= {j,i};
		    		 tileNameMap.put(gameBoard[j][i].getName(), pos);
		    		 ArrayList<Tile> tileArray = tileValueMap.get(gameBoard[j][i].getValue());
		    		
		    		 tileValueMap.put(gameBoard[j][i].getValue(), tileArray);
		    	 }   	 
		     }
		}
	}
	
	/**
	 *Checks if player has connecting roads to the index they're trying to build settlement at
	 */
	public boolean validSettlementIndex(String tileName, int index, Player builder) {
		if(inSetupPhase) {return true;}
		int[] coord = tileNameMap.get(tileName);
		Tile currentTile = gameBoard[coord[0]][coord[1]]; //the target the player is indicating
		Tile tileToCheck = null; //0 -> tLeft, 1-> tRight, 2-> mRight, 3-> bRight, 4-> bLeft, 5->mLeft
		int xOffset = 0, yOffset = 0, roadIndex = 0;
		boolean toReturn = false;
		switch(index) {
			case 0:	//check topLeft road index 3
				xOffset = 0;
				yOffset = -1;
				roadIndex = 3;
				break;
			case 1: //check topRight road index 4
				xOffset = 1;
				yOffset = -1;
				roadIndex = 4;
				break;
			case 2: //check middleRight road index 5
				xOffset = 1;
				yOffset = 0;
				roadIndex = 5;
				break;
			case 3: //check bottomRight road index 0
				xOffset = 1;
				yOffset = -1;
				roadIndex = 0;
				break; 
			case 4: //check bottomLeft road index 1
				xOffset = 0;
				yOffset = 1;
				roadIndex = 1;
				break;
			case 5: //check middleLeft road index 2
				xOffset = -1;
				yOffset = 0;
				roadIndex = 2;
				break;
		}
		int[] newCoords = {coord[0] + xOffset, coord[1] + yOffset};
		int tileRoad1 = Math.abs(index -5); //"left" of the settlement index
		int tileRoad2 = Math.abs(index -4); //"right" of the settlement index
		
		//check if settlements at these indices are null for 2 apart requirement
		int currentTile1 = index - 1; //settlement index to the left of index
		if(index == 0) {
			currentTile1 = 5;
		}
		int currentTile2 = index + 1; //settlement index to the right of index
		int tileToCheck1 = index + 1; //the index of corner in tileToCheck that is 1 away from index
		if(index == 5) {
			currentTile2 = 1;
			tileToCheck1 = 1;
		}

		if(gameBoard[newCoords[0]][newCoords[1]] != null) { //if adjacent is not a water tile
			tileToCheck = gameBoard[newCoords[0]][newCoords[1]]; 
			if(currentTile.getRoads()[tileRoad1].getOwner() == builder
					|| currentTile.getRoads()[tileRoad2].getOwner() == builder
					|| tileToCheck.getRoads()[roadIndex].getOwner() == builder) {
				toReturn = true;
				//CHECK IF 2 apart from other settlements
				if(currentTile.getSettlements()[currentTile1] != null 
						|| currentTile.getSettlements()[currentTile2] != null
						|| tileToCheck.getSettlements()[tileToCheck1] != null) {
					toReturn = false;
				}
			}
		}
		else { //if adjacent is water tile
			if(currentTile.getRoads()[tileRoad1].getOwner() == builder
					|| currentTile.getRoads()[tileRoad2].getOwner() == builder) {
				toReturn = true;
				//CHECK IF 2 apart from other settlements
				if(currentTile.getSettlements()[currentTile1] != null 
						|| currentTile.getSettlements()[currentTile2] != null) {
					toReturn = false;
				}
			}
		}
		return toReturn;
	}
	
	/**
	 *Checks if player has connecting roads to the index they're trying to build road at
	 */
	public boolean validRoadIndex(String tileName, int index, Player builder) {
		if(inSetupPhase) {return true;}
		int[] coord = tileNameMap.get(tileName);
		Tile currentTile = gameBoard[coord[0]][coord[1]]; //the target the player is indicating
		Tile tileToCheck = null; //0 -> tLeft, 1-> tRight, 2-> mRight, 3-> bRight, 4-> bLeft, 5->mLeft
		int xOffset = 0, yOffset = 0;
		int current1 = 0; //"left" of index on current tile
		int current2 = 0; //"right" of index on current tile
		int check1 = 0; //"left" of index on adjacent tile
		int check2 = 0; //"right" of index on adjacent tile
		boolean toReturn = false;
		switch(index) {
			case 0:	//check topLeft road index 3
				xOffset = 0;
				yOffset = -1;
				current1 = 5;
				current2 = 1;
				check1 = 4;
				check2 = 2;
				break;
			case 1: //check topRight road index 4
				xOffset = 1;
				yOffset = -1;
				current1 = 0;
				current2 = 2;
				check1 = 5;
				check2 = 3;
				break;
			case 2: //check middleRight road index 5
				xOffset = 1;
				yOffset = 0;
				current1 = 1;
				current2 = 3;
				check1 = 0;
				check2 = 4;
				break;
			case 3: //check bottomRight road index 0
				xOffset = 1;
				yOffset = -1;
				current1 = 2;
				current2 = 4;
				check1 = 1;
				check2 = 5;
				break; 
			case 4: //check bottomLeft road index 1
				xOffset = 0;
				yOffset = 1;
				current1 = 3;
				current2 = 5;
				check1 = 2;
				check2 = 0;
				break;
			case 5: //check middleLeft road index 2
				xOffset = -1;
				yOffset = 0;
				current1 = 4;
				current2 = 0;
				check1 = 3;
				check2 = 1;
				break;
		}
		int[] newCoords = {coord[0] + xOffset, coord[1] + yOffset};

		if(gameBoard[newCoords[0]][newCoords[1]] != null) { //if adjacent is not a water tile
			tileToCheck = gameBoard[newCoords[0]][newCoords[1]]; 
			if(currentTile.getRoads()[current1].getOwner() == builder
					|| currentTile.getRoads()[current2].getOwner() == builder
					|| tileToCheck.getRoads()[check1].getOwner() == builder
					|| tileToCheck.getRoads()[check2].getOwner() == builder){
				toReturn = true;
			}
		}
		else { //if adjacent is water tile
			if(currentTile.getRoads()[current1].getOwner() == builder
					|| currentTile.getRoads()[current2].getOwner() == builder){
				toReturn = true;
			}
		}
		return toReturn;
	}
	
	public boolean validCityIndex(String tileName, int index, Player builder) {
		// TODO make work
		return true;
	}
	
	public void buildRoad (RoadStructure r, String tileName, int pos, Player builder) {
		int[] coord = tileNameMap.get(tileName);
		boolean result = gameBoard[coord[0]][coord[1]].canPlaceRoad(pos,builder);
		
		if(result) {
			switch(pos) {
			  case 5:
				gameBoard[coord[0]+1][coord[1]-1].buildRoad(2,builder);
			    
			    break;
			  case 0:
				  gameBoard[coord[0]+1][coord[1]-0].buildRoad(3,builder);
			    break;
			  case 1:
				  gameBoard[coord[0]+1][coord[1]+1].buildRoad(4,builder);
				    break;
			  case 2:
				  gameBoard[coord[0]][coord[1]-1].buildRoad(5,builder);
				    break;
			  case 3:
				  gameBoard[coord[0]-1][coord[1]].buildRoad(0,builder);
				    break;
			  case 4:
				  gameBoard[coord[0]][coord[1]-1].buildRoad(1,builder);
				    break;
			  default:
			    System.out.println("RIP");
			}
		}
	}
	
	public void buildSettlement (Player builder, String tileName, int pos) {
		int[] coord = tileNameMap.get(tileName);
		boolean result = gameBoard[coord[0]][coord[1]].canPlaceSettlement(pos,builder);
		
		if(result) {
			switch(pos) {
			  case 0:
				gameBoard[coord[0]+1][coord[1]-1].buildSettlement(2, builder);
				gameBoard[coord[0]+1][coord[1]+0].buildSettlement(4, builder);
			    break;
			  case 1:
				  gameBoard[coord[0]+1][coord[1]+1].buildSettlement(5, builder);
				gameBoard[coord[0]+1][coord[1]+0].buildSettlement(3, builder);
				    break;
			  case 2:
				  
				gameBoard[coord[0]+1][coord[1]+1].buildSettlement(4, builder);
				gameBoard[coord[0]+0][coord[1]+1].buildSettlement(0, builder);
				    break;
			  case 3:
				gameBoard[coord[0]-1][coord[1]+0].buildSettlement(1, builder);
				gameBoard[coord[0]-0][coord[1]+1].buildSettlement(5, builder);	  
				  
				    break;
			  case 4:
				gameBoard[coord[0]-1][coord[1]+0].buildSettlement(0, builder);
				gameBoard[coord[0]-0][coord[1]-1].buildSettlement(2, builder);	 
								  
				 break;
			  case 5:
				gameBoard[coord[0]-1][coord[1]-1].buildSettlement(3, builder);
				gameBoard[coord[0]-0][coord[1]-1].buildSettlement(1, builder);	 
					
				    break;
			  default:
			    System.out.println("RIP");
			}
		}
	}
	public void buildCity (Player builder, String tileName, int pos) {
		int[] coord = tileNameMap.get(tileName);
		boolean result = gameBoard[coord[0]][coord[1]].canPlaceCity(pos,builder);
		
		if(result) {
			switch(pos) {
			 
			  case 0:
				gameBoard[coord[0]+1][coord[1]-1].buildCity(2, builder);
				gameBoard[coord[0]+1][coord[1]+0].buildCity(4, builder);
			    break;
			  case 1:
				  gameBoard[coord[0]+1][coord[1]+1].buildCity(5, builder);
				gameBoard[coord[0]+1][coord[1]+0].buildCity(3, builder);
				    break;
			  case 2:
				  
				gameBoard[coord[0]+1][coord[1]+1].buildCity(4, builder);
				gameBoard[coord[0]+0][coord[1]+1].buildCity(0, builder);
				    break;
			  case 3:
				gameBoard[coord[0]-1][coord[1]+0].buildCity(1, builder);
				gameBoard[coord[0]-0][coord[1]+1].buildCity(5, builder);	  
				  
				    break;
			  case 4:
				gameBoard[coord[0]-1][coord[1]+0].buildCity(0, builder);
				gameBoard[coord[0]-0][coord[1]-1].buildCity(2, builder);	 
								  
				 break;
			  case 5:
				gameBoard[coord[0]-1][coord[1]-1].buildCity(3, builder);
				gameBoard[coord[0]-0][coord[1]-1].buildCity(1, builder);	 
					
				    break;
			  default:
			    System.out.println("RIP");
			}
		}
		
	}
	public void drawBackground() {
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledSquare(0, 0, 1);
	}
	
	public void giveRobber(Tile newRobberTile) {
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
	
	public Tile[] getTiles() {
		return tiles;
	}
	
	//https://www.programcreek.com/2012/02/java-method-to-shuffle-an-int-array-with-random-order/
	public int[] shuffle(int[] array) {
		Random rgen = new Random();  	
		for (int i=0; i<array.length; i++) {
		    int randomPosition = rgen.nextInt(array.length);
		    int temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
		return array;
	}
	
	public void payout(int roll) {
		ArrayList<Tile> tiles = tileValueMap.get(roll);
		System.out.println(tiles);
		if(tiles == null) {
			return;
		}
		tiles.forEach((t) -> t.payout());
	}
	
	public Tile getTile(int x, int y) {
		return gameBoard[y][x];
	}
	
	public Player getWinner() {
		int[] playerScores = new int[4];
		for(Tile tile: tiles) {
			for(int i = 0; i < 2; i++) {
				Structure structure = tile.getSettlements()[i];
				if(structure != null) {
					int index = structure.owner.getType().getId();
					playerScores[index] += structure.vpYield;
					if(playerScores[index]>=10) { return structure.owner; }
				}
			}
		}
		return null;
	}
	
	public void endSetupPhase() {
		inSetupPhase = false;
	}
	 
	public static void main(String[] args) {
		double centerX = .5;
		double centerY= .5;
		double hexagonRadius=.07;
		StdDraw.setCanvasSize(700, 700);
		GameBoard gameBoard = new GameBoard(centerX, centerY, hexagonRadius);
		gameBoard.genBoard();
	}
}