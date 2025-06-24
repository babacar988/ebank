package com.diti.ebank.entities;

import com.diti.ebank.DB.ConnectionDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountImpl implements IAccount {

    private ConnectionDB db = new ConnectionDB();
    private ResultSet rs;
/*
    @Override
    public Account CreerCompte(int custumer_id, double balance) {
        String sql = "INSERT INTO account (balance, custumer_id, creation_date) VALUES ( ?, ?, ?)";
        Account account = null;
        try {
            db.initPrepar(sql);
            db.getPstm().setDouble(1, balance);
            db.getPstm().setInt(2,custumer_id);
            db.getPstm().setDate(3,new java.sql.Date(System.currentTimeMillis()));
            int ok = db.executeMaj();
            db.closeConnection();
            //viderChamp();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }
    */

    @Override
    public Account CreerCompte(String username, double balance) throws SQLException {
        String sql = "INSERT INTO account (balance, custumer_id, creation_date) " +
                "SELECT ?, c.id, ? FROM custumer c WHERE c.username = ?";
        Account account = null;
        try {
            db.initPrepar(sql);
            db.getPstm().setDouble(1, balance);
            db.getPstm().setDate(2,new java.sql.Date(System.currentTimeMillis()));
            db.getPstm().setString(3,username);
            int ok = db.executeMaj();
            db.closeConnection();
            //viderChamp();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }



}
