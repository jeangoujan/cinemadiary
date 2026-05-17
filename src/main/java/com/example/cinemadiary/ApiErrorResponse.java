package com.example.cinemadiary;

import java.util.List;

public class ApiErrorResponse {
    private Integer status;
    private String error;
    private List<String> messages;

    public ApiErrorResponse(Integer status, String error, List<String> messages){
        this.status = status;
        this.error = error;
        this.messages = messages;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public List<String> getMessages() {
        return messages;
    }
    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    
}
