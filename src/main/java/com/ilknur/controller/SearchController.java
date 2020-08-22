package com.ilknur.controller;

import com.ilknur.model.Event;
import com.ilknur.model.Organizer;
import com.ilknur.model.Registration;
import com.ilknur.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {
    private final static String KEYFILEPATH = "./keyFile.key";

    //my additions - search for an event
    //example rest call
    // curl "http://localhost:8080/getEvents?type=zoom&location=virtual"
    @CrossOrigin
    @SuppressWarnings("SqlResolve")
    @RequestMapping(value = "/getEvents", method = RequestMethod.GET)
    public List<Event> getEvents(@RequestParam(required = false) String type, @RequestParam(required = false) String location) {
        System.out.println("type parameter received: " + type);
        System.out.println("location parameter received: " + location);
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        //StringBuilder resultStr = new StringBuilder();
        List<Event> events = new ArrayList<Event>();

        String queryStr = "select * from events";
        if (type == null && location == null) {
            queryStr = queryStr + ";";
        } else if (type != null && location != null) {
            queryStr = queryStr + " where event_type = '" + type + "' and location = '" + location +"';";
        } else if (type != null && location == null) {
            queryStr = queryStr + " where event_type = '" + type + "';";
        } else if (type == null && location != null) {
            queryStr = queryStr + " where location = '" + location + "';";
        }
        System.out.println(queryStr);
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            Event event = new Event();
            event.setId(sqlRowSet.getInt("id"));
            event.setType(sqlRowSet.getString("event_type"));
            event.setName(sqlRowSet.getString("event_name"));
            event.setStartDateTime(sqlRowSet.getTimestamp("start_date_time").toLocalDateTime());
            event.setEndDateTime(sqlRowSet.getTimestamp("end_date_time").toLocalDateTime());
            event.setLocation(sqlRowSet.getString("location"));
            event.setOrganizerUserId(sqlRowSet.getInt("organizer_user_id"));
            event.setTicketAmount(sqlRowSet.getBigDecimal("ticket_amount"));
            event.setServiceFee(sqlRowSet.getBigDecimal("service_fee"));
            events.add(event);
        }
        return events;
    }
}
