package com.cg.training.models;

import java.util.List;
import java.util.Optional;

import com.cg.training.annotations.Security;

/**
 * The Admin class represents an admin user who can manage the system,
 * such as removing doctors from the list.
 * 
 * This class extends the abstract User class.
 * 
 * @author Rajarshi Das
 */
@Security(role = "Admin")
public class Admin extends User {

    /**
     * Constructor to create an Admin with a given ID and name.
     * 
     * @param id   The unique ID of the admin.
     * @param name The name of the admin.
     */
    public Admin(String id, String name) {
        super(id, name);
    }

    /**
     * Displays the profile of the admin user.
     * Prints admin ID and name.
     */
    @Override
    public void showProfile() {
        System.out.println("Admin ID: " + id + ", Name: " + name);
    }

    /**
     * Removes a doctor from the list by doctor ID.
     * If the doctor is not found or the list is empty, a message is shown.
     * 
     * @param doctors  The list of registered doctors.
     * @param doctorId The ID of the doctor to be removed.
     */
    public void removeDoctor(List<Doctor> doctors, String doctorId) {
        if (doctors.isEmpty()) {
            System.out.println("No doctor in the list");
            return;
        }

        Optional<Doctor> dc = doctors.stream()
                .filter(d -> d.getId().equals(doctorId))
                .findFirst();

        if (dc.isPresent()) {
            doctors.remove(dc.get());
            System.out.println("Doctor removed.");
        } else {
            System.out.println("Doctor ID not found.");
        }
    }
}
 