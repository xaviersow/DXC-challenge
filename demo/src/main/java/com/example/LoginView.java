package com.example;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.*;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel languageLabel;
    private JComboBox<String> languageCombo = new JComboBox<String>(new String[]{"English", "中文"});
    private ResourceBundle bundle;

    public LoginView(Locale locale) {
        setLocale(locale);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton();
        usernameLabel = new JLabel();
        passwordLabel = new JLabel();
        languageLabel =  new JLabel();

        JPanel panel = new JPanel();
        
        

        panel.setLayout(new GridLayout(4, 2));
        //usernameLabel.setText(bundle.getString("login.username"));
        panel.add(usernameLabel);
        panel.add(usernameField);
        //passwordLabel.setText(bundle.getString("login.password"));
        panel.add(passwordLabel);
        panel.add(passwordField);
        
        panel.add(languageLabel);
        panel.add(languageCombo);
        panel.add(loginButton);
        
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        
        // Initialize resource bundle with default language
        bundle = ResourceBundle.getBundle("com\\example\\messages", locale);
        updateTexts();
        
        // Add listener for language selection
        languageCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String language = (String) languageCombo.getSelectedItem();
                Locale locale;
                if (language.equals("English")) {
                    locale = new Locale("en", "US");
                } else {
                    locale = new Locale("zh", "CN");
                }
                bundle = ResourceBundle.getBundle("com\\example\\messages", locale);
                updateLocaleAndBundle(locale, bundle);
                updateTexts();
            }
        });
        
    }

    private void updateLocaleAndBundle(Locale locale, ResourceBundle bundle)
    {
        setLocale(locale);
        this.bundle = bundle;
    }

    private void updateTexts() {
        setTitle(bundle.getString("login.title"));
        usernameLabel.setText(bundle.getString("login.username"));
        passwordLabel.setText(bundle.getString("login.password"));
        loginButton.setText(bundle.getString("login.loginButton"));
        languageLabel.setText(bundle.getString("login.languageLabel"));
        languageCombo.setSelectedItem(bundle.getString("login.language"));
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, bundle.getString("login.invalidMessage"), JOptionPane.ERROR_MESSAGE);
    }

    public void clearUsername() {
        usernameField.setText("");
    }

    public void clearPassword() {
        passwordField.setText("");
    }
}