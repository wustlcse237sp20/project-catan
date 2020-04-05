package Catan;


public enum StructureType {
	
	ROAD(0), SETTLEMENT(1), CITY(2);
	
	private int id;
	
	private StructureType(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	

}
