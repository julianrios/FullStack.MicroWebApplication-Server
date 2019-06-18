package com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Controller;

import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity.Account;
import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * This controller handles the flow of data between the client and the server.
 * For incoming requests about information.
 */

@RestController
@CrossOrigin("*")
public class AccountController {

    private AccountService accountService;

    /**
     * Constructs an account controller and injects an account service into it.
     * @param accountService an accountService.
     */
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Creates an account, returns the created account, and an HTTP status code of 201(CREATED).
     *
     * @param account account information that is required in order to generate an account.
     * @return This returns an account and HTTP status created.
     */
    @PostMapping("/accounts")
    public ResponseEntity<Account> create(@RequestBody Account account) {

        return new ResponseEntity<>(accountService.create(account), HttpStatus.CREATED);
    }

    /**
     * Accepts a URI and returns the account at the URI with a HTTP status code of 200(OK).
     * @param accountId accountId that is required for the URI to reach a specific account path.
     * @return This returns an account and HTTP status OK.
     */
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Account> read(@PathVariable Integer accountId) {
        return new ResponseEntity<>(accountService.getAccount(accountId), HttpStatus.OK);
    }

    /**
     * Returns a collection of accounts for a given userId with a HTTP status code of 200(OK).
     * @param userId userId that is required for the URI to reach a specific account path.
     * @return This returns a set of accounts for a specified userId and HTTP status OK..
     */
    @GetMapping("profiles/accounts/{userId}")
    public ResponseEntity<Set<Account>> getAccounts(@PathVariable Integer userId) {
        return new ResponseEntity<>(accountService.getAccounts(userId), HttpStatus.OK);
    }

    /**
     * Returns an integer representing the number of accounts that userId has with a HTTP status code of 200(OK).
     * @param userId userId that is required for the URI to reach a specific account path.
     * @return This returns an integer and HTTP status OK..
     */
    @GetMapping("profiles/numberOfAccounts/{userId}")
    public ResponseEntity<Integer> getNumberOfAccounts(@PathVariable Integer userId) {
        return new ResponseEntity<>(accountService.getNumberOfAccounts(userId), HttpStatus.OK);
    }

    /**
     * Returns the updated account with a HTTP status code of 200(OK).
     * @param accountId userId that is required for the URI to reach a specific account path.
     * @param account account information that is required in order to update an account.
     * @return This returns an account and HTTP status OK.
     */
    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<Account> update(@PathVariable Integer accountId, @RequestBody Account account) {
        return new ResponseEntity<>(accountService.update(accountId, account), HttpStatus.OK);
    }

    /**
     * Withdraws the specified amount and returns the updated account with a HTTP status code of 200(OK).
     * @param accountId userId that is required for the URI to reach a specific account path.
     * @param amount amount to withdraw
     * @return This returns an account and HTTP status OK.
     */
    @PutMapping("/accounts/withdraw/{accountId}/{amount}")
    public ResponseEntity<Account> withdraw(@PathVariable Integer accountId, @PathVariable Double amount) {
        accountService.withdraw(accountId, amount);
        return new ResponseEntity<>(accountService.getAccount(accountId), HttpStatus.OK);
    }

    /**
     * Deposits the specified amount and returns the updated account with a HTTP status code of 200(OK).
     * @param accountId userId that is required for the URI to reach a specific account path.
     * @param amount amount to deposit
     * @return This returns an account and HTTP status OK.
     */
    @PutMapping("/accounts/deposit/{accountId}/{amount}")
    public ResponseEntity<Account> deposit(@PathVariable Integer accountId, @PathVariable Double amount) {
        accountService.deposit(accountId, amount);
        return new ResponseEntity<>(accountService.getAccount(accountId), HttpStatus.OK);
    }


    /**
     * Transfers the specified amount from two different accounts owned by a user and returns the updated accounts with a HTTP status code of 200(OK).
     * @param account1Id userId that is required for the URI to reach a specific account path.
     * @param account2Id userId that is required for the URI to reach a specific account path.
     * @param amount amount to transfer
     * @return This returns an account and HTTP status OK.
     */
    @PutMapping("/accounts/transfer/{account1Id}/{account2Id}/{amount}")
    public ResponseEntity<Account[]> transfer(@PathVariable Integer account1Id, @PathVariable Integer account2Id, @PathVariable Double amount) {
        return new ResponseEntity<>(accountService.transfer(account1Id, account2Id, amount), HttpStatus.OK);

    }

    /**
     * Deletes the specified accountId and returns a boolean with a HTTP status code of 200(OK).
     * @param accountId userId that is required for the URI to reach a specific account path.
     * @return This returns an account and HTTP status OK.
     */
    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer accountId) {
        return new ResponseEntity<>(accountService.delete(accountId), HttpStatus.OK);
    }

    /**
     * For testing purposes only.
     *
     * Deletes the all accounts and returns a boolean with a HTTP status code of 200(OK).
     * @return This returns an account and HTTP status OK.
     */
    @DeleteMapping("/accounts/")
    public ResponseEntity<Boolean> deleteAllAccounts() {
        return new ResponseEntity<>(accountService.deleteAllAccounts(), HttpStatus.OK);
    }
}
