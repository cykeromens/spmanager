package com.cykeromens.web.form;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by omens on 7/18/15.
 */
public abstract class UserCreateForm {

    private String title;
    @NotNull @Size(max = 30)
    private String firstName;
    @NotNull @Size(max = 30)
    private String lastName;
    @Email
    private String email;
    private String phoneNumber;
    @NotNull @Size(max = 25)
    private String username;
    @NotNull
    private String password;
    private String repeatPassword;
    @NotNull
    private String country;
    @NotNull
    private String zone;
    @NotNull
    private String state;
    @NotNull
    private String area;
    @NotNull
    private String city;
    @NotNull
    private String street;


    public UserCreateForm(){}

    public UserCreateForm(String title, String firstName, String lastName, String email, String phoneNumber, String username, String country, String zone, String state, String area, String city, String street) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.country = country;
        this.zone = zone;
        this.state = state;
        this.area = area;
        this.city = city;
        this.street = street;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
