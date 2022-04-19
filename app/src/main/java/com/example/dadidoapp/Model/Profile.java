package com.example.dadidoapp.Model;

import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("username")
    private String username;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("home_address")
    private String homeAddress;

    @SerializedName("profile_url")
    private String profileURL;

    public Profile(String username, String fullname, String email, String password, String homeAddress, String profileURL) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.homeAddress = homeAddress;
        this.profileURL = profileURL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }
}
