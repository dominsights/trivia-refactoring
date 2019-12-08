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
	@UseReporter(DiffReporter.class)
	public void shouldBePlayableWhenGameHasTwoPlayers() {
		
		Approvals.verify("Hello");
		
		Game game = new Game();
		addTwoPlayers(game);
		assertTrue(game.isPlayable());
		assertEquals(2, game.howManyPlayers());
	}
	
	@Test
	public void shouldNotBePlayableWhenGameHasOnePlayer() {
		Game game = new Game();
		game.add("Player 1");
		assertFalse(game.isPlayable());
		assertEquals(1, game.howManyPlayers());
	}
	
	@Test
	@UseReporter(DiffReporter.class)
	public void testGame() {
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(byteOutputStream);
		System.setOut(printStream);
		
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
		
		System.out.flush();
		String output = byteOutputStream.toString();
		
		Approvals.verify(output);
	}
	
	private void addTwoPlayers(Game game) {
		game.add("Player 1");
		game.add("Player 2");
	}
}
