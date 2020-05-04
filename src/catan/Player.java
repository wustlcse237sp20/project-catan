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
	
	// for checking a player hand
	public int getCardsInHand(CardType type) {
		int numCards = handCardAmounts.get(type);
		return numCards;
	}
	
	// for adding resource card(s) when you have the card type but no Card instance
	public void addCardsToHand(CardType cardType, int amount) {
		System.out.println("adding " + amount + " cards");
		int newAmount = handCardAmounts.get(cardType) + amount;
		System.out.println("new amount " + newAmount + " cards");
		handCardAmounts.replace(cardType, newAmount);
		
	}
	
	//TODO: function for determining if can remove x amount of cards of type x from Player's hand
	
	// for removing cards given a type
	public void removeCardsFromHand(CardType cardType, int amount) {
		int newAmount = handCardAmounts.get(cardType) - amount;
		if(newAmount < 0) {
			newAmount = 0;
			System.out.println("Subtracted more cards than available.");
		}
		handCardAmounts.replace(cardType, newAmount);
	}
	
	// for adding an instance to the players hand (important for Dev Cards)
	public void addOneCardToHand(Card newCard) {
		this.addCardsToHand(newCard.getCardType(), 1);
		if(newCard instanceof DevelopmentCard) {
			devCards.add((DevelopmentCard)newCard);
		}
	}

	
	// Structure Factory
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
	
	// Card Factory
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
	
	// generic purchase method to handle transactions
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

	public PlayerType getType() {
		return type;
	}

	public void setType(PlayerType type) {
		this.type = type;
	}
	
	public String getName() {
		return this.type.name();
	}
	
	public void printHand() {
		System.out.println("Your Hand:");
		handCardAmounts.forEach((cardType,amount) -> System.out.println(cardType.name() + ": " + amount));
	}
	
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
