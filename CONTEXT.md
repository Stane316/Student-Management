# CONTEXT.md
# Projet pédagogique — Application de gestion et d'affichage des informations étudiantes

**Document :** Contexte général et cahier de référence du projet  
**Établissement :** IFRI — Institut de Formation et de Recherche en Informatique  
**Cours :** Programmation événementielle  
**Environnement de développement cible :** NetBeans  
**Technologies envisagées :** Java, interface graphique et technologies de présentation adaptées au support de cours  
**Dépôt GitHub :** Source de vérité du projet  
**Statut :** Document de référence initial — évolutif

---

# 1. Présentation générale du projet

## 1.1. Contexte pédagogique

Ce projet est réalisé dans le cadre du cours de **programmation événementielle** dispensé à l'IFRI.

Il ne s'agit pas d'un projet logiciel indépendant, d'un exercice de développement web généraliste ou d'une application destinée à être commercialisée.

Il s'agit avant tout d'un travail pédagogique dont la finalité est de mettre en pratique les notions, les mécanismes et les techniques abordés dans le cours.

L'application devra donc être conçue de manière à démontrer concrètement la compréhension de la programmation événementielle.

Chaque fonctionnalité devra, autant que possible, illustrer une notion effectivement abordée dans le support pédagogique.

Le code ne devra pas uniquement produire le résultat visuel attendu : il devra également témoigner d'une bonne compréhension des événements, de leurs déclencheurs, de leurs gestionnaires et des interactions entre les composants de l'interface.

## 1.2. Objectif général

Développer une application graphique permettant de recueillir, d'enregistrer et d'afficher les informations relatives aux étudiants de l'IFRI.

L'application sera organisée autour de deux espaces visuellement distincts :

- **Espace gauche :** formulaire de saisie des informations d'un étudiant.
- **Espace droit :** zone de restitution synthétique des informations enregistrées.

L'utilisateur doit pouvoir renseigner les informations d'un étudiant, déclencher leur enregistrement, visualiser immédiatement le résultat dans la partie droite et réinitialiser le formulaire.

L'ensemble des interactions devra être piloté par des événements.

## 1.3. Résultat attendu

À la fin du développement, nous devons disposer d'une application fonctionnelle, cohérente, visuellement soignée et directement exploitable dans le cadre d'une démonstration en classe.

L'application devra notamment permettre de démontrer :

1. La création d'une interface graphique structurée.
2. La gestion des événements déclenchés par les interactions utilisateur.
3. L'association de composants graphiques à des gestionnaires d'événements.
4. La récupération et le traitement des données saisies.
5. La mise à jour dynamique d'une interface.
6. La gestion de plusieurs composants interactifs.
7. La réinitialisation d'un formulaire.
8. La restitution des données enregistrées.

---

# 2. Principes directeurs du développement

Les règles suivantes sont fondamentales et doivent guider toutes les décisions techniques.

## 2.1. Respect absolu du support pédagogique

Le support de cours constitue la référence pédagogique prioritaire.

Avant toute implémentation, l'outil de développement devra :

- Examiner intégralement les documents pédagogiques disponibles dans le dépôt.
- Identifier les notions effectivement enseignées.
- Comprendre les exemples de code utilisés par l'enseignant.
- Identifier les bibliothèques, frameworks et environnements présentés.
- Repérer les conventions de programmation employées.
- Identifier les méthodes et mécanismes attendus dans les exercices.
- Comprendre le niveau de complexité correspondant aux enseignements.

Il ne devra pas substituer systématiquement des approches modernes ou sophistiquées aux techniques enseignées.

**Règle essentielle : une fonctionnalité doit être implémentée en privilégiant les mécanismes étudiés dans le cours.**

Si plusieurs solutions sont possibles, celle qui illustre le mieux les notions enseignées doit être privilégiée.

Les technologies supplémentaires ne pourront être introduites que lorsqu'elles sont réellement nécessaires et compatibles avec les objectifs pédagogiques.

## 2.2. Priorité à la compréhension pédagogique

Le projet doit rester compréhensible par un étudiant suivant le cours.

Cela implique :

- Un code lisible.
- Des noms de variables explicites.
- Des méthodes correctement organisées.
- Une séparation raisonnable des responsabilités.
- Des gestionnaires d'événements facilement identifiables.
- Des commentaires pertinents lorsque nécessaire.
- Une complexité proportionnée au niveau d'apprentissage.

