package mru.toys.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import mru.toys.exceptions.InvalidNumberOfPlayersException;
import mru.toys.exceptions.NegativePriceException;
import mru.toys.exceptions.ToyValidationException;
import mru.toys.model.Animal;
import mru.toys.model.BoardGame;
import mru.toys.model.Figure;
import mru.toys.model.Puzzle;
import mru.toys.model.Toy;
import mru.toys.view.ToyMenu;

public class ToyManager {
	/**
	 * This class handles all processes in the program.
	 */

	// Variables
	private final String FILE_PATH = "res/toys.txt"; // Location of database file
	ArrayList<Toy> toys;
	ToyMenu toyMenu;
	Scanner input;

	/**
	 * Constructor
	 * 
	 * @throws IOException
	 */
	public ToyManager() throws IOException {
		input = new Scanner(System.in); // For user inputs
		toys = new ArrayList<Toy>(); // Empty arrayList for the toys.
		toyMenu = new ToyMenu(); // ToyMenu object
		toyMenu.showWelcomeMsg(); // Displays welcome message.
		loadToyData(); // Loads toy data into program.
		launchToyApp(); // Launches the program.
//		printAllToys(toys); // Printing to console for testing.
	}

	// <-----------------------PRELIMINARY METHODS---------------------------->
	/**
	 * Loads the information about the toys on startup.
	 * 
	 * @throws IOException
	 */
	public void loadToyData() throws IOException {
		// Path to the file
		File file = new File(FILE_PATH);
		String toyline;
		String[] splittedToyLine;

		// Checks if the file exists and reads the file using FileReader and
		// BufferedReader.
		if (file.exists()) {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedFile = new BufferedReader(fileReader);

			// Iterating through the file
			toyline = bufferedFile.readLine(); // Priming read
			while (toyline != null) { // Checks if the toy exist.
				splittedToyLine = toyline.split(";"); // Splits the line with semmi-colon.
				Toy toyType = convertToyType(splittedToyLine); // Converts the appropriate data types
				toys.add(toyType); // Adds each toy to the Toy arrayList.
				toyline = bufferedFile.readLine(); // Moves to next line.
			}
			bufferedFile.close(); // Closes the BufferedReader object.
		}
	}

	/**
	 * Converts each toy to one of the four Toy types, along with the appropriate
	 * data types.
	 * 
	 * @param splittedToyLine A String array with no delimiter.
	 * @return Each of the different Toy types or null if the toy does not exist.
	 */
	private Toy convertToyType(String[] splittedToyLine) {
		// Converting all the common attributes amongst all Toy types
		String serialNumber = splittedToyLine[0].trim();
		String name = splittedToyLine[1].trim();
		String brand = splittedToyLine[2].trim();
		double price = Double.parseDouble(splittedToyLine[3].trim());
		int availableCount = Integer.parseInt(splittedToyLine[4].trim());
		int ageAppropriate = Integer.parseInt(splittedToyLine[5].trim());

		// Classifying each Toy type using the first digit of the serial number
		char firstSerialDigit = serialNumber.charAt(0);

		// Implementing polymorphism to reference the different Toy types.
		// For Figures
		if (firstSerialDigit == '0' || firstSerialDigit == '1') {
			char classification = splittedToyLine[6].trim().toUpperCase().charAt(0);
			Toy figureToy = new Figure(serialNumber, name, brand, price, availableCount, ageAppropriate, classification);
			return figureToy;

			// For Animals
		} else if (firstSerialDigit == '2' || firstSerialDigit == '3') {
			String material = splittedToyLine[6].trim();
			char size = splittedToyLine[7].trim().toUpperCase().charAt(0);
			Toy animalToy = new Animal(serialNumber, name, brand, price, availableCount, ageAppropriate, material, size);
			return animalToy;

			// For Puzzles
		} else if (firstSerialDigit == '4' || firstSerialDigit == '5' || firstSerialDigit == '6') {
			char puzzleType = splittedToyLine[6].trim().toUpperCase().charAt(0);
			Toy puzzleToy = new Puzzle(serialNumber, name, brand, price, availableCount, ageAppropriate, puzzleType);
			return puzzleToy;

			// For BoardGames
		} else if (firstSerialDigit == '7' || firstSerialDigit == '8' || firstSerialDigit == '9') {
			String[] numOfPlayersRange = splittedToyLine[6].split("-");
			int minNumOfPlayers = Integer.parseInt(numOfPlayersRange[0].trim());
			int maxNumOfPlayers = Integer.parseInt(numOfPlayersRange[1].trim());
			String designers = splittedToyLine[7].trim();
			Toy boardGameToy = new BoardGame(serialNumber, name, brand, price, availableCount, ageAppropriate, minNumOfPlayers,
					maxNumOfPlayers, designers);
			return boardGameToy;
		} else {
			return null;
		}
	}

