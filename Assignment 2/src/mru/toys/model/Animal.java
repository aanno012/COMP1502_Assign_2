package mru.toys.model;

public class Animal extends Toy {

	private String material; // The material of the item.
	private char size; // Small, Medium, or Large (S, M or L)

	// Standard constructor for Animal Toy type
	public Animal(String serialNumber, String name, String brand, double price, int availableCount, String ageAppropriate, String material,
			char size) {
		super(serialNumber, name, brand, price, availableCount, ageAppropriate);
		this.material = material;
		this.size = size;
	}

	// Copy constructor
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

	// toString method
	public String toString() {
		return super.toString() + ", Material: " + material + ", Size: " + size;
	}
}
