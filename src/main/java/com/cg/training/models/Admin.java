package com.cg.training.models;

import java.util.List;
import java.util.Optional;

import com.cg.training.annotations.Security;

@Security(role = "Admin")
public class Admin extends User {
    public Admin(String id, String name) {
        super(id, name);
    }
    
    

    @Override
    public void showProfile() {
        System.out.println("Admin ID: " + id + ", Name: " + name);
    }

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