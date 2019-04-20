package com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Controller;

import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity.Account;
import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin("*")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/accounts")
    public ResponseEntity<Account> create(@RequestBody Account account){

        return new ResponseEntity<>(accountService.create(account), HttpStatus.CREATED);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Account> read(@PathVariable Integer accountId){
            return new ResponseEntity<>(accountService.getAccount(accountId),HttpStatus.OK);
    }

    @GetMapping("profiles/accounts/{userId}")
    public  ResponseEntity<Set<Account>> getAccounts(@PathVariable Integer userId){
        return new ResponseEntity<>(accountService.getAccounts(userId),HttpStatus.OK);
    }

    @PutMapping("/accounts/{accountId}")
    public  ResponseEntity<Account> update(@PathVariable Integer accountId, @RequestBody Account account){
            return new ResponseEntity<>(accountService.update(accountId, account), HttpStatus.OK);
    }

    @PutMapping("/accounts/withdraw/{accountId}/{amount}")
    public ResponseEntity<Account> withdraw(@PathVariable Integer accountId,@PathVariable Double amount){
        accountService.withdraw(accountId,amount);
        return new ResponseEntity<>(accountService.getAccount(accountId),HttpStatus.OK);
    }

    @PutMapping("/accounts/deposit/{accountId}/{amount}")
    public ResponseEntity<Account> deposit(@PathVariable Integer accountId,@PathVariable Double amount){
        accountService.deposit(accountId,amount);
        return new ResponseEntity<>(accountService.getAccount(accountId),HttpStatus.OK);
    }

    @PutMapping("/accounts/transfer/{account1Id}/{account2Id}/{amount}")
    public ResponseEntity<Account[]> transfer(@PathVariable Integer account1Id, @PathVariable Integer account2Id, @PathVariable Double amount){
        return new ResponseEntity<>(accountService.transfer(account1Id,account2Id,amount),HttpStatus.OK);

    }

    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer accountId){
        return new ResponseEntity<>(accountService.delete(accountId),HttpStatus.OK);
    }
}