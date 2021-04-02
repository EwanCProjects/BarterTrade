package com.example.group15project;

public class Trade {

    private String tradeID;
    private String title;
    private String description;
    private String provider;
    private String receiver;

    public Trade(){

    }
    public Trade(String tradeID, String title, String description, String provider, String receiver){
        this.tradeID = tradeID;
        this.title = title;
        this.description = description;
        this.provider = provider;
        this.receiver = receiver;
    }

    public String getTradeID(){ return tradeID;}

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getProvider(){ return provider;}

    public String getReceiver(){ return receiver;}

    public void setTradeID(String tradeID){
        this.tradeID = tradeID;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setProvider(String provider){ this.provider = provider;}

    public void setReceiver(String receiver){this.receiver = receiver;}
}
