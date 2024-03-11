package fr.ecole3il.rodez2023.mots;

import java.util.*;

public class GestionnaireJeu {
	
	private List<String> motsList;

    public GestionnaireJeu(List<String> motsList) {
        this.motsList = motsList;
    }

    public String obtenirMotAleatoire() {
        Random random = new Random();
        int index = random.nextInt(motsList.size());
        String motEtDefinition = motsList.get(index);
        String[] parts = motEtDefinition.split(" ");
        return parts[0];
    }
}
