package com.example.backend1.Response;

public class DeleteResponse {
    String status;
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DeleteResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
