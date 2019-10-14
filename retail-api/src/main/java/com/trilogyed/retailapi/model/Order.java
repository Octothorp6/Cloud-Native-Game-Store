package com.trilogyed.retailapi.model;

import javax.validation.constraints.*;
import java.util.Objects;


public class Order {
    private int customerId;
    @NotNull(message = "Must enter a value for first name.")
    private String firstName;
    @NotNull(message = "Must enter a value for last name.")
    private String lastName;
    @NotNull(message = "Must enter a value for street.")
    private String street;
    @NotNull(message = "Must enter a value for city.")
    private String city;
    @NotNull(message = "Must enter a value for zip.")
    private String zip;
    @NotNull(message = "Must enter a value for email.")
    @Email(message = "Invalid email format.")
    private String email;
    @NotNull(message = "Must enter a value for phone.")
    @Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}", message = "Incorrect phone number format.")
    private String phone;
    @NotNull(message = "Must enter a value for productName.")
    private String productName;
    @NotNull(message = "Must enter a value for quantity.")
    @Min(value = 0, message = "The value must be a positive number.")
    private int quantity;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return customerId == order.customerId &&
                quantity == order.quantity &&
                Objects.equals(firstName, order.firstName) &&
                Objects.equals(lastName, order.lastName) &&
                Objects.equals(street, order.street) &&
                Objects.equals(city, order.city) &&
                Objects.equals(zip, order.zip) &&
                Objects.equals(email, order.email) &&
                Objects.equals(phone, order.phone) &&
                Objects.equals(productName, order.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, street, city, zip, email, phone, productName, quantity);
    }
}
