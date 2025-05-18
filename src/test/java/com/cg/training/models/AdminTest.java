package com.cg.training.models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the Admin class and its functionality
 * such as initialization, removing doctors, and showing profile.
 * 
 * @author Sampritee Dey
 */
public class AdminTest {

    private Admin admin;
    private List<Doctor> doctors;

    /**
     * This method runs before each test. It initializes
     * an admin and a list of doctors for testing.
     */
    @Before
    public void setUp() {
        admin = new Admin("A1001", "Bob");
        doctors = new ArrayList<>();
        doctors.add(new Doctor("D2001", "Charlie"));
    }

    /**
     * Tests if the Admin object is initialized correctly
     * with the given ID and name.
     */
    @Test
    public void testAdminInitialization() {
        assertEquals("A1001", admin.getId());
        assertEquals("Bob", admin.getName());
    }

    /**
     * Tests that an existing doctor is removed from the list successfully.
     */
    @Test
    public void testRemoveExistingDoctor() {
        admin.removeDoctor(doctors, "D2001");
        assertTrue(doctors.isEmpty());
    }

    /**
     * Tests that if the doctor ID is not found, the list remains unchanged.
     */
    @Test
    public void testRemoveNonExistingDoctor() {
        admin.removeDoctor(doctors, "D9999");
        assertEquals(1, doctors.size());
    }

    /**
     * Tests removing a doctor from an empty list is handled gracefully.
     */
    @Test
    public void testRemoveDoctorFromEmptyList() {
        doctors.clear();
        admin.removeDoctor(doctors, "D2001");
    }

    /**
     * Tests the showProfile method of the admin.
     */
    @Test
    public void testShowProfile() {
        admin.showProfile();
    }
}
