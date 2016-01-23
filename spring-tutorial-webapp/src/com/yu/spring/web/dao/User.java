package com.yu.spring.web.dao;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class User {

    @NotBlank(message="Username cannot be blank.")
    @Size(min=8, max=15, message="Username must be between 8 and 15 characters long.")
    @Pattern(regexp="^[a-zA-Z0-9]*$", message="Username can only consist of numbers, letters and underscore characters")
    private String username;
    
    @NotBlank(message="Password cannot be blank.")
    @Pattern(regexp="^\\S+$", message="Password should not contains space.")
    @Size(min=8, max=15, message="Password must be between 8 and 15 characters long.")
    private String password;
    
    @NotBlank(message="Email cannot be blank.")
    @Email(message="This does not appear to be a valid email.")
    private String email;
    
    private boolean enabled = false;
    
    private String authority;

    public User(String username, String password, boolean enabled, String authority) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authority = authority;
    }
    
    public User() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
    
    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password
                + ", email=" + email + ", enabled=" + enabled + ", authority="
                + authority + "]";
    }
}
