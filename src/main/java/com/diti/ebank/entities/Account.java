package com.diti.ebank.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter

@Data
public class Account {
    private int id;
    private double balance;
    private Customer customer;
    private LocalDate creationDate;

    public Account(int id, double balance, Customer customer, LocalDate creationDate) {
        this.id = id;
        this.balance = balance;
        this.customer = customer;
        this.creationDate = creationDate;
    }
}
