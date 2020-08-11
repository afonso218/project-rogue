package pt.iul.ista.poo.rogue.elementos;

import pt.iul.ista.poo.rogue.utils.Position;

public abstract class Color extends Elemento{

	public Color(String name, Position position){
		super(name, position);
	}
	
	@Override
	public boolean isObstaculo() {
		return false;
	}

	@Override
	public boolean canMove() {
		return false;
	}
	
}
