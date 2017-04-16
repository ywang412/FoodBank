package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.Room;

/**
 * Created by swengineer on 4/9/17.
 */
public interface RoomDAO {

    public Room getRoom(int id);

    public int   getRoomCountByShelterIdAndOccupancy(int shelterId, boolean occupied);

    public Integer findNextAvailableRoom(int shelterId);

    public Integer claimNextAvailableRoom(int shelterId);
    public Integer releaseRoom(int shelterId);
}
