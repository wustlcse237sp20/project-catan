package catan.Player;

public enum PlayerType {
	WHITE(0), RED(1), Blue(2), ORANGE(3);
		
		private int id;
		
		private PlayerType(int id) {
			this.id = id;
		}
		
		public int getId() {
			return id;
		}
}