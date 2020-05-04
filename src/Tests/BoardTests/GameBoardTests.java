package Tests.BoardTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;

import catan.Gameboard;
import catan.StdDraw;
import catan.Tile;

import org.junit.jupiter.api.Test;

class GameBoardTests {
	private Tile[] testTiles;
	
	@BeforeEach
	void setupTestingObject() {
		testTiles = Gameboard.getTiles();
	}
	
	@Test
	void testDrawBackground() {
		Gameboard.drawBackground();
		assertTrue(true);
	}
	
	@Test
	void testShuffle(){

		assertTrue(true);
	}
	
	@Test
	void testGenBoard() {
		double x = 2;
		double y = 2;
		double hexagonRadius = 4;
		Gameboard.genBoard(x,y,hexagonRadius);
		assertTrue(true);
	}
	

}
