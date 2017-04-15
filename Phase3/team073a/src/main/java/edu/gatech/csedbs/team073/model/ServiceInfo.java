package edu.gatech.csedbs.team073.model;

/**
 * Created by Phil on 4/14/2017.
 */
public class ServiceInfo {

    private int siteId;
    private int serviceId;

    private boolean shelter;
    private boolean food_pantry;
    private boolean soup_kitchen;

    private String description;




    public ServiceInfo(int siteId) {
        this.siteId = siteId;
        this.serviceId = 0;
        this.shelter = false;
        this.food_pantry = false;
        this.soup_kitchen = false;

    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public boolean isShelter() {
        return shelter;
    }

    public void setShelter(boolean shelter) {
        this.shelter = shelter;
    }

    public boolean isFood_pantry() {
        return food_pantry;
    }

    public void setFood_pantry(boolean food_pantry) {
        this.food_pantry = food_pantry;
    }

    public boolean isSoup_kitchen() {
        return soup_kitchen;
    }

    public void setSoup_kitchen(boolean soup_kitchen) {
        this.soup_kitchen = soup_kitchen;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}



