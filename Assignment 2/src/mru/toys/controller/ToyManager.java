package mru.toys.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
				break;
			case 2:
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
						if (toy instanceof Figure)
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

	// <------------MISCELLANEOUS METHODS------------->
	// All non-specific or extra methods can go here.

	// Printing to console for testing.
	public void printAllToys(ArrayList<Toy> toys) {
		for (Toy toy : toys) {
			System.out.println(toy.toString());
		}
	}
}
