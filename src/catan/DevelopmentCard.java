package catan;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

public class DevelopmentCard extends Card {
	
	Map<CardType, Integer> cost;

	public DevelopmentCard() {
		super(CardType.DEVELOPMENT);
		this.cost = new HashMap<CardType, Integer>();
		this.cost.put(CardType.WHEAT, 1);
		this.cost.put(CardType.ORE, 1);
		this.cost.put(CardType.SHEEP, 1);
	}

	@Override
	public boolean canPlayerAfford(Map<CardType, Integer> hand) {
		for(Entry<CardType, Integer> costValue: this.cost.entrySet()){
			CardType checkingCardType = costValue.getKey();

			if(hand.get(checkingCardType) < this.cost.get(checkingCardType)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Map<CardType, Integer> getCost() {
		return this.cost;
	}

}
