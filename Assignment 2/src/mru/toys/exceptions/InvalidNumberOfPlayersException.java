package mru.toys.exceptions;

public class InvalidNumberOfPlayersException extends Exception {

	/**
	 * This class handles error messages if the minimum player exceeds the number of
	 * player for a board game.
	 */

	/**
	 * Constructor
	 * 
	 * @param error the error message
	 */
	public InvalidNumberOfPlayersException(String error) {
		super(error);
	}
}
