package pt.iul.ista.poo.rogue.utils;

import java.util.Comparator;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.rogue.elementos.Elemento;

public class ComparadorDePriority implements Comparator<ImageTile>{

	@Override
	public int compare(ImageTile o1, ImageTile o2) {
		return ((Elemento)o1).getPriority() - ((Elemento)o2).getPriority();
	}

}
