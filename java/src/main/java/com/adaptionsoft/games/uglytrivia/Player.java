package com.adaptionsoft.games.uglytrivia;

public class Player {
	public Player(String name, int position) {
		this.name = name;
		this.position = position;
	}
	
	private String name;
	private int position;
	private int coins;

	public String getName() {
		return name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}
}
