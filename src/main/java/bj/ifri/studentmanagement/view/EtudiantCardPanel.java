package bj.ifri.studentmanagement.view;

import bj.ifri.studentmanagement.model.Etudiant;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Carte synthétique affichant les informations d'un étudiant enregistré.
 */
public class EtudiantCardPanel extends JPanel {

    private static final Color COULEUR_FOND = new Color(245, 249, 255);
    private static final Color COULEUR_BORDURE = new Color(180, 205, 230);
    private static final Color COULEUR_TITRE = new Color(31, 78, 121);

    public EtudiantCardPanel(Etudiant etudiant, int numero) {
        setLayout(new GridBagLayout());
        setBackground(COULEUR_FOND);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COULEUR_BORDURE, 1, true),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 4, 2, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        JLabel lblTitre = new JLabel(String.format("Étudiant %02d", numero));
        lblTitre.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTitre.setForeground(COULEUR_TITRE);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 4, 8, 4);
        add(lblTitre, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(2, 4, 2, 4);

        int ligne = 1;
        ajouterLigne(gbc, ligne++, "Nom", etudiant.getNom());
        ajouterLigne(gbc, ligne++, "Prénom", etudiant.getPrenom());
        ajouterLigne(gbc, ligne++, "Matricule", etudiant.getMatricule());
        ajouterLigne(gbc, ligne++, "Filière", etudiant.getFiliere());
        ajouterLigne(gbc, ligne++, "Niveau", etudiant.getNiveau());
        ajouterLigne(gbc, ligne++, "Année académique", etudiant.getAnneeAcademique());
        ajouterLigne(gbc, ligne++, "Statut", etudiant.getStatut());
    }

    private void ajouterLigne(GridBagConstraints gbc, int ligne, String libelle, String valeur) {
        JLabel lblLibelle = new JLabel(libelle + " : ");
        lblLibelle.setFont(new Font("SansSerif", Font.BOLD, 12));

        JLabel lblValeur = new JLabel(valeur);
        lblValeur.setFont(new Font("SansSerif", Font.PLAIN, 12));

        gbc.gridy = ligne;

        gbc.gridx = 0;
        gbc.weightx = 0;
        add(lblLibelle, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        add(lblValeur, gbc);
    }
}
