package fr.ecole3il.rodez2023.jeu;

import fr.ecole3il.rodez2023.mots.GestionnaireJeu;
import fr.ecole3il.rodez2023.mots.GestionnaireLettre;
import fr.ecole3il.rodez2023.jeu.GestionJeuPendu ;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JeuDuPenduUI extends JFrame {

    private JLabel motLabel;
    private JLabel lettresProposeesLabel;
    private JLabel timerLabel; // Nouveau JLabel pour afficher le timer
    public JButton envoyerLettreButton;
    private JButton nouvellePartieButton;
    private JTextField lettreTextField;
    private GestionnaireJeu gestionnaireJeu;
    private AffichagePendu affichagePendu;
    private GestionnaireLettre gestionnaireLettre;
    private GestionJeuPendu gestionJeuPendu;
    
    public boolean partieEnCours = false;
    private int vies = 8;
    private Timer timer;
    private int tempsRestant = 60; // Temps initial en secondes

    public JeuDuPenduUI(GestionnaireJeu gestionnaireJeu, AffichagePendu affichagePendu, GestionnaireLettre gestionnaireLettre) {
        this.gestionnaireJeu = gestionnaireJeu;
        this.affichagePendu = affichagePendu;
        this.gestionnaireLettre = gestionnaireLettre;
        initUI();
        gestionJeuPendu = new GestionJeuPendu(this);
    }

    private void initUI() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        motLabel = new JLabel("Bienvenue dans le Jeu du Pendu !");
        motLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        motLabel.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(motLabel, BorderLayout.CENTER);

        envoyerLettreButton = new JButton("Envoyer");
        envoyerLettreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                envoyerLettre();
            }
        });

        nouvellePartieButton = new JButton("Commencer");
        nouvellePartieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!partieEnCours) {
                    commencerPartie();
                } else {
                    nouvellePartie();
                }
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
        
        // Ajout du JLabel pour afficher le timer
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

    private void commencerPartie() {
        partieEnCours = true;
        motLabel.setText(gestionnaireJeu.getMotMasque());
        nouvellePartieButton.setText("Nouvelle Partie");
        envoyerLettreButton.setEnabled(true);

        affichagePendu.reset();
        vies = 8;

        lettreTextField = new JTextField(1);
        lettreTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                envoyerLettre();
            }
        });
        getContentPane().add(lettreTextField, BorderLayout.EAST);

        gestionJeuPendu.demarrerTimer();
        demarrerTimer();
    }

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

    public void gameOver() {
        partieEnCours = false;
        envoyerLettreButton.setEnabled(false);
        JOptionPane.showMessageDialog(JeuDuPenduUI.this, "Désolé, vous avez perdu ! Le mot était : " + gestionnaireJeu.getMotADeviner(), "Game Over", JOptionPane.ERROR_MESSAGE);
        nouvellePartie();
    }

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

    private void mettreAJourMotLabel() {
        motLabel.setText(gestionnaireJeu.getMotMasque());

        if (!gestionnaireJeu.getMotMasque().contains("_")) {
            JOptionPane.showMessageDialog(JeuDuPenduUI.this, "Bravo, vous avez trouvé le mot !", "Félicitations", JOptionPane.INFORMATION_MESSAGE);
            nouvellePartie();
        }
    }

    private void mettreAJourLettresProposees() {
        StringBuilder lettresProposees = new StringBuilder();
        for (char lettre : gestionnaireJeu.getLettresProposees()) {
            lettresProposees.append(lettre).append(" ");
        }
        lettresProposeesLabel.setText("Lettres déjà proposées : " + lettresProposees.toString());
    }

    // Méthode pour démarrer le timer
    public void demarrerTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tempsRestant--;
                if (tempsRestant >= 0) {
                    timerLabel.setText("Temps restant: " + tempsRestant + " secondes");
                } else {
                    gameOver();
                }
            }
        });
        timer.start();
    }
}
