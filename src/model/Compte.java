package model;

public class Compte {
    private Long idCompte;
    private String email;
    private String username;
    private String password;

    public Compte() {}

    public Compte(Long idCompte, String email, String username, String password) {
        this.idCompte = idCompte;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters

    public Long getIdCompte() { return idCompte; }
    public void setIdCompte(Long idCompte) { this.idCompte = idCompte; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
