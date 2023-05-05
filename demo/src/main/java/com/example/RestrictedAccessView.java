package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class RestrictedAccessView extends JFrame {
    private JPanel contentPane;
    private JLabel restrictedLabel;
    private JButton backButton;
    private ResourceBundle bundle;

    public RestrictedAccessView(Locale locale) {
        setLocale(locale);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Restricted Access");

        contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout());

        restrictedLabel = new JLabel("This is a restricted area!");
        contentPane.add(restrictedLabel);

        backButton = new JButton("Back to Manager Welcome");
        contentPane.add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // When the back button is clicked, dispose of the restricted JFrame and go back to the manager welcome view
                dispose();
            }
        });

        bundle = ResourceBundle.getBundle("com\\example\\messages", locale);
        updateTexts(bundle);

        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null);
    }

    public void updateTexts(ResourceBundle bundle) {
        setTitle(bundle.getString("restricted.title"));
        restrictedLabel.setText(bundle.getString("restricted.label"));
        backButton.setText(bundle.getString("back.button"));
        
    }

    
}
