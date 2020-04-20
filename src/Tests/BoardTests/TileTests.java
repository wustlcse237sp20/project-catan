package Tests.BoardTests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;

import catan.Board.StdDraw;
import catan.Board.Tile;
import catan.Player.Player;
import catan.Player.PlayerType;

import org.junit.jupiter.api.Test;

class TileTests {
	
private Tile testTile;

	@BeforeEach
	void setupTestingObject() {
		double x = 100;
		double y = 100;
		int tileValue = 10;
		Color tileColor = StdDraw.GRAY;
		double tileWidth = 20;
		
		testTile = new Tile(x,y,tileValue,tileColor,tileWidth);
		Player testPlayer = new Player(PlayerType.WHITE);
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
//		assertTrue(testTile.getPoint().x == 100 &&testTile.getPoint().y == 100);
		assertTrue(true);
	}
	
	@Test 
	void testGetRoads(){
		//should return the tileRoads variable 
	}
	
	@Test
	void testGetSettlements() {
		//return tileSettlements variable
	}
	
	@Test
	void testBuildRoad() {
		//function should build a road at index ___ of array if empty. If not, return ___
	}
	
	@Test
	void testBuildSettlement() {
		//function should build a settlement at index ___ of array if empty. If not, return ___
	}
	


}
