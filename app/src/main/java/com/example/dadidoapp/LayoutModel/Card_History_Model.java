package com.example.dadidoapp.LayoutModel;

public class Card_History_Model {

    private String event_history;
    private String price_history;
    private String bayar_from;
    private String seller_history;
    private String date_history;

    public Card_History_Model(String event_history, String price_history, String bayar_from, String seller_history, String date_history) {
        this.event_history = event_history;
        this.price_history = price_history;
        this.bayar_from = bayar_from;
        this.seller_history = seller_history;
        this.date_history = date_history;
    }

    public String getEvent_history() {
        return event_history;
    }

    public String getPrice_history() {
        return price_history;
    }

    public String getBayar_from() {
        return bayar_from;
    }

    public String getSeller_history() {
        return seller_history;
    }

    public String getDate_history() {
        return date_history;
    }

    public void setEvent_history(String event_history) {
        this.event_history = event_history;
    }

    public void setPrice_history(String price_history) {
        this.price_history = price_history;
    }

    public void setBayar_from(String bayar_from) {
        this.bayar_from = bayar_from;
    }

    public void setSeller_history(String seller_history) {
        this.seller_history = seller_history;
    }

    public void setDate_history(String date_history) {
        this.date_history = date_history;
    }
}

