package catan;
import java.util.Map;

import catan.Cards.CardType;

public interface Purchasable {
	boolean canPlayerAfford(Map<CardType, Integer> hand);
	public Map<CardType, Integer> getCost();
}
