package battleship;

/**
 * This subclass represent a ship "Cruiser" with length of 3
 *
 */
public class Cruiser extends Ship {

	/**
	 * type of the ship cruiser
	 */
	static final String TYPE = "cruiser";

	/*
	 * length of the cruise
	 */
	static final int LENGTH = 3;

	// constructor

	/**
	 * call the superclass constructor to create a cruiser
	 */
	public Cruiser() {
		super(LENGTH);
	}

	/**
	 * return the ship type cruiser
	 */
	@Override
	public String getShipType() {
		return TYPE;
	}
}
