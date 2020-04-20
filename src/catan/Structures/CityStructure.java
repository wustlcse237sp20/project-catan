package catan.Structures;

import java.util.HashMap;
import java.util.Map;

import catan.Cards.CardType;
import catan.Player.Player;

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

}
