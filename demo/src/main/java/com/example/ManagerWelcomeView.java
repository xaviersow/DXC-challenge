package com.example;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class ManagerWelcomeView extends JFrame {

    private JButton restrictedButton;
    private JPanel contentPane;

    private JLabel welcomeLabel;
    private JButton logoutButton;
    private static final String FILENAME = "com\\example\\messages";
    private ResourceBundle bundle;

    public ManagerWelcomeView(Locale locale, User user) {

        setLocale(locale);
        bundle = ResourceBundle.getBundle(FILENAME, locale);

        setTitle(bundle.getString("welcome.title"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        String welcomeMessage = bundle.getString("manager.welcome")+ " " + user.getName() + ", " + user.getUsername() + ", " + user.getRole();
        welcomeLabel = new JLabel(welcomeMessage, JLabel.CENTER);

        welcomeLabel.setText(welcomeMessage);
        add(welcomeLabel);
        logoutButton = new JButton(bundle.getString("welcome.logoutButton"));
        panel.add(welcomeLabel, BorderLayout.NORTH);
        panel.add(logoutButton, BorderLayout.SOUTH);

        

        contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout());

        
        contentPane.add(welcomeLabel);

        restrictedButton = new JButton("Restricted Access");
        contentPane.add(restrictedButton);
        restrictedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // When the restricted button is clicked, open the restricted JFrame
                RestrictedAccessView restrictedFrame = new RestrictedAccessView(locale);
                restrictedFrame.setVisible(true);
            }
        });

        setContentPane(contentPane);


        add(panel);
        pack();
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void updateTexts(ResourceBundle bundle, User user) {
        String welcomeMessage = bundle.getString("manager.welcome")+ " " + user.getName() + ", " + user.getUsername() + ", " + user.getRole();
        welcomeLabel = new JLabel(welcomeMessage, JLabel.CENTER);
        restrictedButton.setText(bundle.getString("restricted.button"));
        logoutButton.setText(bundle.getString("welcome.logoutButton"));
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }


}