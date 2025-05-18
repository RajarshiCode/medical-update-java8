package com.cg.training.service;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import com.cg.training.models.Appointment;
import com.cg.training.models.Patient;

public class AppointmentSystemTest {

	private AppointmentSystem system;

    @Before
    public void setUp() {
        system = new AppointmentSystem();
    }

    @Test
    public void testRegisterPatient() {
        system.registerPatient("Lakshmi");
        assertNotNull(system.findPatientById("P1000"));
    }

    @Test
    public void testInvalidPatientName() {
        system.registerPatient("1234");
        assertNull(system.findPatientById("P1000"));
    }

    @Test
    public void testRegisterDoctor() {
        system.registerDoctor("Ramesh");
        assertEquals(1, system.doctors.size());
        assertEquals("Ramesh", system.doctors.get(0).getName());
    }

    @Test
    public void testBookAppointmentSuccess() {
        system.registerPatient("Meera");
        system.registerDoctor("Suresh");

        Patient patient = system.findPatientById("P1000");
        Appointment appt = system.bookAppointment(patient);

        assertNotNull(appt);
        assertEquals("Scheduled", appt.getStatus());
    }

    @Test
    public void testBookAppointmentFailure_NoDoctor() {
        system.registerPatient("Geeta");
        Patient patient = system.findPatientById("P1000");
        Appointment appt = system.bookAppointment(patient);
        assertNull(appt);
    }

    @Test
    public void testCompleteAppointment() {
        system.registerPatient("Amit");
        system.registerDoctor("Ravi");

        Patient p = system.findPatientById("P1000");
        Appointment appt = system.bookAppointment(p);
        assertNotNull("Appointment must not be null", appt);

        system.completeAppointment(0);
        assertEquals("Completed", appt.getStatus());
    }

    @Test
    public void testCompleteAppointmentInvalidIndex() {
        system.completeAppointment(10); // should not crash
    }

    @Test
    public void testReCompleteAppointmentThrows() {
        system.registerPatient("Divya");
        system.registerDoctor("Arun");

        Appointment appt = system.bookAppointment(system.findPatientById("P1000"));
        assertNotNull("Appointment should be booked", appt);

        system.completeAppointment(0);
        system.completeAppointment(0); // should be handled internally
        assertEquals("Completed", appt.getStatus());
    }

    @Test
    public void testShowAllDoctors() {
        system.registerDoctor("Hari");
        system.showAllDoctors();
    }

    @Test
    public void testShowAllAppointments() {
        system.registerPatient("Neha");
        system.registerDoctor("Rohit");
        system.bookAppointment(system.findPatientById("P1000"));
        system.showAllAppointments();
    }

    @Test
    public void testShowAppointmentsByDoctorId() {
        system.registerPatient("Dev");
        system.registerDoctor("Ritu");
        system.bookAppointment(system.findPatientById("P1000"));
        String doctorId = system.doctors.get(0).getId();
        system.showAppointmentsByDoctorId(doctorId);
        system.showAppointmentsByDoctorId("INVALID_ID");
    }

    @Test
    public void testShowAppointmentsByPatientId() {
        system.registerPatient("Seema");
        system.registerDoctor("Raj");
        system.bookAppointment(system.findPatientById("P1000"));
        system.showAppointmentsByPatientId("P1000");
        system.showAppointmentsByPatientId("INVALID_ID");
    }

    @Test
    public void testFindPatientByIdNotFound() {
        Patient p = system.findPatientById("P9999");
        assertNull(p);
    }

    @Test
    public void testSaveAppointmentsToFile() throws Exception {
        system.registerPatient("Manoj");
        system.registerDoctor("Shiv");
        system.bookAppointment(system.findPatientById("P1000"));

        system.saveAppointmentsToFile();

        File file = new File("D:/filesCreating/Appointments.csv");
        assertTrue(file.exists());
        assertTrue(Files.readString(Paths.get(file.getPath())).contains("Manoj"));
    }

    @Test
    public void testSaveAppointmentsToInvalidPath() {
        try {
            AppointmentSystem faultySystem = new AppointmentSystem() {
                @Override
                public void saveAppointmentsToFile() {
                    try (BufferedWriter write = new BufferedWriter(new FileWriter("Z:/invalid/path/test.csv"))) {
                        write.write("test");
                    } catch (Exception e) {
                        System.out.println("Expected error: " + e.getMessage());
                    }
                }
            };
            faultySystem.saveAppointmentsToFile();
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

}
