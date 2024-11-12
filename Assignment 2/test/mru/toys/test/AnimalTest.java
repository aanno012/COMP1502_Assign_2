package mru.toys.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mru.toys.model.Animal;

class AnimalTest {

	Animal animalToy;

	@BeforeEach
	void animalObjects() {
		animalToy = new Animal("3500386023", "Doug Dog", "Dog Pet", 25.80, 3, 8, "Plastic", 'L');
	}

	@Test
	void testAttributes() {
		assertEquals("3500386023", animalToy.getSerialNumber());
		assertEquals("Doug Dog", animalToy.getName());
		assertEquals("Dog Pet", animalToy.getBrand());
		assertEquals(25.80, animalToy.getPrice());
		assertEquals(3, animalToy.getAvailableCount());
		assertEquals(8, animalToy.getAgeAppropriate());
		assertEquals("Plastic", animalToy.getMaterial());
		assertEquals('L', animalToy.getSize());
	}

}
