package de.fuseki.coursemangement.pojos;

import java.util.Objects;

public class Address {
    private String street;
    private String houseNumber;
    private String city;
    private String postalCode;

    public Address(String street, String houseNumber, String city, String postalCode) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Address() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(houseNumber, address.houseNumber) && Objects.equals(city, address.city) && Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, houseNumber, city, postalCode);
    }


    @Override
    public String toString() {
        return "street: " + street  +
                ", houseNumber: " + houseNumber  +
                ", city: " + city  +
                ", postalCode: " + postalCode;
    }

    /**
     * Getter for the Street.
     *
     * @return the street in String.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Setter for the Street.
     *
     * @param street sets the Street.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Getter for HouseNumber.
     *
     * @return the house Number in String.
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * Setter for the houseNumber.
     *
     * @param houseNumber sets the houseNumber in String.
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * Getter for the City.
     *
     * @return the City in String.
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter for the City.
     *
     * @param city sets the city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter for the PostalCode.
     *
     * @return the PostalCode in String.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Setter for the postalCode.
     *
     * @param postalCode sets the postalCode in string.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
