package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Deck {
	public Deck() {
		initializeQuestions();
	}
	
	LinkedList<Question> popQuestions = new LinkedList<Question>();
	LinkedList<Question> scienceQuestions = new LinkedList<Question>();
	LinkedList<Question> sportsQuestions = new LinkedList<Question>();
	LinkedList<Question> rockQuestions = new LinkedList<Question>();

	public void initializeQuestions() {
		for (int i = 0; i < 50; i++) {
			popQuestions.addLast(new Question("Pop Question " + i));
			scienceQuestions.addLast(new Question("Science Question " + i));
			sportsQuestions.addLast(new Question("Sports Question " + i));
			rockQuestions.addLast(new Question("Rock Question " + i));
		}
	}
	
	public void askQuestion(int playerPosition) {
		System.out.println("The category is " + currentCategory(playerPosition));
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
