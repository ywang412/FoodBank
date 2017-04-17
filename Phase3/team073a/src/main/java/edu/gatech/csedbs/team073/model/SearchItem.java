package edu.gatech.csedbs.team073.model;

/**
 * Created by Phil on 4/17/2017.
 */
public class SearchItem {


    private String searchParms;
    private String searchItemType;
    private String searchStorageType;
    private String searchFoodCategory;
    private String searchSupplyCategory;
    private String searchExpired;
    private String searchLocationId;

    /**
     * @return the searchParms
     */
    public String getSearchParms() {
        return searchParms;
    }

    /**
     * @param searchParms the searchParms to set
     */
    public void setSearchParms(String searchParms) {
        this.searchParms = searchParms;
    }

    public String getSearchItemType() {
        return searchItemType;
    }

    public void setSearchItemType(String searchItemType) {
        this.searchItemType = searchItemType;
    }

    public String getSearchStorageType() {
        return searchStorageType;
    }

    public void setSearchStorageType(String searchStorageType) {
        this.searchStorageType = searchStorageType;
    }

    public String getSearchFoodCategory() {
        return searchFoodCategory;
    }

    public void setSearchFoodCategory(String searchFoodCategory) {
        this.searchFoodCategory = searchFoodCategory;
    }

    public String getSearchSupplyCategory() {
        return searchSupplyCategory;
    }

    public void setSearchSupplyCategory(String searchSupplyCategory) {
        this.searchSupplyCategory = searchSupplyCategory;
    }

    public String getSearchExpired() {
        return searchExpired;
    }

    public void setSearchExpired(String searchExpired) {
        this.searchExpired = searchExpired;
    }

    public String getSearchLocationId() {
        return searchLocationId;
    }

    public void setSearchLocationId(String searchLocationId) {
        this.searchLocationId = searchLocationId;
    }
}