Éviter les abstractions inutiles, les architectures excessivement complexes et les dépendances qui ne contribuent pas directement à la réalisation du projet.

L'objectif n'est pas de démontrer une maîtrise artificielle de nombreuses technologies, mais de produire une application propre et pédagogiquement défendable.

## 2.3. Séparation entre exigences obligatoires et améliorations

Le développement sera organisé selon deux niveaux.

**Niveau 1 — Exigences fondamentales**

Toutes les fonctionnalités explicitement demandées dans le projet doivent être réalisées et testées.

**Niveau 2 — Améliorations complémentaires**

Une fois le niveau fondamental entièrement fonctionnel, certaines améliorations pourront être envisagées :

- Validation plus poussée des données.
- Présentation plus élaborée des étudiants enregistrés.
- Animations et transitions.
- Compteur du nombre d'étudiants.
- Indicateurs visuels.
- Gestion plus avancée des interactions.
- Amélioration de l'expérience utilisateur.

Ces améliorations ne doivent jamais compromettre la simplicité, la conformité au cours ou la stabilité de la version fondamentale.

---

# 3. Description fonctionnelle de l'application

## 3.1. Organisation générale de l'interface

L'application doit présenter une interface principale divisée horizontalement en deux grandes portions.

La séparation doit être clairement visible.

### Partie gauche : formulaire étudiant

Cette partie est consacrée à la collecte des informations.

Elle contient un formulaire permettant de renseigner les caractéristiques d'un étudiant.

### Partie droite : espace de restitution

Cette partie reçoit les informations des étudiants enregistrés.

Chaque nouvel enregistrement doit entraîner une mise à jour immédiate de cette zone, sans nécessiter le redémarrage de l'application.

L'organisation doit permettre de comprendre immédiatement le fonctionnement de l'application.

## 3.2. Informations à recueillir

Le formulaire devra proposer les champs suivants :

| Champ | Description |
|---|---|
| Nom | Nom de famille de l'étudiant |
| Prénom | Prénom(s) de l'étudiant |
| Matricule | Identifiant académique |
| Sexe | Sexe de l'étudiant |
| Filière | Formation suivie |
| Niveau | L1, L2 ou L3 |
| Année académique | Année universitaire concernée |
| Statut | Situation académique de l'étudiant |
| Adresse e-mail | Adresse électronique |
| Téléphone | Numéro de contact |

Cette liste constitue une base fonctionnelle. Les champs devront être adaptés si le support de cours ou les consignes de l'enseignant imposent une autre structure.

Pour les champs dont les valeurs sont prédéfinies, privilégier des composants de sélection plutôt que des champs de saisie libres.

Exemples :

- Niveau : L1, L2, L3.
- Statut : étudiant, délégué, ancien étudiant ou autre statut pertinent.
- Sexe : valeurs adaptées aux exigences du formulaire.

Ne pas multiplier inutilement les champs. L'objectif reste de réaliser un formulaire pédagogique cohérent.

## 3.3. Boutons et actions principales

Trois actions principales sont prévues.

### A. Bouton Enregistrer

Lorsqu'un utilisateur clique sur ce bouton :

1. L'événement de clic est détecté.
2. Le gestionnaire associé est exécuté.
3. Les valeurs du formulaire sont récupérées.
4. Les données sont contrôlées selon les règles de validation retenues.
5. Un nouvel étudiant est créé à partir des informations valides.
6. L'étudiant est ajouté à la collection des étudiants enregistrés.
7. La partie droite de l'interface est actualisée.
8. Le formulaire peut être réinitialisé après un enregistrement réussi, selon le comportement retenu.

L'opération doit être immédiate.

Aucun rechargement général de l'application ne doit être nécessaire.

### B. Bouton Effacer

Ce bouton permet de réinitialiser entièrement le formulaire.

Lorsqu'il est déclenché :

- Tous les champs textuels sont vidés.
- Les listes de sélection retrouvent leur valeur initiale.
- Les cases à cocher ou autres composants éventuels sont réinitialisés.
- Les éventuels messages de validation associés au formulaire sont supprimés.

**Important :** l'effacement du formulaire ne doit pas supprimer les étudiants déjà enregistrés dans la partie droite.

Cette distinction doit être respectée.

### C. Bouton Quitter

Ce bouton permet de fermer proprement l'application.

Il devra être associé à un gestionnaire d'événement spécifique.

