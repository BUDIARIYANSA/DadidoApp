package com.example.dadidoapp.LayoutModel;

public class Card_Creator_model {
    private String CollectionTitle;
    private String CreatorName;
    private String TotalFollower;
    private String Description;

    public Card_Creator_model(String coll_title, String creatorName, String totalFoll, String desc){
        this.CollectionTitle = coll_title;
        this.CreatorName = creatorName;
        this.TotalFollower = totalFoll;
        this.Description = desc;
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

}
