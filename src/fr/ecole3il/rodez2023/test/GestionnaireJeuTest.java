package fr.ecole3il.rodez2023.test;

import org.junit.jupiter.api.Test;

import fr.ecole3il.rodez2023.mots.GestionnaireJeu;
import fr.ecole3il.rodez2023.mots.GestionnaireLettre;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

public class GestionnaireJeuTest {

    private class MockGestionnaireLettre extends GestionnaireLettre {
        public MockGestionnaireLettre(GestionnaireJeu gestionnaireJeu) {
            super(gestionnaireJeu);
        }

        @Override
        public void traiterLettre(char lettre) {
            // Ne fait rien dans le mock
        }
    }
    @Test
    public void testTraitementLettreCorrecte() {
        List<String> motsList = Arrays.asList("BONJOUR", "SALUT", "BONSOIR");
        GestionnaireJeu gestionnaireJeu = new GestionnaireJeu(motsList);
        MockGestionnaireLettre mockGestionnaireLettre = new MockGestionnaireLettre(gestionnaireJeu);
        char lettre = 'O';

        gestionnaireJeu.traiterLettre(lettre, mockGestionnaireLettre);

        assertEquals(1, gestionnaireJeu.getLettresCorrectes().size());
        assertEquals(0, gestionnaireJeu.getLettresIncorrectes().size());
    }

    @Test
    public void testTraitementLettreIncorrecte() {
        List<String> motsList = Arrays.asList("BONJOUR", "SALUT", "BONSOIR");
        GestionnaireJeu gestionnaireJeu = new GestionnaireJeu(motsList);
        MockGestionnaireLettre mockGestionnaireLettre = new MockGestionnaireLettre(gestionnaireJeu);
        char lettre = 'X';

        gestionnaireJeu.traiterLettre(lettre, mockGestionnaireLettre);

        assertEquals(0, gestionnaireJeu.getLettresCorrectes().size());
        assertEquals(1, gestionnaireJeu.getLettresIncorrectes().size());
        assertEquals(1, gestionnaireJeu.getErreurs());
    }
}