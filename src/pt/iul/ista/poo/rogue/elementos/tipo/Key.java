package pt.iul.ista.poo.rogue.elementos.tipo;

import pt.iul.ista.poo.rogue.elementos.Power;
import pt.iul.ista.poo.rogue.utils.Position;

public class Key extends Power {

	private String keyName;
	
	public Key(Position position, String keyName) {
		super("key", position);
		this.keyName = keyName;
	}

	public String getKeyName() {
		return keyName;
	}
	
	@Override
	public boolean isKey() {
		return true;
	}
	
}

