package mru.toys.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mru.toys.model.BoardGame;
import mru.toys.model.Puzzle;
import mru.toys.model.Toy;

class ToyTest {

	Toy toy1;
	Toy toy2;
	ArrayList<Toy> toys;

	@BeforeEach
	void setUpObjects() throws IOException {
		toy1 = new BoardGame("9997336000", "Boarderral", "Board Inc.", 30.00, 15, 10, 1, 2, "Board Gamel");
		toy2 = new Puzzle("5507336020", "Puzzler", "Cryptizle", 20.50, 10, 5, 'C');
		toys = new ArrayList<Toy>();
	}

	@Test
	void addToy() {
		// Adding toys to arrayList
		toys.add(toy1);
		toys.add(toy2);

		// Testing for object types.
		assertTrue(toys.contains(toy1), "This toy exist.");
		assertTrue(toys.contains(toy2), "This toy exist.");
		assertTrue(toy1 instanceof BoardGame, "This toy is a Board Game.");
		assertTrue(toy2 instanceof Puzzle, "This toy is a Puzzle.");
	}

}
