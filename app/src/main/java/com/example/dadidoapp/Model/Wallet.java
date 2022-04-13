package com.example.dadidoapp.Model;

import com.google.gson.annotations.SerializedName;

public class Wallet {
    @SerializedName("total_fund")
    private String totalFund;

    @SerializedName("wallet_type")
    private String walletType;

    @SerializedName("contract_address")
    private String contractAddress;

    public Wallet(String totalFund, String walletType, String contractAddress) {
        this.totalFund = totalFund;
        this.walletType = walletType;
        this.contractAddress = contractAddress;
    }

    public String getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(String totalFund) {
        this.totalFund = totalFund;
    }

    public String getWalletType() {
        return walletType;
    }

    public void setWalletType(String walletType) {
        this.walletType = walletType;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }
}
