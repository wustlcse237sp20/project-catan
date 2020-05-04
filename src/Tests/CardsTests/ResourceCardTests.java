package Tests.CardsTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import catan.CardType;
import catan.ResourceCard;

class ResourceCardTests {
	
	private ResourceCard testResourceCard;
	
	@BeforeEach
	void setupTestingObject() {
		testResourceCard = new ResourceCard(CardType.WHEAT);
	}
	
	@Test
	void testPlayerCantAfford() {
		Map<CardType,Integer> hand = new HashMap<CardType,Integer>();
		hand.put(CardType.WHEAT, 3);
		hand.put(CardType.ORE, 3);
		hand.put(CardType.SHEEP, 3);
		
		assertFalse(testResourceCard.canPlayerAfford(hand));
	}
	
	@Test
	void testPlayerCanAfford() {
		Map<CardType,Integer> hand = new HashMap<CardType,Integer>();
		hand.put(CardType.WHEAT, 4);
		hand.put(CardType.ORE, 4);
		hand.put(CardType.SHEEP, 3);
		
		assertTrue(testResourceCard.canPlayerAfford(hand));
	}
}
