package pt.iul.ista.poo.rogue.elementos;

import pt.iul.ista.poo.rogue.utils.Position;

public interface ElementoPropriedades {

	Position move();
	boolean canMove();
	boolean isHero();
	boolean isFight();
	boolean isDoor();
	boolean isPower();
	boolean isKey();
	boolean isObstaculo();
	boolean isFire();
	boolean isWall();
	
}
