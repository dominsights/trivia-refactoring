package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Deck {
	LinkedList<String> popQuestions = new LinkedList<String>();
	LinkedList<String> scienceQuestions = new LinkedList<String>();
	LinkedList<String> sportsQuestions = new LinkedList<String>();
	LinkedList<String> rockQuestions = new LinkedList<String>();

	public void initializeQuestions() {
		for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
		}
	}

	public String createRockQuestion(int index) {
		return "Rock Question " + index;
	}
	
	public void askQuestion(int playerPosition) {
		if (currentCategory(playerPosition) == "Pop") {
			System.out.println(popQuestions.removeFirst());
		} else if (currentCategory(playerPosition) == "Science") {
			System.out.println(scienceQuestions.removeFirst());
		} else if (currentCategory(playerPosition) == "Sports") {
			System.out.println(sportsQuestions.removeFirst());
		} else if (currentCategory(playerPosition) == "Rock") {
			System.out.println(rockQuestions.removeFirst());
		}
	}
	
	public String currentCategory(int playerPosition) {
		switch (playerPosition) {
		case 0:
			return "Pop";
		case 4:
			return "Pop";
		case 8:
			return "Pop";
		case 1:
			return "Science";
		case 5:
			return "Science";
		case 9:
			return "Science";
		case 2:
			return "Sports";
		case 6:
			return "Sports";
		case 10:
			return "Sports";
		}
		return "Rock";
	}
}
