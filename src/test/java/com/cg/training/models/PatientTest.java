package com.cg.training.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PatientTest {

	private Patient patient;

    @Before
    public void setUp() {
        patient = new Patient("P1001", "Anjali");
    }

    @Test
    public void testPatientInitialization() {
        assertEquals("P1001", patient.getId());
        assertEquals("Anjali", patient.getName());
    }

    @Test
    public void testPatientShowProfile() {
        patient.showProfile();
    }

    @Test
    public void testPatientWithMultiWordName() {
        Patient p = new Patient("P1002", "Rina Sharma");
        assertEquals("Rina Sharma", p.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPatientName() {
        new Patient("P1003", "123@Name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPatientNameWithDoubleSpaces() {
        new Patient("P1004", "Karan  Singh");
    }

    @Test
    public void testTrimmedNameStillValid() {
        Patient p = new Patient("P1005", "   Deepak Kumar  ");
        assertEquals("Deepak Kumar", p.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPatientEmptyName() {
        new Patient("P1006", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPatientNullName() {
        new Patient("P1007", null);
    }

}
