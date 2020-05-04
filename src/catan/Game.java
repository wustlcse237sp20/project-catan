package catan;

import java.util.ArrayList;
import java.util.Scanner;

import catan.GameBoard;
import catan.Player;
import catan.PlayerType;

public class Game {
	GameBoard gameBoard;
	ArrayList<Player> players;
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
		inputScanner = new Scanner(System.in);
		this.addPlayers();
		currentlyPlaying = players.remove(0);
		currentlyPlaying.addCardsToHand(CardType.BRICK, 4);
	}
	private void addPlayers() {
		for(int i = 0; i < 4; i++) {
			PlayerType playerType = PlayerType.values()[i];
			Player newPlayer = new Player(playerType);
			players.add(newPlayer);
		}
	}
	private void run() {
		Player winner = null;
		while(winner == null) {
			this.turn();
			winner = gameBoard.getWinner();
		}
		System.out.println(winner.getName() + " Wins!");
	}
	private void turn() {
		this.rollStep();
		this.purchaseStep();
		this.endStep();
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
