package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class UserWelcomeView extends JFrame {
    private JLabel welcomeLabel;
    private JButton logoutButton;
    private static final String FILENAME = "com\\example\\messages";
    private ResourceBundle bundle;

    public UserWelcomeView(Locale locale) {

        setLocale(locale);
        bundle = ResourceBundle.getBundle(FILENAME, locale);

        setTitle(bundle.getString("welcome.title"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        welcomeLabel = new JLabel(bundle.getString("user.welcome"));
        logoutButton = new JButton(bundle.getString("welcome.logoutButton"));
        panel.add(welcomeLabel, BorderLayout.NORTH);
        panel.add(logoutButton, BorderLayout.SOUTH);

        add(panel);
        pack();
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public UserWelcomeView(Locale locale, String name, String username, String role) {
        //this.locale = locale;
        setLocale(locale);
        bundle = ResourceBundle.getBundle(FILENAME, locale);

        setTitle(bundle.getString("welcome.title"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        welcomeLabel = new JLabel(bundle.getString("user.welcome"));
        logoutButton = new JButton(bundle.getString("welcome.logoutButton"));
        panel.add(welcomeLabel, BorderLayout.NORTH);
        panel.add(logoutButton, BorderLayout.SOUTH);

        add(panel);
        pack();
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void updateTexts(ResourceBundle bundle) {
        welcomeLabel.setText(bundle.getString("user.welcome"));
        logoutButton.setText(bundle.getString("welcome.logoutButton"));
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
}