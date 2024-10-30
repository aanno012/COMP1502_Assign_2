package mru.toys.model;

public class Figure extends Toy {

	private char classification; // Action, Doll, or Historic (A, D or H)

	// Standard constructor for Figure Toy type
	public Figure(String serialNumber, String name, String brand, double price, int availableCount, String ageAppropriate,
			char classification) {
		super(serialNumber, name, brand, price, availableCount, ageAppropriate);
		this.classification = classification;
	}

	// Copy constructor
	public Figure(Figure figure) {
		super(figure.getSerialNumber(), figure.getName(), figure.getBrand(), figure.getPrice(), figure.getAvailableCount(),
				figure.getAgeAppropriate());
		this.classification = figure.classification;
	}

	// Getters & Setters
	public char getClassification() {
		return classification;
	}

	public void setClassification(char classification) {
		this.classification = classification;
	}

	// toString method
	public String toString() {
		return super.toString() + ", Classification: " + classification;
	}

}
