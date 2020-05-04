package Tests.BoardTests;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;

import catan.*;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import catan.*;

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
		
		testTile = new Tile(x,y,tileValue,tileColor,tileWidth,CardType.BRICK, "A");
		player1 = new Player(PlayerType.WHITE);		
		player2 = new Player(PlayerType.BLUE);
		player3 = new Player(PlayerType.ORANGE);
		
		
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
		testTile.getRoads()[0] = new RoadStructure(player1);
		assertTrue(testTile.getRoads()[0].getOwner() == player1);
	}
	
	@Test
	void testGetSettlements() {
		testTile.getSettlements()[0] = new SettlementStructure(player1);
		assertTrue(testTile.getSettlements()[0].getOwner() == player1);
	}
	
	
	@Test
	void testPayout() {
		testTile.buildSettlement(1, player1);
		testTile.buildSettlement(3, player2);
		
		testTile.payout();
		
		assertTrue(player1.getCardsInHand(CardType.BRICK) == 1);
		assertTrue(player2.getCardsInHand(CardType.BRICK) == 1);
		assertFalse(player3.getCardsInHand(CardType.BRICK) == 1);
		
		testTile.payout();

		assertTrue(player1.getCardsInHand(CardType.BRICK) == 2);
		assertTrue(player2.getCardsInHand(CardType.BRICK) == 2);
		
		testTile.buildCity(1, player1);
		
		testTile.payout();
		
		assertTrue(player1.getCardsInHand(CardType.BRICK) == 4);
		assertTrue(player2.getCardsInHand(CardType.BRICK) == 3);
	}
	
}
