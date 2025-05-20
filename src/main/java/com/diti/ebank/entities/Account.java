package com.diti.ebank.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Account {
    private int id;
    private double balance;
    private Customer customer;
    private LocalDate creationDate;
}
