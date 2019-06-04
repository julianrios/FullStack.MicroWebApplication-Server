package com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Controller;

import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.BudgetServerApplication;
import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity.Account;
import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity.Profile;
import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Service.ProfileService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BudgetServerApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfileControllerTest {

    @MockBean
    private ProfileService service;

    private ProfileController controller;

    @Before
    public void setUp() {
        this.controller = new ProfileController(service);
    }

    @Test
    public void testCreate() {
        //Given
        HttpStatus expected = HttpStatus.CREATED;
        Profile expectedProfile = new Profile();
        BDDMockito
                .given(service.create(expectedProfile))
                .willReturn(expectedProfile);

        //When
        ResponseEntity<Profile> response = controller.create(expectedProfile);
        HttpStatus actual = response.getStatusCode();
        Profile actualProfile = response.getBody();

        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedProfile, actualProfile);
    }

    @Test
    public void testGetProfile() {
        //Given
        HttpStatus expected = HttpStatus.OK;
        Profile expectedProfile = new Profile(1, "Davis");
        BDDMockito
                .given(service.getUser(1))
                .willReturn(expectedProfile);

        // When
        ResponseEntity<Profile> response = controller.show(1);
        HttpStatus actual = response.getStatusCode();
        Profile actualProfile = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedProfile, actualProfile);
    }

    //Given
    @Test
    public void testDeleteProfile() {
        //Given
        HttpStatus expected = HttpStatus.OK;
        Profile expectedProfile = new Profile(1, "Davis");
        BDDMockito
                .given(service.deleteUser(1))
                .willReturn(true);

        //When
        ResponseEntity<Boolean> response = controller.delete(1);
        HttpStatus actual = response.getStatusCode();
        Boolean actualBoolean = response.getBody();

        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(true, actualBoolean);
    }

    @Test
    public void testGetAccounts() {
        //Given
        HttpStatus expected = HttpStatus.OK;
        Account account = new Account(1, 5.0);
        Set<Account> accountList = new HashSet<>();
        accountList.add(account);
        BDDMockito
                .given(service.getAccounts(1))
                .willReturn(accountList);

        //When
        ResponseEntity<Set<Account>> response = controller.getAccounts(1);
        HttpStatus actual = response.getStatusCode();
        Set<Account> actualAccounts = response.getBody();

        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(accountList, actualAccounts);
    }

    @Test
    public void testGetAllProfiles() {
        // Given
        HttpStatus expected = HttpStatus.OK;
        Profile profile = new Profile(1, "Julian");
        Profile profile1 = new Profile(2, "Mike");
        Set<Profile> expectedProfiles = new HashSet<>();
        expectedProfiles.add(profile);
        expectedProfiles.add(profile1);
        BDDMockito
                .given(service.getAllUsers())
                .willReturn(expectedProfiles);


        // When
        ResponseEntity<Set<Profile>> response = controller.getAllProfiles();
        HttpStatus actual = response.getStatusCode();
        Set<Profile> actualProfiles = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedProfiles, actualProfiles);
    }

    @Test
    public void testDeleteAllProfiles() {
        // Given
        HttpStatus expected = HttpStatus.OK;
        Profile profile = new Profile(1, "Julian");
        Profile profile1 = new Profile(2, "Mike");
        Set<Profile> expectedProfiles = new HashSet<>();
        expectedProfiles.add(profile);
        expectedProfiles.add(profile1);
        BDDMockito
                .given(service.deleteAllProfiles())
                .willReturn(true);

        // When
        ResponseEntity<Boolean> response = controller.deleteAllProfiles();
        HttpStatus actual = response.getStatusCode();
        Boolean actualBoolean = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(true, actualBoolean);
    }
}
