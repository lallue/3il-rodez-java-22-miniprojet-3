package fr.ecole3il.rodez2023.mots;

/**
 * La classe GestionnaireLettre est responsable du traitement des lettres proposées par le joueur.
 * Elle communique avec le GestionnaireJeu pour effectuer les actions nécessaires en fonction
 * de la validité de la lettre proposée.
 */
public class GestionnaireLettre {

    private GestionnaireJeu gestionnaireJeu; // Le gestionnaire de jeu associé

    /**
     * Constructeur de la classe GestionnaireLettre.
     * @param gestionnaireJeu Le gestionnaire de jeu associé.
     */
    public GestionnaireLettre(GestionnaireJeu gestionnaireJeu) {
        this.gestionnaireJeu = gestionnaireJeu;
    }

    /**
     * Traiter une lettre proposée par le joueur.
     * @param lettre La lettre proposée.
     */
    public void traiterLettre(char lettre) {
        gestionnaireJeu.traiterLettre(lettre, this);
    }

    /**
     * Méthode appelée en cas de lettre correctement devinée.
     * Effectue les actions nécessaires en cas de lettre correcte.
     * Par exemple, afficher un message ou mettre à jour l'interface graphique.
     */
    public void lettreCorrecte() {
        // Logique à effectuer en cas de lettre correcte
        System.out.println("Lettre correcte !");
        // Mettez à jour votre interface graphique ici, si nécessaire
    }

    /**
     * Méthode appelée en cas de lettre incorrecte devinée.
     * Effectue les actions nécessaires en cas de lettre incorrecte.
     * Par exemple, afficher un message ou mettre à jour l'interface graphique.
     */
    public void lettreIncorrecte() {
        // Logique à effectuer en cas de lettre incorrecte
        System.out.println("Lettre incorrecte !");
        gestionnaireJeu.incrementerErreurs();
    }
}
