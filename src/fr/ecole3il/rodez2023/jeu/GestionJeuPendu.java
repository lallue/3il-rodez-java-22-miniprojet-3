package fr.ecole3il.rodez2023.jeu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe GestionJeuPendu est responsable de la gestion du timer pour le jeu du pendu.
 * Elle démarre un timer lorsqu'un jeu démarre et arrête le timer lorsque le temps est écoulé.
 */
public class GestionJeuPendu {
    private Timer timer; // Timer pour gérer le temps
    private final int DELAY = 60000; // Durée du timer par défaut (60 secondes)
    private JeuDuPenduUI jeuDuPenduUI; // Interface utilisateur du jeu du pendu

    /**
     * Constructeur de la classe GestionJeuPendu.
     * @param jeuDuPenduUI L'interface utilisateur du jeu du pendu.
     */
    public GestionJeuPendu(JeuDuPenduUI jeuDuPenduUI) {
        this.jeuDuPenduUI = jeuDuPenduUI;
        initTimer(); // Initialise le timer
    }

    /**
     * Initialise le timer avec le délai spécifié.
     */
    private void initTimer() {
        timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finDuTemps(); // Appelle finDuTemps() lorsque le timer expire
            }
        });
    }

    /**
     * Démarre le timer.
     */
    public void demarrerTimer() {
        timer.start();
    }

    /**
     * Arrête le timer.
     */
    public void arreterTimer() {
        timer.stop();
    }

    /**
     * Méthode appelée lorsque le temps est écoulé.
     * Elle désactive le bouton pour envoyer une lettre, affiche un message de fin de temps
     * et met fin à la partie.
     */
    public void finDuTemps() {
        jeuDuPenduUI.partieEnCours = false; // Indique que la partie est terminée
        jeuDuPenduUI.envoyerLettreButton.setEnabled(false); // Désactive le bouton pour envoyer une lettre
        JOptionPane.showMessageDialog(jeuDuPenduUI, "Temps écoulé !", "Fin du temps", JOptionPane.INFORMATION_MESSAGE); // Affiche un message de fin de temps
        jeuDuPenduUI.gameOver(); // Met fin à la partie
    }
}
