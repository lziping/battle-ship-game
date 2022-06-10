
package battleship;

/**
 * This abstract class represent all the ships
 *
 */

public abstract class Ship {

	// instance variable

	/**
	 * The row that contains the bow (front part of the ship)
	 */
	private int bowRow;

	/**
	 * The column that contains the bow (front part of the ship)
	 */
	private int bowColumn;

	/**
	 * length of the ship
	 */
	private int length;

	/**
	 * A boolean that represents whether the ship is going to be placed horizontally
	 * or vertically
	 */
	private boolean horizontal;

	/**
	 * An array of 4 booleans that indicate whether that part of the ship has been
	 * hit or not
	 */
	private boolean[] hit;

	// getter and setter

	/**
	 * @return the bowRow
	 */
	public int getBowRow() {
		return bowRow;
	}

	/**
	 * @param bowRow the bowRow to set
	 */
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}

	/**
	 * @return the bowColumn
	 */
	public int getBowColumn() {
		return bowColumn;
	}

	/**
	 * @param bowColumn the bowColumn to set
	 */
	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @return the horizontal
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * @param horizontal the horizontal to set
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * @return the hit
	 */
	public boolean[] getHit() {
		return hit;
	}

	/**
	 * This constructor sets the length property of the particular ship and
	 * initializes the hit array
	 * 
	 * @param length of the ship
	 */
	public Ship(int length) {

		// set the length
		this.length = length;

		// set the hit array
		this.hit = new boolean[4];
	}

	// abstract methods

	/**
	 * Every specific type of Ship has to override and implement this method and
	 * return the corresponding ship type.
	 * 
	 * @return the type of the ship
	 */
	public abstract String getShipType();

	// other method

	/**
	 * Determine if the its to place the ship on the location based on the given
	 * row, column, and orientation,
	 * 
	 * @param row        to attempt to place
	 * @param colmun     to attempt to place
	 * @param horizontal of the ship
	 * @param ocean      to place the ship
	 * @return true if it is okay to put a ship of this length ,false otherwise
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {

		// create the ship array
		Ship[][] shipArray = ocean.getShipArray();

		// check if the ship will go beyond the ocean
		if (row > Ocean.oceanRow || column > Ocean.oceanCol) {
			return false;
		}

		// if the ship is horizontal
		if (horizontal == true) {

			// if the length of ship go beyond the ocean
			if (column + 1 < this.length) {
				return false;
			}

			// check surrounding if there are other ship around
			for (int i = 0; i < this.length; i++) {
				// if the surrounding is not empty, mean there is other ship around
				if (shipArray[row][column - i].getShipType() != "empty") {
					return false;
				}

			}

			// check horizontal adjacent
			// 1. check left
			if ((column + 1) > this.length) {
				if (shipArray[row][column - this.length].getShipType() != "empty") {
					return false;
				}

			}
			// 2. check right
			if (column < Ocean.oceanCol -1) {
				if (shipArray[row][column + 1].getShipType() != "empty") {
					return false;
				}

			}

			// check vertical adjacent
			// 1. check top
			if (row != 0) {
				for (int i = 0; i < this.length; i++) {
					if (shipArray[row - 1][column - i].getShipType() != "empty") {
						return false;
					}
				}
			}
			// 2. check bottom
			if (row < Ocean.oceanRow -1) {
				for (int i = 0; i < this.length; i++) {
					if (shipArray[row + 1][column - i].getShipType() != "empty") {
						return false;
					}
				}
			}
			// check diagonal adjacent
			// 1.left top corner
			if (row > 0 && (column - this.length) >= 0) {
				if (shipArray[row - 1][column - this.length].getShipType() != "empty") {
					return false;
				}
			}

			// 2. right top corner
			if (row > 0 && column < Ocean.oceanCol -1) {
				if (shipArray[row - 1][column + 1].getShipType() != "empty") {
					return false;
				}
			}

			// 3. left bottom corner
			if (row < Ocean.oceanRow -1 && (column - this.length) >= 0) {
				if (shipArray[row + 1][column - this.length].getShipType() != "empty") {
					return false;
				}
			}
			// 4. right bottom corner
			if (row < Ocean.oceanRow -1 && column < Ocean.oceanCol -1) {
				if (shipArray[row + 1][column + 1].getShipType() != "empty") {
					return false;
				}
			}
			// }
		} else if (horizontal == false) {
			// check if the length of the ship exceeds the ocean
			if (row + 1 < this.length) {
				return false;
			} else {
				// check ship body
				for (int i = 0; i < this.length; i++) {
					// if its not empty, return false.
					if (shipArray[row - i][column].getShipType() != "empty") {
						return false;
					}
				}

				// check vertical adjacent
				// 1. check top
				if ((row + 1) > this.length && shipArray[row - this.length][column].getShipType() != "empty") {
					return false;
				}
				// 2. check bottom
				if (row < Ocean.oceanRow -1 && shipArray[row + 1][column].getShipType() != "empty") {
					return false;
				}

				// check horizontal adjacent
				// 1. check left
				if (column != 0) {
					for (int i = 0; i < this.length; i++) {
						if (shipArray[row - i][column - 1].getShipType() != "empty") {
							return false;
						}
					}
				}
				// 2. check right
				if (column < Ocean.oceanCol -1) {
					for (int i = 0; i < this.length; i++) {
						if (shipArray[row - i][column + 1].getShipType() != "empty") {
							return false;
						}
					}
				}
				// check diagonal adjacent
				// 1.left top corner
				if (column > 0 && (row - this.length) >= 0) {
					if (shipArray[row - this.length][column - 1].getShipType() != "empty") {
						return false;
					}
				}

				// 2. right top corner
				if ((row - this.length) >= 0 && column < Ocean.oceanCol -1) {
					if (shipArray[row - this.length][column + 1].getShipType() != "empty") {
						return false;
					}
				}

				// 3. left bottom corner
				if (column > 0 && row < Ocean.oceanRow -1) {
					if (shipArray[row + 1][column - 1].getShipType() != "empty") {
						return false;
					}
				}
				// 4. right bottom corner
				if (row < Ocean.oceanRow -1 && column < Ocean.oceanCol -1) {
					if (shipArray[row + 1][column + 1].getShipType() != "empty") {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Deploy the ship in the ocean
	 * 
	 * @param row        to place ship
	 * @param colmun     to place ship
	 * @param horizontal of the ship
	 * @param ocean      to place ship
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// set the value to the ship
		this.setBowColumn(column);
		this.setBowRow(row);
		this.setHorizontal(horizontal);

		// System.out.println(row+":"+column+":"+horizontal);

		// if ship is horizontal
		if (this.isHorizontal() == true) {
			// check if ship will go beyond the ocean

			for (int i = column; i > column - this.getLength(); i--) {

				ocean.getShipArray()[row][i] = this;
				// System.out.print(ocean.getShipArray()[row][i]);
			}
		}
		// if ship is not horizontal
		else {
			for (int i = row; i > row - this.getLength(); i--) {
				ocean.getShipArray()[i][column] = this;
				// System.out.print(ocean.getShipArray()[row][i]);
			}
		}

	}

	/**
	 * Shoot at the given location base on the given row and column of the ocean if
	 * part of the ship occupies the given location and ship is not sunk, hit the
	 * ship and mark the part of ship as hit
	 * 
	 * @param row    of the location
	 * @param column of the location
	 * @return true if ship is hit, false otherwise
	 */
	boolean shootAt(int row, int column) {

		if (!isSunk()) {
			if (horizontal && row == this.bowRow && column <= this.bowColumn && column >= (this.bowColumn - length)) {
				hit[this.bowColumn - column] = true;
				return true;
			} else if (!horizontal && column == this.bowColumn && row <= this.bowRow && row >= (this.bowRow - length)) {
				hit[this.bowRow - row] = true;
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * determine if the ship is sunk
	 * 
	 * @return true if ship is sunk, false otherwise
	 */
	boolean isSunk() {
		/**
		 * variable to count the hit
		 */
		int countHit = 0;
		// iterate over the array of hit
		// if hit then add 1 to countHit
		for (int i = 0; i < this.length; i++) {
			// if hit is true increment hit by 1
			if (this.hit[i] == true) {
				countHit++;
			}
		}

		// if the count is equal to length of ship
		// return true for ship is sunk
		if (countHit == this.length) {
			return true;
		} else {
			// ship not sunk
			return false;
		}

	}

	/**
	 * Return a single character string use in the ocean's print method return "S"
	 * if ship have been sunk and "X" if it has not been sunk
	 */
	@Override
	public String toString() {

		// if ship is sunk return s to mark sunk
		if (this.isSunk() == true) {
			return "s";
		} // return "x" to mark hit but not sunk
		else {
			return "x";
		}

	}

}
