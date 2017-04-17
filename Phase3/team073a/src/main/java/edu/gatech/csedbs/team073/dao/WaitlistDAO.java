package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.RoomBunkCount;
import edu.gatech.csedbs.team073.model.Waitlist;

import java.util.List;

/**
 * Created by swengineer on 4/9/17.
 */
public interface WaitlistDAO {

    public List<Waitlist> getAllWaitlist(int id);

    public List<Waitlist> getClientWaitlist(int id);

    public boolean removeClientWaitlist(int client_id, int shelter_id);

    public boolean addClientWaitlist(int client_id, int shelter_id);
}
