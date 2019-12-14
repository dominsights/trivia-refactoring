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
	public void wasCorrectlyAnswered(Player player, PenaltyBox penaltyBox) {
		game.increasePlayerCoins(player);
	}
}
