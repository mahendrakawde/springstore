package com.sun.j2ee.blueprints.consumerwebsite;

public class Address {
  private String streetName1;
  private String streetName2;
  private String city;
  private String state;
  private String zipCode;
  private String country;

  public Address() {}

  public Address(String streetName1, String streetName2, String city,
                 String state, String zipCode, String country) {
    this.streetName1 = streetName1;
    this.streetName2 = streetName2;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.country = country;
    return;
  }

  // getter methods

  public String getStreetName1() {
    return streetName1;
  }

  public String getStreetName2() {
    return streetName2;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getCountry() {
    return country;
  }

  public String getZipCode() {
    return zipCode;
  }

  // setter methods

  public void setStreetName1(String streetName) {
    this.streetName1 = streetName;
    return;
  }

  public void setStreetName2(String streetName) {
    this.streetName2 = streetName;
    return;
  }

  public void setCity(String city) {
    this.city = city;
    return;
  }

  public void setState(String state) {
    this.state = state;
    return;
  }

  public void setCountry(String country) {
    this.country = country;
    return;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
    return;
  }

  public String toString() {
    return "Address[streeName1=" + streetName1 + ", "
      + "streetName2=" + streetName2 + ", "
      + "city=" + city + ", "
      + "state=" + state + ", "
      + "zipCode=" + zipCode + ", "
      + "country=" + country + "]";
  }
}


