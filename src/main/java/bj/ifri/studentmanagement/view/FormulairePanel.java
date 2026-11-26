package bj.ifri.studentmanagement.view;

import bj.ifri.studentmanagement.model.Etudiant;
import bj.ifri.studentmanagement.util.Validateur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Panneau de formulaire pour la saisie des informations d'un étudiant.
 */
public class FormulairePanel extends JPanel {

    private static final Color COULEUR_FOND = new Color(245, 247, 250);
    private static final Color COULEUR_TITRE = new Color(31, 78, 121);
    private static final Color COULEUR_BORDURE = new Color(200, 210, 220);

    private static final Color BTN_ENREGISTRER_NORMAL = new Color(46, 125, 50);
    private static final Color BTN_ENREGISTRER_SURVOL = new Color(76, 175, 80);
    private static final Color BTN_ENREGISTRER_ACTIF = new Color(27, 94, 32);

    private static final Color BTN_EFFACER_NORMAL = new Color(120, 144, 156);
    private static final Color BTN_EFFACER_SURVOL = new Color(144, 164, 174);
    private static final Color BTN_EFFACER_ACTIF = new Color(84, 110, 122);

    private static final Color BTN_QUITTER_NORMAL = new Color(198, 40, 40);
    private static final Color BTN_QUITTER_SURVOL = new Color(229, 57, 53);
    private static final Color BTN_QUITTER_ACTIF = new Color(183, 28, 28);

    private final JTextField txtNom;
    private final JTextField txtPrenom;
    private final JTextField txtMatricule;
    private final JRadioButton rbMasculin;
    private final JRadioButton rbFeminin;
    private final ButtonGroup groupeSexe;
    private final JComboBox<String> cbFiliere;
    private final JComboBox<String> cbNiveau;
    private final JTextField txtAnneeAcademique;
    private final JComboBox<String> cbStatut;
    private final JTextField txtEmail;
    private final JTextField txtTelephone;

    private final JButton btnEnregistrer;
    private final JButton btnEffacer;
    private final JButton btnQuitter;

    private final Consumer<Etudiant> onEnregistrer;

