package com.luv2code.springboot.cruddemo.ExceptionHandling;

public class ErrorResponse {
    
    private String message;
    private int statusCode;
    private long timestamp;

    public ErrorResponse(String message, int statusCode, long timestamp) {
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", statusCode=" + statusCode +
                ", timestamp=" + timestamp +
                '}';
    }
}
