package mru.toys.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mru.toys.model.BoardGame;

class BoardGameTest {

	BoardGame boardgameToy;

	@BeforeEach
	void animalObjects() {
		boardgameToy = new BoardGame("9997336000", "Boarderral", "Board Inc.", 30.00, 15, 10, 1, 2, "Board Gamel");
	}

	@Test
	void testAttributes() {
		assertEquals("9997336000", boardgameToy.getSerialNumber());
		assertEquals("Boarderral", boardgameToy.getName());
		assertEquals("Board Inc.", boardgameToy.getBrand());
		assertEquals(30.00, boardgameToy.getPrice());
		assertEquals(15, boardgameToy.getAvailableCount());
		assertEquals(10, boardgameToy.getAgeAppropriate());
		assertEquals(1, boardgameToy.getMimNumOfPlayers());
		assertEquals(2, boardgameToy.getMaxNumOfPlayers());
		assertEquals("Board Gamel", boardgameToy.getDesigners());
	}

}