Si des données non enregistrées risquent d'être perdues, une confirmation pourra être proposée.

---

# 4. Restitution des informations étudiantes

## 4.1. Principe de fonctionnement

La partie droite constitue l'espace de visualisation des étudiants enregistrés.

Chaque enregistrement doit apparaître sous une forme synthétique et facilement identifiable.

L'affichage ne doit pas reproduire intégralement le formulaire.

Il doit plutôt prendre la forme de petites fiches, cartes ou lignes structurées.

## 4.2. Exemple de restitution

Exemple illustratif :

**Étudiant 01**

- Nom : ANIAM
- Prénom : Stane-Junior
- Filière : Génie Logiciel
- Niveau : L2
- Année académique : 2026-2027
- Statut : Étudiant

**Étudiant 02**

- Nom : DUPONT
- Prénom : Jean
- Filière : Intelligence Artificielle
- Niveau : L1
- Année académique : 2026-2027
- Statut : Étudiant

Les informations affichées devront être organisées de manière compacte, lisible et visuellement cohérente.

Le numéro de chaque étudiant sera généré automatiquement lors de l'enregistrement.

## 4.3. Mise à jour dynamique

L'affichage doit être actualisé automatiquement à chaque nouvel enregistrement.

La partie droite doit donc refléter l'état réel de la collection des étudiants.

Les données déjà enregistrées ne doivent pas disparaître lorsqu'un nouvel étudiant est ajouté.

Dans la version fondamentale, les données peuvent être conservées en mémoire pendant l'exécution de l'application.

La persistance dans un fichier ou une base de données ne constitue pas une exigence initiale, sauf indication contraire dans les supports pédagogiques.

---

# 5. Programmation événementielle : exigence centrale

Cette section est particulièrement importante.

Le projet doit être conçu comme une véritable application événementielle.

La programmation événementielle ne doit pas être simplement évoquée dans les commentaires : elle doit être concrètement identifiable dans le fonctionnement et dans le code source.

## 5.1. Événements attendus

Les événements principaux comprennent :

| Interaction | Événement attendu | Conséquence |
|---|---|---|
| Clic sur Enregistrer | Clic utilisateur | Enregistrement de l'étudiant |
| Clic sur Effacer | Clic utilisateur | Réinitialisation du formulaire |
| Clic sur Quitter | Clic utilisateur | Fermeture de l'application |
| Modification d'un champ | Événement de modification | Mise à jour ou contrôle de la valeur |
| Sélection d'une option | Événement de sélection | Prise en compte de la valeur sélectionnée |
| Survol d'un bouton, si pertinent | Événement de souris | Modification visuelle du bouton |

Les événements devront être associés aux gestionnaires appropriés, conformément aux mécanismes étudiés dans le cours.

## 5.2. Gestion des événements et changement de couleur

Une exigence visuelle particulière concerne les boutons.

Lorsqu'un utilisateur interagit avec un bouton, un changement de couleur doit être observable.

Par exemple :

- Couleur initiale : couleur normale du bouton.
- Au survol : couleur intermédiaire.
- Au clic : couleur active.
- Après l'interaction : retour à l'état normal ou maintien d'un état cohérent selon le comportement choisi.

L'objectif est de démontrer que les interactions utilisateur peuvent modifier dynamiquement l'apparence des composants.

Ces changements devront être déclenchés par de véritables événements, et non être uniquement décoratifs.

## 5.3. Exploitation des notions du cours

L'outil de développement devra établir une correspondance entre les notions pédagogiques et les fonctionnalités de l'application.

Il devra notamment examiner si les notions suivantes sont présentes dans les supports :

- Événements.
- Sources d'événements.
- Écouteurs ou listeners.
- Gestionnaires d'événements.
- Interfaces graphiques.
- Composants interactifs.
- Événements de souris.
- Événements de clavier.
- Événements de sélection.
- Gestion des actions.
- Modèle événementiel.

Cette liste est indicative : elle ne constitue pas une affirmation selon laquelle toutes ces notions ont nécessairement été enseignées.

Les concepts réellement abordés dans les documents pédagogiques devront déterminer les choix d'implémentation.

---

# 6. Exigences relatives à l'interface graphique

## 6.1. Qualité visuelle

L'application devra présenter un rendu professionnel, moderne et soigné, tout en restant adapté à un projet académique.

