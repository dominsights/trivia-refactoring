package com.adaptionsoft.games.uglytrivia;

public class PlayerInsidePenaltyBoxState extends PenaltyBoxState {

	public PlayerInsidePenaltyBoxState(Game game) {
		super(game);
	}

	boolean isGettingOutOfPenaltyBox;
	
	@Override
	public void roll(Player player, int roll) {
		isGettingOutOfPenaltyBox = roll % 2 != 0;
		if (isGettingOutOfPenaltyBox) {
			System.out.println(player.getName() + " is getting out of the penalty box");
			game.updatePlayerPositionAndAskQuestion(roll, player);
		} else {
			System.out.println(player.getName() + " is not getting out of the penalty box");
		}
	}
	
	@Override
	public void wasCorrectlyAnswered(Player player, PenaltyBox penaltyBox) {
		if (penaltyBox.contains(player)) {
			if (isGettingOutOfPenaltyBox) {
				game.increasePlayerCoins(player);
				penaltyBox.removePlayer(player);
			} else {
			}
		} 
	}
}
