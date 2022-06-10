package battleship;

/**
 * This subclass represent a the empty ocean
 *
 *
 */
public class EmptySea extends Ship {

	/**
	 * call the constructor in superclass with para length of 1
	 */
	public EmptySea() {
		super(1);
	}

	// methods

	/**
	 * this return false to indicate nothing have been hit
	 */
	@Override
	boolean shootAt(int row, int column) {
		return false;

	}

	/**
	 * return false to show not sunk
	 */
	@Override
	boolean isSunk() {
		return false;

	}

	/**
	 * return "-" to indicate the a shot have fire at the location but nothing have
	 * been hit
	 */
	@Override
	public String toString() {
		return "-";

	}

	/*
	 * this will return the type empty
	 */
	@Override
	public String getShipType() {
		return "empty";
	}

}
