package bj.ifri.studentmanagement.view;

import bj.ifri.studentmanagement.model.Etudiant;
import bj.ifri.studentmanagement.util.Validateur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * Panneau de formulaire pour la saisie des informations d'un étudiant.
 */
public class FormulairePanel extends JPanel {

    private static final Color COULEUR_FOND = new Color(245, 247, 250);
    private static final Color COULEUR_TITRE = new Color(31, 78, 121);
    private static final Color COULEUR_TEXTE_AIDE = new Color(90, 100, 110);
    private static final Color COULEUR_BORDURE = new Color(210, 218, 226);
    private static final Color COULEUR_ERREUR = new Color(211, 47, 47);
    private static final Color COULEUR_SUCCES = new Color(46, 125, 50);

    private static final Color BTN_ENREGISTRER_NORMAL = new Color(46, 125, 50);
    private static final Color BTN_ENREGISTRER_SURVOL = new Color(67, 160, 71);
    private static final Color BTN_ENREGISTRER_ACTIF = new Color(27, 94, 32);

    private static final Color BTN_EFFACER_NORMAL = new Color(96, 125, 139);
    private static final Color BTN_EFFACER_SURVOL = new Color(120, 144, 156);
    private static final Color BTN_EFFACER_ACTIF = new Color(69, 90, 100);

    private static final Color BTN_QUITTER_NORMAL = new Color(198, 40, 40);
    private static final Color BTN_QUITTER_SURVOL = new Color(229, 57, 53);
    private static final Color BTN_QUITTER_ACTIF = new Color(183, 28, 28);

    private static final Font POLICE_LABEL = new Font("SansSerif", Font.BOLD, 13);
    private static final Font POLICE_CHAMP = new Font("SansSerif", Font.PLAIN, 13);
    private static final Font POLICE_BOUTON = new Font("SansSerif", Font.BOLD, 13);

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
    private final JLabel lblFeedback;

    private final Consumer<Etudiant> onEnregistrer;
    private final Predicate<String> matriculeExiste;
    private final Border bordureChampNormale;
    private final Border bordureComboNormale;

