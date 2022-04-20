package com.example.dadidoapp.LayoutModel;

import android.icu.text.CaseMap;

public class Card_Favorite_Model {

    private String image_title;
    private String creator_name;
    private String image_url;

    public Card_Favorite_Model(String image_title, String creator_name, String image_url) {
        this.image_title = image_title;
        this.creator_name = creator_name;
        this.image_url = image_url;
    }

    public String getImage_title() {
        return image_title;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_title(String image_title) {
        this.image_title = image_title;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
