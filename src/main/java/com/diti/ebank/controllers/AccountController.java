package com.diti.ebank.controllers;

import com.diti.ebank.DB.ConnectionDB;
import com.diti.ebank.entities.Account;
import com.diti.ebank.entities.AccountImpl;
import com.diti.ebank.entities.Customer;
import com.diti.ebank.entities.IAccount;
import com.diti.ebank.tools.Notification;
import com.diti.ebank.tools.Outils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AccountController implements Initializable{

    @FXML
    private Button Creerbtn;

    @FXML
    private TextField balanceTfd;

    @FXML
    private TextField searchBar;


    @FXML
    private TableView<Customer> customerTb;

    @FXML
    private TableColumn<Customer, String> usernameCol;




    private ConnectionDB db = new ConnectionDB();
    public ObservableList<Customer> getCustomers() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        String sql = "select * from custumer order by username";
        try {
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setUsername(rs.getString("username"));
                customers.add(customer);
            }
            db.closeConnection();
        }catch (SQLException e) {
            throw new RuntimeException();
        }
        return customers;
    }

    public void loadTable(){
        ObservableList<Customer> liste = getCustomers();
        customerTb.setItems(liste);
        usernameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("username"));
    }

    IAccount account = new AccountImpl();

    @FXML
    void CreerCompte(ActionEvent event) throws Exception {
        String custumer_idStr = searchBar.getText();
        String balanceStr = balanceTfd.getText();


        if (custumer_idStr.isEmpty() || balanceStr.isEmpty()) {
            Notification.NotifError("ERROR", "Les champs sont obligatoires");
            return;
        }
        try {
            String username = searchBar.getText();
            double balance = Double.parseDouble(balanceStr);

            Account account1 = account.CreerCompte(username, balance);
            if (account1 != null) {
                Notification.NotifError("ERROR", "Erreur de l'ajout");
            } else {
                Notification.NotifSuccess("SUCCESS", "ajout reussie");
                Outils.load(event, "Page de connexion", "/views/create-account.fxml");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    @FXML
    void AjouterClient(ActionEvent event) throws IOException {
        Outils.load(event,"Ajouter de client","/views/customer.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }























}
