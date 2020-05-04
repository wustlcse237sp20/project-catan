package catan;

import java.util.HashMap;
import java.util.Map;

public class CityStructure extends Structure {
	static StructureType type = StructureType.CITY;
	static Map<CardType, Integer> cost;
	static {
		cost = new HashMap<CardType, Integer>();
		cost.put(CardType.WHEAT, 2);
		cost.put(CardType.ORE, 3);
	}
	static int vpYield = 2;
	
	public CityStructure(Player o) {
		super(o, 2);
	}
	
	public StructureType getType() {
		return type;
	}

}
