package mru.toys.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mru.toys.model.Puzzle;

class PuzzleTest {

	Puzzle puzzleToy;

	@BeforeEach
	void animalObjects() {
		puzzleToy = new Puzzle("5507336020", "Puzzler", "Cryptizle", 20.50, 10, 5, 'C');
	}

	@Test
	void testAttributes() {
		assertEquals("5507336020", puzzleToy.getSerialNumber());
		assertEquals("Puzzler", puzzleToy.getName());
		assertEquals("Cryptizle", puzzleToy.getBrand());
		assertEquals(20.50, puzzleToy.getPrice());
		assertEquals(10, puzzleToy.getAvailableCount());
		assertEquals(5, puzzleToy.getAgeAppropriate());
		assertEquals('C', puzzleToy.getPuzzleType());
	}
}
