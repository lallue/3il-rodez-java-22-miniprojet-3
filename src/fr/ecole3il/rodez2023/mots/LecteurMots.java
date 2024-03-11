package fr.ecole3il.rodez2023.mots;

import java.io.*;
import java.util.*;

public class LecteurMots {
	
	 private List<String> motsList;

	    public LecteurMots(String nomFichier) {
	        chargerMots(nomFichier);
	    }

	    private void chargerMots(String nomFichier) {
	        motsList = new ArrayList<>();
	        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
	            String ligne;
	            while ((ligne = br.readLine()) != null) {
	                motsList.add(ligne);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public List<String> getMotsList() {
	        return motsList;
	    }
	}
