package com.example.group15project;

public class Message {
    private String messageID;
    private String message;
    private String user;

    public Message(String messageID, String message, String user){
        this.messageID = messageID;
        this.message = message;
        this.user = user;
    }

    public String getMessageID() { return messageID; }

    public String getMessage() { return message; }

    public String getUser() { return user; }

    public void setMessageID(String messageID) { this.messageID = messageID; }

    public void setMessage(String message) { this.message = message; }

    public void setUser(String user) { this.user = user; }
}
