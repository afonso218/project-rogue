package pt.iul.ista.poo.rogue.elementos.tipo;

import pt.iul.ista.poo.rogue.elementos.Door;
import pt.iul.ista.poo.rogue.utils.Position;

public class DoorClosed extends Door{
	
	
	public DoorClosed(Position position, String room_name, Character door_letter) {
		super("DoorClosed", position, room_name, door_letter);
	}
	
	public DoorClosed(Position position, String room_name, Character door_letter, String keyName) {
		super("DoorClosed", position, room_name, door_letter, keyName);
	}
	
	@Override
	public boolean isObstaculo() {
		return true;
	}
	
}