package com.example.dadidoapp.Model;

import com.google.gson.annotations.SerializedName;

public class Collection {

    @SerializedName("collection_name")
    private String collectionName;

    @SerializedName("url")
    private String url;

    @SerializedName("description")
    private String description;

    @SerializedName("pathfile_server")
    private String pathFileServer;

    public Collection(String collectionName, String url, String description, String pathFileServer) {
        this.collectionName = collectionName;
        this.url = url;
        this.description = description;
        this.pathFileServer = pathFileServer;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
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

    public String getPathFileServer() {
        return pathFileServer;
    }

    public void setPathFileServer(String pathFileServer) {
        this.pathFileServer = pathFileServer;
    }
}
