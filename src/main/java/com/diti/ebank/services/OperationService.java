package com.diti.ebank.services;

public interface OperationService {

    void effectuerVirement(String idCD, String idCC, double montant);

    void retirerArgent(String id, double montant);

    void deposerArgent( String accountNumber, double montant);

    void afficherOperations(String numeroCompte);
}
