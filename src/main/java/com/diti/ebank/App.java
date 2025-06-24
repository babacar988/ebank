package com.diti.ebank;

import com.diti.ebank.DB.ConnectionDB;
import com.diti.ebank.entities.Customer;
import com.diti.ebank.entities.CustomerImpl;
import com.diti.ebank.entities.ICustomer;
import com.diti.ebank.tools.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/views/account.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Ebank");
        stage.show();
    }
    public static void main(String[] args) {
        /*
        ConnectionDB db = new ConnectionDB();

        Customer customer = new Customer();
        customer.setFirstname("Doudou Leye");
        customer.setLastname("Diakhate");
        customer.setUsername("doudou");
        customer.setPassword("Passer@12345");
        String sql = "INSERT INTO custumer VALUES (NULL, ?, ?, ?, ?)";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1,customer.getFirstname());
            db.getPstm().setString(2,customer.getLastname());
            db.getPstm().setString(3,customer.getUsername());
            db.getPstm().setString(4, Utils.hashPassword(customer.getPassword()));
            int ok = db.executeMaj();
            db.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        ICustomer entities = new CustomerImpl();
        Customer customer = entities.seConnecter("babacar", "$2a$10$xUJnmAHqhKcbhh.mbuthe.42MvzgCwT0EuCWBql3LzIWnFlLCwsei");
        if (customer != null) {
            System.out.println("Customer is connected");
        }else
            System.out.println("Customer not found");

         */


        launch(args);
    }
}
