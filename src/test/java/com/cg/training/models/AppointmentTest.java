package com.cg.training.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the Appointment model class to ensure its methods work correctly.
 * It checks appointment creation, status updates, and appointment details.
 * 
 * @author: Sreejit Sarkar
 */
public class AppointmentTest {

    private Appointment appointment;
    private Patient patient;
    private Doctor doctor;

    /**
     * Sets up the objects before each test: one patient, one doctor, and one appointment.
     */
    @Before
    public void setUp() {
        patient = new Patient("P1002", "Ram");
        doctor = new Doctor("D3002", "Shyam");
        appointment = new Appointment(patient, doctor);
    }

    /**
     * Tests if the appointment is initialized correctly with the "Scheduled" status
     * and the doctor becomes unavailable after booking.
     */
    @Test
    public void testAppointmentInitialization() {
        assertEquals("Scheduled", appointment.getStatus());
        assertFalse(doctor.isAvailable()); // Doctor should be unavailable after booking
    }

    /**
     * Tests if the appointment status changes to "Completed"
     * and the doctor becomes available again.
     */
    @Test
    public void testCompleteAppointment() {
        appointment.completeAppointment();
        assertEquals("Completed", appointment.getStatus());
        assertTrue(doctor.isAvailable()); // Doctor should be available after completion
    }

    /**
     * Tests if the appointmentDetails() method returns a string
     * containing the patient name, doctor name, and status.
     */
    @Test
    public void testAppointmentDetails() {
        String details = appointment.appointmentDetails();
        assertTrue(details.contains("Ram"));
        assertTrue(details.contains("Shyam"));
        assertTrue(details.contains("Scheduled") || details.contains("Completed"));
    }

}
