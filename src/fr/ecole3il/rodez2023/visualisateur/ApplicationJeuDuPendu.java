package fr.ecole3il.rodez2023.visualisateur;


import fr.ecole3il.rodez2023.jeu.JeuDuPendueUI;
import fr.ecole3il.rodez2023.mots.GestionnaireJeu;
import fr.ecole3il.rodez2023.mots.LecteurMots;

import java.util.*;
import javax.swing.*;

public class ApplicationJeuDuPendu {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String nomFichier = "mots.txt";
                LecteurMots lecteurMots = new LecteurMots(nomFichier);
                List<String> motsList = lecteurMots.getMotsList();
                GestionnaireJeu gestionnaireJeu = new GestionnaireJeu(motsList);
                JeuDuPendueUI jeuDuPenduUI = new JeuDuPendueUI(gestionnaireJeu);
                jeuDuPenduUI.setVisible(true);
            }
        });
    }
}