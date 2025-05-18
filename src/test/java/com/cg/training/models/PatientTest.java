package com.cg.training.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the Patient model class.
 * It checks proper initialization, name validation, and profile display.
 * 
 * @author Sampritee Dey
 */
public class PatientTest {

    private Patient patient;

    /**
     * Sets up a sample patient before each test.
     */
    @Before
    public void setUp() {
        patient = new Patient("P1001", "Anjali");
    }

    /**
     * Tests if the patient is initialized correctly with ID and name.
     */
    @Test
    public void testPatientInitialization() {
        assertEquals("P1001", patient.getId());
        assertEquals("Anjali", patient.getName());
    }

    /**
     * Tests if the patient's profile is displayed properly.
     */
    @Test
    public void testPatientShowProfile() {
        patient.showProfile(); // Output is printed to console
    }

    /**
     * Tests if a patient with a multi-word name is accepted.
     */
    @Test
    public void testPatientWithMultiWordName() {
        Patient p = new Patient("P1002", "Rina Sharma");
        assertEquals("Rina Sharma", p.getName());
    }

    /**
     * Tests if an invalid name containing numbers or special characters throws an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPatientName() {
        new Patient("P1003", "123@Name");
    }

    /**
     * Tests if a name with double spaces throws an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPatientNameWithDoubleSpaces() {
        new Patient("P1004", "Karan  Singh"); // Two spaces between names
    }

    /**
     * Tests if leading/trailing spaces are trimmed and the name is still valid.
     */
    @Test
    public void testTrimmedNameStillValid() {
        Patient p = new Patient("P1005", "   Deepak Kumar  ");
        assertEquals("Deepak Kumar", p.getName());
    }

    /**
     * Tests if an empty string as a name throws an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPatientEmptyName() {
        new Patient("P1006", "");
    }

    /**
     * Tests if a null name throws an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPatientNullName() {
        new Patient("P1007", null);
    }

}
