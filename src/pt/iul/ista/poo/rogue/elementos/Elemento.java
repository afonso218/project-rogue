package pt.iul.ista.poo.rogue.elementos;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.utils.Position;


public abstract class Elemento implements ImageTile, ElementoPropriedades {

	private String nome;
	private Position position;

	public Elemento(String nome, Position position) {
		this.nome = nome;
		this.position = position;
	}

	public int getPriority(){
		return 0;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public String getName() {
		return nome;
	}

	public Position getPosition() {
		return position;
	}
	
	@Override
	public boolean isDoor(){
		return false;
	}
	
	@Override
	public boolean isPower() {
		return false;
	}
	
	@Override
	public boolean isKey() {
		return false;
	}

	@Override
	public boolean isFight() {
		return false;
	}
	
	@Override
	public boolean isHero() {
		return false;
	}
	
	@Override
	public boolean isFire() {
		return false;
	}
	
	@Override
	public boolean isWall() {
		return false;
	}
	
	@Override
	public Position move() {
		return null;
	}
	
	@Override
	public String toString() {
		return nome + position;
	}
	
}


