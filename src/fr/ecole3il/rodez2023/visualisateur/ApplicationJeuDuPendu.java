package fr.ecole3il.rodez2023.visualisateur;

import fr.ecole3il.rodez2023.jeu.AffichagePendu;
import fr.ecole3il.rodez2023.jeu.JeuDuPenduUI;
import fr.ecole3il.rodez2023.mots.GestionnaireJeu;
import fr.ecole3il.rodez2023.mots.GestionnaireLettre;
import fr.ecole3il.rodez2023.mots.LecteurMots;

import javax.swing.*;
import java.util.List;

/**
 * La classe ApplicationJeuDuPendu est la classe principale du jeu du pendu.
 * Elle lance l'application en créant les instances nécessaires des différentes classes et en les reliant.
 */
public class ApplicationJeuDuPendu {

    /**
     * Méthode principale qui lance l'application du jeu du pendu.
     * @param args Les arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Nom du fichier contenant les mots
            String nomFichier = "mots.txt";

            // Lecture des mots à partir du fichier
            LecteurMots lecteurMots = new LecteurMots(nomFichier);
            List<String> motsList = lecteurMots.getMotsList();

            // Création des instances des classes nécessaires
            GestionnaireJeu gestionnaireJeu = new GestionnaireJeu(motsList);
            AffichagePendu affichagePendu = new AffichagePendu();
            GestionnaireLettre gestionnaireLettre = new GestionnaireLettre(gestionnaireJeu);

            // Création de l'interface utilisateur du jeu du pendu et affichage
            JeuDuPenduUI jeuDuPenduUI = new JeuDuPenduUI(gestionnaireJeu, affichagePendu, gestionnaireLettre);
            jeuDuPenduUI.setVisible(true);
        });
    }
}
