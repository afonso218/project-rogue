package pt.iul.ista.poo.rogue.elementos.tipo;

import pt.iul.ista.poo.rogue.elementos.Door;
import pt.iul.ista.poo.rogue.utils.Position;

public class DoorOpen extends Door {

	public DoorOpen(Position position, String room_name, Character door_letter) {
		super("DoorOpen", position, room_name, door_letter);
	}
	
	@Override
	public boolean isObstaculo() {
		return false;
	}
	
}

	