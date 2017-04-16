package edu.gatech.csedbs.team073.model;

/**
 * Created by swengineer on 4/9/17.
 */
public class Waitlist {

    private String description;
    private boolean headOfHousehold;
    private String fullName;
    private int clientId;

    private int roomNumber;
    private int shelterID;
    private int position;

    public Waitlist() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHeadOfHousehold() {
        return headOfHousehold;
    }

    public void setHeadOfHousehold(boolean headOfHousehold) {
        this.headOfHousehold = headOfHousehold;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }


    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getShelterID() {
        return shelterID;
    }

    public void setShelterID(int shelterID) {
        this.shelterID = shelterID;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


}
