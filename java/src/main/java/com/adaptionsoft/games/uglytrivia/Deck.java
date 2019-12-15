package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Deck {
	public Deck() {
		initializeQuestions();
	}
	
	enum Category {
		Pop,
		Science,
		Sports,
		Rock
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
		Category currentCategory = currentCategory(playerPosition);
		System.out.println("The category is " + currentCategory);
		
		switch (currentCategory) {
		case Pop:
			System.out.println(popQuestions.removeFirst());
			break;
		case Science:
			System.out.println(scienceQuestions.removeFirst());
			break;
		case Sports:
			System.out.println(sportsQuestions.removeFirst());
			break;
		case Rock:
			System.out.println(rockQuestions.removeFirst());
			break;
		}
	}
	
	public Category currentCategory(int playerPosition) {
		switch (playerPosition) {
		case 0:
		case 4:
		case 8:
			return Category.Pop;
		case 1:
		case 5:
		case 9:
			return Category.Science;
		case 2:
		case 6:
		case 10:
			return Category.Sports;
		}
		return Category.Rock;
	}
}
