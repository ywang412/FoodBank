package edu.gatech.csedbs.team073.model;

/**
 * Created by swengineer on 4/9/17.
 */
public class RoomBunkCount {

    private int roomBunkType;
    private int roomBunkCount;
    private String address;
    private String siteName;
    private String hours;
    private String contactNumber;
    private int shelterId;
    private int siteId;
    private String roomBunkTypeString;

    public RoomBunkCount() {
    }

    public String getRoomBunkTypeString() {
        return roomBunkTypeString;
    }

    public void setRoomBunkTypeString(String roomBunkTypeString) {
        this.roomBunkTypeString = roomBunkTypeString;
    }

    public int getRoomBunkType() {
        return roomBunkType;
    }

    public void setRoomBunkType(int roomBunkType) {
        this.roomBunkType = roomBunkType;
    }

    public int getRoomBunkCount() {
        return roomBunkCount;
    }

    public void setRoomBunkCount(int roomBunkCount) {
        this.roomBunkCount = roomBunkCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getShelterId() {
        return shelterId;
    }

    public void setShelterId(int shelterId) {
        this.shelterId = shelterId;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }
}
