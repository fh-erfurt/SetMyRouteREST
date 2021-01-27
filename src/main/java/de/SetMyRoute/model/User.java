package de.SetMyRoute.model;


import de.SetMyRoute.model.difficulty.Difficulty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class represents a user, a climber.
 */
@Entity
public class User extends DatabaseEntity {

    /**
     * the First name of the person
     */
    @NotNull
    @Size(min = 3, max = 128)
    private String firstName;

    /**
     * the Surname of the person
     */
    @NotNull
    @Size(min = 3, max = 128)
    private String lastName;

    /**
     * the email address of the user. Later being used for authentication.
     */
    @NotNull
    @Column(unique = true)
    @Size(min = 3, max = 256)
    private String email;

    /**
     * the onSightDegree represents a number in range from 3- to 15+.
     * It describes the maximum difficulty that a user can certainly climb
     */
    @NotNull
    @OneToOne
    private Difficulty onSightDegree;

    @NotNull
    @Column(unique = true)
    private String authToken;

    @NotNull
    private String passwordHash;

    @NotNull
    private boolean admin;

    public User() {
    }

    public User(String firstName, String lastName, String email, Difficulty onSightDegree, String authToken, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.onSightDegree = onSightDegree;
        this.authToken = authToken;
        this.passwordHash = passwordHash;
        this.admin = false;
    }

    public static String hashPassword(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedPassword;
    }

    public void changePassword(String newPassword) {
        this.passwordHash = hashPassword(newPassword);
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Difficulty getOnSightDegree() {
        return onSightDegree;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setOnSightDegree(Difficulty onSightDegree) {
        this.onSightDegree = onSightDegree;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
