package fr.ecole3il.rodez2023.mots;


public class GestionnaireLettre {

    private GestionnaireJeu gestionnaireJeu;

    public GestionnaireLettre(GestionnaireJeu gestionnaireJeu) {
        this.gestionnaireJeu = gestionnaireJeu;
    }

    public void traiterLettre(char lettre) {
        gestionnaireJeu.traiterLettre(lettre, this);
    }

    public void lettreCorrecte() {
        // Logique à effectuer en cas de lettre correcte
        System.out.println("Lettre correcte !");
        // Mettez à jour votre interface graphique ici, si nécessaire
    }

    public void lettreIncorrecte() {

        System.out.println("Lettre incorrecte !");
        gestionnaireJeu.incrementerErreurs();
    }
}

