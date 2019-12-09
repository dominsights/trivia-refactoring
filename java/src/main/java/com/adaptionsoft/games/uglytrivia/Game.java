package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

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
		Player player = players.get(currentPlayer);
		System.out.println(player.getName() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (penaltyBox.contains(currentPlayer)) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				System.out.println(player.getName() + " is getting out of the penalty box");
				player.setPosition(player.getPosition() + roll);
				if (player.getPosition() > 11) {
					player.setPosition(player.getPosition() - 12);
				}

				System.out.println(player.getName() + "'s new location is " + player.getPosition());
				System.out.println("The category is " + deck.currentCategory(player.getPosition()));
				deck.askQuestion(player.getPosition());
			} else {
				System.out.println(player.getName() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}

		} else {
			player.setPosition(player.getPosition() + roll);
			if (player.getPosition() > 11) {
				player.setPosition(player.getPosition() - 12);
			}

			System.out.println(player.getName() + "'s new location is " + player.getPosition());
			System.out.println("The category is " + deck.currentCategory(player.getPosition()));
			deck.askQuestion(player.getPosition());
		}
	}

	public boolean wasCorrectlyAnswered() {
		Player player = players.get(currentPlayer);
		if (penaltyBox.contains(currentPlayer)) {
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				player.setCoins(player.getCoins() + 1);
				System.out.println(players.get(currentPlayer).getName() + " now has " + player.getCoins() + " Gold Coins.");

				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size()) {
					currentPlayer = 0;
				}

				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) {
					currentPlayer = 0;
				}
				return true;
			}
		} else {
			System.out.println("Answer was corrent!!!!");
			player.setCoins(player.getCoins() + 1);
			System.out.println(players.get(currentPlayer).getName() + " now has " + player.getCoins() + " Gold Coins.");

			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) {
				currentPlayer = 0;
			}
			return winner;
		}
	}

	public boolean wrongAnswer() {
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer).getName() + " was sent to the penalty box");
		penaltyBox.insertPlayer(currentPlayer);

		currentPlayer++;
		if (currentPlayer == players.size()) {
			currentPlayer = 0;
		}
		return true;
	}

	private boolean didPlayerWin() {
		Player player = players.get(currentPlayer);
		return !(player.getCoins() == 6);
	}
}
