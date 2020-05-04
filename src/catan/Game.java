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
		gameBoard = new GameBoard(0, 0, 0);
		players = new ArrayList<Player>();
		inputScanner = new Scanner(System.in);
		this.addPlayers();
		currentlyPlaying = players.remove(0);
	}
	private void addPlayers() {
		for(int i = 0; i < 4; i++) {
			PlayerType playerType = PlayerType.values()[i];
			Player newPlayer = new Player(playerType);
			players.add(newPlayer);
		}
	}
	private void run() {
		while(true) {
			this.turn();
		}
	}
	private void turn() {
		String playerName = currentlyPlaying.getName();
		System.out.println("It is " + playerName + "'s turn");
	}
	private void purchaseStep() {
		boolean purchaseStep = true;
		while(purchaseStep) {
			
			
		}
	}

}
