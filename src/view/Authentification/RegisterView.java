package view.Authentification;


import javax.swing.*;
import java.awt.*;

public class RegisterView extends JFrame {
    
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginBtn;
    private JRadioButton etudiantRadio, enseignantRadio;


    public RegisterView() {

        // Initialisation de la fenêtre
        setTitle("Page de Connection");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // titre
        JPanel TopPanel = new JPanel(new BorderLayout());
        TopPanel.setBorder(BorderFactory.createEmptyBorder(80, 10, 0, 10));

        JLabel title = new JLabel("Gestion Clinique Médical", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        TopPanel.add(title, BorderLayout.CENTER);

        add(TopPanel, BorderLayout.NORTH);

        // Panel pour le formulaire de connexion
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Marges

        // Label utilisateur
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(new JLabel("Username : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(emailField = new JTextField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(new JLabel("Email : "), gbc);

        // Champ utilisateur
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(emailField = new JTextField(20), gbc);

        // Label mot de passe
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(new JLabel("Mot de passe: "), gbc);

        // Champ mot de passe
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(passwordField = new JPasswordField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(new JLabel("Choisissez votre rôle:"), gbc);

        // Champ mot de passe
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        etudiantRadio = new JRadioButton("Patient");
        enseignantRadio = new JRadioButton("Medecin");

        ButtonGroup group = new ButtonGroup();
        group.add(etudiantRadio);
        group.add(enseignantRadio);

        Panel rolePanel = new Panel();
        rolePanel.add(etudiantRadio);
        rolePanel.add(enseignantRadio);
        loginPanel.add(rolePanel, gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE; // Taille normale du bouton
        loginPanel.add(loginBtn = new JButton("SIGN UP"), gbc);

        add(loginPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    
   
}

