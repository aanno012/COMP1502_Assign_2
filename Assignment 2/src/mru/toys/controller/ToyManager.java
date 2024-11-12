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

	// Variables
	private final String FILE_PATH = "res/toys.txt";
	ArrayList<Toy> toys;
	ToyMenu toyMenu;
	Scanner input;

	public ToyManager() throws IOException {
		input = new Scanner(System.in);
		toys = new ArrayList<Toy>();
		toyMenu = new ToyMenu();
		loadToyData();
		launchToyApp();
//		printAllToys(toys); // Printing to console for testing.
		
		// THE COMMENTED CODE BELOW WILL BE USED AS A REFERENCE.
		/*
		 * Toy figure1 = new Figure('A'); // implementing polymorphism
		 * toys.add(figure1);
		 * 
		 * Toy animal1 = new Animal("Wood"); toys.add(animal1);
		 * 
		 * // Animal newAnimal = (Animal) animal1; // Cast Toy object to Animal object
		 * 
		 * if (toys.get(1) instanceof Figure) { // Checking for various instances of Toy
		 * types Figure currFigure = (Figure) toys.get(1); // Casting each instance to
		 * the appropriate Toy type System.out.println("Type Figure"); } else { Animal
		 * currAnimal = (Animal) toys.get(1); System.out.println("Type Animal"); }
		 */
	}

	// <------------PRELIMINARY METHODS--------------->
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
		String ageAppropriate = splittedToyLine[5].trim();

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
				removeToy();
				break;
			case 4:
				saveExit(); // Saves and exits the program.
				flag = false;
			}
		}
	}
	
	
	// Method to search and purchase toys
	public void searchToy() {
		// Prompt user for search term
		String searchQuery = toyMenu.askSearchQuery();
		
		// Temporary List to hold search results
		ArrayList<Toy> searchResults = new ArrayList<>();
		
		// Search through toys List
		for (Toy toy: toys) {
			if (toy.getName().equalsIgnoreCase(searchQuery) ||
					toy.getName().toLowerCase().contains(searchQuery.toLowerCase()) ||
					toy.getBrand().toLowerCase().contains(searchQuery.toLowerCase()) ||
					searchResults.add(toy);
			}
	}
	
	
	
	public void removeToy() {
		// Receiving serial number.
		String toySerialStr = toyMenu.askSerialNum();

		// Looping through the list
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

			// Confirmation
			char ans = toyMenu.askToConfirm();
			if (ans == 'y') {
				toys.remove(removeThisToy);
				toyMenu.showRemoveMsg();
			}

			// Displays item not found
		} else {
			toyMenu.showNotFoundMsg();
		}

		// Returning to main menu.
		toyMenu.pressEnter();
	}

	// Saves and prints to file
	private void saveExit() throws IOException {
		File toyFile = new File(FILE_PATH);
		PrintWriter pw = new PrintWriter(toyFile);

		for (Toy t : toys) {
			pw.println(t.format()); // The format method is called from the player class.
		}
		pw.close();
	}

	// <----------------MENU METHODS------------------>
	// All methods that corresponds to the menu
	// options goes here.

	// <------------MISCELLANEOUS METHODS------------->
	// All non-specific or extra methods can go here.

	// Printing to console
	public void printAllToys(ArrayList<Toy> toys) {
		for (Toy toy : toys) {
			System.out.println(toy.toString());
		}
	}
}
