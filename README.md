# Event App Final Project

This is a sample project developed as part of UC Santa Cruz Extension Developing Enterprise Java Microservices with Spring Boot and Spring Cloud course. The application uses Spring Boot and Spring Framework and is deployed to a jetty server running on an Amazon AWS EC2 instance. The application accesses to mysql database server running on the same AWS EC2 instance.  

## Project Description

The application implements an event registration system where users can search for events, create a user account, create an organizer account, create an event, register to an event and delete an existing registration. The functionality is achieved via REST API calls. 

## Built With

* [IntelliJ](https://www.jetbrains.com/idea/) - IDE used for development
* [Maven](https://maven.apache.org/) - Dependency Management
* [Jetty Server](https://www.eclipse.org/jetty/) - Web server and Servlet Container, distribution version 9.4.30
* [MySQL](https://www.mysql.com/) - Database Server version: 8.0.17 Source distribution
* [Spring Framework](https://spring.io/projects/spring-framework) - Used for infrastructure support
* [Spring Boot](https://github.com/spring-projects/spring-boot/wiki) - Used to create microservices
* [Java 14.0.1](https://openjdk.java.net/projects/jdk/14/)
* [Codebase](https://github.com/hinkmond/dev-ent-java-microserv-spring-jdbc.git) - Used as a template
* [Amazon AWS EC2 Instance](https://aws.amazon.com/) - Red Hat 8.3.1-5 VM hosting Jetty and MySQL Servers 

### Prerequisites

curl, postman or a frontend application to make REST API calls.

## List of MicroServices

- SearchController – getEvents microservice
- AccountController – addUser and addOrganizer microservices
- ManageController – addEvent, registerEvent and deleteRegistration microservices

## REST API Calls to MicroServices

#### getEvents 
Returns a list of events based on type and location in json format. If no type or location parameters are provided, all events are returned.

##### _Sample call:_
curl "http://ec2-34-211-148-82.us-west-2.compute.amazonaws.com:8080/spring-jdbc/getEvents?type=zoom&location=virtual"

#### addUser
Creates a new user. 

##### _Sample call:_
curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data "{\"email\": \"ususpect@gmail.com\", \"firstName\": \"Usual\", \"lastName\": \"Suspect\", \"cityOfResidence\": \"Berkeley\", \"stateOfResidence\": \"CA\", \"address\": \"675 Main St.\", \"zipcode\": \"94705\"}" "http://ec2-34-211-148-82.us-west-2.compute.amazonaws.com:8080/spring-jdbc/addUser"

#### addOrganizer
Creates a new organizer user. An organizer has the right to create a new event.

##### _Sample call:_
curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data "{\"userId\": 123, \"driverLicenseNumber\": \"G12345897\", \"driverLicenseState\": \"CA\"}" "http://ec2-34-211-148-82.us-west-2.compute.amazonaws.com:8080/spring-jdbc/addOrganizer"

#### addEvent
Creates a new event.

##### _Sample call:_
curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data "{\"type\": \"party\", \"name\": \
"Dance Party\", \"startDateTime\": \"2020-08-22T18:30:00\", \"endDateTime\": \"2020-08-22T23:30:00\", \"location\": \"SF Temple\", \"organizerUserId\": 123, \"ticketAmount\": 25.00, \"
serviceFee\": 1.25}" "http://ec2-34-211-148-82.us-west-2.compute.amazonaws.com:8080/spring-jdbc/addEvent"

#### registerEvent 
User registers to an event

##### _Sample call:_
curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST --data "{\"eventId\": 125, \"userId\": 225,  \"paymentMethod\": \"PayPal\"}" "http://ec2-34-211-148-82.us-west-2.compute.amazonaws.com:8080/spring-jdbc/registerEvent"

#### deleteRegistration  
Deletes an event

##### _Sample call:_
curl -i -H "Accept: application/json" -H "Content-Type:application/x-www-form-urlencoded" -X POST -d "id=3" "http://ec2-34-211-148-82.us-west-2.compute.amazonaws.com:8080/spring-jdbc/deleteRegistration"
