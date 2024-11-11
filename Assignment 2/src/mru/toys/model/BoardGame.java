package mru.toys.model;

public class BoardGame extends Toy {

	/**
	 * The class is a subclass of the Toy abstract class.
	 */

	private int minNumOfPlayers; // Minimum number of players
	private int maxNumOfPlayers; // Maximum number of players
	private String designers; // Name of designers

	/**
	 * Standard constructor for BoardGame toy type
	 * 
	 * @param serialNumber    10-digit unique number.
	 * @param name            name of the item
	 * @param brand           brand name
	 * @param price           cost of toy
	 * @param availableCount  number of copies in stock
	 * @param ageAppropriate  minimum age to use toy
	 * @param minNumOfPlayers minimum number of players to play the game
	 * @param maxNumOfPlayers maximum number of players to play the game
	 * @param designers       name of designers
	 */
	public BoardGame(String serialNumber, String name, String brand, double price, int availableCount, int ageAppropriate,
			int minNumOfPlayers, int maxNumOfPlayers, String designers) {
		super(serialNumber, name, brand, price, availableCount, ageAppropriate);
		this.minNumOfPlayers = minNumOfPlayers;
		this.maxNumOfPlayers = maxNumOfPlayers;
		this.designers = designers;
	}

	/**
	 * Copy constructor for BoardGame toy type
	 * 
	 * @param boardGame board game object
	 */
	public BoardGame(BoardGame boardGame) {
		super(boardGame.getSerialNumber(), boardGame.getName(), boardGame.getBrand(), boardGame.getPrice(), boardGame.getAvailableCount(),
				boardGame.getAgeAppropriate());
		this.minNumOfPlayers = boardGame.minNumOfPlayers;
		this.maxNumOfPlayers = boardGame.maxNumOfPlayers;
		this.designers = boardGame.designers;
	}

	// Getters & Setters
	public int getMimNumOfPlayers() {
		return minNumOfPlayers;
	}

	public void setMimNumOfPlayers(int mimNumOfPlayers) {
		this.minNumOfPlayers = mimNumOfPlayers;
	}

	public int getMaxNumOfPlayers() {
		return maxNumOfPlayers;
	}

	public void setMaxNumOfPlayers(int maxNumOfPlayers) {
		this.maxNumOfPlayers = maxNumOfPlayers;
	}

	public String getDesigners() {
		return designers;
	}

	public void setDesigners(String designers) {
		this.designers = designers;
	}

	/**
	 * toString method
	 */
	public String toString() {
		return "Category: BoardGame, " + super.toString() + ", Number of Players: " + minNumOfPlayers + "-" + maxNumOfPlayers + ", Designer(s): "
				+ designers;
	}

	/**
	 * Format for the board game toy information when writing to file
	 * 
	 * @return information format
	 */
	public String format() {
		return super.format() + ";" + minNumOfPlayers + "-" + maxNumOfPlayers + ";" + designers;
	}

}
