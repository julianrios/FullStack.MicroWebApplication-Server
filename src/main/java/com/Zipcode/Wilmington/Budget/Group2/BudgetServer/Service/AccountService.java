package com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Service;

import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity.Account;
import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AccountService {

    AccountRepo accountRepo;

    @Autowired
    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account create(Account account) {

        return accountRepo.save(account);
    }

    public Account getAccount(Integer accountID) {

        return accountRepo.findById(accountID).get();

    }

    public Set<Account> getAccounts(Integer profile_id) {

        return accountRepo.findAllByProfileID(profile_id);

    }

    public Integer getNumberOfAccounts(Integer profile_id) {
        return accountRepo.countByProfileID(profile_id);
    }

    public Account update(Integer accountID, Account newAccountData) {
        Account account = accountRepo.findById(accountID).get();
        account.setBalance(newAccountData.getBalance());
        account.setProfileID(newAccountData.getProfileID());

        return accountRepo.save(account);
    }

    public Account withdraw(Integer accountID, Double amount) {
        Account account = accountRepo.findById(accountID).get();
        if (amount > 0 && amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
        }
        return accountRepo.save(account);
    }

    public Account deposit(Integer accountID, Double amount) {
        Account account = accountRepo.findById(accountID).get();
        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
        }
        return accountRepo.save(account);
    }

    public Account[] transfer(Integer accountIdFrom, Integer accountIdTo, Double amount) {
        if (amount > 0 &&
                accountRepo.findById(accountIdFrom).get().getProfileID()
                        .equals(accountRepo.findById(accountIdTo).get().getProfileID())) {
            Account[] accounts = new Account[2];
            accounts[0] = withdraw(accountIdFrom, amount);
            accounts[1] = deposit(accountIdTo, amount);
            return accounts;
        }
        return null;
    }

    public Boolean delete(Integer accountID) {
        accountRepo.deleteById(accountID);
        return true;
    }

    public Boolean deleteAllAccounts() {
        accountRepo.deleteAll();
        return true;
    }
}
