package com.example.dadidoapp.LayoutModel;

public class Card_History_Model {

    private String event_history;
    private String owner;
    private String date_history;

    public Card_History_Model(String event_history, String owner, String date_history) {
        this.event_history = event_history;
        this.owner = owner;
        this.date_history = date_history;
    }

    public String getEvent_history() {
        return event_history;
    }

    public void setEvent_history(String event_history) {
        this.event_history = event_history;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDate_history() {
        return date_history;
    }

    public void setDate_history(String date_history) {
        this.date_history = date_history;
    }
}

