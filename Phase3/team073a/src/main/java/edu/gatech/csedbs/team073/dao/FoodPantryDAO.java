package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.FoodPantry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swengineer on 4/9/17.
 */
public interface FoodPantryDAO {

    public FoodPantry getFoodPantry(int id);

    public FoodPantry getFoodPantrybysiteID(int id);

    public int getFoodPantryCount();

    public List GetFoodPantryTable();
}
