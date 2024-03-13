package fr.ecole3il.rodez2023.test;

import org.junit.jupiter.api.Test;

import fr.ecole3il.rodez2023.jeu.AffichagePendu;

import javax.swing.*;
import java.awt.*;


public class AffichagePenduTest {

    @Test
    public void testIncrementErreurs() {
        AffichagePendu affichagePendu = new AffichagePendu();
        affichagePendu.incrementErreurs();
    }

    @Test
    public void testReset() {
        AffichagePendu affichagePendu = new AffichagePendu();
        affichagePendu.incrementErreurs();
        affichagePendu.reset();
    }

    @Test
    public void testDessinerPendu() {
        AffichagePendu affichagePendu = new AffichagePendu();

        // On teste le dessin du pendu en fonction du nombre d'erreurs
        JPanel panel = new JPanel();
        Graphics g = panel.getGraphics();

        affichagePendu.incrementErreurs(); // 1 erreur
        // Vous pouvez ajouter ici des assertions pour vérifier si le pendu est correctement dessiné après une erreur

        affichagePendu.reset();
        affichagePendu.incrementErreurs(); // 1 erreur
        affichagePendu.incrementErreurs(); // 2 erreurs
        // Ajoutez d'autres assertions pour les différents cas de dessin du pendu
    }
}
