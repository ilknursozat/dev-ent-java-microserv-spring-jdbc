package com.ilknur.model;

/**
 * id                 | int(11)      | NO   | PRI | NULL    | auto_increment |
 * | email              | varchar(255) | NO   |     | NULL    |                |
 * | first_name         | varchar(255) | NO   |     | NULL    |                |
 * | last_name          | varchar(255) | NO   |     | NULL    |                |
 * | city_of_residence  | varchar(25)  | YES  |     | NULL    |                |
 * | state_of_residence | varchar(25)  | YES  |     | NULL    |                |
 * | address            | varchar(255) | YES  |     | NULL    |                |
 * | zipcode            | varchar(10)  | YES  |     | NULL
 */
public class User {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String cityOfResidence;
    private String stateOfResidence;
    private String address;
    private String zipcode;

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String last_name) {
        this.lastName = last_name;
    }
    public String getCityOfResidence() {
        return this.cityOfResidence;
    }
    public void setCityOfResidence(String cityOfResidence) {
        this.cityOfResidence = cityOfResidence;
    }
    public String getStateOfResidence() {
        return this.stateOfResidence;
    }
    public void setStateOfResidence(String stateOfResidence) {
        this.stateOfResidence = stateOfResidence;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getZipcode() {
        return this.zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
