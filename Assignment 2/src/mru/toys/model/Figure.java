package mru.toys.model;

public class Figure extends Toy {

	/**
	 * The class is a subclass of the Toy abstract class.
	 */

	private char classification; // Action, Doll, or Historic (A, D or H)

	/**
	 * Standard constructor for Figure Toy type
	 * 
	 * @param serialNumber   10-digit unique number.
	 * @param name           name of the item
	 * @param brand          brand name
	 * @param price          cost of toy
	 * @param availableCount number of copies in stock
	 * @param ageAppropriate minimum age to use toy
	 * @param classification Action, Doll, or Historic (A, D or H)
	 */
	public Figure(String serialNumber, String name, String brand, double price, int availableCount, int ageAppropriate,
			char classification) {
		super(serialNumber, name, brand, price, availableCount, ageAppropriate);
		this.classification = classification;
	}

	/**
	 * Copy constructor for Figure toy type
	 * 
	 * @param figure figure object
	 */
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

	/**
	 * toString method
	 */
	public String toString() {
		return "Category: Figure, " + super.toString() + ", Classification: " + classification;
	}

	/**
	 * Format for the figure toy information when writing to file
	 * 
	 * @return information format
	 */
	public String format() {
		return super.format() + ";" + classification;
	}
}
