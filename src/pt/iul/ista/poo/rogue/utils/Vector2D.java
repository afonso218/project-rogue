package pt.iul.ista.poo.rogue.utils;

public class Vector2D {

	private int x;
	private int y;
	
	public Vector2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Vector2D plus(Vector2D other) {
		return new Vector2D(x + other.getX(), y + other.getY());
	}

	public Vector2D minus(Vector2D other) {
		return new Vector2D(x - other.getX(), y - other.getY());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2D other = (Vector2D) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}
