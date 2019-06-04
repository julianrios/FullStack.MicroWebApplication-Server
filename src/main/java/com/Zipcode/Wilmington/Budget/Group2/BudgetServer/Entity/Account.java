package com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull
    @Column(name = "profile_id")
    private Integer profileID;

    @NotNull
    private Double balance;

    public Account() {
    }

    public Account(@NotNull Integer profileID, @NotNull Double balance) {
        this.profileID = profileID;
        this.balance = balance;
    }
}
