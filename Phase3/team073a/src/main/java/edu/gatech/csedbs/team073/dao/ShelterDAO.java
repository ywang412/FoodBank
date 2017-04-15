package edu.gatech.csedbs.team073.dao;


import edu.gatech.csedbs.team073.model.Shelter;


import java.util.List;

/**
 * Created by swengineer on 4/9/17.
 */
public interface ShelterDAO {

    public Shelter getShelter(int id);
    public Shelter getShelterbysiteID(int id);


    public int getShelterCount();

    public List GetShelterTable();

    public boolean updateShelter(int id, String description_string, String hours, String conditions_for_use, int available_bunks,int available_rooms);


 }
