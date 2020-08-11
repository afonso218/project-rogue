package pt.iul.ista.poo.rogue.elementos.tipo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import pt.iul.ista.poo.rogue.elementos.ElementoLuta;
import pt.iul.ista.poo.rogue.elementos.Power;
import pt.iul.ista.poo.rogue.utils.Direction;
import pt.iul.ista.poo.rogue.utils.Position;

public class Hero extends ElementoLuta {

	private static Hero INSTANCE = new Hero(new Position(0,0));
	private Direction direction;
	private int score = 0;
	
	private List<Power> powers = new ArrayList<Power>();
	
	private Hero(Position position) {
		super("Hero", position, 10, 5);
	}
	
	public void move(Direction direction) {
		setPosition(getPosition().plus(direction.asVector()));
	}

	public static Hero getInstance(){
		return INSTANCE;
	}
	
	public boolean addPower(Power p){
		score++;
		if(powers.size() < 3){
			powers.add(p);
			return true;
		}
		return false;
	}

	public void removePower(Power p) {
		powers.remove(p);
	}
	
	public List<Power> getPowers() {
		return powers;
	}

	public boolean hasKey(String keyName) {
		for(Power p: powers){
			if(p.isKey() && ((Key)p).getKeyName().equals(keyName)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int getAttack() {
		return super.getAttack() + getExtraAttack();
	}
	
	private int getExtraAttack(){
		int extraAttack = 0;
		for(Power p : powers){
			extraAttack += p.getExtraAttack();
		}
		return extraAttack;
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
	public boolean isHero() {
		return false;
	}

	public void receiveDamage(int attack) {
		super.receiveDamage(attack);
		if(isDead()){
			JOptionPane.showMessageDialog(null, "Game Over!\n\n" + this + "\nYour Score: " + score + "\n");
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + " EA:" + getExtraAttack();
	}

	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public boolean hasHammer(){
		for(Power p: powers){
			if(p.getName().equals("Hammer"))
				return true;
		}
		return false; 
	}
	
}


