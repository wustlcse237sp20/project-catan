package catan.Structures;
import java.util.Map;
import java.util.Map.Entry;

import catan.Purchasable;
import catan.Cards.CardType;
import catan.Player.Player;

public abstract class Structure implements Purchasable {
	static StructureType type;
	static Map<CardType, Integer> cost;
	static int vpYield;
	
	Player owner;
	
	public Structure(Player o) {
		this.owner = o;
	}
	
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
	
	public Map<CardType, Integer> getCost() {
		return cost;
	}
	
}
