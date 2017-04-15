package edu.gatech.csedbs.team073.model;

/**
 * Created by swengineer on 4/9/17.
 */
public class SoupKitchen {

    private String descriptionString;
    private int soupKitchenId;
    private String hours;
    private String conditionsForUse;
    private int availableSeats;
    private int seatLimit;

    public SoupKitchen() {
    }

    public String getDescriptionString() {
        return descriptionString;
    }

    public void setDescriptionString(String descriptionString) {
        this.descriptionString = descriptionString;
    }

    public int getSoupKitchenId() {
        return soupKitchenId;
    }

    public void setSoupKitchenId(int soupKitchenId) {
        this.soupKitchenId = soupKitchenId;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getConditionsForUse() {
        return conditionsForUse;
    }

    public void setConditionsForUse(String conditionsForUse) {
        this.conditionsForUse = conditionsForUse;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getSeatLimit() {
        return seatLimit;
    }

    public void setSeatLimit(int seatLimit) {
        this.seatLimit = seatLimit;
    }
}
