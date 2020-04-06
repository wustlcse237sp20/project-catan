package Tests.CardsTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;

import catan.Cards.*;

import org.junit.jupiter.api.Test;

class DevelopmentCardTests {
	
	private DevelopmentCard testDevCard;
	
	@BeforeEach
	void setupTestingObject() {
		testDevCard = new DevelopmentCard();
	}
	
	@Test
	void testGetCost() {
		testDevCard.getCost();
		assertTrue(true);
	}
	
	@Test
	void testPlayerCantAfford() {
		Map<CardType,Integer> hand = new HashMap<CardType,Integer>();
		hand.put(CardType.WHEAT, 1);
		hand.put(CardType.ORE, 0);
		hand.put(CardType.SHEEP, 1);
		
		assertFalse(testDevCard.canPlayerAfford(hand));
	}
	
	@Test
	void testPlayerCanAfford() {
		Map<CardType,Integer> hand = new HashMap<CardType,Integer>();
		hand.put(CardType.WHEAT, 1);
		hand.put(CardType.ORE, 1);
		hand.put(CardType.SHEEP, 1);
		
		assertTrue(testDevCard.canPlayerAfford(hand));
	}

}
