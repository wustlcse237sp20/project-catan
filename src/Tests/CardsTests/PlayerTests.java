package Tests.CardsTests;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;

import catan.Gameboard;
import catan.StdDraw;
import catan.Tile;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertTrue;

import catan.*;

class PlayerTests {
	
private Player player1;

	@BeforeEach
	void setupTestingObject() {
		player1 = new Player(PlayerType.WHITE);			
	}
	
	@Test
	void testGetCardsInHand() {
		assertTrue(player1.getCardsInHand(CardType.BRICK) == 0);
		assertTrue(player1.getCardsInHand(CardType.SHEEP) == 0);
		assertTrue(player1.getCardsInHand(CardType.ORE) == 0);
	}

	@Test
	void testAddCardsToHand() {
		int original = player1.getCardsInHand(CardType.BRICK);
		player1.addCardsToHand(CardType.BRICK, 2);
		int newAmt = player1.getCardsInHand(CardType.BRICK);
		assertTrue(original - newAmt == -2);
		
		int sheepOG = player1.getCardsInHand(CardType.SHEEP);
		player1.addCardsToHand(CardType.SHEEP, 2);
		assertTrue(player1.getCardsInHand(CardType.SHEEP) == 2);
		player1.addCardsToHand(CardType.SHEEP, 3);
		assertTrue(player1.getCardsInHand(CardType.SHEEP) == 5);
		player1.addCardsToHand(CardType.SHEEP, 4);
		assertTrue(player1.getCardsInHand(CardType.SHEEP) == 9);

	}
	
	@Test
	void testRemoveCardsFromHand() {
		player1.addCardsToHand(CardType.SHEEP, 4);
		player1.removeCardsFromHand(CardType.SHEEP, 3);
		assertTrue(player1.getCardsInHand(CardType.SHEEP) == 1);
		player1.removeCardsFromHand(CardType.SHEEP, 2);
		assertTrue(player1.getCardsInHand(CardType.SHEEP) == 0);
		player1.removeCardsFromHand(CardType.SHEEP, 2);
		assertTrue(player1.getCardsInHand(CardType.SHEEP) == 0);
	}


}
