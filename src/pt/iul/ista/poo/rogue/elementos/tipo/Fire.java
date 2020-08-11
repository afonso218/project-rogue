package pt.iul.ista.poo.rogue.elementos.tipo;

import pt.iul.ista.poo.rogue.elementos.ElementoLuta;
import pt.iul.ista.poo.rogue.utils.Direction;
import pt.iul.ista.poo.rogue.utils.Position;

public class Fire extends ElementoLuta{

	private Direction direction;
	
	public Fire(Position position, Direction direction) {
		super("Fire", position, 0, 2);
		this.direction = direction;
	}
	
	@Override
	public boolean isObstaculo() {
		return false;
	}
	
	@Override
	public boolean canMove() {
		return true;
	}
	
	@Override
	public boolean isFire() {
		return true;
	}
	
	@Override
	public Position move() {
		return getPosition().plus(direction.asVector());
	}
	
	@Override
	public boolean isDead() {
		return false;
	}
}
