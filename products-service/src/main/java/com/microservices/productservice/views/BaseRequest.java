package com.microservices.productservice.views;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

public class BaseRequest {

    private String userID;
    private Date lastUpdatedTime;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
