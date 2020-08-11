package pt.iul.ista.poo.rogue.elementos;

import pt.iul.ista.poo.rogue.utils.Position;

public abstract class Door extends Elemento{

	private Character door_letter;
	private String room_name;
	private String keyName;
	
	public Door(String nome, Position position, String room_name, Character door_letter, String keyName) {
		super(nome, position);
		this.door_letter = door_letter;
		this.room_name = room_name;
		this.keyName = keyName;
	}
	
	public Door(String nome, Position position, String room_name, Character door_letter) {
		super(nome, position);
		this.door_letter = door_letter;
		this.room_name = room_name;
	}

	public int getPriority(){
		return 2;
	}
	
	public String getRoom_name() {
		return room_name;
	}

	public Character getDoorDestination() {
		return door_letter;
	}
	
	@Override
	public boolean isDoor(){
		return true;
	}
	
	@Override
	public boolean canMove() {
		return false;
	}
	
	public boolean needKey(){
		return keyName != null;
	}

	public String getKeyName() {
		return keyName;
	}
}
