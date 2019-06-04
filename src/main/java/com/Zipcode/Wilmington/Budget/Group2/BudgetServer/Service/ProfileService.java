package com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Service;

import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity.Account;
import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity.Profile;
import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Repositories.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProfileService {
    ProfileRepo profileRepo;

    @Autowired
    public ProfileService(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }

    public Profile create(Profile profile) {
        return profileRepo.save(profile);
    }

    public Profile getUser(Integer userID) {
        return profileRepo.findById(userID).get();
    }

    public Set<Profile> getAllUsers() {
        return new HashSet<>(profileRepo.findAll());
    }

    public Set<Account> getAccounts(Integer userId) {
        return profileRepo.findById(userId).get().getAccounts();
    }

//    public Profile updateName(Profile profile, Integer userID){
//        Profile originalProfile = profileRepo.findById(userID).get();
//        originalProfile.setName(profile.getName());
//        return profileRepo.save(originalProfile);
//    }

    public Boolean deleteUser(Integer userID) {
        profileRepo.deleteById(userID);
        return true;
    }

    public Boolean deleteAllProfiles() {
        profileRepo.deleteAll();
        return true;
    }
}
