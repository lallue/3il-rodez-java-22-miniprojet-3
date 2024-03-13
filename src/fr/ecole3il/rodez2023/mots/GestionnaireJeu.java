package fr.ecole3il.rodez2023.mots;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * La classe GestionnaireJeu est responsable de la gestion du jeu du pendu.
 * Elle gère le mot à deviner, les lettres correctement devinées, les lettres incorrectes,
 * le nombre d'erreurs et fournit diverses méthodes pour interagir avec le jeu.
 */
public class GestionnaireJeu {

    private String motADeviner; // Le mot à deviner
    private List<Character> lettresCorrectes; // Les lettres correctement devinées
    private List<Character> lettresIncorrectes; // Les lettres incorrectes devinées
    private List<String> motsList; // Liste des mots disponibles
    private int erreurs; // Nombre d'erreurs commises

    /**
     * Constructeur de la classe GestionnaireJeu.
     * Initialise les listes de mots, réinitialise le jeu.
     * @param motsList La liste des mots disponibles pour le jeu.
     */
    public GestionnaireJeu(List<String> motsList) {
        this.motsList = motsList;
        reinitialiserJeu();
    }

    /**
     * Obtenir un mot aléatoire à partir de la liste des mots disponibles.
     * @return Un mot aléatoire en majuscules.
     */
    private String obtenirMotAleatoire() {
        Random random = new Random();
        int index = random.nextInt(motsList.size());
        String motEtDefinition = motsList.get(index);
        String[] parts = motEtDefinition.split(" ");
        return parts[0].toUpperCase();
    }

    /**
     * Traiter une lettre proposée par le joueur.
     * Met à jour les listes de lettres correctes et incorrectes, et incrémente les erreurs si nécessaire.
     * @param lettre La lettre proposée.
     * @param gestionnaireLettre Le gestionnaire de lettres pour la gestion des actions liées aux lettres.
     */
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

    /**
     * Réinitialiser le jeu en choisissant un nouveau mot aléatoire et réinitialisant les listes et les erreurs.
     */
    public void reinitialiserJeu() {
        this.motADeviner = obtenirMotAleatoire();
        this.lettresCorrectes = new ArrayList<>();
        this.lettresIncorrectes = new ArrayList<>();
        this.erreurs = 0;
    }

    /**
     * Obtenir la liste des lettres correctement devinées.
     * @return La liste des lettres correctement devinées.
     */
    public List<Character> getLettresCorrectes() {
        return lettresCorrectes;
    }

    /**
     * Obtenir la liste des lettres incorrectes devinées.
     * @return La liste des lettres incorrectes devinées.
     */
    public List<Character> getLettresIncorrectes() {
        return lettresIncorrectes;
    }

    /**
     * Obtenir le mot à deviner.
     * @return Le mot à deviner.
     */
    public String getMotADeviner() {
        return motADeviner;
    }

    /**
     * Obtenir le nombre d'erreurs commises.
     * @return Le nombre d'erreurs commises.
     */
    public int getErreurs() {
        return erreurs;
    }

    /**
     * Obtenir le mot masqué, où les lettres non devinées sont remplacées par des underscores.
     * @return Le mot masqué.
     */
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

    /**
     * Vérifier si une lettre a déjà été traitée (correctement ou incorrectement).
     * @param lettre La lettre à vérifier.
     * @return true si la lettre a déjà été traitée, sinon false.
     */
    public boolean estLettreDejaTraitee(char lettre) {
        return lettresCorrectes.contains(lettre) || lettresIncorrectes.contains(lettre);
    }

    /**
     * Vérifier si une lettre est correcte dans le mot à deviner.
     * @param lettre La lettre à vérifier.
     * @return true si la lettre est correcte, sinon false.
     */
    public boolean estLettreCorrecte(char lettre) {
        return motADeviner.indexOf(lettre) != -1;
    }

    /**
     * Incrémenter le nombre d'erreurs.
     */
    public void incrementerErreurs() {
        erreurs++;
    }

    /**
     * Obtenir la liste des lettres déjà proposées.
     * @return La liste des lettres déjà proposées.
     */
    public List<Character> getLettresProposees() {
        List<Character> lettresProposees = new ArrayList<>();
        lettresProposees.addAll(lettresCorrectes);
        lettresProposees.addAll(lettresIncorrectes);
        return lettresProposees;
    }

    /**
     * Vérifier si une lettre a déjà été proposée (correctement ou incorrectement).
     * @param lettre La lettre à vérifier.
     * @return true si la lettre a déjà été proposée, sinon false.
     */
    public boolean estLettreDejaProposee(char lettre) {
        return lettresCorrectes.contains(lettre) || lettresIncorrectes.contains(lettre);
    }
}
