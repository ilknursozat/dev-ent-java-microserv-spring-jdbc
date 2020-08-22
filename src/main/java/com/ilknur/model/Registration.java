package com.ilknur.model;

/**
 *  id             | int(11)     | NO   | PRI | NULL    | auto_increment |
 * | user_id        | int(11)     | NO   |     | NULL    |                |
 * | payment_method | varchar(50) | NO   |     | NULL
 */
public class Registration {
    private Integer ticketId;
    private Integer eventId;
    private Integer userId;
    private String paymentMethod;
    private Integer isDeleted;

    public Integer getTicketId() {
        return this.ticketId;
    }
    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }
    public Integer getEventId() {
        return this.eventId;
    }
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
    public Integer getUserId() {
        return this.userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getPaymentMethod() {
        return this.paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public Integer getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
