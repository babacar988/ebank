package com.diti.ebank.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
}
