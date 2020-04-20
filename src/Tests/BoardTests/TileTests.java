package Tests.BoardTests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;

import catan.Board.StdDraw;
import catan.Board.Tile;
import catan.Player.Player;
import catan.Player.PlayerType;
import catan.Structures.RoadStructure;
import catan.Structures.SettlementStructure;

import org.junit.jupiter.api.Test;

class TileTests {
	
private Tile testTile;
private Player player1;
private Player player2;
private Player player3;

	@BeforeEach
	void setupTestingObject() {
		double x = 100;
		double y = 100;
		int tileValue = 10;
		Color tileColor = StdDraw.GRAY;
		double tileWidth = 20;
		
		testTile = new Tile(x,y,tileValue,tileColor,tileWidth);
		player1 = new Player(PlayerType.WHITE);		
		player2 = new Player(PlayerType.BLUE);
		player3 = new Player(PlayerType.ORANGE);
		
		testTile.getRoads()[0] = new RoadStructure(player1);
		testTile.getRoads()[1] = new RoadStructure(player2);
		testTile.getRoads()[2] = new RoadStructure(player3);
		
		testTile.getSettlements()[0] = new SettlementStructure(player1);
		testTile.getSettlements()[1] = new SettlementStructure(player2);
		testTile.getSettlements()[2] = new SettlementStructure(player3);
	}

	@Test
	void testDrawTile() {
		testTile.drawTile();
		assertTrue(true);
	}

	@Test
	void testHasRobber() {
		assertTrue(testTile.hasRobber()==false);
	}

	@Test
	void testSetRobber() {
		testTile.setRobber(true);
		assertTrue(testTile.hasRobber()==true);
	}
	
	@Test
	void testGetPoint() {
		assertTrue(testTile.getPoint().x == 100 &&testTile.getPoint().y == 100);
	}
	
	@Test 
	void testGetRoads(){
		assertTrue(testTile.getRoads()[0].getOwner() == player1);
		assertTrue(testTile.getRoads()[1].getOwner() == player2);
		assertTrue(testTile.getRoads()[2].getOwner() == player3);
	}
	
	@Test
	void testGetSettlements() {
		assertTrue(testTile.getSettlements()[0].getOwner() == player1);
		assertTrue(testTile.getSettlements()[1].getOwner() == player2);
		assertTrue(testTile.getSettlements()[2].getOwner() == player3);
	}
	
	@Test
	void testBuildRoad() {
		assertFalse(testTile.buildRoad(testTile, 0, player1) == 1);
		assertFalse(testTile.buildRoad(testTile, 1, player2) == 1);
		assertFalse(testTile.buildRoad(testTile, 2, player3) == 1);
		
		assertTrue(testTile.buildRoad(testTile, 3, player3) == 1);
		assertTrue(testTile.buildRoad(testTile, 4, player3) == 1);
		assertTrue(testTile.buildRoad(testTile, 5, player3) == 1);
		
		assertFalse(testTile.buildRoad(testTile, 6, player3) == 1);

	}
	
	@Test
	void testBuildSettlement() {
		assertFalse(testTile.buildSettlement(testTile, 0, player1) == 1);
		assertFalse(testTile.buildSettlement(testTile, 1, player2) == 1);
		assertFalse(testTile.buildSettlement(testTile, 2, player3) == 1);
		
		assertTrue(testTile.buildSettlement(testTile, 3, player3) == 1);
		assertTrue(testTile.buildSettlement(testTile, 4, player3) == 1);
		assertTrue(testTile.buildSettlement(testTile, 5, player3) == 1);
		
		assertFalse(testTile.buildSettlement(testTile, 6, player3) == 1);	
	}
	
}