	/**
	 * Launches the toy application.
	 * 
	 * @throws IOException
	 */
	private void launchToyApp() throws IOException {
		boolean flag = true;
		int option;

		while (flag) {
			option = toyMenu.showMainMenu();

			switch (option) {
			case 1:
				searchToy(); // Searches for toy.
				break;
			case 2:
				createToyFromInput(); // Adding new toys.
				break;
			case 3:
				removeToy(); // Removes a toy from the database.
				break;
			case 4:
				giftSuggestion(); // Provides a suggested list of toys.
				break;
			case 5:
				saveExit(); // Saves and exits the program.
				toyMenu.showThankYouMsg(); // Exit message
				flag = false;
				break;
			}
		}
	}

	// <----------------------MENU METHODS------------------------->
	// All methods that corresponds to the menu options goes here.

	// Method to search and purchase toys
	public void searchToy() {
		// Prompt user for search term
		String searchQuery = toyMenu.askSearchQuery();

		// Temporary List to hold search results
		ArrayList<Toy> searchResults = new ArrayList<>();

		// Search through toys List
		for (Toy toy : toys) {
			if (toy.getName().equalsIgnoreCase(searchQuery) ||
					toy.getName().toLowerCase().contains(searchQuery.toLowerCase()) ||
					toy.getBrand().toLowerCase().contains(searchQuery.toLowerCase()) ||
					searchResults.add(toy))
				;
		}
	}

	/**
	 * Allows user to remove a toy from the database, based on the serial number.
	 */
	public void removeToy() {
		// Receives serial number.
		String toySerialStr = toyMenu.askSerialNum();

		// Looping through the toys list
		Toy removeThisToy = null;
		for (Toy toy : toys) {
			if (toy.getSerialNumber().equals(toySerialStr)) {
				removeThisToy = toy;
				break;
			}
		}

		// Displaying toy information.
		if (removeThisToy != null) {
			toyMenu.showFoundMsg();
			System.out.println();
			System.out.println("\t" + removeThisToy.toString());
			System.out.println();

			// Asking for user confirmation
			char ans = toyMenu.askToConfirm();
			if (ans == 'y') {
				toys.remove(removeThisToy);
				toyMenu.showRemoveMsg();
			}

			// Displays item not found
		} else {
			toyMenu.showNotFoundMsg();
		}

		// Returns to main menu.
		toyMenu.pressEnter();
	}
	
	
	
	// Adding specific toy methods 
	public class Animal extends Toy {
	    private String material; // e.g., "Plush", "Wood", "Plastic"
	    private String size; // e.g., "Small", "Medium", "Large"

	    public Animal(String serialNumber, String name, String brand, double price, int availableCount, int ageAppropriate, String material, String size) {
	        super(serialNumber, name, brand, price, availableCount, ageAppropriate);
	        this.material = material;
	        this.size = size;
	    }

	    @Override
	    public String toString() {
	        return super.toString() + ", Material: " + material + ", Size: " + size;
	    }
	}
	
	public class BoardGame extends Toy {
	    private int minPlayers;
	    private int maxPlayers;
	    private String designers; // Comma-separated designer names

	    public BoardGame(String serialNumber, String name, String brand, double price, int availableCount, int ageAppropriate, int minPlayers, int maxPlayers, String designers) {
	        super(serialNumber, name, brand, price, availableCount, ageAppropriate);
	        this.minPlayers = minPlayers;
	        this.maxPlayers = maxPlayers;
	        this.designers = designers;
	    }

