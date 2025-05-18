package com.cg.training.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import com.cg.training.dao.AppointmentSystemDAO;
import com.cg.training.exceptions.InvalidAppointmentException;
import com.cg.training.models.Appointment;
import com.cg.training.models.Doctor;
import com.cg.training.models.Patient;

/**
 * This class implements an appointment system that allows registering patients and doctors,
 * booking appointments, viewing details, and saving data to a file.
 * 
 * @author Rajarshi Das
 */

public class AppointmentSystem implements AppointmentSystemDAO {
    /** List to store all registered patients **/
    public List<Patient> patients;
    /** List to store all registered doctors **/
    public List<Doctor> doctors;
    /** List to store all booked appointments **/
    public List<Appointment> appointments;
    /** Counter to generate unique patient IDs **/
    int patientCounter = 1000;
    /** Counter to generate unique doctor IDs **/
    int doctorCounter = 1000;
    
    /**
     * Constructor to initialize the lists for patients, doctors, and appointments.
     */    
    public AppointmentSystem() {
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        appointments = new ArrayList<>();
    }
    /**
     * Registers a new patient with a unique ID.
     * 
     * @param name The name of the patient.
     */
    @Override
    public void registerPatient(String name) {
        try {
            String id = "P" + patientCounter++;
            patients.add(new Patient(id, name));
            System.out.println("Patient registered with ID: " + id);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    /**
     * Registers a new doctor with a unique ID.
     * 
     * @param name The name of the doctor.
     */
    @Override
    public void registerDoctor(String name) {
        try {
            String id = "D" + doctorCounter++;
            doctors.add(new Doctor(id, name));
            System.out.println("Doctor registered with ID: " + id);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    /**
     * Books an appointment for the given patient with an available doctor.
     * Appointment details are also saved to a CSV file.
     * 
     * @param patient The patient booking the appointment.
     * @return The booked appointment or null if booking failed.
     */
    @Override
    public Appointment bookAppointment(Patient patient) {
        try (BufferedWriter write = new BufferedWriter(new FileWriter("D:\\filesCreating\\Appointments.csv", true))) {
            Optional<Doctor> availableDoctor = doctors.stream()
                    .filter(Doctor::isAvailable)
                    .findFirst();

            if (!availableDoctor.isPresent()) {
                throw new InvalidAppointmentException("No available doctor found.");
            }

            Appointment appointment = new Appointment(patient, availableDoctor.get());
            appointments.add(appointment);

            write.write(appointment.appointmentDetails());
            write.newLine();

            System.out.println("Appointment booked.");
            return appointment;

        } catch (InvalidAppointmentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    /**
     * Saves all existing appointments to a CSV file.
     */
    @Override
    public void saveAppointmentsToFile() {
        try (BufferedWriter write = new BufferedWriter(new FileWriter("D:\\filesCreating\\Appointments.csv", true))) {
            appointments.stream()
                    .map(Appointment::appointmentDetails)
                    .forEach(details -> {
                        try {
                            write.write(details);
                            write.newLine();
                        } catch (Exception e) {
                            System.out.println("Error writing appointment: " + e.getMessage());
                        }
                    });
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    /**
     * Marks an appointment as completed based on the index.
     * 
     * @param index The index of the appointment in the list.
     */
    @Override
    public void completeAppointment(int index) {
        try {
            if (index < 0 || index >= appointments.size()) {
                throw new InvalidAppointmentException("Invalid appointment index.");
            }
            Appointment appointment = appointments.get(index);
            if ("Completed".equals(appointment.getStatus())) {
                throw new InvalidAppointmentException("Appointment already completed.");
            }
            appointment.completeAppointment();
            System.out.println("Appointment marked completed.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Displays all registered doctors.
     */
    @Override
    public void showAllDoctors() {
    	if(doctors.size()==0)
    		System.out.println("No registered doctors");
    	else {
    		System.out.println(".....All Doctors.....");
    		doctors.forEach(Doctor::showProfile);
    	}
    }
    /**
     * Displays all booked appointments.
     */
    @Override
    public void showAllAppointments() {
        IntStream.range(0, appointments.size())
                .forEach(i -> System.out.println(i + ": " + appointments.get(i).appointmentDetails()));
    }
    /**
     * Displays all appointments for a specific doctor using doctor ID.
     * 
     * @param doctorId The ID of the doctor.
     */
    @Override
    public void showAppointmentsByDoctorId(String doctorId) {
        List<Appointment> filtered = appointments.stream()
                .filter(a -> a.doctor.getId().equals(doctorId))
                .toList();

        if (filtered.isEmpty()) {
            System.out.println("No appointments found for Doctor ID: " + doctorId);
        } else {
            IntStream.range(0, filtered.size())
                    .forEach(i -> System.out.println("Appointment Index: " + i + ": " + filtered.get(i).appointmentDetails()));
        }
    }
    /**
     * Displays all appointments for a specific patient using patient ID.
     * 
     * @param patientId The ID of the patient.
     */
    @Override
    public void showAppointmentsByPatientId(String patientId) {
        List<Appointment> filtered = appointments.stream()
                .filter(a -> a.patient.getId().equals(patientId))
                .toList();

        if (filtered.isEmpty()) {
            System.out.println("No appointments found for Patient ID: " + patientId);
        } else {
            IntStream.range(0, filtered.size())
                    .forEach(i -> System.out.println("Appointment Index: " + i + ": " + filtered.get(i).appointmentDetails()));
        }
    }
    /**
     * Finds a patient by their ID.
     * 
     * @param id The ID of the patient.
     * @return The patient object or null if not found.
     */
    @Override
    public Patient findPatientById(String id) {
        return patients.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
