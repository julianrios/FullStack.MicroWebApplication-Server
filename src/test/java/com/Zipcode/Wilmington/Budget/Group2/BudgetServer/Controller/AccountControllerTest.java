package com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Controller;

import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.BudgetServerApplication;
import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity.Account;
import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity.Profile;
import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Service.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.Response;
import java.util.HashSet;
import java.util.Set;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BudgetServerApplication.class)
public class AccountControllerTest {

    @MockBean
    private AccountService service;

    private AccountController controller;

    @Before
    public void setUp() {
        this.controller = new AccountController(service);
    }

    @Test
    public void testCreate() {
        HttpStatus expected = HttpStatus.CREATED;
        Account expectedAccount = new Account();
        BDDMockito
                .given(service.create(expectedAccount))
                .willReturn(expectedAccount);

        //When
        ResponseEntity<Account> response = controller.create(expectedAccount);
        HttpStatus actual = response.getStatusCode();
        Account actualAccount = response.getBody();

        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedAccount, actualAccount);
    }

    @Test
    public void testGetAccount() {
        HttpStatus expected = HttpStatus.OK;
        Account expectedAccount = new Account(1, 5.0);
        BDDMockito
                .given(service.getAccount(1))
                .willReturn(expectedAccount);

        // When
        ResponseEntity<Account> response = controller.read(1);
        HttpStatus actual = response.getStatusCode();
        Account actualAccount = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedAccount, actualAccount);
    }

    @Test
    public void testGetAllAccounts() {
        HttpStatus expected = HttpStatus.OK;
        Account account = new Account(1, 100.00);
        Account account1 = new Account(1, 200.00);
        Set<Account> expectedAccountList = new HashSet<>();
        expectedAccountList.add(account);
        expectedAccountList.add(account1);
        BDDMockito
                .given(service.getAccounts(1))
                .willReturn(expectedAccountList);

        // When
        ResponseEntity<Set<Account>> response = controller.getAccounts(1);
        HttpStatus actual = response.getStatusCode();
        Set<Account> actualAccountList = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedAccountList, actualAccountList);
    }

    @Test
    public void testGetNumberOfAccounts() {
        HttpStatus expected = HttpStatus.OK;
        Account account = new Account(1, 100.00);
        Account account1 = new Account(1, 200.00);
        Set<Account> expectedAccountList = new HashSet<>();
        expectedAccountList.add(account);
        expectedAccountList.add(account1);
        Integer expectedNumberOfAccounts = expectedAccountList.size();
        BDDMockito
                .given(service.getNumberOfAccounts(1))
                .willReturn(expectedNumberOfAccounts);

        //When
        ResponseEntity<Integer> response = controller.getNumberOfAccounts(1);
        HttpStatus actual = response.getStatusCode();
        Integer actualAccountList = response.getBody();

        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedNumberOfAccounts, actualAccountList);
    }

    @Test
    public void testUpdate() {
        HttpStatus expected = HttpStatus.OK;
        Account newAccount = new Account(1, 5.0);
        BDDMockito
                .given(service.update(1, newAccount))
                .willReturn(newAccount);

        //When
        ResponseEntity<Account> response = controller.update(1, newAccount);
        HttpStatus actual = response.getStatusCode();
        Account actualAccount = response.getBody();

        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(newAccount, actualAccount);
    }

    @Test
    public void testWithdraw() {
        HttpStatus expected = HttpStatus.OK;
        Account withdrawnAccount = new Account(1, 10.0);
        BDDMockito
                .given(service.withdraw(1, 5.0))
                .willReturn(withdrawnAccount);

        BDDMockito
                .given(service.getAccount(1))
                .willReturn(withdrawnAccount);


        //When
        ResponseEntity<Account> response = controller.withdraw(1, 5.0);
        HttpStatus actual = response.getStatusCode();
        Account actualAccount = response.getBody();

        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(withdrawnAccount, actualAccount);
    }

    @Test
    public void testDeposit() {
        HttpStatus expected = HttpStatus.OK;
        Account depositAccount = new Account(1, 5.0);
        BDDMockito
                .given(service.deposit(1, 5.0))
                .willReturn(depositAccount);

        BDDMockito
                .given(service.getAccount(1))
                .willReturn(depositAccount);
        //When
        ResponseEntity<Account> response = controller.deposit(1, 5.0);
        HttpStatus actual = response.getStatusCode();
        Account actualAccount = response.getBody();

        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(depositAccount, actualAccount);
    }

    @Test
    public void testTransfer() {
        HttpStatus expected = HttpStatus.OK;
        Account account1 = new Account(5, 10.0);
        Account account2 = new Account(10, 10.0);
        Account[] accounts = {account1, account2};
        BDDMockito
                .given(service.transfer(1, 2, 5.0))
                .willReturn(accounts);

        //When
        ResponseEntity<Account[]> response = controller.transfer(1, 2, 5.0);
        HttpStatus actual = response.getStatusCode();
        Account[] actualAccounts = response.getBody();

        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertArrayEquals(accounts, actualAccounts);
    }

    @Test
    public void testDelete() {
        HttpStatus expected = HttpStatus.OK;
        BDDMockito
                .given(service.delete(1))
                .willReturn(true);

        ResponseEntity<Boolean> response = controller.delete(1);
        HttpStatus actual = response.getStatusCode();
        Boolean actualBool = response.getBody();

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(true, actualBool);
    }

    @Test
    public void testDeleteAll() {
        // Given
        HttpStatus expected = HttpStatus.OK;
        Account account = new Account(1, 420.69);
        Account account1 = new Account(2, 1337.00);
        Set<Account> expectedAccounts = new HashSet<>();
        expectedAccounts.add(account);
        expectedAccounts.add(account1);
        BDDMockito
                .given(service.deleteAllAccounts())
                .willReturn(true);

        // When
        ResponseEntity<Boolean> response = controller.deleteAllAccounts();
        HttpStatus actual = response.getStatusCode();
        Boolean actualBoolean = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(true, actualBoolean);
    }
}