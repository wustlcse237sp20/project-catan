package catan;
import java.util.Map;

public interface Purchasable {
	/**
	 * @param hand being checked
	 * @return if the hand contains resources to buy this purchasable object
	 */
	boolean canPlayerAfford(Map<CardType, Integer> hand);
	public Map<CardType, Integer> getCost();
	public String getName();
	public void build(Player player, GameBoard board);
}
