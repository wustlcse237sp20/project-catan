package Catan;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.ArrayList;

public class Player {
	Map<CardType, Integer> handCardAmounts;
	ArrayList<DevelopmentCard> devCards;
	PlayerType type;
	int victoryPoints = 0;
	
	public Player(PlayerType t) {
		handCardAmounts = new HashMap<CardType, Integer>();
		handCardAmounts.put(CardType.BRICK, 0);
		handCardAmounts.put(CardType.LUMBER, 0);
		handCardAmounts.put(CardType.WHEAT, 0);
		handCardAmounts.put(CardType.ORE, 0);
		handCardAmounts.put(CardType.SHEEP, 0);
		handCardAmounts.put(CardType.DEVELOPMENT, 0);
		
		type = t;
	}
	
	// for adding resource card(s) when you have the card type but no Card instance
	public void addCardsToHand(CardType cardType, int amount) {
		int newAmount = handCardAmounts.get(cardType) + amount;
		handCardAmounts.replace(cardType, newAmount);
		
	}
	
	// for removing cards given a type
	public void removeCardsFromHand(CardType cardType, int amount) {
		int newAmount = handCardAmounts.get(cardType) - amount;
		if(newAmount < 0) {
			newAmount = 0;
			System.out.println("something went wrong, subtracted too many cards from hand");
		}
		handCardAmounts.replace(cardType, newAmount);
	}
	
	// for adding an instance to the players hand
	public void addOneCardToHand(Card newCard) {
		this.addCardsToHand(newCard.type, 1);
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
	private boolean purchase(Purchasable p) {
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
}
