package com.cg.training.exception;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cg.training.exceptions.InvalidAppointmentException;

/**
 * This class tests the functionality of the InvalidAppointmentException class.
 * It checks if the exception is created properly and if it throws as expected.
 * 
 * Author: Sreejit Sarkar
 */
public class InvalidAppointmentExceptionTest {

    /**
     * This test checks if the exception object is created correctly
     * and the message is stored properly.
     */
    @Test
    public void testExceptionMessage() {
        String message = "No available doctor found.";
        InvalidAppointmentException exception = new InvalidAppointmentException(message);

        assertNotNull(exception); // Make sure the object is not null
        assertEquals(message, exception.getMessage()); // Check if the message matches
    }

    /**
     * This test checks if the InvalidAppointmentException is thrown as expected.
     * The test will pass if the exception is thrown.
     */
    @Test(expected = InvalidAppointmentException.class)
    public void testExceptionThrown() {
        throw new InvalidAppointmentException("This is a test exception");
    }

}
 