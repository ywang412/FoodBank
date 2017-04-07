package edu.gatech.csedbs.team073.model;

/**
 * Created by Phil on 4/6/2017.
 */
public class Site {

    private String shortName;
    private String contactNumber;
    private String StreetAddress;
    private String City;
    private String State;
    private int zipcode;
    private int siteId;

    /**
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }
    /**
     * @param shortName the shortName to set
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    /**
     * @return the contactNumber
     */
    public String getContactNumber() {
        return contactNumber;
    }
    /**
     * @param contactNumber the contactNumber to set
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    /**
     * @return the StreetAddress
     */
    public String getStreetAddress() {
        return StreetAddress;
    }
    /**
     * @param streetAddress the streetAddress to set
     */
    public void setStreetAddress(String streetAddress) {
        StreetAddress = streetAddress;
    }
    /**
     * @return the City
     */
    public String getCity() {
        return City;
    }
    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        City = city;
    }
    /**
     * @return the State
     */
    public String getState() {
        return State;
    }
    /**
     * @param state the state to set
     */
    public void setState(String state) {
        State = state;
    }
    /**
     * @return the zipcode
     */
    public int getZipcode() {
        return zipcode;
    }
    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
    /**
     * @return the siteId
     */
    public int getSiteId() {
        return siteId;
    }
    /**
     * @param siteId the siteId to set
     */
    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }
}
