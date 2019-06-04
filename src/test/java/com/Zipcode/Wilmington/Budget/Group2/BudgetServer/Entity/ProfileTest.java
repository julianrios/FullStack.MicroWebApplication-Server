package com.Zipcode.Wilmington.Budget.Group2.BudgetServer.Entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Entity;

public class ProfileTest {
    @Test
    public void testClassSignatureAnnotations() {
        Assert.assertTrue(Profile.class.isAnnotationPresent(Entity.class));
    }

    @Test
    public void testCreateJson() throws JsonProcessingException {
        ObjectMapper jsonWriter = new ObjectMapper();
        Profile profile = new Profile();
        String json = jsonWriter.writeValueAsString(profile);
    }

    @Test
    public void testEquals() {
        Profile profile = new Profile(1, "Julian");
        Profile profile1 = new Profile(1, "Julian");
        Assert.assertTrue(profile.equals(profile1) && profile1.equals(profile));
    }

    @Test
    public void testHashCode() {
        Profile profile = new Profile(1, "Julian");
        Profile profile1 = new Profile(1, "Julian");
        Assert.assertTrue(profile.hashCode() == profile1.hashCode());
    }


}
