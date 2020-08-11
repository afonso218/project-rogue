package pt.iul.ista.poo.rogue;

import java.util.ArrayList;
import java.util.List;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.elementos.Power;
import pt.iul.ista.poo.rogue.elementos.tipo.Hero;
import pt.iul.ista.poo.rogue.elementos.tipoStatus.Black;
import pt.iul.ista.poo.rogue.elementos.tipoStatus.Green;
import pt.iul.ista.poo.rogue.elementos.tipoStatus.Red;
import pt.iul.ista.poo.rogue.utils.Position;

public class Status {

	private static Status Instance = new Status();
	private List<ImageTile> elementos = new ArrayList<ImageTile>();
	
	private Status() {
		update();
	}
	
	public static Status getInstance() {
		return Instance;
	}
	
	public void update() {

		ImageMatrixGUI.getInstance().clearStatus();
		Hero hero = Hero.getInstance();
		// HEALTH
		int pintarVerde = (int)((double)((hero.getHealth()*7))/(double)(hero.getMaxHealth()));
		for(int i = 0; i < pintarVerde; i++){
			elementos.add(new Green(new Position(i,0)));	
		}
		for(int i = pintarVerde; i < 7; i++){
			elementos.add(new Red(new Position(i,0)));
		}
		// POWER
		List<Power> powers = hero.getPowers();
		for(int i = 0; i < 3; i++){
			elementos.add(new Black(new Position(7+i,0)));
			if(powers.size() > i){
				Power p = powers.get(i);
				p.setPosition(new Position(7+i,0));
				elementos.add(p);
			}
		}
		
		ImageMatrixGUI.getInstance().newStatusImages(elementos);
	}
	
	public List<ImageTile> getElementos() {
		return elementos;
	}
	
}
