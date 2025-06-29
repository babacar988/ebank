package com.diti.ebank.services.impl;

import com.diti.ebank.DB.ConnectionDB;
import com.diti.ebank.services.OperationService;
import com.diti.ebank.tools.Notification;

import java.sql.ResultSet;

public class OperationServiceImpl implements OperationService {

    private ConnectionDB db = new ConnectionDB();
    private ResultSet rs;

    @Override
    public void effectuerVirement(String idCD, String idCC, double montant) {

        String sql = """
        UPDATE account
        SET balance = balance - ?
        WHERE id = ?
    """;
        String sql2 = """
        UPDATE account
        SET balance = balance + ?
        WHERE id = ?
        """;
        String sql3 = """
        INSERT INTO operation (account_id, amount, type_operation, operation_date)
        VALUES (?, ?, 'deposit', NOW())
     """;
        String sql4 = """
                INSERT INTO operation (account_id, amount, type_operation, operation_date)
                VALUES (?, ?, 'withdrawal', NOW())
                """;
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant du virement doit être positif.");
        }


        try {
            db.initPrepar(sql);
            db.getPstm().setDouble(1, montant);
            db.getPstm().setString(2, idCD);

            int ok = db.executeMaj();
            if (ok == 0) {
                throw new RuntimeException("Aucun compte trouvé pour le numéro de compte débiteur : " + idCD);
            }

            db.initPrepar(sql2);
            db.getPstm().setDouble(1, montant);
            db.getPstm().setString(2, idCC);

            int ok2 = db.executeMaj();
            if (ok2 == 0) {
                throw new RuntimeException("Aucun compte trouvé pour le numéro de compte créditeur : " + idCC);
            }

            db.initPrepar(sql3);
            db.getPstm().setString(1, idCC);
            db.getPstm().setDouble(2, montant);
            int ok3 = db.executeMaj();
            if (ok3 == 0) {
                throw new RuntimeException("Erreur lors de l'enregistrement de l'opération de retrait pour le compte : " + idCC);
            }

            db.initPrepar(sql4);
            db.getPstm().setString(1, idCD);
            db.getPstm().setDouble(2, montant);
            int ok4 = db.executeMaj();
            if (ok4 == 0) {
                throw new RuntimeException("Erreur lors de l'enregistrement de l'opération de retrait pour le compte : " + idCD);
            }


            db.closeConnection();
            Notification.NotifSuccess("SUCCESS", "Virement effectué avec succès de " + idCD + " vers " + idCC);

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du virement : " + e.getMessage(), e);
        }
    }
/*
    @Override
    public void retirerArgent(String numeroCompte, double montant) {

        //String sql = "UPDATE account SET balance = balance - ? WHERE custumer_id = (SELECT id FROM custumer WHERE username = ?)";
        String sql = "UPDATE account SET balance = balance - ? WHERE id = ?";




        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant du retrait doit être positif.");
        }

        if (numeroCompte == null) {
            throw new IllegalArgumentException("Le numéro de compte ne peut pas être nul.");
        }

        if (numeroCompte.isEmpty()) {
            throw new IllegalArgumentException("Le numéro de compte ne peut pas être vide.");
        }
        try {
            db.initPrepar(sql);
            db.getPstm().setDouble(1, montant);
            db.getPstm().setString(2, numeroCompte);
            int ok = db.executeMaj();
            if (ok == 0) {
                throw new RuntimeException("Aucun compte trouvé pour le numéro de compte : " + numeroCompte);
            }
            db.closeConnection();

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du retrait d'argent : " + e.getMessage(), e);
        }


    }
*/
    @Override
    public void retirerArgent(String accountNumber, double montant) {
    String sql = """
        UPDATE account
        SET balance = balance - ?
        WHERE id = ?
    """;
    String sql1 = """
        INSERT INTO operation (account_id, amount, type_operation, operation_date)
        VALUES (?, ?, 'withdrawal', NOW())
    """;

    if (montant <= 0) {
        throw new IllegalArgumentException("Le montant du retrait doit être positif.");
    }

    if (accountNumber == null || accountNumber.isEmpty()) {
        throw new IllegalArgumentException("Le username et le numéro de compte ne peuvent pas être vides.");
    }

    try {
        db.initPrepar(sql);
        db.getPstm().setDouble(1, montant);
        db.getPstm().setString(2, accountNumber);
        int ok = db.executeMaj();
        if (ok == 0) {
            throw new RuntimeException("Aucun compte trouvé pour le username : "  + " avec le compte : " + accountNumber);
        }
        // Vider les champs après le retrait
        //viderChamp();


        db.initPrepar(sql1);
        db.getPstm().setString(1, accountNumber);
        db.getPstm().setDouble(2, montant);
        int ok1 = db.executeMaj();
        if (ok1 == 0) {
            throw new RuntimeException("Erreur lors de l'enregistrement de l'opération de retrait pour le compte : " + accountNumber);
        }

        Notification.NotifSuccess("SUCCESS", "Retrait effectué avec succès pour le compte : " + accountNumber);

        db.closeConnection();
    } catch (Exception e) {
        throw new RuntimeException("Erreur lors du retrait : " + e.getMessage(), e);
    }
}

    @Override
    public void deposerArgent( String accountNumber, double montant) {

        String sql = """
        UPDATE account
        SET balance = balance + ?
        WHERE id = ?
    """;

        String sql1 = """
        INSERT INTO operation (account_id, amount, type_operation, operation_date)
        VALUES (?, ?, 'deposit', NOW())
    """;

        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant du retrait doit être positif.");
        }

        if ( accountNumber == null || accountNumber.isEmpty()) {
            throw new IllegalArgumentException("Le username et le numéro de compte ne peuvent pas être vides.");
        }

        try {
            db.initPrepar(sql);
            db.getPstm().setDouble(1, montant);
            db.getPstm().setString(2, accountNumber);
            int ok = db.executeMaj();
            if (ok == 0) {
                throw new RuntimeException("Aucun compte trouvé pour le username : " + " avec le compte : " + accountNumber);
            }
            db.initPrepar(sql1);
            db.getPstm().setString(1, accountNumber);
            db.getPstm().setDouble(2, montant);
            int ok1 = db.executeMaj();
            if (ok1 == 0) {
                throw new RuntimeException("Erreur lors de l'enregistrement de l'opération de depot pour le compte : " + accountNumber);
            }
            db.closeConnection();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du retrait : " + e.getMessage(), e);
        }
    }

    @Override
    public void afficherOperations(String numeroCompte) {

    }
}
