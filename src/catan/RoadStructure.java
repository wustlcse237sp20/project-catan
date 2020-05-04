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
		System.out.print("Building Road");
	}
	
	public StructureType getType() {
		return type;
	}
}
