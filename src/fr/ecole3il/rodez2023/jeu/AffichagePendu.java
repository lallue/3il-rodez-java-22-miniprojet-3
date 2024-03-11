package fr.ecole3il.rodez2023.jeu;

import java.awt.*;
import javax.swing.*;

public class AffichagePendu extends JPanel {
	
	private int erreurs;

	public AffichagePendu() {
		super();
		this.erreurs = 0;
	}
	
	private void incrementsErreur() {
		erreurs ++;
		repaint();
	}
	@Override 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		dessinerPendu(g);
	}

	private void dessinerPendu(Graphics g) {
	
		if (erreurs >= 1)
			g.drawOval(50, 50, 50, 50);
		if (erreurs >=2)
			g.drawLine(75, 100, 75, 200);
		
	}
}
