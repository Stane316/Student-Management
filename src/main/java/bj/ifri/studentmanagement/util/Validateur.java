package bj.ifri.studentmanagement.util;

import java.util.regex.Pattern;

/**
 * Utilitaire de validation et de normalisation des saisies du formulaire étudiant.
 */
public final class Validateur {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$");

    private static final Pattern TELEPHONE_PATTERN =
            Pattern.compile("^[0-9+()\\-\\s]{8,20}$");

    private static final Pattern ANNEE_ACADEMIQUE_PATTERN =
            Pattern.compile("^\\d{4}-\\d{4}$");

    private Validateur() {
        // Classe utilitaire : pas d'instanciation.
    }

    public static boolean estNonVide(String valeur) {
        return valeur != null && !valeur.trim().isEmpty();
    }

    public static boolean estEmailValide(String email) {
        return estNonVide(email) && EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    public static boolean estTelephoneValide(String telephone) {
        return estNonVide(telephone) && TELEPHONE_PATTERN.matcher(telephone.trim()).matches();
    }

    public static boolean estAnneeAcademiqueValide(String anneeAcademique) {
        if (!estNonVide(anneeAcademique)) {
            return false;
        }
        String valeur = anneeAcademique.trim();
        if (!ANNEE_ACADEMIQUE_PATTERN.matcher(valeur).matches()) {
            return false;
        }
        try {
            int anneeDebut = Integer.parseInt(valeur.substring(0, 4));
            int anneeFin = Integer.parseInt(valeur.substring(5, 9));
            return anneeFin == anneeDebut + 1 && anneeDebut >= 2000 && anneeDebut <= 2100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String normaliserTexte(String valeur) {
        if (valeur == null) {
            return "";
        }
        return valeur.trim().replaceAll("\\s+", " ");
    }

    public static String normaliserEmail(String email) {
        if (email == null) {
            return "";
        }
        return email.trim().toLowerCase();
    }

    public static String normaliserTelephone(String telephone) {
        if (telephone == null) {
            return "";
        }
        return telephone.trim().replaceAll("\\s+", " ");
    }

    public static String normaliserMatricule(String matricule) {
        if (matricule == null) {
            return "";
        }
        return matricule.trim().replaceAll("\\s+", " ").toUpperCase();
    }
}
