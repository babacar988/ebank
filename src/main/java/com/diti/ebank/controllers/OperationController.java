package com.diti.ebank.controllers;

import com.diti.ebank.services.OperationService;
import com.diti.ebank.services.impl.OperationServiceImpl;
import com.diti.ebank.tools.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class OperationController {


    @FXML
    private TextField idTfd;

    @FXML
    private TextField montantTfd;


    @FXML
    private TextField idCCTfd;


    @FXML
    private TextField idCDTfd;

    @FXML
    private TextField MontantTransferTfd;

    @FXML
    private ComboBox<String> compteComboBox;

    OperationService operation = new OperationServiceImpl();



    void viderChamp() {
        idTfd.setText("");
        montantTfd.setText("");
        idCCTfd.setText("");
        idCDTfd.setText("");
        MontantTransferTfd.setText("");
    }

    @FXML
    void depotBtn(ActionEvent event) {

        String id = idTfd.getText();
        double montant1 = Double.parseDouble(montantTfd.getText());
        operation.deposerArgent( id, montant1);
        viderChamp();

    }


    @FXML
    void retraitBtn(ActionEvent event) {

        String id = idTfd.getText();
        double montant2 = Double.parseDouble(montantTfd.getText());
        operation.retirerArgent( id , montant2);
        viderChamp();

    }


    @FXML
    void transferBtn(ActionEvent event) {

        String idCD = idCDTfd.getText();

        String idCC = idCCTfd.getText();
        double montant3 = Double.parseDouble(MontantTransferTfd.getText());
        operation.effectuerVirement(idCD ,  idCC, montant3);
        viderChamp();
    }

    @FXML
    void retourBtn(ActionEvent event) throws IOException {
        Outils.load(event,"Page de connexion","/views/login.fxml");
    }

}
