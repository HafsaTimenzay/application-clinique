package model;

public class Patient {
    private Long id;
    private String nom;
    private String prenom;
    private String sexe;
    private String cin;
    private String gsm;
    private int age;
    private String adresse;
    private double taille;
    private double poids;

    public Patient() {}

    public Patient(Long id, String nom, String prenom, String sexe, String cin, String gsm, int age, String adresse, double taille, double poids) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.cin = cin;
        this.gsm = gsm;
        this.age = age;
        this.adresse = adresse;
        this.taille = taille;
        this.poids = poids;
    }

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }

    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }

    public String getGsm() { return gsm; }
    public void setGsm(String gsm) { this.gsm = gsm; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public double getTaille() { return taille; }
    public void setTaille(double taille) { this.taille = taille; }

    public double getPoids() { return poids; }
    public void setPoids(double poids) { this.poids = poids; }
}
