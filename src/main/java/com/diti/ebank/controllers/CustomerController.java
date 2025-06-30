package com.diti.ebank.controllers;

import com.diti.ebank.DB.ConnectionDB;
import com.diti.ebank.entities.Customer;
import com.diti.ebank.tools.Outils;
import com.diti.ebank.tools.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private TextField firstnameTf;

    @FXML
    private TextField lastnameTf;

    @FXML
    private TextField usernameTf;

    @FXML
    private PasswordField passwordTfd;

    @FXML
    private TableColumn<Customer, Integer> firstnameTfd;

    @FXML
    private TableColumn<Customer, Integer> idCol;

    @FXML
    private TableColumn<Customer, String> firstnameCol;
    @FXML
    private TableColumn<Customer, String> lastnameCol;

    @FXML
    private TableColumn<Customer, String> usernameCol;

    @FXML
    private TableView<Customer> customerTb;

    @FXML
    private Button retourBtn;

    @FXML
    private Button supbtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }
    private ConnectionDB db = new ConnectionDB();
    public ObservableList<Customer> getCustomers() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        String sql = "select * from custumer order by id";
        try {
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirstname(rs.getString("firstname"));
                customer.setLastname(rs.getString("lastname"));
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
        idCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        firstnameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstname"));
        lastnameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastname"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("username"));
    }


    @FXML
    void getRetour(ActionEvent event) throws IOException {

        Outils.load(event, "Page de connexion","/views/login.fxml");
    }

    void viderChamp() {
        firstnameTf.setText("");
        lastnameTf.setText("");
        usernameTf.setText("");
        passwordTfd.setText("");
    }

    @FXML
    void AjouterClient(ActionEvent event) {
        ConnectionDB db = new ConnectionDB();

        Customer customer = new Customer();
        if (customer!=null) {


        String sql = "INSERT INTO custumer VALUES (NULL, ?, ?, ?, ?)";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1,firstnameTf.getText());
            db.getPstm().setString(2,lastnameTf.getText());
            db.getPstm().setString(3,usernameTf.getText());
            db.getPstm().setString(4, Utils.hashPassword(passwordTfd.getText()));
            //db.getPstm().setString(4, passwordTfd.getText());
            int ok = db.executeMaj();
            db.closeConnection();
            loadTable();
            viderChamp();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    }


    @FXML
    private TextField idTfd;

    @FXML
    void SupprimerClient(ActionEvent event) {
        ConnectionDB db = new ConnectionDB();

        Customer customer = new Customer();

            String sql = "DELETE FROM custumer where id=?";
            try {
                db.initPrepar(sql);
                db.getPstm().setString(1,idTfd.getText());

                int ok = db.executeMaj();
                db.closeConnection();
                loadTable();
                viderChamp();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }


