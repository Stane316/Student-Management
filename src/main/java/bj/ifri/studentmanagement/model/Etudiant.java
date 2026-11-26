package bj.ifri.studentmanagement.model;

/**
 * Représente un étudiant enregistré dans l'application.
 */
public class Etudiant {

    private String nom;
    private String prenom;
    private String matricule;
    private String sexe;
    private String filiere;
    private String niveau;
    private String anneeAcademique;
    private String statut;
    private String email;
    private String telephone;

    public Etudiant(String nom, String prenom, String matricule, String sexe,
                    String filiere, String niveau, String anneeAcademique,
                    String statut, String email, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.sexe = sexe;
        this.filiere = filiere;
        this.niveau = niveau;
        this.anneeAcademique = anneeAcademique;
        this.statut = statut;
        this.email = email;
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getSexe() {
        return sexe;
    }

    public String getFiliere() {
        return filiere;
    }

    public String getNiveau() {
        return niveau;
    }

    public String getAnneeAcademique() {
        return anneeAcademique;
    }

    public String getStatut() {
        return statut;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }
}
