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
    private boolean food_bank;

    private String description;

    private int room_number; // if this is 0 then client got a bunk otherwise client needs to be assigned to a room number
    private boolean release_room;
    private boolean release_bunk;

    private boolean add_to_waitlist;

    public ServiceInfo(int siteId) {
        this.siteId = siteId;
        this.serviceId = 0;
        this.shelter = false;
        this.food_pantry = false;
        this.soup_kitchen = false;
        this.room_number = 0;
        this.release_room = false;
        this.release_bunk = false;
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

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public boolean isRelease_room() {
        return release_room;
    }

    public void setRelease_room(boolean release_room) {
        this.release_room = release_room;
    }

    public boolean isRelease_bunk() {
        return release_bunk;
    }

    public void setRelease_bunk(boolean release_bunk) {
        this.release_bunk = release_bunk;
    }

    public boolean isFood_bank() {
        return food_bank;
    }

    public void setFood_bank(boolean food_bank) {
        this.food_bank = food_bank;
    }

    public boolean isAdd_to_waitlist() {
        return add_to_waitlist;
    }

    public void setAdd_to_waitlist(boolean add_to_waitlist) {
        this.add_to_waitlist = add_to_waitlist;
    }
}



