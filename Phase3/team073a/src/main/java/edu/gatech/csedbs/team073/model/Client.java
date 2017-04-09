package edu.gatech.csedbs.team073.model;

/**
 * Created by swengineer on 4/9/17.
 */
public class Client {


    private String descriptionString;
    private boolean headOfHousehold;
    private String fullName;
    private int clientId;

    public Client() {
    }

    public String getDescriptionString() {
        return descriptionString;
    }

    public void setDescriptionString(String descriptionString) {
        this.descriptionString = descriptionString;
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
}
