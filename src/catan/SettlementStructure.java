package catan;

import java.util.HashMap;
import java.util.Map;

public class SettlementStructure extends Structure {
	StructureType type = StructureType.SETTLEMENT;
	static Map<CardType, Integer> cost;
	static {
		cost = new HashMap<CardType, Integer>();
		cost.put(CardType.BRICK, 1);
		cost.put(CardType.LUMBER, 1);
		cost.put(CardType.WHEAT, 1);
		cost.put(CardType.SHEEP, 1);
	}
	int vpYield = 1;
	
	public SettlementStructure(Player o) {
		super(o, 1);
	}
	
	public StructureType getType() {
		return type;
	}

}
