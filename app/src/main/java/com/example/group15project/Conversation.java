package com.example.group15project;

public class Conversation {
    private String conversationName;
    private String user;
    private String oppositeUser;
    private Conversation oppositeConversation;

    public Conversation(String conversationName, String provider, String receiver) {
        this.conversationName = conversationName;
        this.user = provider;
        this.oppositeUser = receiver;
    }

    public Conversation getOppositeConversation() { return oppositeConversation; }

    public String getConversationName() { return conversationName; }

    public String getUser() { return user; }

    public String getOppositeUser() { return oppositeUser; }

    public void setOppositeConversation(Conversation conversation) {
        this.oppositeConversation = conversation;
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
