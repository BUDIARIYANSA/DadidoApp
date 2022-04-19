package com.example.dadidoapp.Model;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    private Integer id;

    @SerializedName("id_collection")
    private Integer idCollection;

    @SerializedName("file_name")
    private String fileName;

    @SerializedName("image_url")
    private String url;

    @SerializedName("description")
    private String description;

    @SerializedName("sell_status")
    private String sellStatus;

    @SerializedName("price")
    private Integer price;

    public Item(Integer idCollection, String fileName, String url, String description, String sellStatus, Integer price, Integer id) {
        this.id = id;
        this.idCollection = idCollection;
        this.fileName = fileName;
        this.url = url;
        this.description = description;
        this.sellStatus = sellStatus;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCollection() {
        return idCollection;
    }

    public void setIdCollection(Integer idCollection) {
        this.idCollection = idCollection;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(String sellStatus) {
        this.sellStatus = sellStatus;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
