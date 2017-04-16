package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.MealCount;
import edu.gatech.csedbs.team073.model.Site;
import edu.gatech.csedbs.team073.model.Item;

/**
 * Created by Taylor on 4/6/2017.
 */
public interface ItemDAO {

    public MealCount getMealCount();
    public Site getFoodPantry(String username);
    public int addItem(final Item inItem);
}
