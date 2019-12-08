package com.adaptionsoft.games.uglytrivia;

public class Question {
	public Question(String description) {
		this.description = description;
	}
	
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return description;
	}
}
