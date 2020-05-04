package catan;
import java.util.Map;
import java.util.HashMap;

public class RoadStructure extends Structure {
	
	public RoadStructure(Player o) {
		super(o,0);
		cost = new HashMap<CardType, Integer>();
		cost.put(CardType.BRICK, 1);
		cost.put(CardType.LUMBER, 1);
		type = StructureType.ROAD;
	}
	
	@Override
	public void build(Player player, GameBoard board) {
		Coordinate buildLocation = this.readBuildInput();
		String tileName = buildLocation.tileName;
		int index = buildLocation.index;
		boolean isValidLocation = board.validRoadIndex(tileName, index, player);
		
		if(isValidLocation) {
			if(player.purchase(this)) {
				System.out.println("Building Road");
				board.buildRoad(this, tileName, index, player);
			}
		}
	}
	
	public StructureType getType() {
		return type;
	}
}
