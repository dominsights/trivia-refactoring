package com.adaptionsoft.games.uglytrivia;

public abstract class PenaltyBoxState {
	protected Game game;
	
	public PenaltyBoxState(Game game) {
		this.game = game;
	}
	
	abstract void roll(Player player, int roll);
	abstract boolean wasCorrectlyAnswered(Player player, PenaltyBox penaltyBox);
}
