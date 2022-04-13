package com.example.dadidoapp.LayoutModel;

public class Card_Item_Model {

    private String nama_item;
    private String token_id;
    private String total_price;
    private String total_like;

    public Card_Item_Model(String nama_item, String token_id, String total_price, String total_like){
        this.nama_item = nama_item;
        this.token_id = token_id;
        this.total_price = total_price;
        this.total_like = total_like;
    }

    public String getNama_item() {
        return nama_item;
    }

    public String getToken_id() {
        return token_id;
    }

    public String getTotal_price() {
        return total_price;
    }

    public String getTotal_like() {
        return total_like;
    }

    public void setNama_item(String nama_item) {
        this.nama_item = nama_item;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public void setTotal_like(String total_like) {
        this.total_like = total_like;
    }
}
