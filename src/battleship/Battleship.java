
package battleship;

/**
 * This subclass represent a ship "battle ship" with length of 4
 *
 *
 */
public class Battleship extends Ship {

	/**
	 * type of the ship battle ship
	 */
	static final String TYPE = "battleship";

	/*
	 * length of the battleship
	 */
	static final int LENGTH = 4;

	// constructor
	
	/**
	 * call the superclass constructor to create a battleship
	 */
	public Battleship() {
		super(LENGTH);
	}

	/**
	 * return the type battle ship
	 */
	@Override
	public String getShipType() {
		return TYPE;
	}

}
