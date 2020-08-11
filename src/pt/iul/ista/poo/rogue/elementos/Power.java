package pt.iul.ista.poo.rogue.elementos;

import pt.iul.ista.poo.rogue.utils.Position;

public abstract class Power extends Elemento{

	private int extraAttack;
	
	public Power(String nome, Position position) {
		super(nome, position);
		extraAttack = 0;
	}
	
	public Power(String nome, Position position, int extraAttack) {
		super(nome, position);
		this.extraAttack = extraAttack;
	}

	public int getPriority(){
		return 1;
	}
	
	@Override
	public boolean isObstaculo() {
		return false;
	}

	@Override
	public boolean canMove() {
		return false;
	}

	@Override
	public boolean isPower() {
		return true;
	}
	
	
	public int getExtraAttack() {
		return extraAttack;
	}
	
}
