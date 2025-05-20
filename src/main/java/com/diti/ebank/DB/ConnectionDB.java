package com.diti.ebank.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionDB {
    private Connection cnx;
    private PreparedStatement pstm;
    private ResultSet rs;
    private int ok;

    private void getConnection() {

        try {
            Class.forName(Constants.DRIVER);
            cnx = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);
        }catch (Exception e) {
            System.out.println("Erreur lors de la connexion a la base de donnee :" +e.getMessage());
        }
    }

    public void initPrepar(String sql) {

        try {
            getConnection();
            pstm = cnx.prepareStatement(sql);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ResultSet executeSelect() {
        rs=null;
        try {
            rs = pstm.executeQuery();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int executeMaj() {
        try {
            ok = pstm.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    public void closeConnection() {
        try {
            if (cnx != null) {
                cnx.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement getPstm() {
        return pstm;
    }
}
