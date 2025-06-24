package com.diti.ebank.controllers;

import com.diti.ebank.entities.Customer;
import com.diti.ebank.entities.CustomerImpl;
import com.diti.ebank.entities.ICustomer;
import com.diti.ebank.tools.Outils;
import com.diti.ebank.tools.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginController {

    @FXML
    private Button connexionBtn;

    @FXML
    private TextField usernameTfd;

    @FXML
    private PasswordField passwordTfd;


    ICustomer customer = new CustomerImpl();

    @FXML
    void getLogin(ActionEvent event) throws IOException {
        String username = usernameTfd.getText();
        String password = passwordTfd.getText();
        if(username.equals("") || password.equals("")) {
            System.out.println("Les champs sont obligatoires");
        }else {
            try {
                Customer user = customer.seConnecter(username);
                if (user == null) {
                    System.out.println("Erreur de connexion");
                }else{
                    if(Utils.checkPassword(password,user.getPassword()));
                    System.out.println("connexion reussie");
                    Outils.load(event, "Page de connexion","/views/account.fxml");

                }
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
