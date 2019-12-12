package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

// Apply SOLID

// Single Responsability

// 1 - Deciding the category of the questions
// 2 - Picking questions to be asked
// 3 - Deciding which player is the winner
// 4 - Checking if the question was correctly answered or not
// 5 - Controlling the penalty box
// 6 - Storing deck of questions
// 7 - Adding players
// 8 - Control players location
// 9 - Keeps track of players' score




// Open-Closed
// Liskov Substitution
// Interface Segregation
// Dependency Inversion

public class Game {
	ArrayList<Player> players = new ArrayList<Player>();
	boolean isGettingOutOfPenaltyBox;
	private Deck deck;
	private PenaltyBox penaltyBox;
	int currentPlayer = 0;

	public Game() {
		penaltyBox = new PenaltyBox();
		deck = new Deck();
	}

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean addPlayer(String playerName) {
		Player player = new Player(playerName, 0);
		players.add(player);
		player.setCoins(0);

		System.out.println(playerName + " was added");
		System.out.println("They are player number " + players.size());
		return true;
	}

	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		Player player = getCurrentPlayer();
		System.out.println("They have rolled a " + roll);

		if (penaltyBox.contains(currentPlayer)) {
			isGettingOutOfPenaltyBox = roll % 2 != 0;
			if (isGettingOutOfPenaltyBox) {
				System.out.println(player.getName() + " is getting out of the penalty box");
				updatePlayerPositionAndAskQuestion(roll, player);
			} else {
				System.out.println(player.getName() + " is not getting out of the penalty box");
			}
		} else {
			updatePlayerPositionAndAskQuestion(roll, player);
		}
	}

	public boolean wasCorrectlyAnswered() {
		Player player = players.get(currentPlayer);
		if (penaltyBox.contains(currentPlayer)) {
			if (isGettingOutOfPenaltyBox) {
				increasePlayerCoins(player);
				updateCurrentPlayer();
				return didPlayerWin();
			} else {
				updateCurrentPlayer();
				return true;
			}
		} else {
			increasePlayerCoins(player);
			updateCurrentPlayer();
			return didPlayerWin();
		}
	}
	
	public boolean wrongAnswer() {
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer).getName() + " was sent to the penalty box");
		penaltyBox.insertPlayer(currentPlayer);

		updateCurrentPlayer();
		return true;
	}
	
	private Player getCurrentPlayer() {
		Player player = players.get(currentPlayer);
		System.out.println(player.getName() + " is the current player");
		return player;
	}

	private void updatePlayerPositionAndAskQuestion(int roll, Player player) {
		updatePlayerPosition(roll, player);
		deck.askQuestion(player.getPosition());
	}

	private void updatePlayerPosition(int roll, Player player) {
		player.setPosition(player.getPosition() + roll);
		int maxPosition = 11;
		if (player.getPosition() > maxPosition) {
			resetPlayerPosition(player);
		}
		System.out.println(player.getName() + "'s new location is " + player.getPosition());
	}

	private void resetPlayerPosition(Player player) {
		player.setPosition(player.getPosition() - 12);
	}

	private void updateCurrentPlayer() {
		currentPlayer++;
		if (currentPlayer == players.size()) {
			currentPlayer = 0;
		}
	}

	private void increasePlayerCoins(Player player) {
		System.out.println("Answer was correct!!!!");
		player.setCoins(player.getCoins() + 1);
		System.out.println(players.get(currentPlayer).getName() + " now has " + player.getCoins() + " Gold Coins.");
	}

	private boolean didPlayerWin() {
		Player player = players.get(currentPlayer);
		return !(player.getCoins() == 6);
	}
}
