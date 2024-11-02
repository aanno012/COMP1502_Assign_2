package mru.toys.model;

public class Puzzle extends Toy {

	private char puzzleType; // Mechanical, Cryptic, Logic, Trivia, or Riddle (M, C, L, T or R).

	// Standard constructor for Puzzle Toy type
	public Puzzle(String serialNumber, String name, String brand, double price, int availableCount, String ageAppropriate,
			char puzzleType) {
		super(serialNumber, name, brand, price, availableCount, ageAppropriate);
		this.puzzleType = puzzleType;
	}

	// Copy constructor
	public Puzzle(Puzzle puzzle) {
		super(puzzle.getSerialNumber(), puzzle.getName(), puzzle.getBrand(), puzzle.getPrice(), puzzle.getAvailableCount(),
				puzzle.getAgeAppropriate());
		this.puzzleType = puzzle.puzzleType;
	}

	// Getters & Setters
	public char getPuzzleType() {
		return puzzleType;
	}

	public void setPuzzleType(char puzzleType) {
		this.puzzleType = puzzleType;
	}

	// toString method
	public String toString() {
		return "Category: Puzzle, " + super.toString() + ", Puzzle Type: " + puzzleType;
	}

	/**
	 * Format for the puzzle toy information when writing to file
	 * 
	 * @return information format
	 */
	public String format() {
		return super.format() + ";" + puzzleType;
	}

}
