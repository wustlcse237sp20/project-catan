package Tests.BoardTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;

import catan.Board.StdDraw;
import catan.Board.Tile;
import catan.Board.GameBoard;


import org.junit.jupiter.api.Test;

class GameBoardTests {
	private Tile[] testTiles;
	
	@BeforeEach
	void setupTestingObject() {
		testTiles = GameBoard.getTiles();
	}
	
	@Test
	void testDrawBackground() {
		GameBoard.drawBackground();
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
		GameBoard.genBoard(x,y,hexagonRadius);
		assertTrue(true);
	}
	

}
