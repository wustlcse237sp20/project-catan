package Catan;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

public class DevelopmentCard extends Card {
	
	Map<CardType, Integer> cost;

	public DevelopmentCard() {
		super(CardType.DEVELOPMENT);
		cost = new HashMap<CardType, Integer>();
		cost.put(CardType.WHEAT, 1);
		cost.put(CardType.ORE, 1);
		cost.put(CardType.SHEEP, 1);
	}

	@Override
	public boolean canPlayerAfford(Map<CardType, Integer> hand) {
		for(Entry<CardType, Integer> costValue: cost.entrySet()){
			CardType checkingCardType = costValue.getKey();
			if(!hand.containsKey(checkingCardType)) {
				return false;
			}
			if(hand.get(checkingCardType) < cost.get(checkingCardType)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Map<CardType, Integer> getCost() {
		return cost;
	}

}
