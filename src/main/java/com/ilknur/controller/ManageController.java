package com.ilknur.controller;

import com.ilknur.model.Event;
import com.ilknur.model.Registration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class ManageController {
    private final static String KEYFILEPATH = "./keyFile.key";

    //create an event
    //example rest call:
    // curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data "{\"type\": \"party\", \"name\": \
    //"End of Summer Party\", \"startDateTime\": \"2020-08-18T18:30:00\", \"endDateTime\": \"2020-08-18T23:30:00\", \"location\": \"Civic Center\", \"organizerUserId\": 123, \"ticketAmount\"
    //: 25.00, \"serviceFee\": 1.25}" "http://localhost:8080/addEvent"
    //
    //insert into events (event_type, event_name, start_date_time, end_date_time,
    //location, organizer_user_id, ticket_amount, service_fee)
    // values ('zoom', 'spring info session', '2020-08-18 18:30:00',
    // '2020-08-18 21:30:00', 'virtual', 123, 50.00, 00.50);
    @CrossOrigin
    @SuppressWarnings("SqlResolve")
    @RequestMapping(value = "/addEvent", method = RequestMethod.POST)
    public String addEvent(@RequestBody Event event) {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        String queryStr = "insert into events (event_type, event_name, start_date_time, " +
                "end_date_time, location, organizer_user_id, ticket_amount, service_fee) values (" +
                "'" + event.getType() + "'," +
                "'" + event.getName() + "'," +
                "'" + event.getStartDateTime() + "'," +
                "'" + event.getEndDateTime() + "'," +
                "'" + event.getLocation() + "'," +
                event.getOrganizerUserId() + "," +
                event.getTicketAmount() + "," +
                event.getServiceFee() +
                ");";
        System.out.println("SQL QUERY : " + queryStr);
        int rowsUpdated = jdbcTemplate.update(queryStr);
        return (rowsUpdated + " event added.");
    }

    //register to an event
    //example rest call:
    // curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data "{\"eventId\": 123, \"userId\": 123,  \"paymentMethod\": \"Visa\"}" "http://localhost:8080/registerEvent"
    // insert into registration (event_id, user_id, payment_method) values (123, 123, 'Visa');
    @CrossOrigin
    @SuppressWarnings("SqlResolve")
    @RequestMapping(value = "/registerEvent", method = RequestMethod.POST)
    public String registerEvent(@RequestBody Registration registration) {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        String queryStr = "insert into registration (event_id, user_id, payment_method) values (" +
                registration.getEventId() + ", " +
                registration.getUserId() + ", '" +
                registration.getPaymentMethod()  + "');";
        System.out.println("SQL QUERY : " + queryStr);
        int rowsUpdated = jdbcTemplate.update(queryStr);
        return (rowsUpdated + " registration added.");
    }

    //delete a registration
    //example rest call:
    // curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST -d "id=123" "http://localhost:8080/deleteRegistration"
    // update registration set is_deleted = 1 where id = 3;
    @CrossOrigin
    @SuppressWarnings("SqlResolve")
    @RequestMapping(value = "/deleteRegistration", method = RequestMethod.POST)
    public String deleteRegistration(@RequestParam Integer id) {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        String queryStr = "update registration set is_deleted = 1 where id = " + id;
        System.out.println("SQL QUERY : " + queryStr);
        int rowsUpdated = jdbcTemplate.update(queryStr);
        return (rowsUpdated + " registration deleted.");
    }
}