package edu.gatech.csedbs.team073.model;

/**
 * Created by jgeorge84 on 4/9/17.
 */
public class Client {


    private String description;
    private boolean headOfHousehold;
    private String fullName;
    private int clientId;
    private String phoneNumber;
    private String hoH;
	
    
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the headOfHousehold
	 */
	public boolean isHeadOfHousehold() {
		return headOfHousehold;
	}
	/**
	 * @param headOfHousehold the headOfHousehold to set
	 */
	public void setHeadOfHousehold(boolean headOfHousehold) {
		this.headOfHousehold = headOfHousehold;
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the clientId
	 */
	public int getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the hoH
	 */
	public String getHoH() {
		return String.valueOf(headOfHousehold);
	}
	/**
	 * @param hoH the hoH to set
	 */
	public void setHoH(String hoH) {
		//this.hoH = hoH;
	}

	
 
}
