package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Game {
	ArrayList<Player> players = new ArrayList<Player>();
	private Deck deck;
	private PenaltyBox penaltyBox;
	Player currentPlayer;
	private PenaltyBoxState penaltyBoxState;

	public Game() {
		penaltyBox = new PenaltyBox();
		deck = new Deck();
		penaltyBoxState = new PlayerOutsidePenaltyBoxState(this);
	}

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean addPlayer(String playerName) {
		Player player = new Player(playerName, 0);
		players.add(player);
		player.setCoins(0);
		
		if(currentPlayer == null) {
			currentPlayer = player;
		}

		System.out.println(playerName + " was added");
		System.out.println("They are player number " + players.size());
		return true;
	}

	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		System.out.println(currentPlayer.getName() + " is the current player");
		System.out.println("They have rolled a " + roll);

		penaltyBoxState.roll(currentPlayer, roll);
	}

	public void correctAnswer() {
		penaltyBoxState.wasCorrectlyAnswered(currentPlayer, penaltyBox);
	}
	
	public void wrongAnswer() {
		System.out.println("Question was incorrectly answered");
		System.out.println(currentPlayer.getName() + " was sent to the penalty box");
		penaltyBox.insertPlayer(currentPlayer);
	}
	
	void updatePlayerPositionAndAskQuestion(int roll, Player player) {
		updatePlayerPosition(roll, player);
		deck.askQuestion(player.getPosition());
	}
	
	void increasePlayerCoins(Player player) {
		System.out.println("Answer was correct!!!!");
		player.setCoins(player.getCoins() + 1);
		System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getCoins() + " Gold Coins.");
	}

	public boolean isNotAWinner() {
		return !(currentPlayer.getCoins() == 6);
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

	public void changeToNextPlayer() {
		int currentPosition = players.indexOf(currentPlayer);
		int nextPosition = currentPosition + 1;
		if (nextPosition > players.size() - 1) {
			nextPosition = 0;
		}
		currentPlayer = players.get(nextPosition);
		
		if (penaltyBox.contains(currentPlayer)) {
			penaltyBoxState = new PlayerInsidePenaltyBoxState(this);
		} else {
			penaltyBoxState = new PlayerOutsidePenaltyBoxState(this);
		}
	}
}
