package com.example.dadidoapp.Model;

import com.google.gson.annotations.SerializedName;

public class ItemCollection {

    @SerializedName("collection_name")
    private String collectionName;

    @SerializedName("username")
    private String ownBy;

    public ItemCollection(String collectionName, String ownBy) {
        this.collectionName = collectionName;
        this.ownBy = ownBy;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getOwnBy() {
        return ownBy;
    }

    public void setOwnBy(String ownBy) {
        this.ownBy = ownBy;
    }
}
