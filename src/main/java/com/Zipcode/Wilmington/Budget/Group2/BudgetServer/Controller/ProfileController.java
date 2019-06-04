package com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Controller;

import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity.Account;
import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity.Profile;
import com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin("*")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/profiles")
    public ResponseEntity<Profile> create(@RequestBody Profile profile) {
        return new ResponseEntity<>(profileService.create(profile), HttpStatus.CREATED);
    }

    @GetMapping("/profiles/{userId}")
    public ResponseEntity<Profile> show(@PathVariable Integer userId) {
        return new ResponseEntity<>(profileService.getUser(userId), HttpStatus.OK);
    }

    @GetMapping("profiles/{userId}/accounts")
    public ResponseEntity<Set<Account>> getAccounts(@PathVariable Integer userId) {
        return new ResponseEntity<>(profileService.getAccounts(userId), HttpStatus.OK);
    }

    @GetMapping("/profiles/allProfiles")
    public ResponseEntity<Set<Profile>> getAllProfiles() {
        return new ResponseEntity<>(profileService.getAllUsers(), HttpStatus.OK);
    }

//    @PutMapping("/users/{userId}")
//    public ResponseEntity<Profile> update(@PathVariable Integer userId, @RequestBody Profile user){
//        return new ResponseEntity<>(userService.updateName(user, userId), HttpStatus.OK);
//    }

    @DeleteMapping("/profiles/{userId}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer userId) {
        return new ResponseEntity<>(profileService.deleteUser(userId), HttpStatus.OK);
    }

    @DeleteMapping("/profiles/deleteAllProfiles")
    public ResponseEntity<Boolean> deleteAllProfiles() {
        return new ResponseEntity<>(profileService.deleteAllProfiles(), HttpStatus.OK);
    }
}
