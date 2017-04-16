/**
 * 
 */
package edu.gatech.csedbs.team073.model;

/**
 * @author phebust
 *
 */
public class Item {

	public String itemName;
	public String getItemName(){return itemName;}
	public void setItemName(String s){itemName=s;}
	public int numberOfUnits;
	public int getNumberOfUnits(){return numberOfUnits;}
	public void setNumberOfUnits(Integer i){numberOfUnits=i;}
	public String storageType;
	public String getStorageType(){return storageType;}
	public void setStorageType(String s){storageType=s;}
	public String itemType;
	public String getItemType(){return itemType;}
	public void setItemType(String s){itemType=s;}
	public String foodCategory;
	public String getFoodCategory(){return foodCategory;}
	public void setFoodCategory(String s){foodCategory=s;}
	public String supplyCategory;
	public String getSupplyCategory(){return supplyCategory;}
	public void setSupplyCategory(String s){supplyCategory=s;}
	public String expirationDate;
	public String getExpirationDate(){return expirationDate;}
	public void setExpirationDate(String s){expirationDate=s;}
	public String foodBank;
	public String getFoodBank(){return foodBank;}
	public void setFoodBank(String s){foodBank=s;}

	public Item() {
	}


}
