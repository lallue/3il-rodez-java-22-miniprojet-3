package fr.ecole3il.rodez2023.mots;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestionnaireJeu {

    private String motADeviner;
    private List<Character> lettresCorrectes;
    private List<Character> lettresIncorrectes;
    private List<String> motsList;
    private int erreurs;

    public GestionnaireJeu(List<String> motsList) {
        this.motsList = motsList;
        reinitialiserJeu();
    }

    private String obtenirMotAleatoire() {
        Random random = new Random();
        int index = random.nextInt(motsList.size());
        String motEtDefinition = motsList.get(index);
        String[] parts = motEtDefinition.split(" ");
        return parts[0].toUpperCase();
    }

    public void traiterLettre(char lettre, GestionnaireLettre gestionnaireLettre) {
        if (estLettreDejaTraitee(lettre)) {
            return;
        }

        if (estLettreCorrecte(lettre)) {
            lettresCorrectes.add(lettre);
        } else {
            lettresIncorrectes.add(lettre);
            incrementerErreurs();
        }
    }

    public void reinitialiserJeu() {
        this.motADeviner = obtenirMotAleatoire();
        this.lettresCorrectes = new ArrayList<>();
        this.lettresIncorrectes = new ArrayList<>();
        this.erreurs = 0;
    }

    public List<Character> getLettresCorrectes() {
        return lettresCorrectes;
    }

    public List<Character> getLettresIncorrectes() {
        return lettresIncorrectes;
    }

    public String getMotADeviner() {
        return motADeviner;
    }

    public int getErreurs() {
        return erreurs;
    }

    public String getMotMasque() {
        StringBuilder motMasque = new StringBuilder();
        for (char lettre : motADeviner.toCharArray()) {
            if (lettresCorrectes.contains(lettre)) {
                motMasque.append(lettre);
            } else {
                motMasque.append('_');
            }
            motMasque.append(' ');
        }
        return motMasque.toString().trim();
    }

    public boolean estLettreDejaTraitee(char lettre) {
        return lettresCorrectes.contains(lettre) || lettresIncorrectes.contains(lettre);
    }
    public boolean estLettreCorrecte(char lettre) {
        return motADeviner.indexOf(lettre) != -1;
    }
    public void incrementerErreurs() {
        erreurs++;
    }
}

