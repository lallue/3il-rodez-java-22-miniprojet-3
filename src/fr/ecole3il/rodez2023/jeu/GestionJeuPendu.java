package fr.ecole3il.rodez2023.jeu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionJeuPendu {
    private Timer timer;
    private final int DELAY = 60000; // 60 secondes par défaut
    private JeuDuPenduUI jeuDuPenduUI;

    public GestionJeuPendu(JeuDuPenduUI jeuDuPenduUI) {
        this.jeuDuPenduUI = jeuDuPenduUI;
        initTimer();
    }

    private void initTimer() {
        timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finDuTemps();
            }
        });
    }

    public void demarrerTimer() {
        timer.start();
    }

    public void arreterTimer() {
        timer.stop();
    }

    public void finDuTemps() {
        jeuDuPenduUI.partieEnCours = false;
        jeuDuPenduUI.envoyerLettreButton.setEnabled(false);
        JOptionPane.showMessageDialog(jeuDuPenduUI, "Temps écoulé !", "Fin du temps", JOptionPane.INFORMATION_MESSAGE);
        jeuDuPenduUI.gameOver();
    }
}
