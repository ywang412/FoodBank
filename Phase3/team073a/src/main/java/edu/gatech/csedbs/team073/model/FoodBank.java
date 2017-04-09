package edu.gatech.csedbs.team073.model;

/**
 * Created by swengineer on 4/9/17.
 */
public class FoodBank {



    private String descriptionString;
    private int foodBankId;

    public FoodBank() {
    }

    public String getDescriptionString() {
        return descriptionString;
    }

    public void setDescriptionString(String descriptionString) {
        this.descriptionString = descriptionString;
    }

    public int getFoodBankId() {
        return foodBankId;
    }

    public void setFoodBankId(int foodBankId) {
        this.foodBankId = foodBankId;
    }
}
