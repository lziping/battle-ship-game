package battleship;

/**
 * This subclass represent a ship "Submarine" with length of 1
 *
 *
 */
public class Submarine extends Ship {		
	
	/*
	 * type of the ship - submarine
	 */
	static final String TYPE = "submarine";
	
	
	/*
	 * length of the submarine
	 */
	static final int LENGTH = 1;
	
	
	//constructor
	
	/**
	 * call the superclass constructor to create a submarine
	 */
	public Submarine() {
		super(LENGTH);
	}
	
	
	/**
	 * return the ship type submarine
	 */
	@Override
	public String getShipType() {
		return TYPE;
	}
}
