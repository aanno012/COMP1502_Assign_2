package mru.toys.model;

public class Animal extends Toy {

	/**
	 * The class is a subclass of the Toy abstract class.
	 */

	private String material; // The material of the item.
	private char size; // Small, Medium, or Large (S, M or L)

	/**
	 * Standard constructor for Animal toy type
	 * 
	 * @param serialNumber
	 * @param name
	 * @param brand
	 * @param price
	 * @param availableCount
	 * @param ageAppropriate
	 * @param material
	 * @param size
	 */
	public Animal(String serialNumber, String name, String brand, double price, int availableCount, int ageAppropriate, String material,
			char size) {
		super(serialNumber, name, brand, price, availableCount, ageAppropriate);
		this.material = material;
		this.size = size;
	}

	/**
	 * Copy constructor for the Animal toy type.
	 * 
	 * @param animal animal object
	 */
	public Animal(Animal animal) {
		super(animal.getSerialNumber(), animal.getName(), animal.getBrand(), animal.getPrice(), animal.getAvailableCount(),
				animal.getAgeAppropriate());
		this.material = animal.material;
		this.size = animal.size;
	}

	// Getters & Setters
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public char getSize() {
		return size;
	}

	public void setSize(char size) {
		this.size = size;
	}

	/**
	 * toString method
	 */
	public String toString() {
		return "Category: Animal, " + super.toString() + ", Material: " + material + ", Size: " + size;
	}

	/**
	 * Format for the animal toy information when writing to file
	 * 
	 * @return information format
	 */
	public String format() {
		return super.format() + ";" + material + ";" + size;
	}
}
