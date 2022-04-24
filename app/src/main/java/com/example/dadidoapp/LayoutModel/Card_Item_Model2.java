package com.example.dadidoapp.LayoutModel;

import android.media.Image;

public class Card_Item_Model2 {

    private String judul_item;
    private String Top_number;
    private String creator_name;
    private String item_price;
    private String ImageURL;
    private String tokenId;

    public Card_Item_Model2(String tokenId, String judul_item, String Top_number, String creator_name, String item_price, String ImageURL){
        this.judul_item = judul_item;
        this.Top_number = Top_number;
        this.creator_name = creator_name;
        this.item_price = item_price;
        this.ImageURL = ImageURL;
        this.tokenId = tokenId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getJudul_item() {
        return judul_item;
    }

    public String getTop_number() {
        return Top_number;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setJudul_item(String judul_item) {
        this.judul_item = judul_item;
    }

    public void setTop_number(String top_number) {
        Top_number = top_number;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
}
