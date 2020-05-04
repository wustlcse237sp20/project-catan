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
	private void addPlayers() {
		for(int i = 0; i < 4; i++) {
			PlayerType playerType = PlayerType.values()[i];
			Player newPlayer = new Player(playerType);
			players.add(newPlayer);
		}
	}
	private void setupDraft() {
		for(int i = 0; i < players.size(); i++) {
			// adds each player to start and end of snake draft
			Player player = players.get(i);
			snakeDraft.add(player);
			snakeDraft.add(0, player);
		}
	}
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
	private void turn() {
		this.rollStep();
		this.purchaseStep();
		this.endStep();
	}
	private void setupTurn() {
		this.setupGiftStep();
		this.purchaseStep();
		this.setupEndStep();
	}
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
	private void purchaseStep() {
		boolean purchaseStep = true;
		while(purchaseStep) {
			ArrayList<Purchasable> allPurchasable = currentlyPlaying.getPurchasable();
			printPurchasable(allPurchasable);
			purchaseStep = readPurchasableInput(allPurchasable);
		}
	}
	private void endStep() {
		players.add(currentlyPlaying);
		currentlyPlaying = players.remove(0);
	}
	
	private void setupGiftStep() {
		String playerName = currentlyPlaying.getName();
		System.out.println("It is " + playerName + "'s turn to place.");
		
		currentlyPlaying.addCardsToHand(CardType.BRICK, 2);
		currentlyPlaying.addCardsToHand(CardType.LUMBER, 2);
		currentlyPlaying.addCardsToHand(CardType.SHEEP, 1);
		currentlyPlaying.addCardsToHand(CardType.WHEAT, 1);
	}
	private void setupEndStep() {
		currentlyPlaying = snakeDraft.remove(0);
	}
	
	private int dieRoll() {
		return (int) (Math.random()*7.0);
	}
	
	private void printPurchasable(ArrayList<Purchasable> allPurchasable) {
		System.out.println("What wouold you like to purchase?");
		for (int i = 0; i < allPurchasable.size(); i++) {
			System.out.println(i + ". " + allPurchasable.get(i).getName());
		}
		System.out.println(allPurchasable.size() + ". Nothing (end turn)");
	}
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
