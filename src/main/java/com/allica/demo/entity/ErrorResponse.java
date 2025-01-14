package com.allica.demo.entity;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime localDateTime;
    private String message;
    private String details;

    public ErrorResponse(LocalDateTime localDateTime,String message,String details){
        this.localDateTime=localDateTime;
        this.message=message;
        this.details=details;
    }
}
