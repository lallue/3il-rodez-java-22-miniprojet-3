package fr.ecole3il.rodez2023.jeu;

import fr.ecole3il.rodez2023.mots.GestionnaireJeu;
import fr.ecole3il.rodez2023.mots.GestionnaireLettre;
import fr.ecole3il.rodez2023.mots.LecteurMots;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class JeuDuPenduUI extends JFrame {

    private JLabel motLabel;
    private JButton nouvellePartieButton;
    private GestionnaireJeu gestionnaireJeu;
    private AffichagePendu affichagePendu;
    private GestionnaireLettre gestionnaireLettre;

    public JeuDuPenduUI(GestionnaireJeu gestionnaireJeu, AffichagePendu affichagePendu, GestionnaireLettre gestionnaireLettre) {
        this.gestionnaireJeu = gestionnaireJeu;
        this.affichagePendu = affichagePendu;
        this.gestionnaireLettre = gestionnaireLettre;
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
                gestionnaireJeu.reinitialiserJeu();
                mettreAJourMotLabel();
            }
        });

        JTextField lettreTextField = new JTextField();
        lettreTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() >= 'A' && e.getKeyChar() <= 'Z') {
                    gestionnaireLettre.traiterLettre(e.getKeyChar());
                    lettreTextField.setText("");
                    mettreAJourMotLabel();
                }
            }
        });

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(motLabel, BorderLayout.CENTER);
        container.add(nouvellePartieButton, BorderLayout.SOUTH);
        container.add(affichagePendu, BorderLayout.NORTH);
        container.add(lettreTextField, BorderLayout.EAST);

        setTitle("Jeu du Pendu");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        mettreAJourMotLabel();
    }

    private void mettreAJourMotLabel() {
        motLabel.setText(gestionnaireJeu.getMotMasque());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String nomFichier = "mots.txt";
                LecteurMots lecteurMots = new LecteurMots(nomFichier);
                List<String> motsList = lecteurMots.getMotsList();

                GestionnaireJeu gestionnaireJeu = new GestionnaireJeu(motsList);
                AffichagePendu affichagePendu = new AffichagePendu();
                GestionnaireLettre gestionnaireLettre = new GestionnaireLettre(gestionnaireJeu);

                JeuDuPenduUI jeuDuPenduUI = new JeuDuPenduUI(gestionnaireJeu, affichagePendu, gestionnaireLettre);
                jeuDuPenduUI.setVisible(true);
            }
        });
    }
}

