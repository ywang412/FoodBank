package edu.gatech.csedbs.team073.model;

/**
 * Created by swengineer on 4/9/17.
 */
public class Shelter {

    private String conditionsForUse;
    private String hours;
    private String descriptionString;
    private int availableBunks;
    private int availableRooms;
    private int shelterId;


    public Shelter() {
    }

    public String getConditionsForUse() {
        return conditionsForUse;
    }

    public void setConditionsForUse(String conditionsForUse) {
        this.conditionsForUse = conditionsForUse;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getDescriptionString() {
        return descriptionString;
    }

    public void setDescriptionString(String descriptionString) {
        this.descriptionString = descriptionString;
    }

    public int getAvailableBunks() {
        return availableBunks;
    }

    public void setAvailableBunks(int availableBunks) {
        this.availableBunks = availableBunks;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public int getShelterId() {
        return shelterId;
    }

    public void setShelterId(int shelterId) {
        this.shelterId = shelterId;
    }

    @Override
    public String toString() {
        return "Shelter{" +
                "conditionsForUse='" + conditionsForUse + '\'' +
                ", hours='" + hours + '\'' +
                ", descriptionString='" + descriptionString + '\'' +
                ", availableBunks=" + availableBunks +
                ", availableRooms=" + availableRooms +
                ", shelterId=" + shelterId +
                '}';
    }
}
