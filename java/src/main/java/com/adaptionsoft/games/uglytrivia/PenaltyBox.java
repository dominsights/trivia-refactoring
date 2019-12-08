package com.adaptionsoft.games.uglytrivia;

public class PenaltyBox {
	boolean[] inPenaltyBox = new boolean[6];
	
	public void insertPlayer(int player) {
		inPenaltyBox[player] = true;
	}
	
	public void removePlayer(int player) {
		inPenaltyBox[player] = false;
	}
	
	public boolean contains(int player) {
		return inPenaltyBox[player];
	}
}
