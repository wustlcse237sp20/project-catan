package catan;

import java.util.Map;

public abstract class Card implements Purchasable {
	CardType type;
	
	public Card(CardType model) {
		type = model;
	}
	
	public CardType getCardType() {
		return type;
	}

	@Override
	public abstract boolean canPlayerAfford(Map<CardType, Integer> hand);
	@Override
	public abstract Map<CardType, Integer> getCost();

	public String getName() {
		return type.name();
	}
}
