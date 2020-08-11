package pt.iul.ista.poo.rogue.utils;

import java.util.Random;

public class Tools {

	public static int distance(Position p1, Position p2){
		return (int)Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
	}
	
	public static Position random(Position p){
		Random gen = new Random();
		int x = gen.nextInt(3) - 1 + p.getX();
		int y = gen.nextInt(3) - 1 + p.getY();
		return new Position(x, y);
	}
	
	public static Position nearPosition(Position origem, Position destino){
		Direction dir;
		if(new Random().nextBoolean()){
			if(origem.getX() > destino.getX()){
				dir = Direction.LEFT;
			}else{
				dir = Direction.RIGHT;
			}
		}else{
			if(origem.getY() > destino.getY()){
				dir = Direction.UP;
			}else{
				dir = Direction.DOWN;
			}
		}
		return origem.plus(dir.asVector());
	}
	
	public static Position farPosition(Position origem, Position destino){
		Direction dir;
		if(new Random().nextBoolean()){
			if(origem.getX() < destino.getX()){
				dir = Direction.LEFT;
			}else{
				dir = Direction.RIGHT;
			}
		}else{
			if(origem.getY() < destino.getY()){
				dir = Direction.UP;
			}else{
				dir = Direction.DOWN;
			}
		}
		return origem.plus(dir.asVector());
	}
}
