package com.cg.training.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
/**
 * The {@code Security} annotation is used to define role-based access control 
 * for a particular class. This can be used at the class level to specify which 
 * role is required to access or execute that class.
 * 
 * <p>This annotation is retained at runtime, meaning it can be accessed using 
 * reflection during the program's execution.</p>
 * 
 * @author Rajarshi Das
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Security {
	  /**
     * Specifies the role required to access the annotated class.
     *
     * @return the required user role (e.g., "Admin", "User")
     */
    String role();
}