package Catan;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

public class Player {
	Map<CardType, Integer> hand;
	PlayerType type;
	int victoryPoints = 0;
	
	public Player(PlayerType t) {
		hand = new HashMap<CardType, Integer>();
		hand.put(CardType.BRICK, 0);
		hand.put(CardType.LUMBER, 0);
		hand.put(CardType.WHEAT, 0);
		hand.put(CardType.ORE, 0);
		hand.put(CardType.SHEEP, 0);
		hand.put(CardType.DEVELOPMENT, 0);
		
		type = t;
	}
	
	public Structure buildStructure(StructureType model) {
		Structure newStructure = null;
		switch(model) {
		case ROAD:
			newStructure = new RoadStructure(this);
			break;
		case SETTLEMENT:
			newStructure = new SettlementStructure(this);
			break;
		case CITY:
			newStructure = new CityStructure(this);
			break;
		default:
			return null;
		}
		if(this.purchase(newStructure)) {
			return newStructure;
		}
		return null;
	}
	
	private boolean purchase(Purchasable p) {
		if(p.canPlayerAfford(hand)) {
			Map<CardType, Integer> cost = p.getCost();
			for(Entry<CardType, Integer> costValue: cost.entrySet()){
				CardType key = costValue.getKey();
				hand.replace(key, hand.get(key) - cost.get(key));
			}
			return true;
		}
		return false;
	}
}
