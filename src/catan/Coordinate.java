package catan;

public class Coordinate {
	
	String tileName;
	int index;

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
