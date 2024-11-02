package mru.toys.view;

import java.util.Scanner;

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

	public int showMainMenu() {
		System.out.println("How We May Help You? \n");
		System.out.println("(1)  Search Inventory and Purchase Toy");
		System.out.println("(2)  Add New Toy");
		System.out.println("(3)  Remove Toy");
		System.out.println("(4)  Save & Exit\n");

		boolean flag = true;
		int option = 0;
		while (flag) {
			System.out.print("Enter Option: ");

			if (input.hasNextInt()) {
				option = input.nextInt(); // Takes in the integer value.
				input.nextLine();
				System.out.println();

				// Check if the input is part of the listed option
				if (option >= 1 && option <= 4) {
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

	// Requests serial number from the user.
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

	// Request for confirmation to remove toy from database.
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

	// Returns to main menu after the "Enter" key is pressed.
	public void pressEnter() {
		System.out.println();
		System.out.println("Press \"Enter\" to continue...");
		input.nextLine();
	}

	// Displays a message when toy is removed.
	public void showRemoveMsg() {
		System.out.println();
		System.out.println("Item Removed!");
	}

	// Displays a message when toy is found.
	public void showFoundMsg() {
		System.out.println();
		System.out.println("This Item Found: ");
	}

	// Displays a message when toy is not found.
	public void showNotFoundMsg() {
		System.out.println();
		System.out.println("No Item Found!");
	}

}
