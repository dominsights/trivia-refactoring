package com.adaptionsoft.games.uglytrivia;

public class PlayerOutsidePenaltyBoxState extends PenaltyBoxState {
	public PlayerOutsidePenaltyBoxState(Game game) {
		super(game);
	}

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
