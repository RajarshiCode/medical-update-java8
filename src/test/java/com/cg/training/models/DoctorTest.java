package com.cg.training.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DoctorTest {

	private Doctor doctor;

    @Before
    public void setUp() {
        doctor = new Doctor("D1001", "JohnDoe");
    }

    @Test
    public void testDoctorInitialization() {
        assertEquals("D1001", doctor.getId());
        assertEquals("JohnDoe", doctor.getName());
        assertTrue(doctor.isAvailable());
    }

    @Test
    public void testSetAvailableTrue() {
        doctor.setAvailable(true);
        assertTrue(doctor.isAvailable());
    }

    @Test
    public void testSetAvailableFalse() {
        doctor.setAvailable(false);
        assertFalse(doctor.isAvailable());
    }

    @Test
    public void testShowProfile() {
        doctor.setAvailable(true);
        doctor.showProfile();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDoctorNameThrowsException() {
        new Doctor("D1002", "Dr123");
    }

}
