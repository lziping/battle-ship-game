
package battleship;

import java.util.Random;

/**
 * this class represent the ocean of the game
 *
 */
public class Ocean {

	// instance variables

	/**
	 * Used to quickly determine which ship is in any given location
	 */
	private Ship[][] ships;

	/**
	 * The total number of shots fired by the user
	 */
	private int shotsFired;

	/**
	 * the number of times a shot hit a ship if user hit same part more than once,
	 * the hit still counted
	 */
	private int hitCount;

	/**
	 * the number of ship been sunk
	 */
	private int shipsSunk;

	/*
	 * to store location that have been hit.
	 */
	private boolean[][] hitLocation = new boolean[10][10];


	public static int oceanRow;
	public static int oceanCol;

	/**
	 * create an empty ocean, and initializes any game variable
	 */
	public Ocean(int oceanRow, int oceanCol) {
		// initializing game variable
		this.hitCount = 0;
		this.shipsSunk = 0;
		this.shotsFired = 0;
		this.oceanRow = oceanRow;
		this.oceanCol = oceanCol;
		ships = new Ship[oceanRow][oceanCol];

		// iterate the row, r for row
		for (int r = 0; r < oceanRow; r++) {

			// iterate the column,c for column, to create empty spot
			for (int c = 0; c < oceanCol; c++) {
				// create the empty sea
				Ship empty = new EmptySea();
				// fill the location for empty
				empty.placeShipAt(r, c, true, this);
			}
		}
	}

	int[] getShipsAmount(){
		int totalCell = this.oceanRow * this.oceanCol;

		int numberOfSet = (int) totalCell / 100;

		int[] shipAmount = new int[]{1 * numberOfSet,2*numberOfSet,3*numberOfSet,4*numberOfSet};

		return shipAmount;
	}

	/**
	 * Place all ten ship randomly on the ocean Will place the ship in the order of:
	 * Battleship, Cruiser, Destroyer and lastly submarine
	 */
	void placeAllShipsRandomly() {
		// declare and import random class
		Random rand = new Random();

		// set the random value of row column and horizontal for battleship
		// also generate the initial location for the first ship to place
		int row = rand.nextInt(oceanRow);
		int column = rand.nextInt(oceanCol);
		boolean horizontal = rand.nextBoolean();

		int[] shipAmount = this.getShipsAmount();
		int battleShipAmount = shipAmount[0];
		int cruiserAmount = shipAmount[1];
		int DestroyerAmount = shipAmount[2];
		int submarineAmount = shipAmount[3];

		/**
		 * placing battleship randomly in the ocean while i is less than number of
		 * battleship, place another battleship
		 */
		for (int i = 0; i < battleShipAmount; i++) {
			// create battle ship
			Ship battleship = new Battleship();

			// if the initial location was not ok to place, generate a new location
			while (battleship.okToPlaceShipAt(row, column, horizontal, this) == false) {
				row = rand.nextInt(oceanRow);
				column = rand.nextInt(oceanCol);
				horizontal = rand.nextBoolean();
			}
			// place the battle ship
			battleship.placeShipAt(row, column, horizontal, this);
		}

		// while this type of ship placed is less than the amount it should be
		for (int i = 0; i < cruiserAmount; i++) {
			// create cruiser
			Ship cruiser = new Cruiser();

			/**
			 * if the location was not ok to place, generate new location it will always
			 * enter the loop at least one time to generate the new location since the
			 * variables still hold the location for ship previously placed so will be false
			 */
			while (cruiser.okToPlaceShipAt(row, column, horizontal, this) == false) {
				// reset the random value of row column and horizontal for cruiser
				row = rand.nextInt(oceanRow);
				column = rand.nextInt(oceanCol);
				horizontal = rand.nextBoolean();
			}
			cruiser.placeShipAt(row, column, horizontal, this);
		}

		// while destroyer placed is less than the amount it should be
		for (int i = 0; i < DestroyerAmount; i++) {
			// create destroyer
			Ship destroyer = new Destroyer();

			// if the location was not ok to place, generate new location
			// it will always enter the loop at least one time to generate the new location
			// since the variables still hold the location for ship previously placed so
			// will be false
			while (destroyer.okToPlaceShipAt(row, column, horizontal, this) == false) {
				// reset the random value of row column and horizontal for cruiser
				row = rand.nextInt(oceanRow);
				column = rand.nextInt(oceanCol);
				horizontal = rand.nextBoolean();
			}

			destroyer.placeShipAt(row, column, horizontal, this);
		}

		// while destroyer placed is less than the amount it should be
		for (int i = 0; i < submarineAmount; i++) {

			// create submarine
			Ship submarine = new Submarine();

			// if the location was not ok to place, generate new location
			// it will always enter the loop at least one time to generate the new location
			// since the variables still hold the location for ship previously placed so
			// will be false
			while (submarine.okToPlaceShipAt(row, column, horizontal, this) == false) {
				// reset the random value of row column and horizontal for cruiser
				row = rand.nextInt(oceanRow);
				column = rand.nextInt(oceanCol);
				horizontal = rand.nextBoolean();
			}
			submarine.placeShipAt(row, column, horizontal, this);
		}
	}

