package bj.ifri.studentmanagement.util;

import java.util.regex.Pattern;

/**
 * Utilitaire de validation des saisies du formulaire étudiant.
 */
public final class Validateur {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$");

    private static final Pattern TELEPHONE_PATTERN =
            Pattern.compile("^[0-9+()\\-\\s]{8,20}$");

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
}
