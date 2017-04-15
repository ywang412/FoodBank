/**
 * 
 */
package edu.gatech.csedbs.team073.model;

/**
 * @author phebust
 *
 */
public class Request {

	public String username;
	public String getUserName(){ return username;}
	public String item_name;
	public String getItemName(){ return item_name;}
	public int units_requested;
	public int getUnitsRequested(){ return units_requested;}
	public int units_fulfilled;
	public int getUnitsFulfilled(){ return units_fulfilled;}
	public String request_date;
	public String getRequestDate(){ return request_date;}
	public String request_status;
	public String getRequestStatus(){ return request_status;}

	public Request() {
	}


}
