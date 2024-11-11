package com.example.gestionclinique.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    private JPanel mainPanel;

    public Dashboard() {
        setTitle("Tableau de Bord Clinique");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout());

        // Créer la navbar
        JPanel navbar = createNavbar();

        // Panneau principal pour afficher le contenu
        mainPanel = new JPanel(new CardLayout());

        // Ajouter des sections au mainPanel
        mainPanel.add(createHomePanel(), "Accueil");
        mainPanel.add(createPatientsPanel(), "Patients");
        mainPanel.add(createDoctorsPanel(), "Médecins");
        mainPanel.add(createAppointmentsPanel(), "Rendez-vous");

        // Ajouter navbar et mainPanel au frame
        add(navbar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createNavbar() {
        JPanel navbar = new JPanel();
        navbar.setLayout(new GridLayout(4, 1));
        navbar.setPreferredSize(new Dimension(200, 0));

        // Boutons de la navbar
        JButton homeButton = new JButton("Accueil");
        JButton patientsButton = new JButton("Patients");
        JButton doctorsButton = new JButton("Médecins");
        JButton appointmentsButton = new JButton("Rendez-vous");

        // Ajouter action listeners pour changer de panel
        homeButton.addActionListener(e -> switchPanel("Accueil"));
        patientsButton.addActionListener(e -> switchPanel("Patients"));
        doctorsButton.addActionListener(e -> switchPanel("Médecins"));
        appointmentsButton.addActionListener(e -> switchPanel("Rendez-vous"));

        // Ajouter les boutons à la navbar
        navbar.add(homeButton);
        navbar.add(patientsButton);
        navbar.add(doctorsButton);
        navbar.add(appointmentsButton);

        return navbar;
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Bienvenue sur le tableau de bord de la clinique !"));
        return panel;
    }

    private JPanel createPatientsPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Gestion des Patients"));
        // Ajouter d'autres composants selon vos besoins
        return panel;
    }

    private JPanel createDoctorsPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Gestion des Médecins"));
        // Ajouter d'autres composants selon vos besoins
        return panel;
    }

    private JPanel createAppointmentsPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Gestion des Rendez-vous"));
        // Ajouter d'autres composants selon vos besoins
        return panel;
    }

    private void switchPanel(String panelName) {
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
        });
    }
}

