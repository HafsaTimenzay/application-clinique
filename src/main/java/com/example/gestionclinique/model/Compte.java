package com.example.gestionclinique.model;

public class Compte {
    private long idCompte;
    private String email;
    private String username;
    private String password;
    private String typeUtilisateur;

    // Constructeurs, getters et setters
    public Compte(long idCompte, String email, String username, String password, String typeUtilisateur) {
        this.idCompte = idCompte;
        this.email = email;
        this.username = username;
        this.password = password;
        this.typeUtilisateur = typeUtilisateur;
    }

    public long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(long idCompte) {
        this.idCompte = idCompte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTypeUtilisateur() {
        return typeUtilisateur;
    }

    public void setTypeUtilisateur(String typeUtilisateur) {
        this.typeUtilisateur = typeUtilisateur;
    }
}
