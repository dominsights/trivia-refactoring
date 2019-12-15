package com.adaptionsoft.games.trivia;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.approvaltests.Approvals;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.Test;

import com.adaptionsoft.games.uglytrivia.Game;

public class SomeTest {

	@Test
	public void shouldBePlayableWhenGameHasTwoPlayers() {
		ByteArrayOutputStream byteOutputStream = setCustomOutStream();
		
		Game game = new Game();
		addTwoPlayers(game);
		assertTrue(game.isPlayable());
		assertEquals(2, game.howManyPlayers());
		
		String output = getOutput(byteOutputStream);
		Approvals.verify(output);
	}
	
	@Test
	public void shouldNotBePlayableWhenGameHasOnePlayer() {
		ByteArrayOutputStream byteOutputStream = setCustomOutStream();
		
		Game game = new Game();
		game.addPlayer("Player 1");
		assertFalse(game.isPlayable());
		assertEquals(1, game.howManyPlayers());
		
		String output = getOutput(byteOutputStream);
		Approvals.verify(output);
	}
	
	@Test
	public void testTwoTurnsWithOneWrongAnswer() {
		ByteArrayOutputStream byteOutputStream = setCustomOutStream();
		
		Game game = new Game();
		addTwoPlayers(game);
		// first turn
		game.roll(1);
		game.correctAnswer();
		game.changeToNextPlayer();
		game.roll(1);
		game.wrongAnswer();
		game.changeToNextPlayer();
		
		// second turn
		game.roll(1);
		game.correctAnswer();
		game.changeToNextPlayer();
		game.roll(1);
		game.correctAnswer();
		
		String output = getOutput(byteOutputStream);
		
		Approvals.verify(output);
	}
	
	@Test
	public void testTwoTurnsWithOneWrongAnswerButRollEvenNumber() {
		ByteArrayOutputStream byteOutputStream = setCustomOutStream();
		
		Game game = new Game();
		addTwoPlayers(game);
		// first turn
		game.roll(1);
		game.correctAnswer();
		game.changeToNextPlayer();
		game.roll(1);
		game.wrongAnswer();
		game.changeToNextPlayer();
		// second turn
		game.roll(1);
		game.correctAnswer();
		game.changeToNextPlayer();
		game.roll(2);
		game.correctAnswer();
		
		String output = getOutput(byteOutputStream);
		
		Approvals.verify(output);
	}
	
	@Test
	public void testTwoTurnsWithOneWrongAnswerAndRollEqualEleven() {
		ByteArrayOutputStream byteOutputStream = setCustomOutStream();
		
		Game game = new Game();
		addTwoPlayers(game);
		// first turn
		game.roll(11);
		game.correctAnswer();
		game.changeToNextPlayer();
		game.roll(1);
		game.wrongAnswer();
		game.changeToNextPlayer();
		// second turn
		game.roll(1);
		game.correctAnswer();
		game.changeToNextPlayer();
		game.roll(11);
		game.correctAnswer();
		
		String output = getOutput(byteOutputStream);
		
		Approvals.verify(output);
	}
	
	@Test
	public void testTwoTurnsWithOneWrongAnswerForFirstPlayerAndRollEqualEleven() {
		ByteArrayOutputStream byteOutputStream = setCustomOutStream();
		
		Game game = new Game();
		addTwoPlayers(game);
		// first turn
		game.roll(11);
		game.wrongAnswer();
		game.changeToNextPlayer();
		game.roll(1);
		game.wrongAnswer();
		game.changeToNextPlayer();
		// second turn
		game.roll(1);
		game.correctAnswer();
		game.changeToNextPlayer();
		game.roll(2);
		game.correctAnswer();
		game.changeToNextPlayer();
		
		String output = getOutput(byteOutputStream);
		
		Approvals.verify(output);
	}
	
	@Test
	public void testTwoTurnsWithOneWrongAnswerForFirstPlayerAndNotLeavingPenaltyBoxAndRollEqualEleven() {
		ByteArrayOutputStream byteOutputStream = setCustomOutStream();
		
		Game game = new Game();
		addTwoPlayers(game);
		// first turn
		game.roll(11);
		game.wrongAnswer();
		game.changeToNextPlayer();
		game.roll(1);
		game.correctAnswer();
		game.changeToNextPlayer();
		// second turn
		game.roll(2);
		game.correctAnswer();
		game.changeToNextPlayer();
		game.roll(2);
		game.correctAnswer();
		game.changeToNextPlayer();
		
		String output = getOutput(byteOutputStream);
		
		Approvals.verify(output);
	}
	
	@Test
	public void shouldPlayerLoseWhenPurseEqualsSix() {
		ByteArrayOutputStream byteOutputStream = setCustomOutStream();
		
		Game game = new Game();
		addTwoPlayers(game);

		for(int i = 0; i < 20; i++) { // roll 12 times
			game.roll(1);
			game.correctAnswer();
			game.changeToNextPlayer();
		}
		
		String output = getOutput(byteOutputStream);
		
		Approvals.verify(output);
	}
	
	@Test
	public void shouldNotExitTheTwiceWhenTwoCorrectAnswersInARow() {
		ByteArrayOutputStream byteOutputStream = setCustomOutStream();
		
		Game game = new Game();
		addTwoPlayers(game);
		
		// first turn
		
		// first player
		game.roll(1);
		game.wrongAnswer();
		
		// second player
		game.changeToNextPlayer();
		game.roll(1);
		game.correctAnswer();
		
		// second turn
		
		// first player
		game.changeToNextPlayer();
		game.roll(1);
		game.correctAnswer();
		
		// second player
		game.changeToNextPlayer();
		game.roll(1);
		game.correctAnswer();
		
		// third turn
		
		// first player
		game.changeToNextPlayer();
		game.roll(1);
		game.correctAnswer();
		
		String output = getOutput(byteOutputStream);
		
		Approvals.verify(output);
		
	}
	
//	@Test
//	public void shouldNotRunOutOfQuestions() {
//		Game game = new Game();
//		addTwoPlayers(game);
//		
//		for(int i = 0; i < 300; i++) {
//			game.roll(1);
//			game.wrongAnswer();
//		}
//	}

	private String getOutput(ByteArrayOutputStream byteOutputStream) {
		System.out.flush();
		String output = byteOutputStream.toString();
		return output;
	}

	private ByteArrayOutputStream setCustomOutStream() {
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(byteOutputStream);
		System.setOut(printStream);
		return byteOutputStream;
	}
	
	private void addTwoPlayers(Game game) {
		game.addPlayer("Player 1");
		game.addPlayer("Player 2");
	}
}
