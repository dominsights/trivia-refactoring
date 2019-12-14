package com.adaptionsoft.games.uglytrivia;

public class PlayerOutsidePenaltyBox extends PenaltyBoxState {
	public PlayerOutsidePenaltyBox(Game game) {
		super(game);
	}

	boolean isGettingOutOfPenaltyBox;
	
	@Override
	public void roll(Player player, int roll) {
		game.updatePlayerPositionAndAskQuestion(roll, player);
	}

	@Override
	public boolean wasCorrectlyAnswered(Player player, PenaltyBox penaltyBox) {
		boolean playerDidWin = false;
		game.increasePlayerCoins(player);
		playerDidWin = game.didPlayerWin();
		return playerDidWin;
	}
}
