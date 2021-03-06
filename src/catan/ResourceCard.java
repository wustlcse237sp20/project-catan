package catan;

import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

public class ResourceCard extends Card {
	
	Map<CardType, Integer> affordableCost;

	public ResourceCard(CardType resourceType) {
		super(resourceType);
		affordableCost = null;
	}

	/**
	 * If quantity of any of the cars is >= 4, then returns true 
	 * because then player can trade with the bank for this resource
	 */
	@Override
	public boolean canPlayerAfford(Map<CardType, Integer> hand) {
		affordableCost = new HashMap<CardType, Integer>();
		for(Entry<CardType, Integer> handValue: hand.entrySet()){
			if (handValue.getValue() >= 4) {
				affordableCost.put(handValue.getKey(),4);
				return true;
			}
		}
		return false;
	}

	@Override
	public Map<CardType, Integer> getCost() {
		return affordableCost;
	}
	
	@Override
	public void build(Player player, GameBoard board) {
		if(player.purchase(this)) {
			player.addOneCardToHand(this);
		}
		System.out.println("Traded for Resource Card");
	}

}
