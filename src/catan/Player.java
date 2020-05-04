package catan;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Player {
	Map<CardType, Integer> handCardAmounts;
	ArrayList<DevelopmentCard> devCards;
	private PlayerType type;
	int victoryPoints = 0;
	
	ArrayList<Purchasable> purchasableOptions;
	
	public Player(PlayerType t) {
		handCardAmounts = new HashMap<CardType, Integer>();
		handCardAmounts.put(CardType.BRICK, 0);
		handCardAmounts.put(CardType.LUMBER, 0);
		handCardAmounts.put(CardType.WHEAT, 0);
		handCardAmounts.put(CardType.ORE, 0);
		handCardAmounts.put(CardType.SHEEP, 0);
		handCardAmounts.put(CardType.DEVELOPMENT, 0);
		
		setType(t);
		
		purchasableOptions = new ArrayList<Purchasable>();
		purchasableOptions.add(new ResourceCard(CardType.BRICK));
		purchasableOptions.add(new ResourceCard(CardType.LUMBER));
		purchasableOptions.add(new ResourceCard(CardType.WHEAT));
		purchasableOptions.add(new ResourceCard(CardType.ORE));
		purchasableOptions.add(new ResourceCard(CardType.SHEEP));
		purchasableOptions.add(new RoadStructure(this));
		purchasableOptions.add(new SettlementStructure(this));
		purchasableOptions.add(new CityStructure(this));
	}
	
	/**
	 * @param type of card to check amount of
	 * @return the amount of cards of selected type in the players hand
	 */
	public int getCardsInHand(CardType type) {
		int numCards = handCardAmounts.get(type);
		return numCards;
	}
	
	/**
	 * useful for adding a bunch of cards to the player's hand, ie: when a tile pays out
	 * @param cardType the type of card to add
	 * @param amount the amount of card to add
	 */
	public void addCardsToHand(CardType cardType, int amount) {
		int newAmount = handCardAmounts.get(cardType) + amount;
		handCardAmounts.replace(cardType, newAmount);
		
	}
	
	// for removing cards given a type
	/**
	 * @param cardType to remove
	 * @param amount to remove
	 */
	public void removeCardsFromHand(CardType cardType, int amount) {
		int newAmount = handCardAmounts.get(cardType) - amount;
		if(newAmount < 0) {
			newAmount = 0;
			System.out.println("Subtracted more cards than available.");
		}
		handCardAmounts.replace(cardType, newAmount);
	}
	
	/**
	 * useful when adding an instanced card to players hand, such as when buying from purchasable
	 * @param newCard instance of card to add
	 */
	public void addOneCardToHand(Card newCard) {
		this.addCardsToHand(newCard.getCardType(), 1);
		if(newCard instanceof DevelopmentCard) {
			devCards.add((DevelopmentCard)newCard);
		}
	}

	
	// Structure Factory
	/**
	 * @param model to build the structure from, an enum value
	 * @return the built structure
	 */
	public Structure buildStructure(StructureType model) {
		Structure newStructure = null;
		switch(model) {
		case ROAD:
			newStructure = new RoadStructure(this);
			break;
		case SETTLEMENT:
			newStructure = new SettlementStructure(this);
			break;
		case CITY:
			newStructure = new CityStructure(this);
			break;
		default:
			return null;
		}
		if(this.purchase(newStructure)) {
			return newStructure;
		}
		return null;
	}
	
	/**
	 * @param model build a new card of type model and add it directly to hand
	 */
	public void buildCard(CardType model) {
		Card newCard = null;
		switch(model) {
		case DEVELOPMENT:
			newCard = new DevelopmentCard();
			break;
		default:
			newCard = new ResourceCard(model);
		}
		if(this.purchase(newCard)) {
			this.addOneCardToHand(newCard);
		}
	}
	
	/**
	 * generic method to purchase cards and structures, holy polymorphism!
	 * @param p the object that is being purchased
	 * @return if the purchase was successful
	 */
	public boolean purchase(Purchasable p) {
		if(p.canPlayerAfford(handCardAmounts)) {
			Map<CardType, Integer> cost = p.getCost();
			for(Entry<CardType, Integer> costValue: cost.entrySet()){
				CardType key = costValue.getKey();
				handCardAmounts.replace(key, handCardAmounts.get(key) - cost.get(key));
			}
			return true;
		}
		return false;
	}

	/**
	 * @return the player type
	 */
	public PlayerType getType() {
		return type;
	}

	/**
	 * @param type the player type to set
	 */
	private void setType(PlayerType type) {
		this.type = type;
	}
	
	/**
	 * @return players color as a string, or its name
	 */
	public String getName() {
		return this.type.name();
	}
	
	/**
	 * prints type of cards and quantities from players hand
	 */
	public void printHand() {
		System.out.println("Your Hand:");
		handCardAmounts.forEach((cardType,amount) -> System.out.println(cardType.name() + ": " + amount));
	}
	
	/**
	 * @return a list of all of the purchasable items this player can currently afford
	 */
	public ArrayList<Purchasable> getPurchasable() {
		ArrayList<Purchasable> allAffordable = new ArrayList<Purchasable>();
		for(int i = 0; i<purchasableOptions.size(); i++) {
			Purchasable checkingPurchasableItem = purchasableOptions.get(i);
			if(checkingPurchasableItem.canPlayerAfford(this.handCardAmounts)) {
				allAffordable.add(checkingPurchasableItem);
			}
		}
		return allAffordable;
	}
}
