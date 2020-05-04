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
		Coordinate buildLocation = this.readBuildInput();
		String tileName = buildLocation.tileName;
		int index = buildLocation.index;
		boolean isValidLocation = board.validSettlementIndex(tileName, index, player);
		
		if(isValidLocation) {
			if(player.purchase(this)) {
				System.out.println("Building Settlement");
				board.buildSettlement(player, tileName, index);
			}
		}
	}
	
	public StructureType getType() {
		return type;
	}

}
