package Tests.CardsTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import catan.*;

class DevelopmentCardTests {
	
	private DevelopmentCard testDevCard;
	private Map<CardType, Integer> expectedCost;
	
	@BeforeEach
	void setupTestingObject() {
		testDevCard = new DevelopmentCard();
		expectedCost = new HashMap<CardType, Integer>();
	}
	
	@Test
	void testGetCost() {
		expectedCost.put(CardType.WHEAT, 1);
		expectedCost.put(CardType.ORE, 1);
		expectedCost.put(CardType.SHEEP, 1);

		assertTrue(expectedCost.equals(testDevCard.getCost()));
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
