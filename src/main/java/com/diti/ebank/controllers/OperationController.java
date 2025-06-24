package com.diti.ebank.controllers;

import com.diti.ebank.services.OperationService;
import com.diti.ebank.services.impl.OperationServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class OperationController {

    @FXML
    private TextField compteTfd;

    @FXML
    private TextField idTfd;

    @FXML
    private TextField montantTfd;

    @FXML
    private TextField CompteCrediteurTfd;

    @FXML
    private TextField idCCTfd;

    @FXML
    private TextField CompteDebiteurTfd;

    @FXML
    private TextField idCDTfd;

    @FXML
    private TextField MontantTransferTfd;

    @FXML
    private ComboBox<String> compteComboBox;

    OperationService operation = new OperationServiceImpl();


    @FXML
    void depotBtn(ActionEvent event) {
        String username = compteTfd.getText();
        String id = idTfd.getText();
        double montant1 = Double.parseDouble(montantTfd.getText());
        operation.deposerArgent(username, id, montant1);

    }


    @FXML
    void retraitBtn(ActionEvent event) {
        String username = compteTfd.getText();
        String id = idTfd.getText();
        double montant2 = Double.parseDouble(montantTfd.getText());
        operation.retirerArgent(username, id , montant2);

    }


    @FXML
    void transferBtn(ActionEvent event) {
        String compteDebiteur = CompteDebiteurTfd.getText();
        String idCD = idCDTfd.getText();
        String compteCrediteur = CompteCrediteurTfd.getText();
        String idCC = idCCTfd.getText();
        double montant3 = Double.parseDouble(MontantTransferTfd.getText());
        operation.effectuerVirement(compteDebiteur,idCD , compteCrediteur, idCC, montant3);

    }

}
