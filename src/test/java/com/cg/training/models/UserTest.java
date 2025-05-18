package com.cg.training.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private User user;

    private class TestUser extends User {
        public TestUser(String id, String name) {
            super(id, name);
        }

        @Override
        public void showProfile() {
            System.out.println("User ID: " + id + ", Name: " + name);
        }
    }

    @Before
    public void setUp() {
        user = new TestUser("U1001", "Lakshmi Devi");
    }

    @Test
    public void testUserInitialization() {
        assertEquals("U1001", user.getId());
        assertEquals("Lakshmi Devi", user.getName());
    }

    @Test
    public void testShowProfile() {
        user.showProfile();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullNameThrowsException() {
        new TestUser("U1002", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyStringThrowsException() {
        new TestUser("U1003", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOnlySpacesThrowsException() {
        new TestUser("U1004", "     ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSpecialCharactersThrowsException() {
        new TestUser("U1005", "R@hul123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoubleMiddleSpacesThrowsException() {
        new TestUser("U1006", "Ram  Kumar");
    }

    @Test
    public void testSingleWordNameValid() {
        User u = new TestUser("U1007", "Meena");
        assertEquals("Meena", u.getName());
    }

    @Test
    public void testTrimmedValidName() {
        User u = new TestUser("U1008", "   Sita Ram   ");
        assertEquals("Sita Ram", u.getName());
    }

    @Test
    public void testTwoWordValidName() {
        User u = new TestUser("U1009", "Gita Kumari");
        assertEquals("Gita Kumari", u.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameWithTabThrowsException() {
        new TestUser("U1010", "Shyam\tKumar");
    }

}
