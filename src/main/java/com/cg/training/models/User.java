package com.cg.training.models;

import java.util.Optional;

/**
 * This is an abstract class that represents a user in the system.
 * It contains common properties and methods shared by all users like doctors and patients.
 * 
 * @author Rajarshi Das
 */
public abstract class User {
    
    /** Unique ID of the user (e.g., patient or doctor) */
    protected String id;
    
    /** Name of the user */
    protected String name;

    /**
     * Constructor to create a new User object with ID and name.
     * It validates the name to make sure it contains only alphabets and single spaces.
     * 
     * @param id   The unique ID of the user.
     * @param name The name of the user.
     * @throws IllegalArgumentException if the name is invalid.
     */
    public User(String id, String name) {
        this.id = id;
        this.name = validateName(Optional.ofNullable(name).map(String::trim).orElse(null));
    }

    /**
     * Returns the ID of the user.
     * 
     * @return User's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the user.
     * 
     * @return User's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Validates that the name contains only alphabets and single spaces.
     * 
     * @param name The name to validate.
     * @return The validated name.
     * @throws IllegalArgumentException if the name does not meet the requirements.
     */
    private String validateName(String name) {
        return Optional.ofNullable(name)
                .filter(n -> n.matches("^[A-Za-z]+(\\s[A-Za-z]+)*$"))
                .orElseThrow(() -> new IllegalArgumentException("Name must contain only alphabets and single spaces between words."));
    }

    /**
     * Abstract method to show the user's profile.
     * This must be implemented by subclasses (like Patient and Doctor).
     */
    public abstract void showProfile();
}
 