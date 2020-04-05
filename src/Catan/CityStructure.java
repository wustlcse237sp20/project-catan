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
	static int resourceYield = 2;
	
	public CityStructure(Player o) {
		super(o);
		// TODO Auto-generated constructor stub
	}

}
