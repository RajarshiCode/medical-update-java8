package com.cg.training.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the Doctor model class to ensure it behaves correctly.
 * It checks doctor creation, availability status, and name validation.
 * 
 * Author: Soumili Ghosh
 */
public class DoctorTest {

    private Doctor doctor;

    /**
     * Sets up a Doctor object before each test.
     */
    @Before
    public void setUp() {
        doctor = new Doctor("D1001", "JohnDoe");
    }

    /**
     * Tests if the doctor is initialized with the correct ID, name, and availability.
     */
    @Test
    public void testDoctorInitialization() {
        assertEquals("D1001", doctor.getId());
        assertEquals("JohnDoe", doctor.getName());
        assertTrue(doctor.isAvailable());
    }

    /**
     * Tests if the doctor is set to available.
     */
    @Test
    public void testSetAvailableTrue() {
        doctor.setAvailable(true);
        assertTrue(doctor.isAvailable());
    }

    /**
     * Tests if the doctor is set to unavailable.
     */
    @Test
    public void testSetAvailableFalse() {
        doctor.setAvailable(false);
        assertFalse(doctor.isAvailable());
    }

    /**
     * Calls the method to display the doctor's profile.
     * (This test ensures the method runs without errors.)
     */
    @Test
    public void testShowProfile() {
        doctor.setAvailable(true);
        doctor.showProfile(); // Output is printed to console
    }

    /**
     * Tests that creating a doctor with an invalid name throws an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDoctorNameThrowsException() {
        new Doctor("D1002", "Dr123"); // Invalid name with numbers
    }

}