    public FormulairePanel(Consumer<Etudiant> onEnregistrer, Predicate<String> matriculeExiste) {
        this.onEnregistrer = onEnregistrer;
        this.matriculeExiste = matriculeExiste;

        setLayout(new BorderLayout(10, 10));
        setBackground(COULEUR_FOND);
        setBorder(new EmptyBorder(16, 16, 16, 16));

        JLabel titre = new JLabel("Informations de l'étudiant", SwingConstants.CENTER);
        titre.setFont(new Font("SansSerif", Font.BOLD, 22));
        titre.setForeground(COULEUR_TITRE);

        JLabel aide = new JLabel("Tous les champs sont obligatoires. Vérifiez les informations avant l'enregistrement.",
                SwingConstants.CENTER);
        aide.setFont(new Font("SansSerif", Font.PLAIN, 12));
        aide.setForeground(COULEUR_TEXTE_AIDE);
        aide.setBorder(new EmptyBorder(4, 0, 0, 0));

        JPanel entete = new JPanel(new BorderLayout());
        entete.setOpaque(false);
        entete.add(titre, BorderLayout.CENTER);
        entete.add(aide, BorderLayout.SOUTH);
        entete.setBorder(new EmptyBorder(0, 0, 8, 0));

        JPanel formulaire = new JPanel(new GridBagLayout());
        formulaire.setBackground(Color.WHITE);
        formulaire.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COULEUR_BORDURE, 1, true),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));

        txtNom = creerChampTexte("Ex. : ANIAM");
        txtPrenom = creerChampTexte("Ex. : Stane-Junior");
        txtMatricule = creerChampTexte("Ex. : IFRI-2026-001");
        txtAnneeAcademique = creerChampTexte("Ex. : 2026-2027");
        txtAnneeAcademique.setText("2026-2027");
        txtEmail = creerChampTexte("Ex. : nom.prenom@ifri.bj");
        txtTelephone = creerChampTexte("Ex. : +229 01 97 96 59 78");
        bordureChampNormale = txtNom.getBorder();

        rbMasculin = creerRadioBouton("Masculin");
        rbFeminin = creerRadioBouton("Féminin");
        groupeSexe = new ButtonGroup();
        groupeSexe.add(rbMasculin);
        groupeSexe.add(rbFeminin);
        rbMasculin.setSelected(true);

        JPanel panneauSexe = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        panneauSexe.setBackground(Color.WHITE);
        panneauSexe.add(rbMasculin);
        panneauSexe.add(rbFeminin);

        cbFiliere = creerComboBox(new String[]{
                "-- Sélectionner une filière --",
                "Génie Logiciel",
                "Sécurité Informatique",
                "Internet et Multimédia",
                "Systèmes Embarqués et Internet des Objets",
                "Intelligence Artificielle"
        });
        bordureComboNormale = cbFiliere.getBorder();

        cbNiveau = creerComboBox(new String[]{
                "-- Sélectionner un niveau --",
                "L1", "L2", "L3"
        });

        cbStatut = creerComboBox(new String[]{
                "-- Sélectionner un statut --",
                "Étudiant",
                "Délégué",
                "Ancien étudiant"
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 6, 7, 6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int ligne = 0;
        ajouterChamp(formulaire, gbc, ligne++, "Nom", txtNom, true);
        ajouterChamp(formulaire, gbc, ligne++, "Prénom", txtPrenom, true);
        ajouterChamp(formulaire, gbc, ligne++, "Matricule", txtMatricule, true);
        ajouterChamp(formulaire, gbc, ligne++, "Sexe", panneauSexe, true);
        ajouterChamp(formulaire, gbc, ligne++, "Filière", cbFiliere, true);
        ajouterChamp(formulaire, gbc, ligne++, "Niveau", cbNiveau, true);
        ajouterChamp(formulaire, gbc, ligne++, "Année académique", txtAnneeAcademique, true);
        ajouterChamp(formulaire, gbc, ligne++, "Statut", cbStatut, true);
        ajouterChamp(formulaire, gbc, ligne++, "Adresse e-mail", txtEmail, true);
        ajouterChamp(formulaire, gbc, ligne++, "Téléphone", txtTelephone, true);

        lblFeedback = new JLabel(" ");
        lblFeedback.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblFeedback.setForeground(COULEUR_ERREUR);
        lblFeedback.setBorder(new EmptyBorder(8, 4, 0, 4));

        JPanel wrapperFormulaire = new JPanel(new BorderLayout());
        wrapperFormulaire.setBackground(Color.WHITE);
        wrapperFormulaire.add(formulaire, BorderLayout.NORTH);
        wrapperFormulaire.add(lblFeedback, BorderLayout.SOUTH);

        JScrollPane scrollFormulaire = new JScrollPane(wrapperFormulaire);
        scrollFormulaire.setBorder(BorderFactory.createEmptyBorder());
        scrollFormulaire.getViewport().setBackground(Color.WHITE);
        scrollFormulaire.getVerticalScrollBar().setUnitIncrement(16);

        btnEnregistrer = creerBouton("Enregistrer", 'E', "Valider et enregistrer l'étudiant",
                BTN_ENREGISTRER_NORMAL, BTN_ENREGISTRER_SURVOL, BTN_ENREGISTRER_ACTIF);
        btnEffacer = creerBouton("Effacer", 'F', "Réinitialiser tous les champs du formulaire",
                BTN_EFFACER_NORMAL, BTN_EFFACER_SURVOL, BTN_EFFACER_ACTIF);
        btnQuitter = creerBouton("Quitter", 'Q', "Fermer l'application",
                BTN_QUITTER_NORMAL, BTN_QUITTER_SURVOL, BTN_QUITTER_ACTIF);

        JPanel panneauBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panneauBoutons.setOpaque(false);
        panneauBoutons.setBorder(new EmptyBorder(8, 0, 0, 0));
        panneauBoutons.add(btnEnregistrer);
        panneauBoutons.add(btnEffacer);
        panneauBoutons.add(btnQuitter);

        ajouterGestionEvenements();

        add(entete, BorderLayout.NORTH);
        add(scrollFormulaire, BorderLayout.CENTER);
        add(panneauBoutons, BorderLayout.SOUTH);
    }

    private JTextField creerChampTexte(String infobulle) {
        JTextField champ = new JTextField();
        champ.setFont(POLICE_CHAMP);
        champ.setToolTipText(infobulle);
        champ.setPreferredSize(new Dimension(260, 32));
        champ.setMargin(new Insets(4, 8, 4, 8));
        return champ;
    }

    private JRadioButton creerRadioBouton(String texte) {
        JRadioButton radio = new JRadioButton(texte);
        radio.setBackground(Color.WHITE);
        radio.setFont(POLICE_CHAMP);
        radio.setFocusPainted(false);
        return radio;
    }

    private JComboBox<String> creerComboBox(String[] elements) {
        JComboBox<String> comboBox = new JComboBox<String>(elements);
        comboBox.setFont(POLICE_CHAMP);
        comboBox.setBackground(Color.WHITE);
        comboBox.setPreferredSize(new Dimension(260, 32));
        return comboBox;
    }

    private void ajouterChamp(JPanel formulaire, GridBagConstraints gbc, int ligne,
                              String libelle, JComponent champ, boolean obligatoire) {
        String texteLabel = obligatoire ? libelle + " * :" : libelle + " :";
        JLabel label = new JLabel(texteLabel);
        label.setFont(POLICE_LABEL);
        label.setLabelFor(champ);

        gbc.gridy = ligne;

        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        formulaire.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formulaire.add(champ, gbc);
    }

    private JButton creerBouton(String texte, char mnemonique, String infobulle,
                                Color couleurNormale, Color couleurSurvol, Color couleurActive) {
        final JButton bouton = new JButton(texte) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        bouton.setFont(POLICE_BOUTON);
        bouton.setForeground(Color.WHITE);
        bouton.setBackground(couleurNormale);
        bouton.setFocusPainted(false);
        bouton.setBorderPainted(false);
        bouton.setContentAreaFilled(false);
        bouton.setOpaque(false);
        bouton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bouton.setMnemonic(mnemonique);
        bouton.setToolTipText(infobulle);
        bouton.setPreferredSize(new Dimension(132, 38));
        bouton.setMargin(new Insets(8, 14, 8, 14));

        appliquerEffetSurvol(bouton, couleurNormale, couleurSurvol, couleurActive);
        return bouton;
    }

    private void appliquerEffetSurvol(final JButton bouton, final Color couleurNormale,
                                      final Color couleurSurvol, final Color couleurActive) {
        bouton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (bouton.isEnabled()) {
                    bouton.setBackground(couleurSurvol);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (bouton.isEnabled()) {
                    bouton.setBackground(couleurNormale);
                }
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
        reinitialiserStylesValidation();

        String nom = Validateur.normaliserTexte(txtNom.getText());
        String prenom = Validateur.normaliserTexte(txtPrenom.getText());
        String matricule = Validateur.normaliserMatricule(txtMatricule.getText());
        String sexe = rbMasculin.isSelected() ? "Masculin" : "Féminin";
        String filiere = (String) cbFiliere.getSelectedItem();
        String niveau = (String) cbNiveau.getSelectedItem();
        String anneeAcademique = Validateur.normaliserTexte(txtAnneeAcademique.getText());
        String statut = (String) cbStatut.getSelectedItem();
        String email = Validateur.normaliserEmail(txtEmail.getText());
        String telephone = Validateur.normaliserTelephone(txtTelephone.getText());

        if (!Validateur.estNonVide(nom)) {
            afficherErreur("Le champ « Nom » est obligatoire. Veuillez saisir le nom de famille de l'étudiant.", txtNom);
            return;
        }
        if (!Validateur.estNonVide(prenom)) {
            afficherErreur("Le champ « Prénom » est obligatoire. Veuillez saisir le prénom de l'étudiant.", txtPrenom);
            return;
        }
        if (!Validateur.estNonVide(matricule)) {
            afficherErreur("Le champ « Matricule » est obligatoire. Veuillez saisir l'identifiant académique.", txtMatricule);
            return;
        }
        if (matriculeExiste != null && matriculeExiste.test(matricule)) {
            afficherErreur("Le matricule « " + matricule + " » est déjà enregistré pour cette session. Veuillez saisir un matricule unique.", txtMatricule);
            return;
        }
        if (!estSelectionValide(filiere)) {
            afficherErreur("La filière n'a pas été sélectionnée. Veuillez choisir une filière dans la liste.", cbFiliere);
            return;
        }
        if (!estSelectionValide(niveau)) {
            afficherErreur("Le niveau n'a pas été sélectionné. Veuillez choisir L1, L2 ou L3.", cbNiveau);
            return;
        }
        if (!Validateur.estNonVide(anneeAcademique)) {
            afficherErreur("Le champ « Année académique » est obligatoire. Exemple attendu : 2026-2027.", txtAnneeAcademique);
            return;
        }
        if (!Validateur.estAnneeAcademiqueValide(anneeAcademique)) {
            afficherErreur("Année académique invalide. Utilisez le format AAAA-AAAA avec deux années consécutives (ex. : 2026-2027).", txtAnneeAcademique);
            return;
        }
        if (!estSelectionValide(statut)) {
            afficherErreur("Le statut n'a pas été sélectionné. Veuillez choisir le statut de l'étudiant.", cbStatut);
            return;
        }
        if (!Validateur.estEmailValide(email)) {
            afficherErreur("L'adresse e-mail est invalide. Exemple attendu : nom.prenom@ifri.bj.", txtEmail);
            return;
        }
        if (!Validateur.estTelephoneValide(telephone)) {
            afficherErreur("Le numéro de téléphone est invalide. Utilisez 8 à 20 caractères (chiffres, espaces, +, -, parenthèses).", txtTelephone);
            return;
        }

        Etudiant etudiant = new Etudiant(
                nom, prenom, matricule, sexe, filiere, niveau,
                anneeAcademique, statut, email, telephone
        );

        if (onEnregistrer != null) {
            onEnregistrer.accept(etudiant);
        }

        afficherSucces("Étudiant enregistré : " + prenom + " " + nom + " (" + matricule + ").");
        JOptionPane.showMessageDialog(
                this,
                "L'étudiant " + prenom + " " + nom + " a été enregistré avec succès.\n"
                        + "La fiche a été ajoutée dans l'espace de restitution.",
                "Enregistrement réussi",
                JOptionPane.INFORMATION_MESSAGE
        );

        reinitialiserFormulaire();
    }

    private boolean estSelectionValide(String valeur) {
        return valeur != null && !valeur.startsWith("--");
    }

    private void afficherErreur(String message, JComponent composant) {
        marquerChampErreur(composant);
        lblFeedback.setForeground(COULEUR_ERREUR);
        lblFeedback.setText(message);
        JOptionPane.showMessageDialog(
                this,
                message,
                "Validation du formulaire",
                JOptionPane.WARNING_MESSAGE
        );
        composant.requestFocusInWindow();
    }

    private void afficherSucces(String message) {
        lblFeedback.setForeground(COULEUR_SUCCES);
        lblFeedback.setText(message);
    }

    private void marquerChampErreur(JComponent composant) {
        composant.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COULEUR_ERREUR, 2, true),
                BorderFactory.createEmptyBorder(4, 6, 4, 6)
        ));
    }

    private void reinitialiserStylesValidation() {
        restaurerBordure(txtNom);
        restaurerBordure(txtPrenom);
        restaurerBordure(txtMatricule);
        restaurerBordure(txtAnneeAcademique);
        restaurerBordure(txtEmail);
        restaurerBordure(txtTelephone);
        restaurerBordureCombo(cbFiliere);
        restaurerBordureCombo(cbNiveau);
        restaurerBordureCombo(cbStatut);
        lblFeedback.setForeground(COULEUR_ERREUR);
        lblFeedback.setText(" ");
    }

    private void restaurerBordure(JComponent champ) {
        if (bordureChampNormale != null) {
            champ.setBorder(bordureChampNormale);
        }
    }

    private void restaurerBordureCombo(JComboBox<String> comboBox) {
        if (bordureComboNormale != null) {
            comboBox.setBorder(bordureComboNormale);
        }
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
        reinitialiserStylesValidation();
        txtNom.requestFocusInWindow();
    }

    public JButton getBtnEnregistrer() {
        return btnEnregistrer;
    }

    private void quitterApplication() {
        int choix = JOptionPane.showConfirmDialog(
                this,
                "Voulez-vous vraiment quitter l'application ?\nLes données non sauvegardées dans un fichier seront perdues à la fermeture.",
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