	    @Override
	    public String toString() {
	        return super.toString() + ", Min Players: " + minPlayers + ", Max Players: " + maxPlayers + ", Designers: " + designers;
	    }
	}
	public class Puzzle extends Toy {
	    private int numberOfPieces; // e.g., 50, 100, 500
	    private String puzzleType; // e.g., "3D", "Jigsaw", "Crossword"

	    public Puzzle(String serialNumber, String name, String brand, double price, int availableCount, int ageAppropriate, int numberOfPieces, String puzzleType) {
	        super(serialNumber, name, brand, price, availableCount, ageAppropriate);
	        this.numberOfPieces = numberOfPieces;
	        this.puzzleType = puzzleType;
	    }
	    public String toString() {
	        return super.toString() + ", Number of Pieces: " + numberOfPieces + ", Puzzle Type: " + puzzleType;
	    }
	}

	
	public Toy createToyFromInput() {
		Scanner scanner = new Scanner(System.in);
		
		try {
			System.out.println("Enter Serial Number:");
	        String serialNumber = scanner.nextLine();

	        System.out.println("Enter Toy Name:");
	        String name = scanner.nextLine();

	        System.out.println("Enter Toy Brand:");
	        String brand = scanner.nextLine();

	        System.out.println("Enter Toy Price:");
	        double price = Double.parseDouble(scanner.nextLine());

	        System.out.println("Enter Available Count:");
	        int availableCount = Integer.parseInt(scanner.nextLine());

	        System.out.println("Enter Appropriate Age:");
	        int ageAppropriate = Integer.parseInt(scanner.nextLine());
	        char firstSerialDigit = serialNumber.charAt(0);

	        Toy newToy;
	        
	     // Creating a specific toy type
	        if (firstSerialDigit == '0' || firstSerialDigit == '1') { // Figure
	            System.out.println("Enter Classification (A for Action, D for Doll, H for Historic):");
	            char classification = scanner.nextLine().toUpperCase().charAt(0);
	            newToy = new Figure(serialNumber, name, brand, price, availableCount, ageAppropriate, classification);

	        } else if (firstSerialDigit == '2' || firstSerialDigit == '3') { // Animal
	            System.out.println("Enter Material (Plush, Wood, Plastic):");
	            String material = scanner.nextLine();
	            System.out.println("Enter Size (Small, Medium, Large):");
	            String size = scanner.nextLine();
	            newToy = new Animal(serialNumber, name, brand, price, availableCount, ageAppropriate, material, size);

	        } else if (firstSerialDigit == '4' || firstSerialDigit == '5' || firstSerialDigit == '6') { // Puzzle
	            System.out.println("Enter Puzzle Type (3D, Jigsaw, Crossword):");
	            String puzzleType = scanner.nextLine();
	            System.out.println("Enter Number of Pieces:");
	            int numberOfPieces = Integer.parseInt(scanner.nextLine());
	            newToy = new Puzzle(serialNumber, name, brand, price, availableCount, ageAppropriate, numberOfPieces, puzzleType);

	        } else if (firstSerialDigit == '7' || firstSerialDigit == '8' || firstSerialDigit == '9') { // BoardGame
	            System.out.println("Enter Minimum Number of Players:");
	            int minPlayers = Integer.parseInt(scanner.nextLine());
	            System.out.println("Enter Maximum Number of Players:");
	            int maxPlayers = Integer.parseInt(scanner.nextLine());
	            System.out.println("Enter Designers (comma-separated):");
	            String designers = scanner.nextLine();
	            newToy = new BoardGame(serialNumber, name, brand, price, availableCount, ageAppropriate, minPlayers, maxPlayers, designers);
	        }

	        // Validate and add to the toys list
	        if (newToy != null) {
	            newToy.validate(); // Validates toy attributes
	            toys.add(newToy);
	            System.out.println("Toy added successfully!");
	        } else {
	            System.out.println("Invalid serial number! Could not determine toy type.");
	        }

	    } catch (NumberFormatException e) {
	        System.out.println("Invalid input! Please enter the correct data type.");
	    } catch (ToyValidationException e) {
	        System.out.println("Validation Error: " + e.getMessage());
	    }

	    return null;
	}