	/**
	 * determine if location contains a ship
	 * 
	 * @param row    of the ocean location
	 * @param column of the ocean location
	 * @return true if location contains a ship, false if not
	 */
	boolean isOccupied(int row, int column) {

		// return false for not occupied if the location is empty
		if (this.getShipArray()[row][column].getShipType() == "empty") {
			return false;
		}
		// return true for occupied for type beside empty of the location
		else {
			return true;
		}

	}

	/**
	 * determine if a ship is at the given location update the number of shots been
	 * fired and number of hits
	 * 
	 * @param row    of the location
	 * @param column of the location
	 * @return true if location contain a ship, false otherwise.
	 */
	boolean shootAt(int row, int column) {

		// increment the number of shot fired
		this.shotsFired++;
		// store the location been hit into the hitLocation array
		hitLocation[row][column] = true;

		// if the location is occupied
		if (this.isOccupied(row, column) == true) {
			// if the ship at location was not sunk
			if (this.ships[row][column].isSunk() == false) {
				// increment the number of hit since its occupied location
				this.hitCount++;

				// shoot the location
				ships[row][column].shootAt(row, column);

				return true;
			}
		}

		return false;

	}

	/**
	 * this show the shots been fired
	 * 
	 * @return the number of shots fired
	 */
	int getShotsFired() {
		return shotsFired;
	}

	/**
	 * this show the shots been hit
	 * 
	 * @return the number of shots hit
	 */
	int getHitCount() {
		return this.hitCount;
	}

	/**
	 * show the ship sunk
	 * 
	 * @return the number of ship sunk
	 */
	int getShipsSunk() {
		// call this method to compute amount of ship sunk
		findShipSunk();
		return this.shipsSunk;
	}

	/**
	 * determine if the game is over
	 * 
	 * @return true if game is over, false if not
	 */
	boolean isGameOver() {

		// return true if 10 ship are sunk for game over
		if (this.getShipsSunk() >= 10) {
			return true;
		}

		else {
			return false;
		}

	}

	/**
	 * show the ship
	 * 
	 * @return the array of ships
	 */
	Ship[][] getShipArray() {
		return this.ships;
	}

	/**
	 * this print the ocean with row and column number a 10x10 ocean
	 */
	void print() {
		// print the column number
		System.out.print("  ");
		for(int i = 0 ; i < oceanCol; i++) {
			if (i < 10){
				System.out.print("  " + i);
			} else{
				System.out.print(" " + i);
			}
		}
		System.out.println("");
		/*
		 * iterate the array of ship and print the ocean
		 */
		for (int r = 0; r < oceanRow; r++) {
			// print the row number
			if(r < 10){
			System.out.print(r + " ");
			} else{
				System.out.print(r );
			}
			// iterate the column
			for (int c = 0; c < oceanCol; c++) {

				// if the location is hit print the the location with mark
//				if (this.hitLocation[r][c] == true) {

					System.out.print("  " + ships[r][c]);

//				} else {
//					// print "." to indicate a location that is never fired
//					System.out.print(" " + "."); // ships[r][c]);
//				}

			}
			// print new line
			System.out.println("");

		}
	}

	/**
	 * This will compute the amount of ship sunk base on the amount of "s" it will
	 * find out the s for each type of ship and find out the amount sunk. For
	 * example: 4 "s" of Battleship = 1 sunk , 3 "s" of Cruiser = 1 sunk 2 "s" of
	 * Destroyer = 1 sunk.
	 */
	private void findShipSunk() {
		// variable to store "s" of each type of ships
		int battleshipSunk = 0;
		int cruiserSunk = 0;
		int destroyerSunk = 0;
		int submarineSunk = 0;
		// iterate over the ocean
		for (int r = 0; r < oceanRow; r++) {
			for (int c = 0; c < oceanCol; c++) {
				// if the location is sunk, add 1 to its type sunk
				if (ships[r][c].isSunk() == true) {
					// increment the type "s" was found
					if (ships[r][c].getShipType() == "battleship") {
						battleshipSunk++;
					} else if (ships[r][c].getShipType() == "cruiser") {
						cruiserSunk++;
					} else if (ships[r][c].getShipType() == "destroyer") {
						destroyerSunk++;
					} else if (ships[r][c].getShipType() == "submarine") {
						submarineSunk++;
					}
				}
			}
		}
		// battleship was length of 4, so 4 "s" = 1 battleship sunk
		battleshipSunk = battleshipSunk / 4;
		// cruiser was length of 4, so 4 "s" = 1 battleship sunk
		cruiserSunk = cruiserSunk / 3;
		// destroyer was length of 4, so 4 "s" = 1 battleship sunk
		destroyerSunk = destroyerSunk / 2;

		shipsSunk = battleshipSunk + cruiserSunk + destroyerSunk + submarineSunk;

	}
}
