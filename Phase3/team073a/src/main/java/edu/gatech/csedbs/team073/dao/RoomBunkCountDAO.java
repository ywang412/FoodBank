package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.RoomBunkCount;

/**
 * Created by swengineer on 4/15/17.
 */
public interface RoomBunkCountDAO {

    public RoomBunkCount getAvailableRoom(int id);
    public RoomBunkCount getAvailableBunk_Male(int id);
    public RoomBunkCount getAvailableBunk_Female(int id);
    public RoomBunkCount getAvailableBunk_Mixed (int id);



}

