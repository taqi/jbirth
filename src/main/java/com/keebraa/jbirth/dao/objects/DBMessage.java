package com.keebraa.jbirth.dao.objects;

public class DBMessage {
    private String message;
    private String fromUserId;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getFromUserId() {
        return fromUserId;
    }
    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }
}