L'interface ne doit pas ressembler à un simple formulaire brut assemblé sans réflexion graphique.

Les éléments suivants devront être travaillés :

- Hiérarchie visuelle.
- Alignement des composants.
- Espacement.
- Typographie.
- Couleurs.
- Lisibilité.
- Cohérence des boutons.
- Distinction entre les deux espaces.
- Gestion des états interactifs.
- Adaptation de l'interface à la taille de la fenêtre.

## 6.2. Technologies de présentation

Le choix des technologies devra être effectué après analyse des supports du cours.

La priorité est donnée aux technologies compatibles avec les exigences pédagogiques et l'environnement de développement de l'enseignant.

Si le cours repose sur Java et les interfaces graphiques Java, privilégier les mécanismes graphiques correspondants.

Si le support enseigne une autre technologie de présentation, elle devra être examinée avant de prendre une décision.

L'utilisation de HTML et CSS ne doit pas être considérée comme automatique : elle dépendra de l'architecture réellement retenue.

Les animations et transitions sont souhaitables lorsqu'elles sont compatibles avec le cadre pédagogique.

**Ne pas sacrifier la conformité au cours au profit d'effets visuels.**

---

# 7. Environnement de développement et ouverture du projet

## 7.1. Environnement cible

Le projet doit être développé de manière à être facilement ouvert, compilé et exécuté dans NetBeans.

NetBeans est l'environnement privilégié, car l'enseignant possède une certaine aisance avec cet IDE.

Eclipse constitue une alternative éventuelle, mais la compatibilité NetBeans est prioritaire.

## 7.2. Structure de projet attendue

Le dépôt devra contenir un véritable projet organisé selon les conventions de l'environnement retenu.

Si le choix technique est Java, l'outil devra privilégier un projet NetBeans standard, par exemple :

```text
student-management/
│
├── context.md
├── README.md
├── support/
│   └── documents-pedagogiques/
│
├── src/
│   └── main/
│       └── java/
│           └── ...
│
├── nbproject/
│   ├── project.xml
│   └── project.properties
│
├── build.xml
│
└── ...
```

Cette structure est indicative et devra être adaptée au système de construction choisi.

Pour un projet Java NetBeans classique utilisant Ant, les fichiers de configuration NetBeans et `build.xml` devront permettre l'ouverture et la compilation du projet.

Une architecture Maven peut également être retenue si elle est compatible avec les exigences du cours et l'environnement de l'enseignant.

## 7.3. Expérience d'ouverture

L'objectif est que l'enseignant puisse :

1. Récupérer le dépôt.
2. Ouvrir le dossier du projet dans NetBeans.
3. Identifier immédiatement le projet.
4. Compiler le projet.
5. Exécuter l'application.
6. Observer directement le résultat.

Il ne doit pas être nécessaire de reconstruire manuellement toute la configuration.

Le projet devra contenir les fichiers de configuration nécessaires à son ouverture dans l'IDE.

Un simple ensemble de fichiers sources isolés n'est pas suffisant.

## 7.4. Documentation technique

Un fichier `README.md` devra expliquer :

- La présentation du projet.
- Les prérequis.
- La technologie utilisée.
- La structure des dossiers.
- La procédure d'ouverture dans NetBeans.
- La procédure de compilation.
- La procédure d'exécution.

Les instructions devront être vérifiées sur le projet réellement produit.

---

# 8. Architecture et organisation du code

L'architecture doit rester proportionnée au projet et au niveau du cours.

Il est préférable d'avoir quelques classes clairement organisées plutôt qu'une architecture excessivement sophistiquée.

Une séparation fonctionnelle peut être envisagée entre :

- Le point d'entrée de l'application.
- La représentation des données d'un étudiant.
- La fenêtre principale.
- Le formulaire de saisie.
- L'espace de restitution.
- Les gestionnaires d'événements.

La répartition définitive dépendra du contenu du support pédagogique.

Les responsabilités doivent être compréhensibles.

Éviter :

- Une classe principale contenant absolument toute la logique.
- La duplication inutile du code.
- Les gestionnaires d'événements excessivement longs.
- Les variables aux noms ambigus.
- Les dépendances non nécessaires.
- Les mécanismes qui masquent le fonctionnement pédagogique des événements.

---

# 9. Gestion des données et validation

Les données saisies doivent faire l'objet d'un contrôle élémentaire.

Exigences fondamentales :

