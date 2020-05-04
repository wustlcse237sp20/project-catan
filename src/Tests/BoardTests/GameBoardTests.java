package Tests.BoardTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

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
		Gameboard gameboard = new Gameboard(x,y,hexagonRadius);
		gameboard.genBoard();
		assertTrue(true);
	}
	
	@Test
	void testAdjacent() {
		double x = 2;
		double y = 2;
		double hexagonRadius = 4;
		Gameboard gameBoard = new Gameboard(x,y,hexagonRadius);
		gameBoard.genBoard();
	
		int n = 3;
		Tile tile = gameBoard.getTile(n,n);
		assertTrue(gameBoard.getTile(n+1,n).isAdjacent(tile));
		assertTrue(gameBoard.getTile(n-1,n).isAdjacent(tile));
		assertTrue(gameBoard.getTile(n+1,n+1).isAdjacent(tile));
		assertTrue(gameBoard.getTile(n+1,n-1).isAdjacent(tile));
		assertTrue(gameBoard.getTile(n,n+1).isAdjacent(tile));
		assertTrue(gameBoard.getTile(n,n-1).isAdjacent(tile));
	}

}
