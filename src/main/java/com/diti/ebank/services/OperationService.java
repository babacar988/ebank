package com.diti.ebank.services;

public interface OperationService {

    void effectuerVirement(String compteDebiteur,String idCD, String compteCrediteur,String idCC, double montant);

    void retirerArgent(String username,String id, double montant);

    void deposerArgent(String username, String accountNumber, double montant);

    void afficherOperations(String numeroCompte);
}
