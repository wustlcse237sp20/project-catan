package catan.Structures;
import java.util.Map;

import catan.Cards.CardType;
import catan.Player.Player;

import java.util.HashMap;

public class RoadStructure extends Structure {
	static StructureType type = StructureType.ROAD;
	static Map<CardType, Integer> cost;
	static {
		cost = new HashMap<CardType, Integer>();
		cost.put(CardType.BRICK, 1);
		cost.put(CardType.LUMBER, 1);
	}
	static int vpYield = 0;

	public RoadStructure(Player o) {
		super(o,0);
	}
}
