package pt.iul.ista.poo.rogue.elementos.tipo;

import pt.iul.ista.poo.rogue.elementos.Elemento;
import pt.iul.ista.poo.rogue.utils.Position;

public class Wall extends Elemento {

	public Wall(Position position) {
		super("Wall", position);
	}

	@Override
	public boolean isObstaculo() {
		return true;
	}

	@Override
	public boolean canMove() {
		return false;
	}
	
	@Override
	public boolean isWall() {
		return true;
	}

}