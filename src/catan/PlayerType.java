package catan;

import java.awt.Color;

public enum PlayerType {
	WHITE(0, StdDraw.WHITE), RED(1, StdDraw.RED), BLUE(2, StdDraw.BLUE), ORANGE(3, StdDraw.ORANGE);
		
		private int id;
		private Color color;
		
		private PlayerType(int id, Color color) {
			this.id = id;
			this.color = color;
		}
		
		public int getId() {
			return id;
		}
		
		public Color getColor() {
			return color;
		}
}