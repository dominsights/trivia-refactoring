package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class PenaltyBox {
	ArrayList<Player> inPenaltyBox = new ArrayList<Player>();
	
	public void insertPlayer(Player player) {
		inPenaltyBox.add(player);
	}
	
	public void removePlayer(Player player) {
		inPenaltyBox.remove(player);
	}
	
	public boolean contains(Player player) {
		return inPenaltyBox.contains(player);
	}
}
