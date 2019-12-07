package com.adaptionsoft.games.trivia;

import static org.junit.Assert.*;

import org.junit.Test;

import com.adaptionsoft.games.uglytrivia.Game;

public class SomeTest {

	@Test
	public void shouldBePlayableWhenGameHasTwoPlayers() {
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
	
	private void addTwoPlayers(Game game) {
		game.add("Player 1");
		game.add("Player 2");
	}
}
