/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.semanticapp.model;

/**
 *
 * @author andre
 */
public class User {

    private String id;
    private String role;
    private String username;
    private String password;
    private String mail;
    private String firstName;
    private String lastName;

    public User(String role, String username, String password, String mail, String firstName, String lastName) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "<user><id>" + id
                + "</id><username>" + username
                + "</username><role>" + role
                + "</role><mail>" + mail
                + "</mail><password>" + password
                + "</password><first-name>" + firstName
                + "</first-name><last-name>" + lastName
                + "</last-name></user>";
    }

}