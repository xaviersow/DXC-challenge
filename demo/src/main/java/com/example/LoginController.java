package com.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JFrame;

public class LoginController {
    private LoginView view;
    private UserModel model;
    private ResourceBundle bundle;
    private JFrame welcomeView;

    public LoginController(LoginView view, UserModel model) {
        this.view = view;
        this.model = model;

        view.addLoginListener(new LoginListener());
    }

    public User login(String username, String password) {
        User user = model.getUser(username, password);
        return user;
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();

            if (model.login(username, password))
            {
                view.dispose();

                String name = model.getName(username);
                String role = model.getRole(username);

                if (role.equals("manager")) {
                    User user = login(username, password);
                    ManagerWelcomeView managerView = new ManagerWelcomeView(view.getLocale(), user);
                    welcomeView = managerView;
                    managerView.addLogoutListener(new LogoutListener());
                    //managerView.addRestrictedPageListener(new RestrictedPageListener());
                    bundle = ResourceBundle.getBundle("com\\example\\messages", view.getLocale());
                    managerView.updateTexts(bundle, user);
                } else if (role.equals("user")) {
                    //UserWelcomeView userView = new UserWelcomeView(view.getSelectedLocale());
                    UserWelcomeView userView = new UserWelcomeView(view.getLocale(), name, username, role);
                    welcomeView = userView;
                    userView.addLogoutListener(new LogoutListener());
                    //bundle = ResourceBundle.getBundle("com\\example\\messages", view.getSelectedLocale());
                    bundle = ResourceBundle.getBundle("com\\example\\messages", view.getLocale());
                    userView.updateTexts(bundle);
                } else
                {
                    System.out.println("No role");
                }

            }
            else
            {
                bundle = ResourceBundle.getBundle("com\\example\\messages", view.getLocale());
                view.showErrorMessage(bundle.getString("login.invalidMessage"));
            }
        }

        
    }

    class LogoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            welcomeView.dispose();
            
            LoginView loginView = new LoginView(welcomeView.getLocale()); // Recreate the login view
            UserModel userModel = new UserModel();
            LoginController loginController = new LoginController(loginView, userModel);
                 
        }
    }

    
}