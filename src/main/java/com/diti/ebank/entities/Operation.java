package com.diti.ebank.entities;

import com.diti.ebank.enums.TypeOperation;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Operation {
    private int id;
    private double amount;
    private Account account;
    private LocalDate operationDate;
    private TypeOperation typeOperation;
}
