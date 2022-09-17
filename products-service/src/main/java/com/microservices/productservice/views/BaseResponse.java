package com.microservices.productservice.views;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class BaseResponse {
    
    private String Message;
    
    private int code;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
