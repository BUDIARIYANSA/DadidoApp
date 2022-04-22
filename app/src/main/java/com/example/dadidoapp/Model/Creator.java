package com.example.dadidoapp.Model;

import com.google.gson.annotations.SerializedName;

public class Creator {

    @SerializedName("collection_name")
    String collectionName;

    @SerializedName("image_banner_url")
    String imageBanner;

    @SerializedName("description")
    String description;

    @SerializedName("username")
    String username;

    @SerializedName("profile_url")
    String profileURL;

    public Creator(String collectionName, String imageBanner, String description, String username, String profileURL) {
        this.collectionName = collectionName;
        this.imageBanner = imageBanner;
        this.description = description;
        this.username = username;
        this.profileURL = profileURL;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getImageBanner() {
        return imageBanner;
    }

    public void setImageBanner(String imageBanner) {
        this.imageBanner = imageBanner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }
}
