package pt.iul.ista.poo.rogue.utils;

/**
 * @author POO2016
 * 
 * Cardinal directions
 *
 */
public enum Direction {
	LEFT, UP, RIGHT, DOWN;

	public Vector2D asVector() {
		if(this == UP)
			return new Vector2D(0,-1);
		if(this == DOWN )
			return new Vector2D(0,1);
		if(this == LEFT )
			return new Vector2D(-1,0);
		if(this == RIGHT )
			return new Vector2D(1,0);
		
		return new Vector2D(0,0);
	}
}