    public FormulairePanel(Consumer<Etudiant> onEnregistrer) {
        this.onEnregistrer = onEnregistrer;

        setLayout(new BorderLayout(10, 10));
        setBackground(COULEUR_FOND);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel titre = new JLabel("Informations de l'étudiant", JLabel.CENTER);
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(COULEUR_TITRE);
        titre.setBorder(new EmptyBorder(0, 0, 10, 0));

        JPanel formulaire = new JPanel(new GridBagLayout());
        formulaire.setBackground(Color.WHITE);
        formulaire.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COULEUR_BORDURE, 1, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        txtNom = creerChampTexte();
        txtPrenom = creerChampTexte();
        txtMatricule = creerChampTexte();
        txtAnneeAcademique = creerChampTexte();
        txtAnneeAcademique.setText("2026-2027");
        txtEmail = creerChampTexte();
        txtTelephone = creerChampTexte();

        rbMasculin = new JRadioButton("Masculin");
        rbFeminin = new JRadioButton("Féminin");
        rbMasculin.setBackground(Color.WHITE);
        rbFeminin.setBackground(Color.WHITE);
        rbMasculin.setFont(new Font("SansSerif", Font.PLAIN, 13));
        rbFeminin.setFont(new Font("SansSerif", Font.PLAIN, 13));
        groupeSexe = new ButtonGroup();
        groupeSexe.add(rbMasculin);
        groupeSexe.add(rbFeminin);
        rbMasculin.setSelected(true);

        JPanel panneauSexe = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panneauSexe.setBackground(Color.WHITE);
        panneauSexe.add(rbMasculin);
        panneauSexe.add(rbFeminin);

        cbFiliere = creerComboBox(new String[]{
                "-- Sélectionner --",
                "Génie Logiciel",
                "Sécurité Informatique",
                "Internet et Multimédia",
                "Systèmes Embarqués et Internet des Objets",
                "Intelligence Artificielle"
        });

        cbNiveau = creerComboBox(new String[]{
                "-- Sélectionner --",
                "L1", "L2", "L3"
        });

        cbStatut = creerComboBox(new String[]{
                "-- Sélectionner --",
                "Étudiant",
                "Délégué",
                "Ancien étudiant"
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int ligne = 0;
        ajouterChamp(formulaire, gbc, ligne++, "Nom", txtNom);
        ajouterChamp(formulaire, gbc, ligne++, "Prénom", txtPrenom);
        ajouterChamp(formulaire, gbc, ligne++, "Matricule", txtMatricule);
        ajouterChamp(formulaire, gbc, ligne++, "Sexe", panneauSexe);
        ajouterChamp(formulaire, gbc, ligne++, "Filière", cbFiliere);
        ajouterChamp(formulaire, gbc, ligne++, "Niveau", cbNiveau);
        ajouterChamp(formulaire, gbc, ligne++, "Année académique", txtAnneeAcademique);
        ajouterChamp(formulaire, gbc, ligne++, "Statut", cbStatut);
        ajouterChamp(formulaire, gbc, ligne++, "Adresse e-mail", txtEmail);
        ajouterChamp(formulaire, gbc, ligne++, "Téléphone", txtTelephone);

        btnEnregistrer = creerBouton("Enregistrer", BTN_ENREGISTRER_NORMAL,
                BTN_ENREGISTRER_SURVOL, BTN_ENREGISTRER_ACTIF);
        btnEffacer = creerBouton("Effacer", BTN_EFFACER_NORMAL,
                BTN_EFFACER_SURVOL, BTN_EFFACER_ACTIF);
        btnQuitter = creerBouton("Quitter", BTN_QUITTER_NORMAL,
                BTN_QUITTER_SURVOL, BTN_QUITTER_ACTIF);

        JPanel panneauBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panneauBoutons.setBackground(COULEUR_FOND);
        panneauBoutons.add(btnEnregistrer);
        panneauBoutons.add(btnEffacer);
        panneauBoutons.add(btnQuitter);

        ajouterGestionEvenements();

        add(titre, BorderLayout.NORTH);
        add(formulaire, BorderLayout.CENTER);
        add(panneauBoutons, BorderLayout.SOUTH);
    }

    private JTextField creerChampTexte() {
        JTextField champ = new JTextField();
        champ.setFont(new Font("SansSerif", Font.PLAIN, 13));
        champ.setPreferredSize(new Dimension(220, 30));
        return champ;
    }

    private JComboBox<String> creerComboBox(String[] elements) {
        JComboBox<String> comboBox = new JComboBox<String>(elements);
        comboBox.setFont(new Font("SansSerif", Font.PLAIN, 13));
        comboBox.setBackground(Color.WHITE);
        return comboBox;
    }

    private void ajouterChamp(JPanel formulaire, GridBagConstraints gbc, int ligne,
                              String libelle, java.awt.Component champ) {
        JLabel label = new JLabel(libelle + " :");
        label.setFont(new Font("SansSerif", Font.BOLD, 13));

        gbc.gridy = ligne;

        gbc.gridx = 0;
        gbc.weightx = 0;
        formulaire.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formulaire.add(champ, gbc);
    }

    private JButton creerBouton(String texte, Color couleurNormale,
                                Color couleurSurvol, Color couleurActive) {
        JButton bouton = new JButton(texte);
        bouton.setFont(new Font("SansSerif", Font.BOLD, 13));
        bouton.setForeground(Color.WHITE);
        bouton.setBackground(couleurNormale);
        bouton.setFocusPainted(false);
        bouton.setBorderPainted(false);
        bouton.setOpaque(true);
        bouton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bouton.setPreferredSize(new Dimension(120, 36));

        appliquerEffetSurvol(bouton, couleurNormale, couleurSurvol, couleurActive);
        return bouton;
    }

    private void appliquerEffetSurvol(final JButton bouton, final Color couleurNormale,
                                      final Color couleurSurvol, final Color couleurActive) {
        bouton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bouton.setBackground(couleurSurvol);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bouton.setBackground(couleurNormale);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (bouton.isEnabled()) {
                    bouton.setBackground(couleurActive);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (bouton.isEnabled()) {
                    if (bouton.contains(e.getPoint())) {
                        bouton.setBackground(couleurSurvol);
                    } else {
                        bouton.setBackground(couleurNormale);
                    }
                }
            }
        });
    }

