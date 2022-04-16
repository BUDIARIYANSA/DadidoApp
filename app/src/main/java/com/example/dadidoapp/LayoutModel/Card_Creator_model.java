package com.example.dadidoapp.LayoutModel;

public class Card_Creator_model {
    private String CollectionTitle;
    private String CreatorName;
    private String TotalFollower;
    private String Description;
    private String CreatorDP_URL;
    private String CollectionBannerURL;

    public Card_Creator_model(String coll_title, String creatorName, String totalFoll, String desc, String CreatorDP_URL, String CollectionBannerURL){
        this.CollectionTitle = coll_title;
        this.CreatorName = creatorName;
        this.TotalFollower = totalFoll;
        this.Description = desc;
        this.CreatorDP_URL = CreatorDP_URL;
        this.CollectionBannerURL = CollectionBannerURL;
    }

    public String getCollectionTitle() {
        return CollectionTitle;
    }

    public String getCreatorName() {
        return CreatorName;
    }

    public String getTotalFollower() {
        return TotalFollower;
    }

    public String getDescription() {
        return Description;
    }

    public String getCreatorDP_URL() {
        return CreatorDP_URL;
    }

    public String getCollectionBannerURL() {
        return CollectionBannerURL;
    }

    public void setCollectionTitle(String collectionTitle) {
        CollectionTitle = collectionTitle;
    }

    public void setCreatorName(String creatorName) {
        CreatorName = creatorName;
    }

    public void setTotalFollower(String totalFollower) {
        TotalFollower = totalFollower;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setCreatorDP_URL(String creatorDP_URL) {
        CreatorDP_URL = creatorDP_URL;
    }

    public void setCollectionBannerURL(String collectionBannerURL) {
        CollectionBannerURL = collectionBannerURL;
    }
}
