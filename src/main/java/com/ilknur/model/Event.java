package com.ilknur.model;

import java.math.BigDecimal;
//import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Event {
    private Integer id;
    private String type;
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String location;
    private Integer organizerUserId;
    private BigDecimal ticketAmount;
    private BigDecimal serviceFee;

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {this.name = name; }
    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }
    public void setStartDateTime(LocalDateTime start_date_time) {
        this.startDateTime = start_date_time;
    }
    public LocalDateTime getEndDateTime() {
        return this.endDateTime;
    }
    public void setEndDateTime(LocalDateTime end_date_time) {
        this.endDateTime = end_date_time;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Integer getOrganizerUserId() {
        return this.organizerUserId;
    }
    public void setOrganizerUserId(Integer organizer_user_id) {
        this.organizerUserId = organizer_user_id;
    }
    public BigDecimal getTicketAmount() {
        return this.ticketAmount;
    }
    public void setTicketAmount(BigDecimal ticket_amount) {
        this.ticketAmount = ticket_amount;
    }
    public BigDecimal getServiceFee() {
        return this.serviceFee;
    }
    public void setServiceFee(BigDecimal service_fee) {
        this.serviceFee = service_fee;
    }
}
