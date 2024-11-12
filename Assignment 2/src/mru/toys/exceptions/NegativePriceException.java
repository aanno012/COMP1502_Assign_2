package mru.toys.exceptions;

public class NegativePriceException extends Exception {
	/**
	 * This class handles all error messages if the user inputs a negative prices.
	 */

	/**
	 * Constructor
	 * 
	 * @param error the error message
	 */
	public NegativePriceException(String error) {
		super(error);
	}
}
