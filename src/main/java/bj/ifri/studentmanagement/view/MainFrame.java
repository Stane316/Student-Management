package bj.ifri.studentmanagement.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Fenêtre principale de l'application de gestion des étudiants.
 */
public class MainFrame extends JFrame {

    private final FormulairePanel formulairePanel;
    private final RestitutionPanel restitutionPanel;

    public MainFrame() {
        super("Gestion des Étudiants - IFRI");
        initialiserFenetre();
        restitutionPanel = new RestitutionPanel();
        formulairePanel = new FormulairePanel(restitutionPanel::ajouterEtudiant);
        construireInterface();
        getRootPane().setDefaultButton(formulairePanel.getBtnEnregistrer());
    }

    private void initialiserFenetre() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new Dimension(1100, 700));
        setSize(1250, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmerFermeture();
            }
        });
    }

    private void construireInterface() {
        JPanel entete = new JPanel(new BorderLayout());
        entete.setBackground(new Color(31, 78, 121));
        entete.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel titre = new JLabel("Système de Gestion des Étudiants", SwingConstants.CENTER);
        titre.setFont(new Font("SansSerif", Font.BOLD, 26));
        titre.setForeground(Color.WHITE);

        JLabel sousTitre = new JLabel("Institut de Formation et de Recherche en Informatique (IFRI)",
                SwingConstants.CENTER);
        sousTitre.setFont(new Font("SansSerif", Font.PLAIN, 14));
        sousTitre.setForeground(new Color(220, 230, 245));

        entete.add(titre, BorderLayout.CENTER);
        entete.add(sousTitre, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formulairePanel, restitutionPanel);
        splitPane.setDividerLocation(560);
        splitPane.setResizeWeight(0.45);
        splitPane.setContinuousLayout(true);
        splitPane.setOneTouchExpandable(true);
        splitPane.setBorder(BorderFactory.createEmptyBorder());

        add(entete, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
    }

    private void confirmerFermeture() {
        int choix = JOptionPane.showConfirmDialog(
                this,
                "Voulez-vous vraiment quitter l'application ?",
                "Confirmation de fermeture",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (choix == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }
}
