package pt.iul.ista.poo.rogue.elementos.tipo;

import pt.iul.ista.poo.rogue.elementos.ElementoLuta;
import pt.iul.ista.poo.rogue.utils.Position;
import pt.iul.ista.poo.rogue.utils.Tools;

public class Skeleton extends ElementoLuta {

	public Skeleton(Position position) {
		super("Skeleton", position, 10, 2);
	}
	
	public Position move() {
		Hero hero = Hero.getInstance();
		if(Tools.distance(hero.getPosition(), getPosition()) > 4){
			return Tools.random(getPosition());
		}else{
			return Tools.nearPosition(getPosition(), hero.getPosition());
		}
	}

	@Override
	public boolean isObstaculo() {
		return true;
	}
	
	@Override
	public boolean canMove() {
		return true;
	}
	
}
