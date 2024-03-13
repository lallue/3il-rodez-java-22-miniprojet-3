package fr.ecole3il.rodez2023.visualisateur;


import fr.ecole3il.rodez2023.jeu.AffichagePendu;
import fr.ecole3il.rodez2023.jeu.JeuDuPenduUI;
import fr.ecole3il.rodez2023.mots.GestionnaireJeu;
import fr.ecole3il.rodez2023.mots.GestionnaireLettre;
import fr.ecole3il.rodez2023.mots.LecteurMots;

import javax.swing.*;
import java.util.List;

public class ApplicationJeuDuPendu {

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String nomFichier = "mots.txt";
            LecteurMots lecteurMots = new LecteurMots(nomFichier);
            List<String> motsList = lecteurMots.getMotsList();

            GestionnaireJeu gestionnaireJeu = new GestionnaireJeu(motsList);
            AffichagePendu affichagePendu = new AffichagePendu();
            GestionnaireLettre gestionnaireLettre = new GestionnaireLettre(gestionnaireJeu);

            JeuDuPenduUI jeuDuPenduUI = new JeuDuPenduUI(gestionnaireJeu, affichagePendu, gestionnaireLettre);
            jeuDuPenduUI.setVisible(true);
        });
    }
}