	/**
	 * Provides a list of gift suggestions based on the details provided by user.
	 * 
	 * @throws IOException
	 */
	public void giftSuggestion() throws IOException {
		// Shows the user which details to provide.
		toyMenu.showProvideDtls();

		// Request details from the user.
		String strAge = toyMenu.askAge();
		String type = toyMenu.askType();
		String[] priceRange = toyMenu.askPriceRange();

		// Empty arrayList to hold all suggested toys.
		ArrayList<Toy> giftList = new ArrayList<Toy>();

		if ((strAge != null || strAge == null) && (type != null || type == null) && (priceRange != null || priceRange == null)) {
			// Converts string age to integer age.
			int age = 0;
			if (strAge != null) {
				age = Integer.parseInt(strAge);
			}

			// Converts the price string array into minimum and maximum prices
			double minPrice = 0;
			double maxPrice = 0;
			if (priceRange != null) {
				minPrice = Double.parseDouble(priceRange[0].trim());
				maxPrice = Double.parseDouble(priceRange[1].trim());
			}

			// Filtering toys based on the given details.
			for (Toy toy : toys) {
				// Searching with age
				boolean ageFilter = false; // Variable used for filtering age.
				if (toy.getAgeAppropriate() == age) {
					ageFilter = true;
				}

				// Filtering with toy type through polymorphism.
				boolean typeFilter = false; // Variable used for filtering the toy type.
				if (type != null && type.length() != 0) {
					switch (type) {
					case "figure":
						if (toy instanceof Figure) // 
							typeFilter = true;
						break;
					case "animal":
						if (toy instanceof Animal)
							typeFilter = true;
						break;
					case "puzzle":
						if (toy instanceof Puzzle)
							typeFilter = true;
						break;
					case "boardgame":
					case "board game":
						if (toy instanceof BoardGame)
							typeFilter = true;
						break;
					default:
						typeFilter = false;
					}
				}

				// Filtering with price range
				boolean priceFilter = false;
				if (minPrice >= 0 && maxPrice >= 0) {
					if (toy.getPrice() >= minPrice && toy.getPrice() <= maxPrice) {
						priceFilter = true;
					}
				}

				// Adding toy to the suggested list based on the user details
				if (strAge == null && type == null && priceRange == null) {
					toyMenu.showNoDtlsMsg(); // No provided details message.
					break;
				} else if ((strAge == null || ageFilter) && (type == null || typeFilter) && (priceRange == null || priceFilter)) {
					giftList.add(toy);
				}
			}

			// Displaying suggested toy list.
			int itemIndex = 0;
			int menuIndex = 0;
			if (giftList.size() > 0) {
				toyMenu.showSgtLabel(); // Label for the suggested list.
				for (int i = 0; i < giftList.size(); i++) {
					itemIndex = i + 1; // Start index at 1.
					System.out.println("\t(" + itemIndex + ") " + giftList.get(i).toString()); // Prints all the suggested toys.
					menuIndex = giftList.size() + 1; // Maximum index plus 1 for main menu.
				}

				// Showing the specific index to return to the main menu.
				toyMenu.backToMainMsg(menuIndex);

				// Handling toy index from the gift list.
				int itemNum = toyMenu.askPurchaseGift(); // Get index from the user selection.
				Toy giftToy = null;
				if (itemNum >= 1 && itemNum <= giftList.size()) {
					giftToy = giftList.get(itemNum - 1); // Adjust index and get toy from the gift list
				} else if (itemNum == menuIndex) {
					launchToyApp(); // Resets the program and returns to the main menu.
				} else {
					toyMenu.showNotExist(); // No toy exist message.
				}

				// Allowing the user to purchase a gift from the suggested list.
				int toyIndex;
				int newCount;
				if (toys.contains(giftToy)) { // Checks if toy exist in the toys arrayList.
					toyIndex = toys.indexOf(giftToy); // Get index of the toy in toys arrayList
					if (giftToy.getAvailableCount() > 0) { // Checks if toy is available.
						newCount = toys.get(toyIndex).getAvailableCount() - 1; // Subtract 1 from the available count for that toy.
						toys.get(toyIndex).setAvailableCount(newCount); // Sets a new available count for the toy.
						toyMenu.showSuccessMsg(); // Successful transaction message.
						toyMenu.pressEnter();
					} else {
						toyMenu.showOutofStkMsg(); // "Out of stock" message.
					}
				}
			}
		}
		System.out.println();
	}
	

