package com.example.dadidoapp.Model;

import com.google.gson.annotations.SerializedName;

public class Transaction {

    @SerializedName("id_item")
    private Integer idItem;

    @SerializedName("id_profile_buyer")
    private Integer idProfileBuyer;

    @SerializedName("id_profile_Seller")
    private Integer idProfileSeller;

    @SerializedName("transaction_time")
    private String transactionTime;

    public Transaction(Integer idItem, Integer idProfileBuyer, Integer idProfileSeller, String transactionTime) {
        this.idItem = idItem;
        this.idProfileBuyer = idProfileBuyer;
        this.idProfileSeller = Transaction.this.idProfileSeller;
        this.transactionTime = Transaction.this.transactionTime;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdProfileBuyer() {
        return idProfileBuyer;
    }

    public void setIdProfileBuyer(Integer idProfileBuyer) {
        this.idProfileBuyer = idProfileBuyer;
    }

    public Integer getIdProfileSeller() {
        return idProfileSeller;
    }

    public void setIdProfileSeller(Integer idProfileSeller) {
        this.idProfileSeller = idProfileSeller;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }
}
