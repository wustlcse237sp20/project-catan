package Catan;

import java.util.HashMap;
import java.util.Map;

public class SettlementStructure extends Structure {
	static StructureType type = StructureType.CITY;
	static Map<CardType, Integer> cost;
	static {
		cost = new HashMap<CardType, Integer>();
		cost.put(CardType.WHEAT, 2);
		cost.put(CardType.ORE, 3);
	}
	static int resourceYield = 2;
	
	public SettlementStructure(Player o) {
		super(o);
	}

}
