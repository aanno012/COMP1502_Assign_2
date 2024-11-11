package mru.toys.view;

import java.util.ArrayList;
import java.util.Scanner;

import mru.toys.model.Toy;

public class ToyMenu {

	/**
	 * This class will be used to show the menus and sub menus to the user. It also
	 * prompts the user for the inputs and validates them
	 */
	Scanner input;

	/**
	 * Constructor
	 */
	public ToyMenu() {
		input = new Scanner(System.in); // For user inputs
	}

	/**
	 * Displays a welcome banner on startup.
	 */
	public void showWelcomeMsg() {
		System.out.println();
		System.out.println("****************************************************");
		System.out.println("*          WELCOME TO TOY STORE COMPANY!           *");
		System.out.println("****************************************************");
		System.out.println();
	}

	/**
	 * Displays the main menu options to the user.
	 * 
	 * @return the selected menu number.
	 */
	public int showMainMenu() {
		System.out.println("How We May Help You? \n");
		System.out.println("(1)  Search Inventory and Purchase Toy");
		System.out.println("(2)  Add New Toy");
		System.out.println("(3)  Remove Toy");
		System.out.println("(4)  Make a Gift Suggestion");
		System.out.println("(5)  Save & Exit\n");

		boolean flag = true;
		int option = 0;
		while (flag) {
			System.out.print("Enter Option: ");

			if (input.hasNextInt()) {
				option = input.nextInt(); // Takes in the integer value.
				input.nextLine();
				System.out.println();

				// Check if the input is part of the listed option
				if (option >= 1 && option <= 5) {
					return option; // returns any of the correct integer options

				} else { // Displays error message for out of range values/options.
					System.out.println("This Is Not a valid option! Try again. \n");
				}

			} else { // Displays error message if option is not an integer.
				System.out.println("This is Not an Integer Number! Try again.\n");
				input.next();
			}
		}
		return option;
	}

	// Prompt;s user for search query
	public String askSearchQuery() {
		System.out.println("Enter search term (serial number, name, or type):");
		return input.nextLine().trim();
	}

	// Display search results to the user
	public void DisplaySearchResults(ArrayList<Toy> searchResults) {
		System.out.println("Search Results:");
		for (int i = 0; i < searchResults.size(); i++) {
			System.out.println("(" + (i + 1) + ")" + searchResults.get(i));
		}
	}

	// Ask user to select a toy from the search result for purchase
	public int askToySelection(int resultSize) {
		System.out.print("Enter the number of the toy you want to purchase (or -1 to cancel): ");
		int selection = input.nextInt();
		input.nextLine(); // Consume newLine
		if (selection > 0 && selection <= resultSize) {
			return selection - 1; // Adjust for zero-based index

		}
		return -1; // Indicates cancellation or invalid input
	}

	// Show success message for a successful purchase
	public void ShowPurchaseSuccess(Toy toy) {
		System.out.println("Sorry, this item is out of stock.");
	}

	// Show a message if no items match the search
	public void ShowNoResultsMessage() {
		System.out.println("No items match your search criteria.");
	}

	/**
	 * Requests serial number from the user.
	 * 
	 * @return The serial number of the toy
	 */
	public String askSerialNum() {
		boolean flag = true;
		String strSerialNum = null;
		while (flag) {
			System.out.print("Enter Serial Number: ");
			strSerialNum = input.nextLine().trim();

			if (strSerialNum.length() == 10) {

				boolean allSerialDigits = true;
				for (char sn : strSerialNum.toCharArray()) {
					if (!Character.isDigit(sn)) {
						System.out.println("Error! The serial number should contain only numerical digits. No spaces allowed.");
						System.out.println();
						allSerialDigits = false;
						break;
					}
				}

				if (allSerialDigits) {
					return strSerialNum;
				}
			} else {
				System.out.println("Error! The serial number must contain 10 digits.");
				System.out.println();
			}
		}
		return strSerialNum;
	}

	/**
	 * Request for confirmation to remove toy from database.
	 * 
	 * @return y/n confirmation from user.
	 */
	public char askToConfirm() {
		boolean flag = true;
		char confirmAns = 0;
		while (flag) {
			System.out.print("Do  you want to remove it (Y/N)? ");
			confirmAns = input.nextLine().trim().toLowerCase().charAt(0);

			if (confirmAns == 'y' || confirmAns == 'n') {
				return confirmAns;
			} else {
				System.out.println("Invalid choice! Please enter \"y\" for Yes or \"n\" for No");
				System.out.println();
			}
		}
		return confirmAns;
	}

	/**
	 * Prompts the user to enter an age.
	 * 
	 * @return age from the user.
	 */
	public String askAge() {
		boolean flag = true;
		String age = null;
		while (flag) {
			System.out.print("Enter Age: ");
			age = input.nextLine().trim(); // Takes in the value.

			for (char a : age.toCharArray()) {
				if (!Character.isDigit(a)) {
					System.out.println("Error! Only numerical values are accepted. ");
					System.out.println();
					break;
				}
			}

			if (age.length() > 0) {
				return age; // returns any age

			} else {
				return null;
			}
		}
		return age;
	}

