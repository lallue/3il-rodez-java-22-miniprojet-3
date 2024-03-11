package fr.ecole3il.rodez2023.jeu;

import fr.ecole3il.rodez2023.mots.GestionnaireJeu;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class JeuDuPendueUI extends JFrame {

    private JLabel motLabel;
    private JButton nouvellePartieButton;
    private GestionnaireJeu gestionnaireJeu;

    public JeuDuPendueUI(GestionnaireJeu gestionnaireJeu) {
        this.gestionnaireJeu = gestionnaireJeu;
        initUI();
    }

    private void initUI() {
        motLabel = new JLabel("<html> Mot à deviner <br> Appuyer sur Nouvelle Partie pour commencer<html>");
        motLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        motLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nouvellePartieButton = new JButton("Nouvelle Partie");
        nouvellePartieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String motADeviner = gestionnaireJeu.obtenirMotAleatoire();
                motLabel.setText(motADeviner);
            }
        });

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(motLabel, BorderLayout.CENTER);
        container.add(nouvellePartieButton, BorderLayout.SOUTH);
        setTitle("Jeu du Pendu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
