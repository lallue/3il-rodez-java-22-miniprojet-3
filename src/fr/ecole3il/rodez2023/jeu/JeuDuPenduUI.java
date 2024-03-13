package fr.ecole3il.rodez2023.jeu;

import fr.ecole3il.rodez2023.mots.GestionnaireJeu;
import fr.ecole3il.rodez2023.mots.GestionnaireLettre;
import fr.ecole3il.rodez2023.jeu.GestionJeuPendu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe JeuDuPenduUI représente l'interface utilisateur du jeu du pendu.
 * Elle permet de jouer au jeu du pendu en proposant des lettres pour deviner un mot caché.
 */
public class JeuDuPenduUI extends JFrame {

    private JLabel motLabel; // Label pour afficher le mot caché
    private JLabel lettresProposeesLabel; // Label pour afficher les lettres déjà proposées
    private JLabel timerLabel; // Label pour afficher le timer
    public JButton envoyerLettreButton; // Bouton pour envoyer une lettre
    private JButton nouvellePartieButton; // Bouton pour démarrer une nouvelle partie
    private JTextField lettreTextField; // Champ de texte pour entrer une lettre
    private GestionnaireJeu gestionnaireJeu; // Gestionnaire du jeu du pendu
    private AffichagePendu affichagePendu; // Affichage graphique du pendu
    private GestionnaireLettre gestionnaireLettre; // Gestionnaire des lettres proposées
    private GestionJeuPendu gestionJeuPendu; // Gestion du jeu du pendu

    public boolean partieEnCours = false; // Indicateur de partie en cours
    private int vies = 8; // Nombre de vies (erreurs autorisées)
    private Timer timer; // Timer pour le temps de jeu
    private int tempsRestant = 60; // Temps initial en secondes

    /**
     * Constructeur de la classe JeuDuPenduUI.
     * Initialise l'interface utilisateur du jeu du pendu avec les gestionnaires de jeu et d'affichage associés.
     * @param gestionnaireJeu Gestionnaire du jeu du pendu.
     * @param affichagePendu Affichage graphique du pendu.
     * @param gestionnaireLettre Gestionnaire des lettres proposées.
     */
    public JeuDuPenduUI(GestionnaireJeu gestionnaireJeu, AffichagePendu affichagePendu, GestionnaireLettre gestionnaireLettre) {
        this.gestionnaireJeu = gestionnaireJeu;
        this.affichagePendu = affichagePendu;
        this.gestionnaireLettre = gestionnaireLettre;
        initUI();
        gestionJeuPendu = new GestionJeuPendu(this);
    }

    /**
     * Initialise l'interface utilisateur du jeu du pendu.
     * Crée et dispose les composants graphiques nécessaires.
     */
    private void initUI() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        motLabel = new JLabel("Bienvenue dans le Jeu du Pendu!");
        motLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        motLabel.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(motLabel, BorderLayout.CENTER);

        envoyerLettreButton = new JButton("Envoyer");
        envoyerLettreButton.addActionListener(e -> envoyerLettre());

        nouvellePartieButton = new JButton("Commencer");
        nouvellePartieButton.addActionListener(e -> {
            if (!partieEnCours) {
                commencerPartie();
            } else {
                nouvellePartie();
            }
        });

        JPanel boutonsPanel = new JPanel();
        boutonsPanel.setLayout(new GridLayout(1, 2));
        boutonsPanel.add(envoyerLettreButton);
        boutonsPanel.add(nouvellePartieButton);

        JPanel boutonsEtLettresPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        boutonsEtLettresPanel.add(boutonsPanel, gbc);

        gbc.gridy = 1;
        lettresProposeesLabel = new JLabel("Lettres déjà proposées : ");
        boutonsEtLettresPanel.add(lettresProposeesLabel, gbc);

        gbc.gridy = 2;
        timerLabel = new JLabel("Temps restant: " + tempsRestant + " secondes");
        boutonsEtLettresPanel.add(timerLabel, gbc);

