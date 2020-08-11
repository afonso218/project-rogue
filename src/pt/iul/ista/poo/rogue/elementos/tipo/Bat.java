package pt.iul.ista.poo.rogue.elementos.tipo;

import pt.iul.ista.poo.rogue.elementos.ElementoLuta;
import pt.iul.ista.poo.rogue.utils.Position;
import pt.iul.ista.poo.rogue.utils.Tools;

public class Bat extends ElementoLuta {

	public Bat(Position position) {
		super("Bat", position, 5, 1);
	}
	
	public Position move() {
		Hero hero = Hero.getInstance();
		if(Tools.distance(hero.getPosition(), getPosition()) > 5){
			return Tools.random(getPosition());
		}else{
			return Tools.farPosition(getPosition(), hero.getPosition());
		}
	}
	
	public boolean isObstaculo() {
		return true;
	}
	
	public boolean canMove() {
		return true;
	}
	
}


