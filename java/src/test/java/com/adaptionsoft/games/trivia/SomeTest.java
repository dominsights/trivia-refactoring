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
		game.add("Player 1");
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
		game.wasCorrectlyAnswered();
		game.roll(1);
		game.wrongAnswer();
		// second turn
		game.roll(1);
		game.wasCorrectlyAnswered();
		game.roll(1);
		game.wasCorrectlyAnswered();
		
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
		game.wasCorrectlyAnswered();
		game.roll(1);
		game.wrongAnswer();
		// second turn
		game.roll(1);
		game.wasCorrectlyAnswered();
		game.roll(2);
		game.wasCorrectlyAnswered();
		
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
		game.wasCorrectlyAnswered();
		game.roll(1);
		game.wrongAnswer();
		// second turn
		game.roll(1);
		game.wasCorrectlyAnswered();
		game.roll(11);
		game.wasCorrectlyAnswered();
		
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
		game.roll(1);
		game.wrongAnswer();
		// second turn
		game.roll(1);
		game.wasCorrectlyAnswered();
		game.roll(2);
		game.wasCorrectlyAnswered();
		
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
		game.roll(1);
		game.wasCorrectlyAnswered();
		// second turn
		game.roll(2);
		game.wasCorrectlyAnswered();
		game.roll(2);
		game.wasCorrectlyAnswered();
		
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
			game.wasCorrectlyAnswered();
		}
		
		String output = getOutput(byteOutputStream);
		
		Approvals.verify(output);
	}

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
		game.add("Player 1");
		game.add("Player 2");
	}
}
