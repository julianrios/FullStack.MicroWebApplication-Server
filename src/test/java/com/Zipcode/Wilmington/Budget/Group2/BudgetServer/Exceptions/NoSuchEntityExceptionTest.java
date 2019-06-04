package com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Exceptions;

import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity.Account;
import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity.Profile;
import org.junit.Test;

public class NoSuchEntityExceptionTest {

    @Test(expected = NoSuchEntityException.class)
    public void TestNoSuchEntityException() throws NoSuchEntityException {
        Object object = new Object();

        if (!(object instanceof Account || object instanceof Profile)) {
            throw new NoSuchEntityException("thrown");
        }
    }
}
