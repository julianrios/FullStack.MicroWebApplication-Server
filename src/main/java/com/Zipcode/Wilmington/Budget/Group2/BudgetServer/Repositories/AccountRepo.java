package com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Repositories;

import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
    Set<Account> findAllByProfileID(Integer profile_id);

    Integer countByProfileID(Integer profile_id);
}
