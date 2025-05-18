package com.cg.training.exception;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cg.training.exceptions.InvalidAppointmentException;

public class InvalidAppointmentExceptionTest {

	@Test
    public void testExceptionMessage() {
        String message = "No available doctor found.";
        InvalidAppointmentException exception = new InvalidAppointmentException(message);

        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

    @Test(expected = InvalidAppointmentException.class)
    public void testExceptionThrown() {
        throw new InvalidAppointmentException("This is a test exception");
    }

}
