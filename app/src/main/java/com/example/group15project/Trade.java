package com.example.group15project;

public class Trade {

    private String tradeID;
    private String title;
    private String description;

    public Trade(){

    }
    public Trade(String tradeID, String title, String description){
        this.tradeID = tradeID;
        this.title = title;
        this.description = description;
    }

    public String getTradeID(){ return tradeID;}

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public void setTradeID(String tradeID){
        this.tradeID = tradeID;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
