package catan;

import java.util.HashMap;
import java.util.Map;

public class CityStructure extends Structure {
	
	public CityStructure(Player o) {
		super(o, 2);
		cost = new HashMap<CardType, Integer>();
		cost.put(CardType.WHEAT, 2);
		cost.put(CardType.ORE, 3);
		type = StructureType.CITY;
	}
	
	public StructureType getType() {
		return type;
	}

	@Override
	public void build(Player player, GameBoard board) {
		if(player.purchase(this)) {
			
		}
		System.out.print("Building City");
	}
}
