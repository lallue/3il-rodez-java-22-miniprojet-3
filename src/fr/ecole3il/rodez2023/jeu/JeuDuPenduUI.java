package fr.ecole3il.rodez2023.jeu;

import fr.ecole3il.rodez2023.mots.GestionnaireJeu;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javax.swing.*;
import java.awt.*;

public class JeuDuPenduUI extends JFrame {

    private JLabel motLabel;
    private JButton nouvellePartieButton;
    private GestionnaireJeu gestionnaireJeu;
    private AffichagePendu affichagePendu;

    public JeuDuPenduUI(GestionnaireJeu gestionnaireJeu, AffichagePendu affichagePendu) {
        this.gestionnaireJeu = gestionnaireJeu;
        this.affichagePendu = affichagePendu;
        initUI();
    }

    private void initUI() {
        motLabel = new JLabel("Mot à deviner");
        motLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        motLabel.setHorizontalAlignment(SwingConstants.CENTER);

        nouvellePartieButton = new JButton("Nouvelle Partie");
        nouvellePartieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                affichagePendu.repaint();
                String motADeviner = gestionnaireJeu.obtenirMotAleatoire();
                motLabel.setText(motADeviner);
            }
        });

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(motLabel, BorderLayout.CENTER);
        container.add(nouvellePartieButton, BorderLayout.SOUTH);
        container.add(affichagePendu, BorderLayout.NORTH);

        setTitle("Jeu du Pendu");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

