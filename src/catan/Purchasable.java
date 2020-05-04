package catan;
import java.util.Map;

public interface Purchasable {
	boolean canPlayerAfford(Map<CardType, Integer> hand);
	public Map<CardType, Integer> getCost();
	public String getName();
	public void build(Player player, GameBoard board);
}
