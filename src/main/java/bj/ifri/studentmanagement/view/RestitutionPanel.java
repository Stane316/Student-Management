package bj.ifri.studentmanagement.view;

import bj.ifri.studentmanagement.model.Etudiant;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Panneau de restitution affichant les fiches synthétiques des étudiants enregistrés.
 */
public class RestitutionPanel extends JPanel {

    private static final Color COULEUR_FOND = new Color(250, 252, 255);
    private static final Color COULEUR_TITRE = new Color(31, 78, 121);
    private static final Color COULEUR_TEXTE_AIDE = new Color(100, 110, 120);
    private static final Color COULEUR_BORDURE_SCROLL = new Color(220, 225, 230);

    private final JPanel listePanel;
    private final JLabel lblCompteur;
    private int nombreEtudiants;

    public RestitutionPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(COULEUR_FOND);
        setBorder(new EmptyBorder(16, 16, 16, 16));

        JLabel titre = new JLabel("Étudiants enregistrés", SwingConstants.CENTER);
        titre.setFont(new Font("SansSerif", Font.BOLD, 22));
        titre.setForeground(COULEUR_TITRE);

        lblCompteur = new JLabel("Aucun étudiant enregistré pour le moment.", SwingConstants.CENTER);
        lblCompteur.setFont(new Font("SansSerif", Font.ITALIC, 12));
        lblCompteur.setForeground(COULEUR_TEXTE_AIDE);
        lblCompteur.setBorder(new EmptyBorder(4, 0, 0, 0));

        JPanel entete = new JPanel(new BorderLayout(4, 4));
        entete.setOpaque(false);
        entete.add(titre, BorderLayout.NORTH);
        entete.add(lblCompteur, BorderLayout.SOUTH);
        entete.setBorder(new EmptyBorder(0, 0, 8, 0));

        listePanel = new JPanel();
        listePanel.setLayout(new BoxLayout(listePanel, BoxLayout.Y_AXIS));
        listePanel.setBackground(COULEUR_FOND);
        listePanel.setBorder(new EmptyBorder(8, 8, 8, 8));

        JPanel conteneur = new JPanel(new BorderLayout());
        conteneur.setBackground(COULEUR_FOND);
        conteneur.add(listePanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(conteneur);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COULEUR_BORDURE_SCROLL, 1, true),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(COULEUR_FOND);

        add(entete, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        nombreEtudiants++;

        EtudiantCardPanel carte = new EtudiantCardPanel(etudiant, nombreEtudiants);
        carte.setAlignmentX(Component.LEFT_ALIGNMENT);

        if (nombreEtudiants > 1) {
            listePanel.add(Box.createVerticalStrut(12));
        }
        listePanel.add(carte);

        mettreAJourCompteur();
        listePanel.revalidate();
        listePanel.repaint();
        carte.setMaximumSize(new Dimension(Integer.MAX_VALUE, carte.getPreferredSize().height));
        listePanel.revalidate();
        listePanel.repaint();
    }

    private void mettreAJourCompteur() {
        String libelle = nombreEtudiants > 1 ? " étudiants enregistrés" : " étudiant enregistré";
        lblCompteur.setText(nombreEtudiants + libelle + " — les fiches les plus récentes sont en bas de la liste.");
    }

    public int getNombreEtudiants() {
        return nombreEtudiants;
    }
}
