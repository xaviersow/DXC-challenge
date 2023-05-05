package com.example;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        UserModel model = new UserModel();
        LoginView view = new LoginView(new Locale("en"));
        LoginController controller = new LoginController(view, model);
    }
}