        container.add(boutonsEtLettresPanel, BorderLayout.SOUTH);

        affichagePendu.setPreferredSize(new Dimension(200, 300));
        container.add(affichagePendu, BorderLayout.NORTH);

        setTitle("Jeu du Pendu");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Démarre une nouvelle partie du jeu du pendu.
     */
    private void commencerPartie() {
        partieEnCours = true;
        motLabel.setText(gestionnaireJeu.getMotMasque());
        nouvellePartieButton.setText("Nouvelle Partie");
        envoyerLettreButton.setEnabled(true);

        affichagePendu.reset();
        vies = 8;

        lettreTextField = new JTextField(1);
        lettreTextField.addActionListener(e -> envoyerLettre());
        getContentPane().add(lettreTextField, BorderLayout.EAST);

        gestionJeuPendu.demarrerTimer();
        demarrerTimer();
    }

    /**
     * Traite l'envoi d'une lettre par le joueur.
     */
    private void envoyerLettre() {
        String lettre = lettreTextField.getText().toUpperCase();
        if (lettre.length() == 1) {
            if (!gestionnaireJeu.estLettreDejaProposee(lettre.charAt(0))) {
                if (vies > 0) {
                    gestionnaireLettre.traiterLettre(lettre.charAt(0));
                    mettreAJourMotLabel();
                    mettreAJourLettresProposees();
                    lettreTextField.setText("");
                    if (!gestionnaireJeu.getMotMasque().contains(lettre)) {
                        vies--;
                        affichagePendu.incrementErreurs();
                        if (vies == 0) {
                            gameOver();
                        }
                    }
                } else {
                    gameOver();
                }
            } else {
                JOptionPane.showMessageDialog(JeuDuPenduUI.this, "Cette lettre a déjà été proposée.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(JeuDuPenduUI.this, "Veuillez entrer une seule lettre.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Affiche le message de fin de partie en cas de défaite.
     */
    public void gameOver() {
        partieEnCours = false;
        envoyerLettreButton.setEnabled(false);
        JOptionPane.showMessageDialog(JeuDuPenduUI.this, "Désolé, vous avez perdu ! Le mot était : " + gestionnaireJeu.getMotADeviner(), "Game Over", JOptionPane.ERROR_MESSAGE);
        nouvellePartie();
    }

    /**
     * Démarre une nouvelle partie après la fin d'une partie.
     */
    private void nouvellePartie() {
        gestionJeuPendu.arreterTimer();
        gestionnaireJeu.reinitialiserJeu();
        partieEnCours = false;
        motLabel.setText("Bienvenue dans le Jeu du Pendu !");
        nouvellePartieButton.setText("Commencer");
        getContentPane().remove(lettreTextField);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    /**
     * Met à jour l'affichage du mot caché.
     */
    private void mettreAJourMotLabel() {
        motLabel.setText(gestionnaireJeu.getMotMasque());

        if (!gestionnaireJeu.getMotMasque().contains("_")) {
            JOptionPane.showMessageDialog(JeuDuPenduUI.this, "Bravo, vous avez trouvé le mot !", "Félicitations", JOptionPane.INFORMATION_MESSAGE);
            nouvellePartie();
        }
    }

    /**
     * Met à jour l'affichage des lettres déjà proposées.
     */
    private void mettreAJourLettresProposees() {
        StringBuilder lettresProposees = new StringBuilder();
        for (char lettre : gestionnaireJeu.getLettresProposees()) {
            lettresProposees.append(lettre).append(" ");
        }
        lettresProposeesLabel.setText("Lettres déjà proposées : " + lettresProposees.toString());
    }

    /**
     * Démarre le timer pour le temps de jeu.
     */
    public void demarrerTimer() {
        timer = new Timer(1000, e -> {
            tempsRestant--;
            if (tempsRestant >= 0) {
                timerLabel.setText("Temps restant: " + tempsRestant + " secondes");
            } else {
                gameOver();
            }
        });
        timer.start();
    }
}
