package catan;

import java.util.HashMap;
import java.util.Map;

public class SettlementStructure extends Structure {
	
	public SettlementStructure(Player o) {
		super(o, 1);
		cost = new HashMap<CardType, Integer>();
		cost.put(CardType.BRICK, 1);
		cost.put(CardType.LUMBER, 1);
		cost.put(CardType.WHEAT, 1);
		cost.put(CardType.SHEEP, 1);
		type = StructureType.SETTLEMENT;
	}
	
	@Override
	public void build(Player player, GameBoard board) {
		System.out.print("Building Settlement");
	}
	
	public StructureType getType() {
		return type;
	}

}