	/**
	 * Saves and prints to file
	 * 
	 * @throws IOException
	 */
	private void saveExit() throws IOException {
		toyMenu.showSaveMsg(); // Saving message.

		// File handling
		File toyFile = new File(FILE_PATH);
		PrintWriter pw = new PrintWriter(toyFile);

		// Looping through the toys list and writing to file.
		for (Toy t : toys) {
			pw.println(t.format());
		}
		pw.close(); // Closes the PrintWriter object.
	}

	// <--------------------------EXCEPTION METHODS---------------------------->
	// All exception methods used for validation can go here.
	/**
	 * 
	 * @param price the price of the toy.
	 * @return true for positive price
	 * @throws NegativePriceException shows an error message about the negative
	 *                                price.
	 */
	public static boolean validateToyPrice(double price) throws NegativePriceException {

		// Checks if the price is negative. An exception will be thrown if true.
		if (price < 0) {
			throw new NegativePriceException("The price cannot be negative! Only positive values are permitted.");
		}

		return true; // Returns true if price is positive and bypasses the exception.
	}

	/**
	 * 
	 * @param minPlys the minimum # of players to play the game.
	 * @param maxPlys the maximum # of players to play the game.
	 * @return true if no exception is thrown.
	 * @throws InvalidNumberOfPlayersException shows an error message about the
	 *                                         number of players.
	 */
	public static boolean validateBoardgamePlayers(int minPlys, int maxPlys) throws InvalidNumberOfPlayersException {

		// Compares both number of players and throws an exception if true.
		if (minPlys > maxPlys) {
			throw new InvalidNumberOfPlayersException("The minimum number of players cannot exceed the maximum number of players!");
		}

		return true; // Returns true if the above condition is false or no exception is thrown.
	}

	/**
     * Validates common toy attributes.
     * 
     * @param serialNumber the serial number of the toy.
     * @param name the name of the toy.
     * @param brand the brand of the toy.
     * @param price the price of the toy.
     * @throws ToyValidationException if any validation fails.
     */
    public static void validateToyAttributes(String serialNumber, String name, String brand, double price)
            throws ToyValidationException {

        // Validate serial number
        if (serialNumber == null || !serialNumber.matches("\\d{10}")) {
            throw new ToyValidationException("Invalid serial number. It must be exactly 10 digits.");
        }

        // Validate name
        if (name == null || name.trim().isEmpty()) {
            throw new ToyValidationException("Toy name cannot be empty.");
        }

        // Validate brand
        if (brand == null || brand.trim().isEmpty()) {
            throw new ToyValidationException("Toy brand cannot be empty.");
        }

        // Validate price
        if (price <= 0) {
            throw new ToyValidationException("Price must be greater than zero.");
        }
    }

    /**
     * Validates the number of players for a board game.
     * 
     * @param minPlys the minimum number of players to play the game.
     * @param maxPlys the maximum number of players to play the game.
     * @throws InvalidNumberOfPlayersException if the minimum players exceed the maximum.
     */
    public static void validateBoardgamePlayers1(int minPlys, int maxPlys) throws InvalidNumberOfPlayersException {
        if (minPlys > maxPlys) {
            throw new InvalidNumberOfPlayersException("The minimum number of players cannot exceed the maximum number of players!");
        }
    }

    
	// <------------------MISCELLANEOUS METHODS------------------>
	// All non-specific or extra methods can go here.

	/**
	 * Printing to console for testing.
	 * 
	 * @param toys an arrayList of all the toys.
	 */
	public void printAllToys(ArrayList<Toy> toys) {
		for (Toy toy : toys) {
			System.out.println(toy.toString());
		}
	}

}
