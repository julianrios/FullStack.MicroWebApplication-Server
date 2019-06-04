package com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Service;

import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity.Account;
import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity.Profile;
import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Repositories.ProfileRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProfileServiceTest {

    @MockBean
    private ProfileRepo repo;

    @MockBean
    private ProfileService service;

    @Before
    public void setUp() {
        service = new ProfileService(repo);
    }

    @Test
    public void testDelete() {
        Profile profile = new Profile(1, "Davis");
        Mockito.when(repo.findById(1)).thenReturn(Optional.of(profile));

        service.deleteUser(1);

        Mockito.verify(repo, Mockito.times(1)).deleteById(1);
    }

    @Test
    public void testGetAccounts() {
        Profile profile = new Profile(1, "Davis");
        Account account = new Account(1, 5.0);
        Set<Account> accountList = new HashSet<>();
        accountList.add(account);
        profile.setAccounts(accountList);
        Mockito.when(repo.findById(1)).thenReturn(Optional.of(profile));

        service.getAccounts(1);

        Mockito.verify(repo, Mockito.times(1)).findById(1);
    }

    @Test
    public void testCreateUser() {
        Profile profile = new Profile(1, "Davis");
        Mockito.when(repo.save(profile)).thenReturn(profile);

        service.create(profile);

        Mockito.verify(repo, Mockito.times(1)).save(profile);
    }

    @Test
    public void testGetUser() {
        Profile profile = new Profile(1, "Davis");
        Mockito.when(repo.findById(1)).thenReturn(Optional.of(profile));

        service.getUser(1);

        Mockito.verify(repo, Mockito.times(1)).findById(1);
    }

    @Test
    public void testGetAllProfiles() {
        Profile profile = new Profile(1, "Julian");
        Profile profile1 = new Profile(2, "Mike");
        Set<Profile> expectedProfiles = new HashSet<>();
        expectedProfiles.add(profile);
        expectedProfiles.add(profile1);

        service.getAllUsers();

        Mockito.verify(repo, Mockito.times(1)).findAll();
    }

    @Test
    public void testDeleteAllProfiles() {
        Profile profile = new Profile(1, "Julian");
        Profile profile1 = new Profile(2, "Mike");
        Set<Profile> expectedProfiles = new HashSet<>();
        expectedProfiles.add(profile);
        expectedProfiles.add(profile1);

        service.deleteAllProfiles();
        Mockito.verify(repo, Mockito.times(1)).deleteAll();
    }
//    @Test
//    public void testShow() throws Exception {
//        Integer givenId = 1;
//        BDDMockito
//                .given(repo.findById(givenId))
//                .willReturn(Optional.of(new Profile(givenId, "Davis")));
//
//        String expectedContent = "{\"id\":1,\"name\":\"Davis\"}";
//        this.mockMvc.perform(MockMvcRequestBuilders
//                .get("/profiles/" + givenId))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//        //.andExpect(MockMvcResultMatchers.content().string(expectedContent));
//    }
//
//    @Test
//    public void testCreate() throws Exception {
//        Profile profile = new Profile(1, "Davis");
//        BDDMockito
//                .given(repo.save(profile))
//                .willReturn(profile);
//
//        String expectedContent = "{\"id\":1,\"name\":\"Davis\"}";
//        this.mockMvc.perform(MockMvcRequestBuilders
//                .post("/profiles/")
//                .content(expectedContent)
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isCreated());
//        //.andExpect(MockMvcResultMatchers.content().string(expectedContent));

//    }
}
