package pt.iul.ista.poo.rogue.elementos;

import pt.iul.ista.poo.rogue.utils.Position;


public abstract class ElementoLuta extends Elemento {

	private int maxHealth;
	private int health;
	private int attack;

	public ElementoLuta(String nome, Position position, int health, int attack) {
		super(nome, position);
		this.maxHealth = health;
		this.health = health;
		this.attack = attack;
	}
	
	public int getPriority(){
		return 2;
	}
	
	public int getAttack() {
		return attack;
	}

	public int getHealth() {
		return health;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void receiveDamage(int dmg){
		health -= dmg;
		if(health < 0){
			health = 0;
		}
	}
	
	public boolean isDead(){
		return health == 0;
	}
	
	@Override
	public boolean isFight(){
		return true;
	}
	
	@Override
	public String toString() {
		return super.toString() + " H:" + health + "/" + maxHealth + " A:" + attack;
	}
}


