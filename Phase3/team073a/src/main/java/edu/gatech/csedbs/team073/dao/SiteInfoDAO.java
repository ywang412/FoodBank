package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.*;

/**
 * Created by swengineer on 4/6/17.
 */
public interface SiteInfoDAO {

    public SiteInfo getSiteInfo(int id);
    public FoodPantry getFoodPantry(int id);
    public FoodBank getFoodBank(int id);
    public SoupKitchen getSoupKitchen(int id);
    public Shelter getShelter(int id);


}
