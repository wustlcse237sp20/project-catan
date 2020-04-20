package Tests.BoardTests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;

import catan.Board.StdDraw;
import catan.Board.Tile;
import catan.Cards.CardType;
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
		
		testTile = new Tile(x,y,tileValue,tileColor,tileWidth,CardType.BRICK);
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
		assertFalse(testTile.buildRoad( 0, player1) == 1);
		assertFalse(testTile.buildRoad( 1, player2) == 1);
		assertFalse(testTile.buildRoad( 2, player3) == 1);
		
		assertTrue(testTile.buildRoad( 3, player3) == 1);
		assertTrue(testTile.buildRoad( 4, player3) == 1);
		assertTrue(testTile.buildRoad( 5, player3) == 1);
		
		assertFalse(testTile.buildRoad( 6, player3) == 1);

	}
	
	@Test
	void testBuildSettlement() {
		assertFalse(testTile.buildSettlement( 0, player1) == 1);
		assertFalse(testTile.buildSettlement( 1, player2) == 1);
		assertFalse(testTile.buildSettlement( 2, player3) == 1);
		
		assertTrue(testTile.buildSettlement( 3, player3) == 1);
		assertTrue(testTile.buildSettlement( 4, player3) == 1);
		assertTrue(testTile.buildSettlement( 5, player3) == 1);
		
		assertFalse(testTile.buildSettlement( 6, player3) == 1);	
	}
	
	@Test
	void testPayout() {
		Player p1 = new Player(PlayerType.BLUE);
		Player p2 = new Player(PlayerType.RED);
		Tile tile  = new Tile(1.0,1.0,2,Color.yellow,20,CardType.WHEAT);
		tile.buildCity(1, p1);
		tile.buildSettlement(3, p2);
		int oldP1Wheat = p1.getCardsInHand(CardType.WHEAT);
		int oldP2Wheat = p2.getCardsInHand(CardType.WHEAT);
		tile.payout();
		System.out.println(oldP2Wheat);
		System.out.println(p2.getCardsInHand(CardType.WHEAT));
		assertTrue(p1.getCardsInHand(CardType.WHEAT)-oldP1Wheat == 2);
		assertTrue(p2.getCardsInHand(CardType.WHEAT)-oldP2Wheat == 1);
	}
	
}
