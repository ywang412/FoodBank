package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.FoodPantry;
import edu.gatech.csedbs.team073.model.Item;

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

    public List GetRequestTable(String user);
    public List GetItemTable();
    public List GetItemTableWith(String username);

    public boolean updateFoodPantry(int id, String description_string, String hours, String conditions_for_use);

    public int addFoodPantry( int siteid, String description_string, String hours, String conditions_for_use);

    public boolean removeFoodPantry(int siteid, int fpid);

}
