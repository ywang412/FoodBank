package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.MealCount;
import edu.gatech.csedbs.team073.model.Site;
import edu.gatech.csedbs.team073.model.Item;

import java.util.List;

/**
 * Created by Taylor on 4/6/2017.
 */
public interface ItemDAO {

    public MealCount getMealCount();
    public Site getFoodPantry(String username);
    public int addItem(final Item inItem);


    public List<Item> searchItemByName(String name);
     public List<Item> searchItemByItemType(String itype);

     public List<Item> searchItemByStorageType(String sttype);
     public List<Item> searchItemByFoodCategory(String fcat);

     public List<Item> searchItemBySupplyCategory(String scat);

     public List<Item> searchItemByExpired(String exp);

    public List<Item> searchItemByLocationId(int locid);
}
