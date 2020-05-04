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

	/**
	 * handles the building of a city, checks if player can afford and if location is valid
	 */
	@Override
	public void build(Player player, GameBoard board) {
		Coordinate buildLocation = this.readBuildInput();
		String tileName = buildLocation.tileName;
		int index = buildLocation.index;
		boolean isValidLocation = board.validCityIndex(tileName, index, player);
		
		if(isValidLocation) {
			if(player.purchase(this)) {
				System.out.println("Building City");
				board.buildCity(player, tileName, index);
			}
		}
	}
}
