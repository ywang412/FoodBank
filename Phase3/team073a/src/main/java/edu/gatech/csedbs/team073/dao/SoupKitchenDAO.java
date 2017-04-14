package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.SoupKitchen;

import java.util.List;

/**
 * Created by swengineer on 4/9/17.
 */
public interface SoupKitchenDAO {

    public int getSoupKitchenCount();
    public SoupKitchen getSoupKitchen(int id);

    public List GetSoupKitchenTable();

}

