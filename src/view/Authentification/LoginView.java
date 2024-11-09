package view.Authentification;


import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import java.awt.*;
// import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;


public class LoginView extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginBtn;

    public LoginView() {

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
        loginPanel.add(new JLabel("Email : "), gbc);

        // Champ utilisateur
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(emailField = new JTextField(20), gbc);

        // Label mot de passe
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(new JLabel("Mot de passe: "), gbc);

        // Champ mot de passe
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(passwordField = new JPasswordField(20), gbc);

        // Bouton de connexion (taille normale)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE; // Taille normale du bouton
        loginPanel.add(loginBtn = new JButton("SIGN IN"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE; 
        JPanel infoPanel = new JPanel(new FlowLayout());
        JLabel infoLabel = new JLabel("<html>Vous n'avez pas un compte? <font color='blue'><u>SIGN UP</u></font></html>");
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        infoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginPanel.add(infoPanel.add(infoLabel), gbc);

        add(loginPanel, BorderLayout.CENTER);



    //     loginBtn.addActionListener(new ActionListener() {
    //         @Override
    //         public void actionPerformed(ActionEvent e){
    //             String user = emailField.getText();
    //             char[] password = passwordField.getPassword();
    //             String password_str = new String(password);
    //             if(user.equalsIgnoreCase("client") && password_str.equals("0000")){
    //                 dispose();
    //                 UserView user_view = new UserView();
    //                 user_view.setVisible(true);
    //             }else if(user.equalsIgnoreCase("admin") && password_str.equals("0000")){
    //                 dispose();
    //                 AdminView admin_view = new AdminView();
    //                 admin_view.setVisible(true);
    //             }else{
    //                 JOptionPane.showMessageDialog(loginPanel, "password or username is incorrect!");
    //             }
    //         }
    //     });


    infoLabel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            dispose();
            new RegisterView();
        }
    });
    setVisible(true);
}



}