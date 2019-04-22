package com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Service;

import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Controller.AccountController;
import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity.Account;
import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Repositories.AccountRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @MockBean
    private AccountRepo repo;


    @MockBean
    private AccountService service;

    @MockBean
    private AccountController controller;

    @Before
    public void setup() {
        this.controller = new AccountController(service);
        this.repo = Mockito.mock(AccountRepo.class);
        this.service = new AccountService(repo);
    }

    @Test
    public void testCreateAccount() {
        Account account = new Account(1, 100.00);
        Mockito.when(repo.save(account)).thenReturn(account);

        service.create(account);

        Mockito.verify(repo, Mockito.times(1)).save(account);
    }

    @Test
    public void testDeleteAccount() {
        Account account = new Account(1, 10.00);
        Mockito.when(repo.findById(1)).thenReturn(Optional.of(account));

        service.delete(1);

        Mockito.verify(repo, Mockito.times(1)).deleteById(1);
    }

    @Test
    public void testGetAccount() {
        Account account = new Account(1, 1000.00);
        Mockito.when(repo.findById(1)).thenReturn(Optional.of(account));

        service.getAccount(1);

        Mockito.verify(repo, Mockito.times(1)).findById(1);
    }

    @Test
    public void testGetAllAccounts() {
        Account account = new Account(1, 100.00);
        Account account1 = new Account(1, 200.00);

        service.getAccounts(1);

        Mockito.verify(repo, Mockito.times(1)).findAllByProfileID(1);
    }

    @Test
    public void testGetNumberOfAccounts() {
        Account account = new Account(1, 100.00);
        Account account1 = new Account(2, 200.00);

        service.getNumberOfAccounts(1);

        Mockito.verify(repo, Mockito.times(1)).countByProfileID(1);

    }

    @Test
    public void testUpdateAccount() {
        Account account = new Account(1, 10.00);
        Account account2 = new Account(1, 100.00);
        Mockito.when(repo.findById(1)).thenReturn(Optional.of(account));

        service.update(1, account2);

        Mockito.verify(repo, Mockito.times(1)).findById(1);
    }

    @Test
    public void testWithdraw() {
        Account account = new Account(1, 100.00);
        Mockito.when(repo.findById(1)).thenReturn(Optional.of(account));

        service.withdraw(1, 10.00);

        Mockito.verify(repo, Mockito.times(1)).findById(1);
    }

    @Test
    public void testDeposit() {
        Account account = new Account(1, 1000.00);
        Mockito.when(repo.findById(1)).thenReturn(Optional.of(account));

        service.deposit(1, 100.00);

        Mockito.verify(repo, Mockito.times(1)).findById(1);
    }

    @Test
    public void testTransfer() {
        Account account = new Account(1, 1000.00);
        Account account2 = new Account(1, 10.00);
        Mockito.when(repo.findById(1)).thenReturn(Optional.of(account));
        Mockito.when(repo.findById(2)).thenReturn(Optional.of(account2));

        service.transfer(1, 2, 100.00);

        Mockito.verify(repo, Mockito.times(2)).findById(1);
    }

    @Test
    public void testTransfer2() {
        Account account = new Account(1, 1000.00);
        Account account2 = new Account(1, 10.00);
        Mockito.when(repo.findById(1)).thenReturn(Optional.of(account));
        Mockito.when(repo.findById(2)).thenReturn(Optional.of(account2));

        Account[] actual = service.transfer(1, 2, -100.00);

        Mockito.verify(repo, Mockito.times(0)).findById(1);
        Assert.assertNull(actual);
    }

    @Test
    public void testDeleteAllProfiles() {
        Account account = new Account(1, 420.69);
        Account account1 = new Account(2, 1337.00);
        Set<Account> expectedAccounts = new HashSet<>();
        expectedAccounts.add(account);
        expectedAccounts.add(account1);

        service.deleteAllAccounts();
        Mockito.verify(repo, Mockito.times(1)).deleteAll();
    }

}