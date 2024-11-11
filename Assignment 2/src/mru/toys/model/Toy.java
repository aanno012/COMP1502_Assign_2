package mru.toys.model;

public abstract class Toy {
	/**
	 * The class is an abstract class for the toy object. It also holds all common
	 * attributes between all toy types.
	 */

	// Instance variables
	private String serialNumber; // Unique 10-digit number.
	private String name; // Name of the toy
	private String brand; // Brand name
	private double price; // The cost of the toy
	private int availableCount; // Number of copies in stock
	private int ageAppropriate; // Minimum age to use toy

	/**
	 * Standard constructor for all Toy types with common attributes.
	 * 
	 * @param serialNumber   10-digit unique number.
	 * @param name           name of the item
	 * @param brand          brand name
	 * @param price          cost of toy
	 * @param availableCount number of copies in stock
	 * @param ageAppropriate minimum age to use toy
	 */
	public Toy(String serialNumber, String name, String brand, double price, int availableCount, int ageAppropriate) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.availableCount = availableCount;
		this.ageAppropriate = ageAppropriate;
	}

	/**
	 * Copy constructor for the toy object.
	 * 
	 * @param toy toy object
	 */
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

	public int getAgeAppropriate() {
		return ageAppropriate;
	}

	public void setAgeAppropriate(int ageAppropriate) {
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

	/**
	 * toString method
	 */
	public String toString() {
		return "Serial Number: " + serialNumber + ", Name: " + name + ", Brand: " + brand + ", Price: $" + String.format("%.2f", price)
				+ ", Available Count: "
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
