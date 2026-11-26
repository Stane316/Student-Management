# Student Management — Gestion des étudiants (IFRI)

Application graphique Java Swing développée dans le cadre du cours de **programmation événementielle** à l'**IFRI (Institut de Formation et de Recherche en Informatique)**.

L'application permet de saisir, enregistrer et afficher dynamiquement les informations des étudiants à travers une interface divisée en deux espaces :
- **Gauche** : formulaire de saisie des informations étudiant.
- **Droite** : restitution synthétique des étudiants enregistrés sous forme de fiches/cartes.

## Technologies

- **Langage** : Java 8 (source/target 1.8)
- **Interface graphique** : Java Swing (`javax.swing`, `java.awt`)
- **Gestion d'événements** : modèle par délégation (`ActionListener`, `MouseAdapter`, `WindowAdapter`)
- **Build** : Ant (projet NetBeans classique)
- **IDE cible** : NetBeans
- **Persistance** : mémoire vive pendant l'exécution (aucune base de données)

Aucune dépendance externe n'est requise en dehors du JDK.

## Prérequis

- **JDK 8** ou supérieur (JDK 8+ recommandé, compilation ciblée Java 8).
- **NetBeans** avec support Ant/Java SE (recommandé pour ouvrir et exécuter le projet).
- Optionnel : **Ant** installé si vous souhaitez compiler en ligne de commande hors NetBeans.

## Structure du projet

```text
Student-Management/
├── build.xml
├── manifest.mf
├── context.md
├── README.md
├── nbproject/
│   ├── build-impl.xml
│   ├── genfiles.properties
│   ├── project.properties
│   └── project.xml
├── src/
│   └── main/
│       └── java/
│           └── bj/
│               └── ifri/
│                   └── studentmanagement/
│                       ├── app/
│                       │   └── Main.java
│                       ├── model/
│                       │   └── Etudiant.java
│                       ├── util/
│                       │   └── Validateur.java
│                       └── view/
│                           ├── EtudiantCardPanel.java
│                           ├── FormulairePanel.java
│                           ├── MainFrame.java
│                           └── RestitutionPanel.java
└── support/
    ├── Cours Partie 1.pdf
    └── Java_Avancee_IFRI P1.pptx
```

## Ouverture dans NetBeans

1. Lancer NetBeans.
2. Menu **File > Open Project...** (ou **Fichier > Ouvrir un projet...**).
3. Sélectionner le dossier racine `Student-Management`.
4. NetBeans doit reconnaître le projet grâce à `nbproject/` et `build.xml`.
5. Vérifier que le JDK configuré est un JDK 8+.

## Compilation

### Dans NetBeans
- Clic droit sur le projet > **Build** (ou **Clean and Build**).

### En ligne de commande (avec Ant)
Depuis la racine du projet :

```bash
ant clean jar
```

### En ligne de commande (avec javac uniquement)
Depuis la racine du projet :

```bash
mkdir -p build/classes
javac --release 8 -encoding UTF-8 -d build/classes $(find src/main/java -name "*.java")
```

## Exécution

### Dans NetBeans
- Clic droit sur le projet > **Run** (ou bouton **Run Project**).
- La classe principale est : `bj.ifri.studentmanagement.app.Main`.

### En ligne de commande (après compilation javac)

```bash
java -cp build/classes bj.ifri.studentmanagement.app.Main
```

### Avec le JAR (après `ant clean jar`)

```bash
java -jar dist/Student-Management.jar
```

## Fonctionnalités actuelles (Implémentation 1)

- Interface principale divisée en deux zones (formulaire à gauche, restitution à droite).
- Formulaire étudiant : Nom, Prénom, Matricule, Sexe, Filière, Niveau, Année académique, Statut, E-mail, Téléphone.
- Bouton **Enregistrer** : validation des champs, création d'un étudiant, ajout en mémoire, affichage immédiat d'une fiche synthétique à droite, réinitialisation du formulaire après succès.
- Bouton **Effacer** : réinitialisation complète du formulaire sans supprimer les étudiants enregistrés.
- Bouton **Quitter** : confirmation avant fermeture de l'application.
- Changement de couleur des boutons au survol et au clic via `MouseAdapter`.
- Confirmation de fermeture également sur la croix de la fenêtre.

## Notes pédagogiques

Le projet privilégie les notions enseignées dans les supports de cours :
- Swing / `javax.swing`
- Modèle événementiel par délégation
- `ActionListener`, `MouseAdapter`, `WindowAdapter`
- Gestionnaires de disposition (`BorderLayout`, `GridBagLayout`, `FlowLayout`, `BoxLayout`)
- `JOptionPane`, `JScrollPane`, `JSplitPane`
- `SwingUtilities.invokeLater`

## Documents de référence

- `context.md` : cahier de référence du projet.
- `support/` : supports pédagogiques du cours.
