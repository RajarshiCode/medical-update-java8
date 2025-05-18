package com.cg.training.models;

import java.util.Optional;

public abstract class User {
    protected String id;
    protected String name;
    
    public String getName() {
		return name;
	}

    public User(String id, String name) {
        this.id = id;
        this.name = validateName(Optional.ofNullable(name).map(String::trim).orElse(null));
    }
    private String validateName(String name) {
        return Optional.ofNullable(name)
                .filter(n -> n.matches("^[A-Za-z]+(\\s[A-Za-z]+)*$")) // Regex to allow only alphabets and single spaces
                .orElseThrow(() -> new IllegalArgumentException("Name must contain only alphabets and single spaces between words."));
    }

    public String getId() {
        return id;
    }

    public abstract void showProfile();
}


