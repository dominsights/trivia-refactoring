package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public abstract class Board {

	public Board() {
		super();
	}

	protected abstract void updatePlayerPosition(int roll, Player player);

	protected abstract int getNextPosition(ArrayList<Player> players, Player currentPlayer);

	public abstract QuestionCategory currentCategory(int playerPosition);
	
	public static Board createBoardBasedOnSize(int numberOfPositions, int numberOfCategories) {
		if(numberOfPositions == 12 && numberOfCategories == 4) {
			return new TwelvePositionsBoard();
		}
		
		throw new UnsupportedOperationException("Game doesn't support a board with the size specified.");
	}

}