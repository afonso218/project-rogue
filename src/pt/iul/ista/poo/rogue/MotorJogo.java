package pt.iul.ista.poo.rogue;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.elementos.Door;
import pt.iul.ista.poo.rogue.elementos.ElementoLuta;
import pt.iul.ista.poo.rogue.elementos.Power;
import pt.iul.ista.poo.rogue.elementos.tipo.Fire;
import pt.iul.ista.poo.rogue.elementos.tipo.Hero;
import pt.iul.ista.poo.rogue.utils.Direction;
import pt.iul.ista.poo.rogue.utils.Position;

public class MotorJogo implements Observer{
	
	private final int NUMBER_OF_ROOMS = 4;
	
	private Sala salaAtiva;
	private Map<String, Sala> salas = new HashMap<String, Sala>();
	
	/**
	 * Inicializacao dos seguinte atributos: um hero, um status e sala
	 */
	public MotorJogo(){
		for(int i = 0; i <= NUMBER_OF_ROOMS; i++){
			salas.put("room" + i + ".txt", new Sala("room" + i + ".txt"));
		}
		salaAtiva = salas.get("room0.txt");
		salaAtiva.addHero('0');
	}

	/**
	 * devolve os elementos da sala ativa
	 * @return List<ImageTile> 	elementos 
	 */
	public List<ImageTile> getElementos() {
		return salaAtiva.getElementos();
	}
	
	/**
	 * move os elementos da sala ativa
	 */
	public void update() {
		if(!Hero.getInstance().isDead()){
			salaAtiva.moveElements();
			ImageMatrixGUI.getInstance().update();
		}
	}

	/**
	 * metodo obrigatorio porque a classe implementa observer
	 * sempre que é acionada uma tecla e aciconado este metodo
	 * se o utilizador pressionar a tecla(com setas) o hero move-se para a proximaPosicao se esta estiver livre 
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		Hero hero = Hero.getInstance();
		if(!hero.isDead()){
			Integer keyCode = (Integer) arg1;
			if (keyCode == KeyEvent.VK_SPACE && hero.getDirection() != null){
				Position p = hero.getPosition().plus(hero.getDirection().asVector());
				if(salaAtiva.isFree(p)){
					salaAtiva.addElemento(new Fire(p, hero.getDirection()));
				}else{
					if(hero.hasHammer()){
						salaAtiva.removeWall(p);
					}
				}
			}
			if (keyCode == KeyEvent.VK_DOWN){
				moveHero(Direction.DOWN);
			}
			if (keyCode == KeyEvent.VK_UP){
				moveHero(Direction.UP);
			}
			if (keyCode == KeyEvent.VK_LEFT){
				moveHero(Direction.LEFT);
			}
			if (keyCode == KeyEvent.VK_RIGHT){
				moveHero(Direction.RIGHT);
			}
			if (keyCode == KeyEvent.VK_1 && hero.getPowers().size() > 0){
				Power p = hero.getPowers().get(0);
				p.setPosition(hero.getPosition());
				salaAtiva.addElemento(p);
				hero.removePower(p);
				Status.getInstance().update();
			}
			if (keyCode == KeyEvent.VK_2 && hero.getPowers().size() > 1){
				Power p = hero.getPowers().get(1);
				p.setPosition(hero.getPosition());
				salaAtiva.addElemento(p);
				hero.removePower(p);
				Status.getInstance().update();
			}
			if (keyCode == KeyEvent.VK_3 && hero.getPowers().size() > 2){
				Power p = hero.getPowers().get(2);
				p.setPosition(hero.getPosition());
				salaAtiva.addElemento(p);
				hero.removePower(p);
				Status.getInstance().update();
			}
			System.out.print(KeyEvent.getKeyText(keyCode) + " ");
			ImageMatrixGUI.getInstance().update();
		}
	}

	/**
	 * criacao da nova posicao do hero, se essa posicao estiver livre na sala ativa move o hero
	 * @param direction direcao para a qual o hero se move
	 */
	public void moveHero(Direction direction) {
		Hero hero = Hero.getInstance();
		hero.setDirection(direction);
		Position proximaPosicao = hero.getPosition().plus(direction.asVector());
		if(salaAtiva.isFighter(proximaPosicao)){
			ElementoLuta fighter = salaAtiva.getFighter(proximaPosicao);
			fighter.receiveDamage(hero.getAttack());
			System.out.println("Hero make damage to " + Hero.getInstance() + " > " + fighter);
			if(fighter.isDead()){
				salaAtiva.removeElemento(fighter);
			}
		}
		if(salaAtiva.isFree(proximaPosicao)){
			hero.move(direction);
		}
		if(salaAtiva.isDoor(proximaPosicao)){
			Door door = salaAtiva.getDoor(proximaPosicao);
			
			if(!salas.containsKey(door.getRoom_name())){
				throw new IllegalArgumentException("Sala " + door.getRoom_name() + " nao existe! (ficheiro mal configurado)");
			}
			if(!door.needKey() || (door.needKey() && Hero.getInstance().hasKey(door.getKeyName()))){
				salaAtiva.removeElemento(hero);
				salaAtiva = salas.get(door.getRoom_name());
				salaAtiva.addHero(door.getDoorDestination());
				
				System.out.println("Heroi entrou na sala " + door.getRoom_name() + " na porta " + door.getDoorDestination());
				ImageMatrixGUI.getInstance().clearImages();
				ImageMatrixGUI.getInstance().newImages(salaAtiva.getElementos());
			}
			
		}
		if(salaAtiva.isPower(proximaPosicao)){
			Power power = salaAtiva.getPower(proximaPosicao);
			if(hero.addPower(power)){
				salaAtiva.removeElemento(power);
				Status.getInstance().update();
			}
		}
	}

}
