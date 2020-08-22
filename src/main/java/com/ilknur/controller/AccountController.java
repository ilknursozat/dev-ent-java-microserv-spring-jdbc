package com.ilknur.controller;

import com.ilknur.model.Organizer;
import com.ilknur.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    private final static String KEYFILEPATH = "./keyFile.key";

    //create a user
    //example rest call:
    // curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data "{\"email\": \"austin@gmail.com\", \"firstName\": \"Austin\", \"lastName\": \"Powers\", \"cityOfResidence\": \"Berkeley\", \"stateOfResidence\": \"CA\", \"address\": \"675 Main St.\", \"zipcode\": \"CA\"}" "http://localhost:8080/addUser"
    //
    //insert into user (email, first_name, last_name, city_of_residence, state_of_residence, address, zipcode)
    // values ('austin@gmail.url -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --datacom', 'Austin', 'Powers', 'Berkeley', 'CA', '875 Vine St.', '95703');
    @CrossOrigin
    @SuppressWarnings("SqlResolve")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestBody User user) {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        String queryStr = "insert into user (email, first_name, last_name, city_of_residence, " +
                "state_of_residence, address, zipcode) values (" +
                "'" + user.getEmail() + "'," +
                "'" + user.getFirstName() + "'," +
                "'" + user.getLastName() + "'," +
                "'" + user.getCityOfResidence() + "'," +
                "'" + user.getStateOfResidence() + "'," +
                "'" + user.getAddress() + "'," +
                "'" + user.getZipcode() + "'" +
                ");";
        System.out.println("SQL QUERY : " + queryStr);
        int rowsUpdated = jdbcTemplate.update(queryStr);
        return (rowsUpdated + " user added.");
    }

    //create an organizer user
    //example rest call:
    // curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data "{\"userId\": 123, \"driverLicenseNumber\": \"G12345897\", \"driverLicenseState\": \"CA\"}" "http://localhost:8080/addOrganizer"
    // insert into organizer (user_id, driver_license_number, driver_license_state) values (123, 'F12450878976', 'CA');
    @CrossOrigin
    @SuppressWarnings("SqlResolve")
    @RequestMapping(value = "/addOrganizer", method = RequestMethod.POST)
    public String addOrganizer(@RequestBody Organizer organizer) {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        String queryStr = "insert into organizer (user_id, driver_license_number, driver_license_state) values (" +
                "'" + organizer.getUserId() + "'," +
                "'" + organizer.getDriverLicenseNumber() + "'," +
                "'" + organizer.getDriverLicenseState() + "');";
        System.out.println("SQL QUERY : " + queryStr);
        int rowsUpdated = jdbcTemplate.update(queryStr);
        return (rowsUpdated + " organizer added.");
    }
}

