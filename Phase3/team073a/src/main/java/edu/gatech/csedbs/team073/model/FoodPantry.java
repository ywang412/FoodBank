package edu.gatech.csedbs.team073.model;

/**
 * Created by swengineer on 4/9/17.
 */
public class FoodPantry {


    private String descriptionString;
    private int foodPantryId;
    private String hours;
    private String conditionsForUse;


    public FoodPantry() {
    }

    public String getDescriptionString() {
        return descriptionString;
    }

    public void setDescriptionString(String descriptionString) {
        this.descriptionString = descriptionString;
    }

    public int getFoodPantryId() {
        return foodPantryId;
    }

    public void setFoodPantryId(int foodPantryId) {
        this.foodPantryId = foodPantryId;
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
}
