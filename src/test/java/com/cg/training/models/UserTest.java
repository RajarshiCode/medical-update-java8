package com.cg.training.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the User abstract class using a concrete inner class called TestUser.
 * It checks name validation, trimming, and profile display functionality.
 * 
 * @author Sampritee Dey
 */
public class UserTest {

    private User user;

    /**
     * A simple subclass of User for testing purposes.
     */
    private class TestUser extends User {
        public TestUser(String id, String name) {
            super(id, name);
        }

        @Override
        public void showProfile() {
            System.out.println("User ID: " + id + ", Name: " + name);
        }
    }

    /**
     * Initializes a User object before each test.
     */
    @Before
    public void setUp() {
        user = new TestUser("U1001", "Lakshmi Devi");
    }

    /**
     * Tests if the user is initialized with correct ID and name.
     */
    @Test
    public void testUserInitialization() {
        assertEquals("U1001", user.getId());
        assertEquals("Lakshmi Devi", user.getName());
    }

    /**
     * Tests if the showProfile() method runs without any error.
     */
    @Test
    public void testShowProfile() {
        user.showProfile();
    }

    /**
     * Tests that a null name throws an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullNameThrowsException() {
        new TestUser("U1002", null);
    }

    /**
     * Tests that an empty string name throws an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyStringThrowsException() {
        new TestUser("U1003", "");
    }

    /**
     * Tests that a name with only spaces throws an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testOnlySpacesThrowsException() {
        new TestUser("U1004", "     ");
    }

    /**
     * Tests that names with special characters are rejected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSpecialCharactersThrowsException() {
        new TestUser("U1005", "R@hul123");
    }

    /**
     * Tests that names with double spaces in the middle are rejected.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDoubleMiddleSpacesThrowsException() {
        new TestUser("U1006", "Ram  Kumar");
    }

    /**
     * Tests that a single-word name is valid.
     */
    @Test
    public void testSingleWordNameValid() {
        User u = new TestUser("U1007", "Meena");
        assertEquals("Meena", u.getName());
    }

    /**
     * Tests that a valid name with leading and trailing spaces is trimmed correctly.
     */
    @Test
    public void testTrimmedValidName() {
        User u = new TestUser("U1008", "   Sita Ram   ");
        assertEquals("Sita Ram", u.getName());
    }

    /**
     * Tests that a two-word name with a single space is accepted.
     */
    @Test
    public void testTwoWordValidName() {
        User u = new TestUser("U1009", "Gita Kumari");
        assertEquals("Gita Kumari", u.getName());
    }

    /**
     * Tests that a name with tab character throws an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNameWithTabThrowsException() {
        new TestUser("U1010", "Shyam\tKumar");
    }

}
