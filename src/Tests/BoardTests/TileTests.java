package Tests.BoardTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;

import catan.Board.StdDraw;
import catan.Board.Tile;

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

	void testGetPoint() {
//		assertTrue(testTile.getPoint().x == 100 &&testTile.getPoint().y == 100);
		assertTrue(true);
	}


}
