package catan;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Structure implements Purchasable {
	static StructureType type;
	static Map<CardType, Integer> cost;
	static int resourceYield;
	
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
