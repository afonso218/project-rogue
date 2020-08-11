package pt.iul.ista.poo;

import pt.iul.ista.poo.gui.*;
import pt.iul.ista.poo.rogue.MotorJogo;
import pt.iul.ista.poo.rogue.Status;

public class Main {

	/**
	 * Método que executa a aplicacao (responsavel por criar MotorJogo,
	 * carregar a gui com imagens do motorjogo e status) Extra: Lanca uma thread
	 * que de 100ms em 100ms move elementos do motor e atualiza a gui
	 * @param args
	 */
	public static void main(String[] args) {

		ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
		gui.setName("Rogue POO");

		MotorJogo motor = new MotorJogo();
		gui.addObserver(motor); 
		gui.go();

		// Carregar elementos na parte grafica do mapa
		gui.newStatusImages(Status.getInstance().getElementos());
		gui.newImages(motor.getElementos());

		while (true) {
			motor.update();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
