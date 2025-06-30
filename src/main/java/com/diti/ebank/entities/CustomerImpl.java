package com.diti.ebank.entities;

import com.diti.ebank.DB.ConnectionDB;
import com.diti.ebank.tools.Utils;

import java.sql.ResultSet;

public class CustomerImpl implements ICustomer {

    private ConnectionDB db = new ConnectionDB();
    private ResultSet rs;

    @Override
    public Customer seConnecter(String username) {
        String sql = "SELECT * FROM custumer WHERE username=? ";
        //Customer customers=null;
        Customer customers = new Customer();

        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, username);


            rs = db.executeSelect();
            if (rs.next()) {
                customers = new Customer();
                customers.setId(rs.getInt("id"));
                customers.setUsername(rs.getString("username"));
                customers.setPassword(rs.getString("password"));
                //customers.setPassword(rs.getString(Utils.checkPassword(password,customers.getPassword())));
            }
            db.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }
}
