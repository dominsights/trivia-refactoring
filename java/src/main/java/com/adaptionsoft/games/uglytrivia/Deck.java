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
	
	public void askQuestion(int playerPosition, QuestionCategory currentCategory) {
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
}
