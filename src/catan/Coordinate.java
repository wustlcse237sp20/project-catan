package catan;

public class Coordinate {
	
	String tileName;
	int index;

	/**
	 * coordinate object allows for separation of reading input and building structures
	 * @param tileName is the tile location
	 * @param index the index within that tile
	 */
	public Coordinate(String tileName, int index) {
		this.tileName = tileName;
		this.index = index;
	}
	
	public String getTileName() {
		return tileName;
	}
	
	public int getIndex() {
		return index;
	}

}
