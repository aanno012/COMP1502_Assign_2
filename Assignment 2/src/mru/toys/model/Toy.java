package mru.toys.model;

public abstract class Toy {

	// Instance variables
	private String serialNumber; // Unique 10-digit number.
	private String name; // Name of the item
	private String brand; // Brand name
	private double price; // The cost of the item
	private int availableCount; // Number of copies in stock
	private String ageAppropriate; // Minimum age to use item

	// Standard constructor for all Toy types with common attributes.
	public Toy(String serialNumber, String name, String brand, double price, int availableCount, String ageAppropriate) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.availableCount = availableCount;
		this.ageAppropriate = ageAppropriate;
	}

	// Copy constructor
	public Toy(Toy toy) {
		this.serialNumber = toy.serialNumber;
		this.name = toy.name;
		this.brand = toy.brand;
		this.price = toy.price;
		this.availableCount = toy.availableCount;
		this.ageAppropriate = toy.ageAppropriate;
	}

	// Getters & Setters
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAgeAppropriate() {
		return ageAppropriate;
	}

	public void setAgeAppropriate(String ageAppropriate) {
		this.ageAppropriate = ageAppropriate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAvailableCount() {
		return availableCount;
	}

	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
	}

	// toString method
	public String toString() {
		return "Serial Number: " + serialNumber + ", Name: " + name + ", Brand: " + brand + ", Price: $" + price + ", Available Count: "
				+ availableCount + ", Age Appropriate: "
				+ ageAppropriate;
	}

	/**
	 * Format for the toy information when writing to file
	 * 
	 * @return information format
	 */
	public String format() {
		return serialNumber + ";" + name + ";" + brand + ";" + price + ";" + availableCount + ";" + ageAppropriate;
	}
}
