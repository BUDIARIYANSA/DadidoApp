package com.example.dadidoapp.Model;

import com.google.gson.annotations.SerializedName;

public class Favorit {

    @SerializedName("id_collection")
    private Integer idCollection;

    @SerializedName("id_item")
    private Integer idItem;

    @SerializedName("id_profile")
    private Integer idProfile;

    @SerializedName("favorit_status")
    private Integer favoritStatus;

    public Favorit(Integer idCollection, Integer idItem, Integer idProfile, Integer favoritStatus) {
        this.idCollection = idCollection;
        this.idItem = idItem;
        this.idProfile = idProfile;
        this.favoritStatus = favoritStatus;
    }

    public Integer getIdCollection() {
        return idCollection;
    }

    public void setIdCollection(Integer idCollection) {
        this.idCollection = idCollection;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Integer idProfile) {
        this.idProfile = idProfile;
    }

    public Integer getFavoritStatus() {
        return favoritStatus;
    }

    public void setFavoritStatus(Integer favoritStatus) {
        this.favoritStatus = favoritStatus;
    }
}
