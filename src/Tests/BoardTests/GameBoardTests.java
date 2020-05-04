package Tests.BoardTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;

import catan.GameBoard;
import catan.StdDraw;
import catan.Tile;

import org.junit.jupiter.api.Test;

class GameBoardTests {
	private Tile[] testTiles;
	
	@BeforeEach
	void setupTestingObject() {
		double x = 2;
		double y = 2;
		double hexagonRadius = 4;
		GameBoard gameBoard = new GameBoard(x,y,hexagonRadius);
		testTiles = gameBoard.getTiles();
	}
	
	@Test
	void testDrawBackground() {
		double x = 2;
		double y = 2;
		double hexagonRadius = 4;
		GameBoard gameBoard = new GameBoard(x,y,hexagonRadius);
		gameBoard.drawBackground();
		assertTrue(true);
	}
	
	
	@Test
	void testGenBoard() {
		double x = 2;
		double y = 2;
		double hexagonRadius = 4;
		GameBoard gameBoard = new GameBoard(x,y,hexagonRadius);
		gameBoard.genBoard();
		assertTrue(true);
	}
	
}
