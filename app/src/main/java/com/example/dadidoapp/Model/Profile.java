package com.example.dadidoapp.Model;

import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("id_user")
    private Integer idUser;

    @SerializedName("id_collection")
    private Integer idCollection;

    @SerializedName("id_wallet")
    private Integer idWallet;

    public Profile(Integer idUser, Integer idCollection, Integer idWallet) {
        this.idUser = idUser;
        this.idCollection = idCollection;
        this.idWallet = idWallet;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdCollection() {
        return idCollection;
    }

    public void setIdCollection(Integer idCollection) {
        this.idCollection = idCollection;
    }

    public Integer getIdWallet() {
        return idWallet;
    }

    public void setIdWallet(Integer idWallet) {
        this.idWallet = idWallet;
    }
}
