package fr.ecole3il.rodez2023.mots;

import java.io.*;
import java.util.*;

/**
 * La classe LecteurMots est responsable de la lecture des mots à partir d'un fichier.
 * Elle charge les mots depuis le fichier spécifié lors de sa création et les stocke dans une liste.
 */
public class LecteurMots {
	
	private List<String> motsList; // Liste des mots chargés depuis le fichier

    /**
     * Constructeur de la classe LecteurMots.
     * Charge les mots depuis le fichier spécifié lors de sa création.
     * @param nomFichier Le nom du fichier contenant les mots.
     */
    public LecteurMots(String nomFichier) {
        chargerMots(nomFichier);
    }

    /**
     * Charge les mots à partir du fichier spécifié.
     * Les mots sont stockés dans la liste motsList.
     * @param nomFichier Le nom du fichier contenant les mots.
     */
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

    /**
     * Obtient la liste des mots chargés depuis le fichier.
     * @return La liste des mots.
     */
    public List<String> getMotsList() {
        return motsList;
    }
}
