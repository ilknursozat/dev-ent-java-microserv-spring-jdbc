package com.ilknur.model;

/**
 *  id                   | int(11)     | NO   | PRI | NULL    | auto_increment |
 * | user_id              | int(11)     | NO   |     | NULL    |                |
 * | driver_license       | varchar(25) | NO   |     | NULL    |                |
 * | driver_license_state | varchar(25) | NO   |     | NULL
 */
public class Organizer {
    private Integer id;
    private Integer userId;
    private String driverLicenseNumber;
    private String driverLicenseState;

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return this.userId;
    }
    public void setUserId(Integer user_id) {
        this.userId = user_id;
    }
    public String getDriverLicenseNumber() {
        return this.driverLicenseNumber;
    }
    public void setDriverLicenseNumber(String driver_license_number) {
        this.driverLicenseNumber = driver_license_number;
    }

    public String getDriverLicenseState() {
        return this.driverLicenseState;
    }
    public void setDriverLicenseState(String driver_license_state) {
        this.driverLicenseState = driver_license_state;
    }
}
