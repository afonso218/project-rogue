package pt.iul.ista.poo.rogue.elementos.tipo;

import pt.iul.ista.poo.rogue.elementos.Elemento;
import pt.iul.ista.poo.rogue.utils.Position;

public class Floor extends Elemento {

	public Floor(Position position) {
		super("Floor", position);
	}

	public boolean isObstaculo() {
		return false;
	}

	public boolean canMove() {
		return false;
	}
	
}	