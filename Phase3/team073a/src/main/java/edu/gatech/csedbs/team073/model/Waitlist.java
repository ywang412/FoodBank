package edu.gatech.csedbs.team073.model;

/**
 * Created by swengineer on 4/9/17.
 */
public class Waitlist {

    private int clientID;
    private int roomNumber;
    private int shelterID;
    private int position;

    public Waitlist() {
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
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

    @Override
    public String toString() {
        return "Waitlist{" +
                "clientID=" + clientID +
                ", roomNumber=" + roomNumber +
                ", shelterID=" + shelterID +
                ", position=" + position +
                '}';
    }
}
