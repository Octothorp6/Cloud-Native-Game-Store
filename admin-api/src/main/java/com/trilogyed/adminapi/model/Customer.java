package com.trilogyed.adminapi.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Customer {

    @NotNull
    private int customerId;

    @NotNull
    @Size(max = 50, message = "First Name field cannot exceed 50 characters")
    private String firstName;

    @NotNull
    @Size(max = 50, message = "Last Name field cannot exceed 50 characters")
    private String lastName;

    @NotNull
    @Size(max = 50, message = "Street field cannot exceed 50 characters")
    private String street;

    @NotNull
    @Size(max = 50, message = "City field cannot exceed 50 characters")
    private String city;

    @NotNull
    @Size(min =5, max = 10, message = "Zip field cannot exceed 10 characters or be less than 5")
    private String zip;

    @NotNull
    @Email
    @Size(max = 75, message = "Email field cannot exceet 75 characters")
    private String email;

    @NotNull
    @Size(max = 20, message = "Phone field cannot exceed 20 characters")
    private String phone;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(street, customer.street) &&
                Objects.equals(city, customer.city) &&
                Objects.equals(zip, customer.zip) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(phone, customer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, street, city, zip, email, phone);
    }
}