    private void ajouterGestionEvenements() {
        btnEnregistrer.addActionListener(e -> enregistrerEtudiant());
        btnEffacer.addActionListener(e -> reinitialiserFormulaire());
        btnQuitter.addActionListener(e -> quitterApplication());
    }

    private void enregistrerEtudiant() {
        String nom = txtNom.getText().trim();
        String prenom = txtPrenom.getText().trim();
        String matricule = txtMatricule.getText().trim();
        String sexe = rbMasculin.isSelected() ? "Masculin" : "Féminin";
        String filiere = (String) cbFiliere.getSelectedItem();
        String niveau = (String) cbNiveau.getSelectedItem();
        String anneeAcademique = txtAnneeAcademique.getText().trim();
        String statut = (String) cbStatut.getSelectedItem();
        String email = txtEmail.getText().trim();
        String telephone = txtTelephone.getText().trim();

        if (!Validateur.estNonVide(nom)) {
            afficherErreur("Le nom est obligatoire.", txtNom);
            return;
        }
        if (!Validateur.estNonVide(prenom)) {
            afficherErreur("Le prénom est obligatoire.", txtPrenom);
            return;
        }
        if (!Validateur.estNonVide(matricule)) {
            afficherErreur("Le matricule est obligatoire.", txtMatricule);
            return;
        }
        if (!estSelectionValide(filiere)) {
            afficherErreur("Veuillez sélectionner une filière.", cbFiliere);
            return;
        }
        if (!estSelectionValide(niveau)) {
            afficherErreur("Veuillez sélectionner un niveau.", cbNiveau);
            return;
        }
        if (!Validateur.estNonVide(anneeAcademique)) {
            afficherErreur("L'année académique est obligatoire.", txtAnneeAcademique);
            return;
        }
        if (!estSelectionValide(statut)) {
            afficherErreur("Veuillez sélectionner un statut.", cbStatut);
            return;
        }
        if (!Validateur.estEmailValide(email)) {
            afficherErreur("Veuillez saisir une adresse e-mail valide.", txtEmail);
            return;
        }
        if (!Validateur.estTelephoneValide(telephone)) {
            afficherErreur("Veuillez saisir un numéro de téléphone valide.", txtTelephone);
            return;
        }

        Etudiant etudiant = new Etudiant(
                nom, prenom, matricule, sexe, filiere, niveau,
                anneeAcademique, statut, email, telephone
        );

        if (onEnregistrer != null) {
            onEnregistrer.accept(etudiant);
        }

        JOptionPane.showMessageDialog(
                this,
                "L'étudiant " + prenom + " " + nom + " a été enregistré avec succès.",
                "Enregistrement réussi",
                JOptionPane.INFORMATION_MESSAGE
        );

        reinitialiserFormulaire();
    }

    private boolean estSelectionValide(String valeur) {
        return valeur != null && !valeur.startsWith("--");
    }

    private void afficherErreur(String message, java.awt.Component composant) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Erreur de validation",
                JOptionPane.ERROR_MESSAGE
        );
        composant.requestFocusInWindow();
    }

    private void reinitialiserFormulaire() {
        txtNom.setText("");
        txtPrenom.setText("");
        txtMatricule.setText("");
        rbMasculin.setSelected(true);
        cbFiliere.setSelectedIndex(0);
        cbNiveau.setSelectedIndex(0);
        txtAnneeAcademique.setText("2026-2027");
        cbStatut.setSelectedIndex(0);
        txtEmail.setText("");
        txtTelephone.setText("");
        txtNom.requestFocusInWindow();
    }

    private void quitterApplication() {
        int choix = JOptionPane.showConfirmDialog(
                this,
                "Voulez-vous vraiment quitter l'application ?",
                "Confirmation de fermeture",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (choix == JOptionPane.YES_OPTION) {
            java.awt.Window fenetre = javax.swing.SwingUtilities.getWindowAncestor(this);
            if (fenetre != null) {
                fenetre.dispose();
            }
            System.exit(0);
        }
    }
}
