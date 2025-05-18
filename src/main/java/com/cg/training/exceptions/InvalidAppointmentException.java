package com.cg.training.exceptions;

/**
 * This class represents a custom exception for handling invalid appointment-related scenarios.
 * It is used to throw errors when an appointment cannot be booked or completed due to certain conditions.
 * 
 * 
 * @author Sanjona Bhattacharjee and Rishiraj Ray
 */

public class InvalidAppointmentException extends RuntimeException {
	/**
     * Constructor to create an InvalidAppointmentException with a specific message.
     * 
     * @param message the detail message explaining the exception.
     */
    public InvalidAppointmentException(String message) {
        super(message);
    }
    
}