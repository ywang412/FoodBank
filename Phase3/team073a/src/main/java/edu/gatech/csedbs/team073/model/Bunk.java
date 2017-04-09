package edu.gatech.csedbs.team073.model;

/**
 * Created by swengineer on 4/9/17.
 */
public class Bunk {

    private int bunkType;
    private int bunkNumber;
    private boolean occupied;
    private int shelterId;

    public Bunk() {
    }

    public int getBunkType() {
        return bunkType;
    }

    public void setBunkType(int bunkType) {
        this.bunkType = bunkType;
    }

    public int getBunkNumber() {
        return bunkNumber;
    }

    public void setBunkNumber(int bunkNumber) {
        this.bunkNumber = bunkNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public int getShelterId() {
        return shelterId;
    }

    public void setShelterId(int shelterId) {
        this.shelterId = shelterId;
    }
}
