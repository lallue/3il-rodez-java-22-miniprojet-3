package fr.ecole3il.rodez2023.jeu;

import java.awt.*;
import javax.swing.*;

public class AffichagePendu extends JPanel {

    private int erreurs;

    public AffichagePendu() {
        super();
        this.erreurs = 0;
    }

    public void incrementErreurs() {
        erreurs++;
        repaint();
    }

    public void reset() {
        erreurs = 0;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dessinerPendu(g);
    }

    private void dessinerPendu(Graphics g) {
        if (erreurs >= 1) {
            // Potence verticale
            g.drawLine(100, 50, 100, 300);
        }
        if (erreurs >= 2) {
            // Barre horizontale
            g.drawLine(100, 50, 250, 50);
        }
        if (erreurs >= 3) {
            // Tête
        	g.drawLine(250, 50, 250, 100);
            g.drawOval(225, 100, 50, 50);
        }
        if (erreurs >= 4) {
            // Corps
            g.drawLine(250, 150, 250, 250);
        }
        if (erreurs >= 5) {
            // Bras gauche
            g.drawLine(250, 150, 225, 200);
        }
        if (erreurs >= 6) {
            // Bras droit
            g.drawLine(250, 150, 275, 200);
        }
        if (erreurs >= 7) {
            // Jambe gauche
            g.drawLine(250, 250, 225, 300);
        }
        if (erreurs >= 8) {
            // Jambe droite
            g.drawLine(250, 250, 275, 300);
        }
    }
}