- Le nom ne doit pas être vide.
- Le prénom ne doit pas être vide.
- Les champs indispensables doivent être renseignés.
- Les sélections doivent correspondre aux valeurs prévues.
- Les données doivent être récupérées correctement.
- Un enregistrement invalide ne doit pas être ajouté à la liste.

Les messages d'erreur devront être compréhensibles.

Une validation plus avancée pourra être envisagée après réalisation des fonctionnalités fondamentales.

La gestion des doublons, la recherche, la modification et la suppression d'étudiants enregistrés ne sont pas des exigences initiales.

Elles ne doivent pas être ajoutées au détriment des fonctionnalités demandées.

---

# 10. Périmètre du projet

## 10.1. Fonctionnalités obligatoires

| Fonctionnalité | Priorité |
|---|---|
| Interface divisée en deux espaces | Obligatoire |
| Formulaire de renseignements étudiant | Obligatoire |
| Saisie des informations | Obligatoire |
| Bouton Enregistrer | Obligatoire |
| Bouton Effacer | Obligatoire |
| Bouton Quitter | Obligatoire |
| Gestion événementielle des boutons | Obligatoire |
| Changement de couleur lors des interactions | Obligatoire |
| Affichage synthétique des étudiants | Obligatoire |
| Actualisation dynamique de l'affichage | Obligatoire |
| Conservation des étudiants pendant la session | Obligatoire |
| Ouverture du projet dans NetBeans | Obligatoire |
| Compilation et exécution fonctionnelles | Obligatoire |

## 10.2. Améliorations complémentaires

Ces éléments ne doivent être abordés qu'après validation du socle obligatoire.

- Animations de transition.
- Compteur d'étudiants enregistrés.
- Indicateur de nombre d'étudiants.
- Amélioration des fiches de restitution.
- Validation avancée.
- Recherche d'étudiants.
- Modification d'un enregistrement.
- Suppression d'un enregistrement.
- Sauvegarde persistante des données.

Toute amélioration doit être justifiée et rester cohérente avec le cours.

---

# 11. Workflow de développement à distance

Cette section définit le fonctionnement attendu de l'outil de développement.

L'outil interviendra principalement à travers son environnement de développement distant.

Le dépôt GitHub constitue la source de vérité du projet.

## 11.1. Principe de référence unique

Le dépôt GitHub contient les documents de référence, les sources du projet et les configurations nécessaires.

L'outil devra systématiquement considérer la version distante du dépôt comme la référence officielle.

Le workspace distant n'est qu'un environnement de travail.

Il ne doit jamais devenir une copie divergente du projet.

## 11.2. Phase initiale : analyse

Avant toute modification, l'outil devra :

1. Examiner le dépôt GitHub.
2. Identifier la branche de travail.
3. Examiner les fichiers existants.
4. Identifier les documents de contexte.
5. Examiner les supports pédagogiques.
6. Comprendre les objectifs du cours.
7. Identifier les conventions de nommage.
8. Identifier les technologies et méthodes enseignées.
9. Vérifier les éventuelles consignes particulières de l'enseignant.
10. Identifier les contraintes de compilation et d'exécution.

Il devra ensuite produire dans le chat un bilan structuré de sa compréhension.

Aucun développement ne devra commencer avant cette analyse et la validation des décisions techniques importantes.

## 11.3. Phase de préparation

Après analyse, l'outil devra proposer :

- L'architecture technique.
- La structure des fichiers.
- Les composants graphiques nécessaires.
- Les événements à gérer.
- La correspondance entre fonctionnalités et notions du cours.
- La procédure de compilation et d'exécution.
- Les risques techniques identifiés.

Les choix structurants devront être expliqués avant leur mise en œuvre.

## 11.4. Phase d'implémentation

Le développement sera effectué progressivement.

Chaque étape devra correspondre à une fonctionnalité identifiable.

L'outil devra :

- Respecter le périmètre validé.
- Préserver les documents de référence.
- Éviter les modifications non nécessaires.
- Tester les fonctionnalités après leur implémentation.
- Corriger les erreurs avant de poursuivre.
- Vérifier régulièrement la compilation.

## 11.5. Synchronisation GitHub

Après chaque étape cohérente :

1. Vérifier les modifications.
2. Vérifier les fichiers ajoutés, modifiés ou supprimés.
3. Contrôler qu'aucun document de référence n'a été altéré involontairement.
4. Vérifier la compilation.
5. Synchroniser les modifications sur la branche de travail.
6. Vérifier que le dépôt distant contient bien les modifications attendues.

