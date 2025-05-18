package com.cg.training.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AppointmentTest {

	private Appointment appointment;
    private Patient patient;
    private Doctor doctor;

    @Before
    public void setUp() {
        patient = new Patient("P1002", "Ram");
        doctor = new Doctor("D3002", "Shyam");
        appointment = new Appointment(patient, doctor);
    }

    @Test
    public void testAppointmentInitialization() {
        assertEquals("Scheduled", appointment.getStatus());
        assertFalse(doctor.isAvailable());
    }

    @Test
    public void testCompleteAppointment() {
        appointment.completeAppointment();
        assertEquals("Completed", appointment.getStatus());
        assertTrue(doctor.isAvailable());
    }

    @Test
    public void testAppointmentDetails() {
        String details = appointment.appointmentDetails();
        assertTrue(details.contains("Ram"));
        assertTrue(details.contains("Shyam"));
        assertTrue(details.contains("Scheduled") || details.contains("Completed"));
    }

}
