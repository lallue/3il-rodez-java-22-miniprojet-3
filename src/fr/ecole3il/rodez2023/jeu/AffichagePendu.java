package fr.ecole3il.rodez2023.jeu;

import java.awt.*;
import javax.swing.*;

/**
 * La classe AffichagePendu est une extension de JPanel qui est utilisée pour afficher visuellement
 * le pendu dans le jeu du pendu en fonction du nombre d'erreurs.
 */
public class AffichagePendu extends JPanel {

    private int erreurs; // Le nombre d'erreurs commises

    /**
     * Constructeur de la classe AffichagePendu.
     * Initialise le nombre d'erreurs à 0.
     */
    public AffichagePendu() {
        super();
        this.erreurs = 0;
    }

    /**
     * Incrémente le nombre d'erreurs et redessine le composant.
     */
    public void incrementErreurs() {
        erreurs++;
        repaint();
    }

    /**
     * Réinitialise le nombre d'erreurs à 0 et redessine le composant.
     */
    public void reset() {
        erreurs = 0;
        repaint();
    }

    /**
     * Méthode appelée automatiquement pour dessiner le composant.
     * Dessine les éléments du pendu en fonction du nombre d'erreurs.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dessinerPendu(g);
    }

    /**
     * Dessine les différentes parties du pendu en fonction du nombre d'erreurs.
     * @param g L'objet Graphics utilisé pour dessiner.
     */
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
