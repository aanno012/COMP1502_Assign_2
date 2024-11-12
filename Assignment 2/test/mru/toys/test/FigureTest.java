package mru.toys.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mru.toys.model.Figure;

class FigureTest {

	Figure figureToy;

	@BeforeEach
	void figureObjects() {
		figureToy = new Figure("1586386024", "Batman", "DC", 15.99, 10, 4, 'A');
	}

	@Test
	void testAttributes() {
		assertEquals("1586386024", figureToy.getSerialNumber());
		assertEquals("Batman", figureToy.getName());
		assertEquals("DC", figureToy.getBrand());
		assertEquals(15.99, figureToy.getPrice());
		assertEquals(10, figureToy.getAvailableCount());
		assertEquals(4, figureToy.getAgeAppropriate());
		assertEquals('A', figureToy.getClassification());
	}

}
