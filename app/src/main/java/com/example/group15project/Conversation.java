package com.example.group15project;

public class Conversation {
    private String user;
    private String oppositeUser;
    private String conversationName;
    private String oppositeConversation;

    public Conversation(String user, String oppositeUser) {
        this.user = user;
        this.oppositeUser = oppositeUser;
        conversationName = user+"_"+oppositeUser;
        oppositeConversation = oppositeUser+"_"+user;
    }

    public Conversation() {}

    public String getOppositeConversation() { return oppositeConversation; }

    public String getConversationName() { return conversationName; }

    public String getUser() { return user; }

    public String getOppositeUser() { return oppositeUser; }

    public void setOppositeConversation(String oppositeConversationName) {
        this.oppositeConversation = oppositeConversationName;
    }

    public void setConversationName(String conversationName) {
        this.conversationName = conversationName;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setOppositeUser(String oppositeUser) {
        this.oppositeUser = oppositeUser;
    }
}
