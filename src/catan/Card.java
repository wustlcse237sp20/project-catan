package catan;

import java.util.Map;

public abstract class Card implements Purchasable {
	CardType type;
	
	public Card(CardType model) {
		type = model;
	}
	
	/**
	 * @return the Card type
	 */
	public CardType getCardType() {
		return type;
	}

	/**
	 * method to determine if a player can afford this purchasable object, implemented by seperate subclasses
	 */
	@Override
	public abstract boolean canPlayerAfford(Map<CardType, Integer> hand);
	/**
	 *
	 */
	@Override
	public abstract Map<CardType, Integer> getCost();

	/**
	 *
	 */
	public String getName() {
		return type.name();
	}
}
