package com.example.dadidoapp.Model;

import com.google.gson.annotations.SerializedName;

public class ItemCollection {

    @SerializedName("collection_name")
    private String collectionName;

    @SerializedName("owner")
    private String ownBy;

    @SerializedName("last_activity")
    private String last_activity;

    @SerializedName("event")
    private String event;

    public ItemCollection(String collectionName, String ownBy, String last_activity, String event) {
        this.collectionName = collectionName;
        this.ownBy = ownBy;
        this.last_activity = last_activity;
        this.event = event;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getOwnBy() {
        return ownBy;
    }

    public String getLast_activity() {
        return last_activity;
    }

    public String getEvent() {
        return event;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setOwnBy(String ownBy) {
        this.ownBy = ownBy;
    }

    public void setLast_activity(String last_activity) {
        this.last_activity = last_activity;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
