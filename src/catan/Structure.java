package catan;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Structure implements Purchasable {
	StructureType type;
	Map<CardType, Integer> cost;
	int vpYield;
	
	Player owner;
	
	public Structure(Player o, int yield) {
		this.owner = o;
		this.vpYield = yield;
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
	
	public void payout(CardType cardType) {
		owner.addCardsToHand(cardType, vpYield);
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public String getName() {
		return type.name();
	}
}
