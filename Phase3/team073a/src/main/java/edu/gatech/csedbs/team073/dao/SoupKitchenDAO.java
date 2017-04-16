package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.SoupKitchen;

import java.util.List;

/**
 * Created by swengineer on 4/9/17.
 */
public interface SoupKitchenDAO {

    public int getSoupKitchenCount();
    public SoupKitchen getSoupKitchen(int id);
    public SoupKitchen getSoupKitchenbysiteID(int id);

    public List GetSoupKitchenTable();

    public boolean updateSoupKitchen(int id, String description_string, String hours, String conditions_for_use, int available_seats, int seats_limit);

    public int addSoupKitchen( int siteid, String description_string, String hours, String conditions_for_use, int available_seats, int seats_limit);

    public boolean removeSoupKitchen(int siteid, int skid);

    public boolean decrementSoupKitchenSeats(int id);
    public boolean incrementSoupKitchenSeats(int id);
}

