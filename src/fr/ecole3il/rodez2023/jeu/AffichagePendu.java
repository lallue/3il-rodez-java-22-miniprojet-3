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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dessinerPendu(g);
    }

    private void dessinerPendu(Graphics g) {
        if (erreurs >= 1) {
            // Tête
            g.drawOval(50, 50, 50, 50);
        }
        if (erreurs >= 2) {
            // Corps
            g.drawLine(75, 100, 75, 200);
        }
        if (erreurs >= 3) {
            // Bras gauche
            g.drawLine(75, 120, 50, 150);
        }
        if (erreurs >= 4) {
            // Bras droit
            g.drawLine(75, 120, 100, 150);
        }
        if (erreurs >= 5) {
            // Jambe gauche
            g.drawLine(75, 200, 50, 250);
        }
        if (erreurs >= 6) {
            // Jambe droite
            g.drawLine(75, 200, 100, 250);
        }
    }
}

