package com.cg.training.models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AdminTest {

	private Admin admin;
    private List<Doctor> doctors;

    @Before
    public void setUp() {
        admin = new Admin("A1001", "Bob");
        doctors = new ArrayList<>();
        doctors.add(new Doctor("D2001", "Charlie"));
    }

    @Test
    public void testAdminInitialization() {
        assertEquals("A1001", admin.getId());
        assertEquals("Bob", admin.getName());
    }

    @Test
    public void testRemoveExistingDoctor() {
        admin.removeDoctor(doctors, "D2001");
        assertTrue(doctors.isEmpty());
    }

    @Test
    public void testRemoveNonExistingDoctor() {
        admin.removeDoctor(doctors, "D9999");
        assertEquals(1, doctors.size());
    }

    @Test
    public void testRemoveDoctorFromEmptyList() {
        doctors.clear();
        admin.removeDoctor(doctors, "D2001");
    }

    @Test
    public void testShowProfile() {
        admin.showProfile();
    }

}
