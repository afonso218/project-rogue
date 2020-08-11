package pt.iul.ista.poo.rogue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.elementos.Door;
import pt.iul.ista.poo.rogue.elementos.Elemento;
import pt.iul.ista.poo.rogue.elementos.ElementoLuta;
import pt.iul.ista.poo.rogue.elementos.Power;
import pt.iul.ista.poo.rogue.elementos.tipo.*;
import pt.iul.ista.poo.rogue.utils.ComparadorDePriority;
import pt.iul.ista.poo.rogue.utils.Position;

public class Sala {

	private Key key;
	private Map<Character, Door> doors = new HashMap<Character, Door>();
	private List<ImageTile> elementos = new ArrayList<ImageTile>();
	
	/**
	 * carrega o chao 
	 * carrega o ficheiro
	 * adiciona o hero aos elementos
	 * @param nome_ficheiro
	 * @param hero
	 */
	public Sala(String nome_ficheiro){
		for (int i = 0; i < 10; i++){
			for (int j = 0; j < 10; j++){
				elementos.add(new Floor(new Position(i, j)));
			}
		}
		lerFicheiro(nome_ficheiro);
	}

	/**
	 * le o ficheiro e converte os simbolos que estao no ficheiro para os respetivos elementos
	 * ao detetar uma porta principal o hero e colocado nessa posicao
	 * @param nome_ficheiro
	 * @param hero
	 */
	@SuppressWarnings("resource")
	private void lerFicheiro(String nome_ficheiro) {
		try {
			Scanner s = new Scanner(new File("rooms/" + nome_ficheiro));
			int y = 0;
			while(s.hasNextLine()){
				String linha = s.nextLine();
				
				if(linha.startsWith("#")){
					convertComment(linha);
				}else{
					if(linha.length() != 10){
						throw new IllegalArgumentException("Ficheiro mal configurado");
					}
					for(int x = 0; x < 10; x++){
						converteSymbol(y, x, linha.charAt(x));
					}
					y++;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Erro: Ficheiro nao encontrado");
			e.printStackTrace();
		}
	}

	private void convertComment(String linha) {
		String[] tokens = linha.split(" ");
		if(tokens.length == 3){
			key = new Key(new Position(0, 0), tokens[2]);
		}
		if(tokens.length >= 5){
			try{
				char number = tokens[1].charAt(0);
				String simbolo = tokens[2];
				String room_file_name = tokens[3];
				Character room_door_destination = tokens[4].charAt(0);
				if(simbolo.equals("D")){
					if(tokens.length >= 6){
						String key_name = tokens[5];
						doors.put(number, new DoorClosed(new Position(0, 0), room_file_name, room_door_destination, key_name));
					}else{
						doors.put(number, new DoorOpen(new Position(0, 0), room_file_name, room_door_destination));
					}
				}
				if(simbolo.equals("E")){
					doors.put(number, new DoorWay(new Position(0, 0), room_file_name, room_door_destination));
				}
			}catch(Exception e){
				System.err.println("Erro na leitura de comentários do ficheiro! > " + linha);
			}
		}
	}
	
	private void converteSymbol(int y, int x, char letra) {
		if(Character.isDigit(letra)){
			if(!doors.containsKey(letra)){
				throw new IllegalArgumentException("Elemento nao existe em comentario > " + letra + " - " + doors);
			}
			Elemento e = doors.get(letra);
			e.setPosition(new Position(x,y));
			elementos.add(e);
		}
		if(letra == 'k'){
			key.setPosition(new Position(x, y));
			elementos.add(key);
		}
		if(letra == 'W'){
			elementos.add(new Wall(new Position(x, y)));
		}
		if(letra == 'S'){
			elementos.add(new Skeleton(new Position(x, y)));
		}
		if(letra == 'B'){
			elementos.add(new BadGuy(new Position(x, y)));
		}
		if(letra == 'b'){
			elementos.add(new Bat(new Position(x, y)));
		}
		if(letra == 's'){
			elementos.add(new Sword(new Position(x, y)));
		}
		if(letra == 'h'){
			elementos.add(new Hammer(new Position(x, y)));
		}
	}

	public void addHero(Character numero_porta){
		Hero hero = Hero.getInstance();
		if(!doors.containsKey(numero_porta)){
			throw new IllegalArgumentException("Porta " + numero_porta + " nao existe > " + doors);
		}
		hero.setPosition(doors.get(numero_porta).getPosition());
		elementos.add(hero);
	}
	
	public void addElemento(Elemento e){
		elementos.add(e);
		ImageMatrixGUI.getInstance().addImage(e);
	}
	
	public void removeElemento(Elemento e){
		elementos.remove(e);
		ImageMatrixGUI.getInstance().removeImage(e);
	}
	
	/**
	 * devolve os elementos da sala
	 * @return List<ImageTile> elementos
	 */
	public List<ImageTile> getElementos() {
		Collections.sort(elementos, new ComparadorDePriority());
		return elementos;
	}

	/**
	 * verifica se uma posicao esta dentro dos limites e se o elemento se pode mover para a posicao pretendida
	 * este metodo tambem verifica se existir um elemento nessa posicao, este e um obstaculo
	 * @param posicao
	 * @return boolean se a posicao esta livre ou nao 
	 */
	public boolean isFree(Position position) {
		if(position.getX() < 0 || position.getX() > 9 || position.getY() < 0 || position.getY() > 9 ){
			return false;
		}
		for(ImageTile e : elementos){
			Elemento el = ((Elemento)e);
			if(el.getPosition().equals(position) && el.isObstaculo()){
				return false;
			}
		}
		return true;
	}
	
	public void removeWall(Position position) {
		for(ImageTile e : elementos){
			Elemento el = ((Elemento)e);
			if(el.getPosition().equals(position) && el.isWall()){
				removeElemento(el);
				break;
			}
		}
	}
	
	public boolean isDoor(Position position) {
		for(ImageTile e : elementos){
			Elemento el = ((Elemento)e);
			if(el.getPosition().equals(position) && el.isDoor()){
				return true;
			}
		}
		return false;
	}
	
	public Door getDoor(Position position) {
		for(ImageTile e : elementos){
			Elemento el = ((Elemento)e);
			if(el.getPosition().equals(position) && el.isDoor()){
				return (Door)el;
			}
		}
		return null;
	}
	
	public boolean isPower(Position position) {
		for(ImageTile e : elementos){
			Elemento el = ((Elemento)e);
			if(el.getPosition().equals(position) && el.isPower()){
				return true;
			}
		}
		return false;
	}
	
	public Power getPower(Position position) {
		for(ImageTile e : elementos){
			Elemento el = ((Elemento)e);
			if(el.getPosition().equals(position) && el.isPower()){
				return (Power)el;
			}
		}
		return null;
	}
	
	public boolean isFighter(Position position) {
		for(ImageTile e : elementos){
			Elemento el = ((Elemento)e);
			if(el.getPosition().equals(position) && el.isFight()){
				return true;
			}
		}
		return false;
	}
	
	public ElementoLuta getFighter(Position position) {
		for(ImageTile e : elementos){
			Elemento el = ((Elemento)e);
			if(el.getPosition().equals(position) && el.isFight()){
				return (ElementoLuta)el;
			}
		}
		return null;
	}
	
	/**
	 * percorre a lista de ImageTile da sala
	 * converte os ImageTile para elementos e pergunta se podem mover 
	 * se sim converte para elementoMove e move
	 */
	public void moveElements() {
		Iterator<ImageTile> els = elementos.iterator();
		while(els.hasNext()){
			Elemento el = (Elemento)els.next();
			if(el.canMove()) {
				Position pos = el.move();
				if(this.isFree(pos)){
					el.setPosition(pos);
				}else{
					if(el.isFight() && pos.equals(Hero.getInstance().getPosition())){
						Hero.getInstance().receiveDamage(((ElementoLuta)el).getAttack());
						System.out.println("Hero toke damage from " + el + " > " + Hero.getInstance());
						Status.getInstance().update();
					}
					if(el.isFire()){
						if(isFighter(pos)){
							getFighter(pos).receiveDamage(((Fire)el).getAttack());
						}
						els.remove(); 
						ImageMatrixGUI.getInstance().removeImage(el);
					}
				}
			}
		}
		els = elementos.iterator();
		while(els.hasNext()){
			Elemento el = (Elemento)els.next();
			if(el.isFight() && ((ElementoLuta)el).isDead()){
				ImageMatrixGUI.getInstance().removeImage(el);
				els.remove(); 
			}
		}
	}
	
}
