package com.in28minutes.rest.webservices.restfulwebservices.model;

import java.time.LocalDateTime;

public record ErrorDetails(String message, LocalDateTime timeStamp, String details) {


}
