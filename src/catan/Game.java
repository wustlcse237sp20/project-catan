package catan;

import java.util.ArrayList;
import java.util.Scanner;

import catan.GameBoard;
import catan.Player;
import catan.PlayerType;

public class Game {
	GameBoard gameBoard;
	ArrayList<Player> players;
	ArrayList<Player> snakeDraft;
	Scanner inputScanner;
	Player currentlyPlaying;

	public Game() {
		double centerX = .5;
		double centerY= .5;
		double hexagonRadius=.07;
		StdDraw.setCanvasSize(700, 700);
		gameBoard = new GameBoard(centerX, centerY, hexagonRadius);
		gameBoard.genBoard();
		players = new ArrayList<Player>();
		snakeDraft = new ArrayList<Player>();
		inputScanner = new Scanner(System.in);
		this.addPlayers();
		this.setupDraft();
		currentlyPlaying = snakeDraft.remove(0);
	}
	/**
	 * instances the players and adds them to player list
	 */
	private void addPlayers() {
		for(int i = 0; i < PlayerType.values().length; i++) {
			PlayerType playerType = PlayerType.values()[i];
			Player newPlayer = new Player(playerType);
			players.add(newPlayer);
		}
	}
	/**
	 * builds snack draft from the inside out ie... 3,2,1,1,2,3
	 */
	private void setupDraft() {
		for(int i = 0; i < players.size(); i++) {
			// adds each player to start and end of snake draft
			Player player = players.get(i);
			snakeDraft.add(player);
			snakeDraft.add(0, player);
		}
	}
	/**
	 * main runner method, runs setup phase and then main game turns until the game ends
	 */
	private void run() {
		// Run snake draft setup
		while(snakeDraft.size() > 0) {
			this.setupTurn();
		}
		gameBoard.endSetupPhase();
		
		// Run normal turns until someone wins
		Player winner = null;
		currentlyPlaying = players.remove(0);
		while(winner == null) {
			this.turn();
		}
	}
	/**
	 * Runs a turn from the normal phase of gameplay
	 */
	private void turn() {
		this.rollStep();
		this.purchaseStep();
		this.endStep();
	}
	/**
	 * Runs a turn from the setup phase of gameplay
	 */
	private void setupTurn() {
		this.setupGiftStep();
		this.purchaseStep();
		this.setupEndStep();
	}
	/**
	 * rolls the dice and pays out from the gameboard depending on the value
	 */
	private void rollStep() {
		String playerName = currentlyPlaying.getName();
		System.out.println("It is " + playerName + "'s turn");
		
		int die1 = dieRoll();
		int die2 = dieRoll();
		int dieTotal = die1 + die2;
		
		System.out.println("Dice rolled: " + die1 + " and " + die2);
		gameBoard.payout(dieTotal);
		
		currentlyPlaying.printHand();
	}
	/**
	 * allows the current player to purchase items they can afford
	 */
	private void purchaseStep() {
		boolean purchaseStep = true;
		while(purchaseStep) {
			ArrayList<Purchasable> allPurchasable = currentlyPlaying.getPurchasable();
			printPurchasable(allPurchasable);
			purchaseStep = readPurchasableInput(allPurchasable);
		}
	}
	/**
	 * moves to next player
	 */
	private void endStep() {
		players.add(currentlyPlaying);
		currentlyPlaying = players.remove(0);
	}
	
	/**
	 * gifts player with materials for their setup settlement/road
	 */
	private void setupGiftStep() {
		String playerName = currentlyPlaying.getName();
		System.out.println("It is " + playerName + "'s turn to place.");
		
		currentlyPlaying.addCardsToHand(CardType.BRICK, 2);
		currentlyPlaying.addCardsToHand(CardType.LUMBER, 2);
		currentlyPlaying.addCardsToHand(CardType.SHEEP, 1);
		currentlyPlaying.addCardsToHand(CardType.WHEAT, 1);
	}
	/**
	 * moves to next player, from snake draft
	 */
	private void setupEndStep() {
		currentlyPlaying = snakeDraft.remove(0);
	}
	
	/**
	 * @return random die roll between 1-6, inclusive 
	 */
	private int dieRoll() {
		return (int) (Math.random()*7.0);
	}
	
	/**
	 * @param allPurchasable List of all items the player should be able to buy
	 */
	private void printPurchasable(ArrayList<Purchasable> allPurchasable) {
		System.out.println("What wouold you like to purchase?");
		for (int i = 0; i < allPurchasable.size(); i++) {
			System.out.println(i + ". " + allPurchasable.get(i).getName());
		}
		System.out.println(allPurchasable.size() + ". Nothing (end turn)");
	}
	/**
	 * @param allPurchasable List of all items the player should be able to buy
	 * @return returns if the purchasable input lead to a purchase 
	 */
	private boolean readPurchasableInput(ArrayList<Purchasable> allPurchasable) {
		int selection = inputScanner.nextInt();
		if (selection < allPurchasable.size() && selection > -1) {
			allPurchasable.get(selection).build(currentlyPlaying, gameBoard);
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}
}