L'outil ne devra jamais écraser silencieusement des modifications existantes.

Il devra signaler les conflits et les résoudre de manière contrôlée.

Les opérations destructrices devront être évitées.

## 11.6. Maintien d'un workspace propre

Le workspace doit rester organisé.

L'outil devra :

- Éviter les fichiers temporaires inutiles.
- Éviter les copies concurrentes du projet.
- Supprimer uniquement les fichiers générés devenus inutiles, après vérification.
- Ne pas conserver des versions obsolètes présentées comme officielles.
- Vérifier la synchronisation avec GitHub avant de terminer une session.

L'objectif est de disposer d'un environnement de travail propre, reproductible et cohérent.

---

# 12. Critères de validation finale

Le projet sera considéré comme terminé lorsque les conditions suivantes seront satisfaites.

## Fonctionnement

- L'application démarre correctement.
- Les deux espaces sont présents.
- Le formulaire est utilisable.
- Les informations sont correctement récupérées.
- L'enregistrement fonctionne.
- Les étudiants apparaissent immédiatement à droite.
- Plusieurs étudiants peuvent être enregistrés successivement.
- Le bouton Effacer réinitialise le formulaire sans supprimer les étudiants enregistrés.
- Le bouton Quitter fonctionne.
- Les interactions entraînent les changements visuels attendus.

## Conformité pédagogique

- Les notions du support sont effectivement exploitées.
- Les événements sont clairement identifiables.
- Les gestionnaires sont correctement associés.
- Le code reste compréhensible.
- Les choix techniques sont compatibles avec le cours.
- Les conventions de l'enseignant sont respectées.

## Qualité technique

- Le projet est correctement structuré.
- Il s'ouvre dans NetBeans.
- Il compile sans erreur.
- Il s'exécute sans erreur bloquante.
- Les fichiers nécessaires sont présents.
- La documentation est cohérente avec le résultat réel.

## Qualité visuelle

- L'interface est lisible.
- Les deux portions sont bien différenciées.
- Les composants sont correctement alignés.
- Les boutons présentent des états visuels cohérents.
- La restitution des étudiants est synthétique et organisée.

---

# 13. Règles de conduite de l'outil de développement

L'outil doit adopter une démarche de développement rigoureuse.

Les règles suivantes sont impératives :

1. Ne pas inventer le contenu du cours.
2. Ne pas supposer qu'une technologie a été enseignée sans vérifier les documents.
3. Ne pas développer avant d'avoir compris les supports.
4. Ne pas remplacer les méthodes pédagogiques par des abstractions inutiles.
5. Ne pas ajouter des fonctionnalités hors périmètre sans justification.
6. Ne pas modifier les documents de référence sans autorisation.
7. Ne pas considérer le workspace comme la source de vérité.
8. Ne pas laisser des modifications locales non synchronisées sans le signaler.
9. Ne pas déclarer le projet terminé sans avoir vérifié sa compilation et son exécution.
10. Ne pas confondre qualité visuelle et complexité technique.
11. Expliquer les décisions techniques importantes.
12. Signaler immédiatement toute contradiction entre le support, les consignes et les exigences du présent document.

---

# 14. Évolution du document

Ce document constitue la référence générale du projet.

Il pourra être enrichi lorsque de nouvelles informations seront disponibles, notamment :

- Les supports détaillés du cours.
- Les exemples de code de l'enseignant.
- Les conventions de nommage.
- Les exigences complémentaires.
- Les éventuelles précisions concernant les technologies.
- Les décisions architecturales validées.

Toute évolution devra conserver la cohérence générale du projet.

Le document ne doit pas être modifié pour justifier rétroactivement des choix techniques non validés.

---

# 15. Principe fondamental

**Le projet doit démontrer la maîtrise de la programmation événementielle à travers une application concrète, fonctionnelle et compréhensible.**

La priorité est de transformer les notions enseignées dans le cours en comportements réellement observables dans l'application.

Le résultat attendu est un travail académique sérieux, conforme aux exigences de l'enseignant, techniquement propre, visuellement soigné et facilement ouvrable dans NetBeans.

GitHub demeure la source de vérité. Les supports pédagogiques demeurent la référence technique et pédagogique. Le présent document demeure la référence générale du projet.