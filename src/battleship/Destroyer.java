package battleship;

/**
 * This subclass represent a Ship "Destroyer" with length of 2
 *
 *  */
public class Destroyer extends Ship {

	/**
	 * type of the ship destroyer
	 */
	static final String TYPE = "destroyer";

	/*
	 * length of the destroyer
	 */
	static final int LENGTH = 2;

	// constructor
	
	/**
	 * call the superclass constructor to create a destroyer
	 */
	public Destroyer() {
		super(LENGTH);
	}

	/**
	 * return the ship type destroyer
	 */
	@Override
	public String getShipType() {
		return TYPE;
	}
}
