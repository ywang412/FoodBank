package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.Bunk;

/**
 * Created by swengineer on 4/9/17.
 */
public interface BunkDAO {

     Bunk getBunk(int id);

     int   getBunkCountByShelterIdAndTypeAndOccupancy(int shelterId, int type, boolean occupied);


     Integer claimNextAvailableBunk(int shelterId, int bunkType);
     Integer releaseBunk(int shelterId, int bunkType);
}