	/**
	 * Asks the user for the type of toy.
	 * 
	 * @return the requested type from the user.
	 */
	public String askType() {
		boolean flag = true;
		String type = null;
		while (flag) {
			System.out.print("Enter Toy Type (e.g, figure, animal, puzzle, or board game): ");
			type = input.nextLine().trim().toLowerCase();

			// Checks the appropriate toy type.
			if (type.equals("figure") || type.equals("animal") || type.equals("puzzle") || type.equals("boardgame") || type.equals("board game")) {
				return type;

				// Checks if the user skips the question.
			} else if (type.length() == 0) {
				return null;

				// Displays error message if above conditions are not met.
			} else {
				System.out.println("Error! Invalid toy type or incorrect spelling. Try again.");
				System.out.println();
			}
		}
		return type;
	}

	/**
	 * Asks the user for the price range
	 * 
	 * @return the price range from the user.
	 */
	public String[] askPriceRange() {
		boolean flag = true;
		String priceRange = null;
		String[] priceArray = null;
		while (flag) {
			System.out.print("Enter Price Range (e.g, 10.00-19.99): ");
			priceRange = input.nextLine().trim();

			// Checks if user provided something.
			if (priceRange.length() > 0) {

				// Splitting the price range.
				for (char pr : priceRange.toCharArray()) {
					if (pr == '-') {
						priceArray = priceRange.split("-");
						return priceArray; // Returns the string array.
					}
				}

				// Verifying the range format.
				for (char pr : priceRange.toCharArray()) {
					if (pr != '-') {
						// Error for wrong format.
						System.out.println("Incorrect format! The correct format for the price range is: Min-Max");
						break;
					}
				}
				// Checks if the user skips the question.
			} else {
				return null;
			}
		}
		return priceArray;
	}

	/**
	 * Asks user if they want to purchase a gift by selecting the item number.
	 * 
	 * @return the selected item number
	 */
	public int askPurchaseGift() {
		boolean flag = true;
		int itemNum = 0;
		while (flag) {
			System.out.print("Enter item number to purchase: ");
			if (input.hasNextInt()) {
				itemNum = input.nextInt();
				input.nextLine();
				System.out.println();

				// Check if the input is part of the listed items
				if (itemNum >= 0) {
					return itemNum; // returns any number that matches the suggested toy number.
				} else {
					System.out.println("This Is Not a valid item! Try again. \n");
				}
			} else {
				System.out.println("This is Not an Integer Number! Try again.\n");
				input.next();
			}
		}
		return itemNum;
	}

	/**
	 * Displays a message when toy is removed.
	 */
	public void showRemoveMsg() {
		System.out.println();
		System.out.println("Item Removed!");
	}

	/**
	 * Displays a message when toy is found.
	 */
	public void showFoundMsg() {
		System.out.println();
		System.out.println("This Item Found: ");
	}

	/**
	 * Displays a message when toy is not found.
	 */
	public void showNotFoundMsg() {
		System.out.println();
		System.out.println("No Item(s) Found!");
	}

	/**
	 * Displays the optional details the user can provide.
	 */
	public void showProvideDtls() {
		System.out.println("Provide at least one of the following details (age, toy type, or price range): ");
		System.out.println();
	}

	/**
	 * Displays a message if toy details are not provided.
	 */
	public void showNoDtlsMsg() {
		System.out.println();
		System.out.println("No details were provided. Please try again.");
	}

	/**
	 * Displays a "out of stock" message.
	 */
	public void showOutofStkMsg() {
		System.out.println("The selected toy is currently out of stock.");
	}

	/**
	 * Displays a message about the successful transactions.
	 */
	public void showSuccessMsg() {
		System.out.println("The Transaction Successfully Terminated!");
	}

	/**
	 * Display a message if the toy does not exist.
	 */
	public void showNotExist() {
		System.out.println("The selected toy does not exist!");
		System.out.println();
	}

	/**
	 * Displays a label for the suggested toys.
	 */
	public void showSgtLabel() {
		System.out.println();
		System.out.println("Suggested toy item(s): ");
		System.out.println();
	}

	/**
	 * Allows user to return to main menu after the specified number from the
	 * suggested gift list.
	 * 
	 * @param menuIndex the specific number used for returning to the main menu.
	 */
	public void backToMainMsg(int menuIndex) {
		System.out.println("\t(" + menuIndex + ") " + "Back to Main Menu");
		System.out.println();
	}

	/**
	 * Displays saving message.
	 */
	public void showSaveMsg() {
		System.out.println("Saving Data into Database...");
		System.out.println();

	}

	/**
	 * Displays a "Thank You!" me message upon exit.
	 */
	public void showThankYouMsg() {
		System.out.println("****************************************************");
		System.out.println("*             THANKS FOR VISITING US!              *");
		System.out.println("****************************************************");
		System.out.println();
	}

	/**
	 * Returns to main menu after the "Enter" key is pressed.
	 */
	public void pressEnter() {
		System.out.println();
		System.out.println("Press \"Enter\" to continue...");
		input.nextLine();
	}

}
