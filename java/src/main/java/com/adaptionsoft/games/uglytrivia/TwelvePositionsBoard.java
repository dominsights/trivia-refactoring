package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class TwelvePositionsBoard extends Board {
	private final int BOARD_SIZE = 12;
	
	@Override
	public QuestionCategory currentCategory(int playerPosition) {
		switch (playerPosition) {
		case 0:
		case 4:
		case 8:
			return QuestionCategory.Pop;
		case 1:
		case 5:
		case 9:
			return QuestionCategory.Science;
		case 2:
		case 6:
		case 10:
			return QuestionCategory.Sports;
		}
		return QuestionCategory.Rock;
	}
	
	@Override
	protected int getNextPosition(ArrayList<Player> players, Player currentPlayer) {
		int currentPosition = players.indexOf(currentPlayer);
		int nextPosition = currentPosition + 1;
		if (nextPosition > players.size() - 1) {
			nextPosition = 0;
		}
		return nextPosition;
	}
	
	@Override
	protected void updatePlayerPosition(int roll, Player player) {
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
