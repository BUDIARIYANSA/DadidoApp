package com.example.dadidoapp.Model;

import com.google.gson.annotations.SerializedName;

public class ItemCollection {

    @SerializedName("collection_name")
    private String collectionName;

    @SerializedName("username")
    private String ownBy;

    @SerializedName("status_favorit")
    private Integer statusFav;

    public ItemCollection(String collectionName, String ownBy, Integer statusFav) {
        this.collectionName = collectionName;
        this.ownBy = ownBy;
        this.statusFav = statusFav;
    }

    public Integer getStatusFav() {
        return statusFav;
    }

    public void setStatusFav(Integer statusFav) {
        this.statusFav = statusFav;
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
