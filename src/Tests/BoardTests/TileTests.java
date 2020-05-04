package Tests.BoardTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
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
	void testCanPlaceRoad() {
		testTile.getRoads()[3] = new RoadStructure(player1);
		assertFalse(testTile.canPlaceRoad(3, player1));
		assertTrue(testTile.canPlaceRoad(4,player2));
		assertFalse(testTile.canPlaceRoad(6, player1));
		assertFalse(testTile.canPlaceRoad(-1, player1));
	}
	
	@Test
	void testCanPlaceSettlement() {
		testTile.getSettlements()[1] = new SettlementStructure(player1);
		assertFalse(testTile.canPlaceSettlement(1, player1));
		assertTrue(testTile.canPlaceSettlement(2, player2));
		assertFalse(testTile.canPlaceSettlement(6, player1));
		assertFalse(testTile.canPlaceSettlement(-1, player1));
	}
	
	@Test
	void testCanPlaceCity() {
		testTile.getSettlements()[1] = new SettlementStructure(player1);
		testTile.getSettlements()[2] = new SettlementStructure(player2);

		assertTrue(testTile.canPlaceCity(1, player1));
		assertFalse(testTile.canPlaceCity(2, player1));
		assertFalse(testTile.canPlaceCity(3, player1));
		assertFalse(testTile.canPlaceCity(6, player1));
		assertFalse(testTile.canPlaceCity(-1, player1));
	
		testTile.getSettlements()[3] = new CityStructure(player3);
		assertFalse(testTile.canPlaceCity(3,player3));
	}
	
	
	@Test
	void testPayout() {
//		testTile.buildSettlement(1, player1);
//		testTile.buildSettlement(3, player2);
//
//		Tile newTile  = new Tile(1.0,1.0,2,Color.yellow,20,CardType.WHEAT, "A");
//		testTile.buildCity(1, player1);
//		testTile.buildCity(3, player3);
////		tile.buildCity(1, p1);
//		tile.buildSettlement(3, p2);
//		int oldP1Wheat = p1.getCardsInHand(CardType.WHEAT);
//		int oldP2Wheat = p2.getCardsInHand(CardType.WHEAT);
//		tile.payout();
//		System.out.println(oldP2Wheat);
//		System.out.println(p2.getCardsInHand(CardType.WHEAT));
//		assertTrue(p1.getCardsInHand(CardType.WHEAT)-oldP1Wheat == 2);
//		assertTrue(p2.getCardsInHand(CardType.WHEAT)-oldP2Wheat == 1);
	}
	
}
