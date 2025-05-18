package com.cg.training.dao;

import com.cg.training.models.Appointment;
import com.cg.training.models.Patient;

/**
 * This interface defines the methods for managing an appointment system.
 * It includes functionalities like registering patients/doctors, 
 * booking appointments, displaying data, and saving information.
 * 
 * @author Rajarshi Das
 */
public interface AppointmentSystemDAO {

    /**
     * Registers a new patient with a unique ID and validates the name.
     * 
     * @param name The name of the patient.
     */
    void registerPatient(String name);

    /**
     * Registers a new doctor with a unique ID and validates the name.
     * 
     * @param name The name of the doctor.
     */
    void registerDoctor(String name);

    /**
     * Books an appointment for a given patient with the first available doctor.
     * 
     * @param patient The patient who wants to book an appointment.
     * @return The appointment object if booked, otherwise null.
     */
    Appointment bookAppointment(Patient patient);

    /**
     * Marks the appointment as completed based on the index provided by the user.
     * 
     * @param index The index of the appointment in the appointment list.
     */
    void completeAppointment(int index);

    /**
     * Displays the profile of all registered doctors.
     */
    void showAllDoctors();

    /**
     * Displays all the appointments booked in the system.
     */
    void showAllAppointments();

    /**
     * Displays all the appointments for a specific doctor using their doctor ID.
     * 
     * @param doctorId The ID of the doctor.
     */
    void showAppointmentsByDoctorId(String doctorId);

    /**
     * Displays all the appointments for a specific patient using their patient ID.
     * 
     * @param patientId The ID of the patient.
     */
    void showAppointmentsByPatientId(String patientId);

    /**
     * Finds a patient in the system by their ID.
     * 
     * @param id The ID of the patient.
     * @return The Patient object, or null if not found.
     */
    Patient findPatientById(String id);

    /**
     * Saves all appointment details to a file for record-keeping.
     */
    void saveAppointmentsToFile();

    /**
     * Default method to display a message when no appointments are found.
     * Can be used by implementing classes as needed.
     */
    default void showNoAppointmentsMessage() {
        System.out.println("No appointments found.");
    }
}