package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Board {
	private final int BOARD_SIZE = 12;
	
	int getNextPosition(ArrayList<Player> players, Player currentPlayer) {
		int currentPosition = players.indexOf(currentPlayer);
		int nextPosition = currentPosition + 1;
		if (nextPosition > players.size() - 1) {
			nextPosition = 0;
		}
		return nextPosition;
	}
	
	void updatePlayerPosition(int roll, Player player) {
		player.setPosition(player.getPosition() + roll);
		if (player.getPosition() + 1 > BOARD_SIZE) {
			resetPlayerPosition(player);
		}
		System.out.println(player.getName() + "'s new location is " + player.getPosition());
	}

	private void resetPlayerPosition(Player player) {
		player.setPosition(player.getPosition() - 12);
	}
}
