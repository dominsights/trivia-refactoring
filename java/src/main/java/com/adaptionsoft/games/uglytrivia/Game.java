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
	ArrayList<String> players = new ArrayList<String>();
	int[] places = new int[6];
	int[] purses = new int[6];
	
	boolean isGettingOutOfPenaltyBox;

	private Deck deck;
	private PenaltyBox penaltyBox;

	int currentPlayer = 0;

	public Game() {
		penaltyBox = new PenaltyBox();
		deck = new Deck();
		deck.initializeQuestions();
	}

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean addPlayer(String playerName) {
		players.add(playerName);
		places[howManyPlayers()] = 0;
		purses[howManyPlayers()] = 0;

		System.out.println(playerName + " was added");
		System.out.println("They are player number " + players.size());
		return true;
	}

	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (penaltyBox.contains(currentPlayer)) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) {
					places[currentPlayer] = places[currentPlayer] - 12;
				}

				System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
				System.out.println("The category is " + deck.currentCategory(places[currentPlayer]));
				deck.askQuestion(places[currentPlayer]);
			} else {
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}

		} else {
			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11) {
				places[currentPlayer] = places[currentPlayer] - 12;
			}

			System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
			System.out.println("The category is " + deck.currentCategory(places[currentPlayer]));
			deck.askQuestion(places[currentPlayer]);
		}
	}

	public boolean wasCorrectlyAnswered() {
		if (penaltyBox.contains(currentPlayer)) {
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

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
			purses[currentPlayer]++;
			System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

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
		System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
		penaltyBox.insertPlayer(currentPlayer);

		currentPlayer++;
		if (currentPlayer == players.size()) {
			currentPlayer = 0;
		}
		return true;
	}

	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
